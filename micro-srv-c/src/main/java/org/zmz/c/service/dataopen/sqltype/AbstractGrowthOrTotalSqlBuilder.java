package org.zmz.c.service.dataopen.sqltype;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetConditionQo;
import org.zmz.c.qo.dataopen.MetricsDimensionPathVo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.qo.dataopen.OrgDimension;
import org.zmz.c.qo.dataopen.SubQuerySqlQo;
import org.zmz.c.service.dataopen.dataset.SqlComponent;
import org.zmz.c.service.dataopen.dataset.SqlFuncEnum;
import org.zmz.c.service.dataopen.sqlfunc.PeriodExpression;
import org.zmz.c.utils.AcctTimeUtil;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;
import org.zmz.c.vo.dataopen.dataset.ResultSql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AbstractGrowthOrTotalSqlBuilder extends AbstractSqlBuilderBase {

    /**
     * 检查是否有同环比、月/年累计
     *
     * @param metrics 度量
     * @return true/false
     */
    @Override
    protected List<DatasetColumnQo> checkGrowthOrTotal(List<DatasetColumnQo> metrics, String dimensionType) {
        List<DatasetColumnQo> growthOrTotalMetric = new ArrayList<>();
        for (DatasetColumnQo metric : metrics) {
            if (StringUtils.isBlank(metric.getFunc()) || !dimensionType.equalsIgnoreCase(metric.getDimensionType())) {
                continue;
            }
            // 同比、环比
            if (SqlBuilderHelper.isGrowthOrTotal(metric.getFunc())) {
                growthOrTotalMetric.add(metric);
            }
        }
        return growthOrTotalMetric;
    }

    /**
     * 同/环比或者月/年累计的子查询
     */
    protected void subSqlGrowthOrTotal(List<SubQuerySqlQo> subSqlList,
                                       List<DatasetColumnQo> metricList,
                                       String dimensionType,
                                       List<DatasetColumnQo> dimensionList,
                                       List<DatasetConditionQo> condList,
                                       boolean needAppendPeriod,
                                       OrgDimension replaceLevelColumn,
                                       String scheduleType) {
        SqlComponent component = new SqlComponent();
        Map<String, List<MetricsDimensionPathVo>> pathsMap = metricList.get(0).getPathsMap();
        String dataPrivPathKey = metricList.get(0).getDataPrivPathKey();
        Map<String, Map<String, String>> alias = joinTables(pathsMap, dataPrivPathKey, needAppendPeriod, component);
        // 度量函数
        SqlFuncEnum funcEnum = SqlFuncEnum.getFuncByName(metricList.get(0).getFunc());

        // 判断是否需要关联时间维表
        boolean joinTimeSql = false;
        DatasetColumnQo acctDimension = dimensionList.stream().filter(d -> Constants.YES_VALUE_1.equals(d.getIsAcct()))
                .findFirst().orElse(null);
        if (Constants.SCHEDULE_LOOP_TYPE_O.equalsIgnoreCase(scheduleType) && acctDimension != null) {
            Column periodColumn = getPeriodColumnFromMetric(metricList.get(0), acctDimension);
            if (null != periodColumn) {
                // 一次性有账期维度的年月累计需要关联时间维表
                joinTimeSql = SqlBuilderHelper.isTotal(funcEnum);
                // 全局度量条件
                DatasetConditionQo acctCond = CollectionUtils.isEmpty(condList) ? null
                        : condList.stream().filter(obj -> KeyValues.YES_VALUE_1.equals(obj.getIsAcct())).findFirst()
                        .orElse(null);
                PeriodExpression condPeriodExp = new PeriodExpression();
                if (acctCond != null) {
                    condPeriodExp.setCycleType(acctCond.getCycleType());
                    condPeriodExp.setOperator(acctCond.getCondOperator());
                    condPeriodExp.setIsDynamic(acctCond.getIsDynamic());
                    String[] split = acctCond.getCondValue().contains("~") ? acctCond.getCondValue().split("~")
                            : acctCond.getCondValue().split(",");
                    condPeriodExp.setPeriodScope(new LinkedList<>(Arrays.asList(split)));
                } else {
                    condPeriodExp = this.getPeriodExpressionByFunc(acctDimension.getCycleType(), funcEnum);
                }
                if (joinTimeSql) {
                    String tbName = SqlBuilderHelper.getAliasName(alias, periodColumn.getMetaDataId());
                    String columnExp = tbName + SqlUtils.STR_POINT + periodColumn.getColumnCode();
                    component.join.append(SqlUtils.SQL_INNER_JOIN).append("(").append(getTimeSubSql(condPeriodExp))
                            .append(") tm ").append(totalJoinOnSql(columnExp, funcEnum));
                }
            }
        }

        this.appendOutField(false, metricList, dimensionType, dimensionList, alias, component.field,
                replaceLevelColumn, funcEnum, joinTimeSql);

        if (SqlBuilderHelper.isGrowthOrTotal(funcEnum)) {
            // 同比、环比
            this.appendWhere(false, component.where, metricList, dimensionType, condList, alias, null, null,
                    funcEnum);
        }

        this.appendGroupBy(metricList, dimensionList, component.group, alias, replaceLevelColumn, joinTimeSql);
        SubQuerySqlQo relativeDimension = new SubQuerySqlQo();
        relativeDimension.setDimensionList(dimensionList);
        relativeDimension.setSql(component.swapSql().toString());
        relativeDimension.setMetricList(metricList);
        relativeDimension.setDimensionType(dimensionType);
        subSqlList.add(relativeDimension);
    }

    @Override
    protected void subScheduleGrowthOrTotal(List<SubQuerySqlQo> subSqlList,
                                            boolean singleSql,
                                            List<DatasetColumnQo> metricList,
                                            String dimensionType,
                                            List<DatasetColumnQo> dimensionList,
                                            List<DatasetConditionQo> condList,
                                            ResultSql result,
                                            Map<String, List<MetricsDimensionPathVo>> cacheTempPath,
                                            OrgDimension replaceLevelColumn) {
        Map<String, Map<String, String>> mainTbPathAlias = new HashMap<>();
        Map<String, String> tempTbPathAlias = new HashMap<>();

        Map<String, Map<String, List<MetricsDimensionPathVo>>> tempTablesMap = this.params.getTempTablesMap();
        Map<String, Map<String, MetricsDimensionPathVo>> mainTablesToTempTablesMap = this.params
                .getMainTablesToTempTablesMap();
        Map<String, Map<String, List<MetricsDimensionPathVo>>> mainTablesMap = this.params.getMainTablesMap();
        boolean hasOrgTable = SqlBuilderHelper.hasOrgTable(getDataPrivCtrlInfo(), metricList.get(0).getPathsMap());
        boolean mainSlaveTabPeriod = needSlaveTablePeriod(metricList, dimensionList);

        SqlComponent component = new SqlComponent();
        String tmpTbName = subJoinTables(component, mainTbPathAlias, tempTbPathAlias, hasOrgTable, result,
                metricList.get(0), cacheTempPath, tempTablesMap, mainTablesToTempTablesMap, mainTablesMap,
                mainSlaveTabPeriod);
        Map<String, List<MetricsDimensionPathVo>> mainTbPaths = mainTablesMap.get(metricList.get(0).getPath());

        SqlFuncEnum funcEnum = SqlFuncEnum.getFuncByName(metricList.get(0).getFunc());

        StringBuilder fields = mergeField(singleSql, metricList, dimensionType, dimensionList, mainTbPathAlias,
                tempTbPathAlias, hasOrgTable, tmpTbName, replaceLevelColumn, funcEnum);
        component.field.append(fields);

        if (SqlBuilderHelper.isGrowth(funcEnum)) {
            // 同比、环比
            this.appendWhere(singleSql, component.where, metricList, dimensionType, condList, mainTbPathAlias,
                    mainTbPaths, tempTbPathAlias, funcEnum);
        } else if (SqlBuilderHelper.isTotal(funcEnum)) {
            // 月/年累计 需要关联时间维表
            this.appendWhere(singleSql, component.where, metricList, dimensionType, condList, mainTbPathAlias,
                    mainTbPaths, tempTbPathAlias, funcEnum);
        }
        this.mergeGroupBy(metricList, dimensionList, component.group, mainTbPathAlias, tempTbPathAlias, hasOrgTable,
                tmpTbName, replaceLevelColumn);

        SubQuerySqlQo relativeDimension = new SubQuerySqlQo();
        relativeDimension.setDimensionList(dimensionList);
        relativeDimension.setSql(component.swapSql().toString());
        relativeDimension.setMetricList(metricList);
        relativeDimension.setDimensionType(dimensionType);
        subSqlList.add(relativeDimension);
    }

    @Override
    protected String getDefultPeriod(Long tableId, Map<String, String> tableAlias) {
        if (ObjectUtils.isEmpty(tableId)) {
            return null;
        }
        StringBuilder period = new StringBuilder();
        Column column = this.allPeriod.get(tableId);
        if (!ObjectUtils.isEmpty(column)) {
            judgeDefultPeriod(tableAlias.get(String.valueOf(tableId)), column, period);
        }
        return period.toString();
    }

    private void judgeDefultPeriod(String tbName, Column column, StringBuilder metricPeriod) {
        boolean isStr = SqlBuilderHelper.isStringType(getDbType(), column.getColumnType());
        String acct;
        // 账期偏移,todo 偏移支持小时/分钟
        if (this.params.isAcctOffset()) {
            // 上个账期
            String acct_1;
            if ("O".equalsIgnoreCase(this.scheduleType)) {
                if (KeyValues.ACCT_TMIE_M.equals(column.getCycleType())) {
                    // 一次性任务调度
                    acct = "'$add_month(${month_id},0,-1)'";
                    acct_1 = "'$add_month(${month_id},0,-2)'";
                } else {
                    acct = "'$add_day(${day_id},0,0,-1)'";
                    acct_1 = "'$add_day(${day_id},0,0,-2)'";
                }
            } else if (KeyValues.ACCT_TMIE_M.equals(column.getCycleType())) {
                // 月账任务调度
                acct = "'${month_id}'";
                acct_1 = "'$add_month(${month_id},0,-1)'";
            } else {
                acct = "'${day_id}'";
                acct_1 = "'$add_day(${day_id},0,0,-1)'";
            }

            metricPeriod.append(tbName).append(SqlUtils.STR_POINT).append(column.getColumnCode())
                    .append(SqlUtils.STR_IN).append(SqlUtils.STR_LEFT_BRACKET)
                    .append(SqlBuilderHelper.replaceString(acct_1, isStr)).append(SqlUtils.STR_DOT)
                    .append(SqlBuilderHelper.replaceString(acct, isStr)).append(SqlUtils.STR_RIGHT_BRACKET);
        } else if (KeyValues.ACCT_TMIE_D.equalsIgnoreCase(column.getCycleType())) {
            // 日账期
            acct = AcctTimeUtil.getAcctValAddQuotOutPubMode(this.scheduleType, column.getCycleType(),
                    column.getColumnType(), this.outPutMode);
            metricPeriod.append(tbName).append(SqlUtils.STR_POINT).append(column.getColumnCode())
                    .append(SqlUtils.STR_EQUAL).append(acct);
        } else if (!checkMixed() && KeyValues.ACCT_TMIE_M.equalsIgnoreCase(column.getCycleType())) {
            // 非日月账期混用、月账期不减1
            acct = AcctTimeUtil.getAcctValAddQuotOutPubMode(this.scheduleType, column.getCycleType(),
                    column.getColumnType(), this.outPutMode);
            metricPeriod.append(tbName).append(SqlUtils.STR_POINT).append(column.getColumnCode())
                    .append(SqlUtils.STR_EQUAL).append(acct);
        } else if (checkMixed() && KeyValues.ACCT_TMIE_M.equalsIgnoreCase(column.getCycleType())) {
            // 日月账期混用、全局条件没有配置月账期则减1，否则为配置的月账期
            acct = "'$add_month(${month_id},0,-1)'";
            metricPeriod.append(tbName).append(SqlUtils.STR_POINT).append(column.getColumnCode())
                    .append(SqlUtils.STR_EQUAL).append(SqlBuilderHelper.replaceString(acct, isStr));
        } else {
            // 其他 acct = "'${acct}'";
            acct = AcctTimeUtil.getAcctValAddQuotOutPubMode(this.scheduleType, column.getCycleType(),
                    column.getColumnType(), this.outPutMode);
            metricPeriod.append(tbName).append(SqlUtils.STR_POINT).append(column.getColumnCode())
                    .append(SqlUtils.STR_EQUAL).append(SqlBuilderHelper.replaceString(acct, isStr));
        }
    }

    protected abstract Map<String, Map<String, String>> joinTables(Map<String, List<MetricsDimensionPathVo>> pathsMap,
                                                                   String dataPrivPathKey,
                                                                   boolean needAppendPeriod,
                                                                   SqlComponent component);

    protected abstract void appendOutField(boolean singleSql,
                                           List<DatasetColumnQo> metrics,
                                           String dimensionType,
                                           List<DatasetColumnQo> dimensionList,
                                           Map<String, Map<String, String>> aliasMap,
                                           StringBuilder fieldSql,
                                           OrgDimension replaceLevelColumn,
                                           SqlFuncEnum funcEnum,
                                           boolean joinTimeSql);

    protected abstract void appendGroupBy(List<DatasetColumnQo> metrics,
                                          List<DatasetColumnQo> dimensionList,
                                          StringBuilder groupSql,
                                          Map<String, Map<String, String>> aliasMap,
                                          OrgDimension replaceLevelColumn,
                                          boolean joinTimeSql);

    protected PeriodExpression getMetricPeriodExpression(Column column, SqlFuncEnum funcEnum) {
        return getPeriodExpressionByFunc(column.getCycleType(), funcEnum);
    }

    protected PeriodExpression getPeriodExpressionByFunc(String cycleType, SqlFuncEnum funcEnum) {
        PeriodExpression exp = new PeriodExpression();
        exp.setCycleType(cycleType);
        List<String> periodList = new ArrayList<>();
        exp.setPeriodScope(periodList);
        if (SqlFuncEnum.monthTotal.equals(funcEnum)) {
            this.getPeriodMonthTotal(cycleType, exp);
        } else if (SqlFuncEnum.yearTotal.equals(funcEnum)) {
            this.getPeriodYearTotal(cycleType, exp);
        } else {
            String period = AcctTimeUtil.getAcctValOutPubMode(scheduleType, cycleType, outPutMode);
            // 默认账期为动态
            exp.setIsDynamic(Constants.YES_VALUE_1);
            periodList.add(period);
            exp.setOperator("=");
        }

        return exp;
    }

    private void getPeriodMonthTotal(String cycleType, PeriodExpression exp) {
        List<String> periodList = exp.getPeriodScope();
        String currStar;
        String currEnd;
        if ("D".equalsIgnoreCase(cycleType)) {
            if (StringUtils.equals("M", scheduleType)) {
                // 日账期按月调度
                currStar = "$add_day(${month_id},0,F)";
                currEnd = "$add_day(${month_id},0,L)";
            } else if (StringUtils.equals("O", scheduleType)) {
                // 日账期-一次性
                // todo 一次性 昨天所属月份的第一天，比如9月1号，要取8月1号
                currStar = "${month_id}01";
                currEnd = "$add_day(${day_id},-1)";
            } else {
                // 日账期-周期性按日调度
                currStar = "${month_id}01";
                currEnd = "${day_id}";
            }
            periodList.add(currStar);
            periodList.add(currEnd);
            exp.setOperator("BETWEEN");
        } else {
            currEnd = StringUtils.equals("O", scheduleType) ? "$add_month(${month_id},-1)" : "${month_id}";
            exp.setOperator("=");
            periodList.add(currEnd);
        }
    }

    private void getPeriodYearTotal(String cycleType, PeriodExpression exp) {
        List<String> periodList = exp.getPeriodScope();
        String currStar;
        String currEnd;
        if ("D".equalsIgnoreCase(cycleType)) {
            if (StringUtils.equals("M", scheduleType)) {
                // 日账期按月调度
                currStar = "$add_day(${year_id},F)";
                currEnd = "$add_day(${month_id},L)";
            } else if (StringUtils.equals("O", scheduleType)) {
                // 日账期一次性
                // todo 一次性 昨天所属年份的第一天，比如20230101，要取20220101
                currStar = "$add_day(${year_id},F)";
                currEnd = "$add_day(${day_id},-1)";
            } else {
                // 日账期按日调度
                currStar = "$add_day(${year_id},F)";
                currEnd = "${day_id}";
            }
        } else {
            // todo 一次性 上月所属年份的第一月，比如202301，要取202201
            currStar = "${year_id}01";
            currEnd = StringUtils.equals("O", scheduleType) ? "$add_month(${month_id},-1)" : "${month_id}";
        }
        exp.setOperator("BETWEEN");
        periodList.add(currStar);
        periodList.add(currEnd);
    }

    protected StringBuilder getTimeSubSql(PeriodExpression periodExpression) {
        StringBuilder timeSql = new StringBuilder();
        // 时间维表
        ModelInfo timeModel = dataCommonService.getTimeModel(this.getDbType());
        if (timeModel != null) {
            timeSql.append("SELECT * FROM ").append(timeModel.getMetaDataInfo().getSchemaCode()).append(".")
                    .append(timeModel.getMetaDataInfo().getMetaDataCode());
        } else {
            log.error("{} 类型数据库没有配置时间维表", this.getDbType());
        }
        return timeSql;
    }

    protected StringBuilder totalJoinOnSql(String acctColExp, SqlFuncEnum funcEnum) {
        StringBuilder onSql = new StringBuilder();
        // 年累计
        if (funcEnum.equals(SqlFuncEnum.yearTotal)) {
            onSql.append("ON ").append(acctColExp).append(" <= tm.acct AND SUBSTR(CAST(").append(acctColExp)
                    .append(" AS varchar), 1, 4) = SUBSTR(CAST(tm.acct AS varchar), 1, 4)");
        }
        // 月累计
        if (funcEnum.equals(SqlFuncEnum.monthTotal)) {
            onSql.append("ON ").append(acctColExp).append(" <= tm.acct AND SUBSTR(CAST(").append(acctColExp)
                    .append(" AS varchar), 1, 6) = SUBSTR(CAST(tm.acct AS varchar), 1, 6)");
        }
        return onSql;
    }

    public String getAcctColumnExp(String columnExp, SqlFuncEnum funcEnum, Column periodColumn) {
        // 为保证union数据类型一致，账期数据类型转换为数据集账期类型整型
        String intType = Constants.DATA_TYPE_INT.get(this.getDbType());
        if (AcctTimeUtil.isCycle(this.scheduleType)) {
            // 周期性的在条件值上处理
            return this.castColumnType(periodColumn, columnExp, intType);
        }
        // 一次性的在条件字段上处理
        // 如果是同比，环比，账期字段需要特殊处理
        if (SqlFuncEnum.yoyGrowth.equals(funcEnum) || SqlFuncEnum.yoy.equals(funcEnum)) {
            // 加年
            return this.castColumnType(getDateOffset(periodColumn.getCycleType(), columnExp, -1, "year"), intType);
        } else if (SqlFuncEnum.mmGrowth.equals(funcEnum) || SqlFuncEnum.mm.equals(funcEnum)) {
            // 加月
            return this.castColumnType(getDateOffset(periodColumn.getCycleType(), columnExp, -1, "month"), intType);
        } else if (SqlFuncEnum.momGrowth.equals(funcEnum) || SqlFuncEnum.pp.equals(funcEnum)) {
            String unit = KeyValues.ACCT_TMIE_D.equalsIgnoreCase(periodColumn.getCycleType()) ? "day" : "month";
            // 加日
            return this.castColumnType(getDateOffset(periodColumn.getCycleType(), columnExp, -1, unit), intType);
        }
        // 比上年末 日：$add_day(${day_id},-1,0,L) 月：$add_month(${month_id},-1,L) 不支持
        else {
            return this.castColumnType(periodColumn, columnExp, intType);
        }
    }
}
