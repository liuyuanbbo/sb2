package org.zmz.c.service.dataopen.sqlfunc;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetConditionQo;
import org.zmz.c.qo.dataopen.OutPutMode;
import org.zmz.c.service.dataopen.sql.AbstractSqlParser;
import org.zmz.c.service.dataopen.sql.SqlParserFactory;
import org.zmz.c.service.dataopen.sqltype.AbstractSqlBuilderBase;
import org.zmz.c.service.dataopen.sqltype.SqlBuilderHelper;
import org.zmz.c.utils.AcctTimeUtil;
import org.zmz.c.utils.BuildSqlUtil;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlUtils;
import org.zmz.c.vo.dataopen.dataset.CycleInfo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractFuncParser {

    protected AbstractSqlBuilderBase builder;

    protected String dbType;

    protected DatasetColumnQo currMetric;

    protected List<DatasetColumnQo> metrics;

    protected Map<String, Map<String, String>> aliasMetric;

    protected Map<String, String> temTableAlias;

    public void initParams(AbstractSqlBuilderBase builder, String dbType, DatasetColumnQo currMetric,
                           List<DatasetColumnQo> metrics, Map<String, Map<String, String>> aliasMetric,
                           Map<String, String> temTableAlias) {
        this.builder = builder;
        this.dbType = dbType;
        this.currMetric = currMetric;
        this.metrics = metrics;
        this.aliasMetric = aliasMetric;
        this.temTableAlias = temTableAlias;
    }

    protected StringBuilder absOutField() {
        StringBuilder sb = new StringBuilder();

        String srcColumnType = currMetric.getSrcColumnType();
        String columnType = currMetric.getColumnType();
        boolean equalSrc = columnType.equalsIgnoreCase(srcColumnType);

        if (this.isCkFamily(this.dbType) && "String".equalsIgnoreCase(srcColumnType)
                && StringUtils.containsIgnoreCase(columnType, "Decimal")) {
            sb.append(SqlUtils.STR_TO_DECIMAL128_OR_ZERO).append(SqlUtils.STR_LEFT_BRACKET);
            sb.append(getAlias(currMetric)).append(SqlUtils.STR_POINT).append(currMetric.getColumnCode());
            sb.append(SqlUtils.STR_RIGHT_BRACKET);
        } else if (this.isCkFamily(this.dbType) && "String".equalsIgnoreCase(srcColumnType)
                && StringUtils.containsIgnoreCase(columnType, "Int")) {
            sb.append(SqlUtils.STR_TO_INT64_OR_ZERO).append(SqlUtils.STR_LEFT_BRACKET);
            sb.append(getAlias(currMetric)).append(SqlUtils.STR_POINT).append(currMetric.getColumnCode());
            sb.append(SqlUtils.STR_RIGHT_BRACKET);
        } else if (this.isCkFamily(this.dbType) && "String".equalsIgnoreCase(srcColumnType)
                && StringUtils.containsIgnoreCase(columnType, "Float")) {
            sb.append(SqlUtils.STR_TO_FLOAT32_OR_ZERO).append(SqlUtils.STR_LEFT_BRACKET);
            sb.append(getAlias(currMetric)).append(SqlUtils.STR_POINT).append(currMetric.getColumnCode());
            sb.append(SqlUtils.STR_RIGHT_BRACKET);
        } else if (this.isCkFamily(this.dbType) && !"String".equalsIgnoreCase(srcColumnType)
                && "String".equalsIgnoreCase(columnType)) {
            // 来源不是字符串类型，转成字符串类型
            sb.append(SqlUtils.STR_FUNC_TO_STRING).append(SqlUtils.STR_LEFT_BRACKET);
            sb.append(getAlias(currMetric)).append(SqlUtils.STR_POINT).append(currMetric.getColumnCode());
            sb.append(SqlUtils.STR_RIGHT_BRACKET);
        } else if (!equalSrc) {
            sb.append("CAST(").append(getAlias(currMetric)).append(SqlUtils.STR_POINT);
            sb.append(currMetric.getColumnCode()).append(" AS ").append(currMetric.getColumnType()).append(")");
        } else {
            sb.append(getAlias(currMetric)).append(SqlUtils.STR_POINT).append(currMetric.getColumnCode());
        }
        return sb;
    }

    /**
     * 是不是ck家族类型表
     *
     * @param dbType 数据库类型
     * @return isCkFamily
     */
    private boolean isCkFamily(String dbType) {
        return KeyValues.DS_CLICKHOUSE.equalsIgnoreCase(dbType) || KeyValues.DS_WHALEHOUSE.equalsIgnoreCase(dbType);
    }

    protected String getAlias(DatasetColumnQo column) {
        return SqlBuilderHelper.getAliasFromTempTableAndMetric(column, column, metrics.get(0).getPathsMap(),
                aliasMetric, temTableAlias);
    }

    protected String getAliasByCond(DatasetColumnQo metric, DatasetConditionQo column) {
        if (null == column.getTableId()) {
            return null;
        }
        if (metric.getTableId().equals(column.getTableId())) {
            return SqlBuilderHelper.getAliasFromTempTableAndMetric(metric, metric, metrics.get(0).getPathsMap(),
                    aliasMetric, temTableAlias);
        }
        DatasetColumnQo newDatasetColumnQo = new DatasetColumnQo();
        BeanUtils.copyProperties(column, newDatasetColumnQo, "id", "parent", "root");
        return SqlBuilderHelper.getAliasFromTempTableAndMetric(metric, newDatasetColumnQo, metrics.get(0).getPathsMap(),
                aliasMetric, temTableAlias);
    }

    /**
     * 获取条件表达式
     *
     * @return 返回度量上的条件拼接
     */
    protected String condListParser() {
        StringBuilder whereBuilder = new StringBuilder();
        if (CollUtil.isNotEmpty(currMetric.getCondList())) {
            AbstractSqlParser abstractSqlParser = SqlParserFactory.getViewSqlParser(this.dbType);
            String isSql = OutPutMode.SQL.equalsIgnoreCase(this.builder.outPutMode) ? Constants.YES : Constants.NO;
            for (DatasetConditionQo conditionQo : currMetric.getCondList()) {
                String condOperator = StringUtils.isNotBlank(conditionQo.getCondOperator())
                        && conditionQo.getCondOperator().contains("_dynamic")
                        ? conditionQo.getCondOperator().replace("_dynamic", "")
                        : conditionQo.getCondOperator();
                String condValue = conditionQo.getCondValue();
                String condType = conditionQo.getCondType();
                String colCode = null;
                if (null != conditionQo.getTableId() && StringUtils.equals("simpleCond", conditionQo.getCondType())) {
                    colCode = getAliasByCond(currMetric, conditionQo) + SqlUtils.STR_POINT
                            + conditionQo.getColumnCode();
                }

                // 连接符，括号，算术运算符
                if ("connectOpt".equals(condType) || "bracket".equals(condType) || "arithmeticOpt".equals(condType)) {
                    whereBuilder.append(" ").append(condValue).append(" ");
                    // 算术运算项目
                } else if ("arithmeticCondItem".equals(condType)) {
                    whereBuilder.append(colCode).append(" ");
                    // 算术运算条件
                } else if (StrUtil.isNotEmpty(condOperator)) {
                    if ("simpleCond".equals(condType) && StringUtils.isNotEmpty(colCode)
                            && !"SQL".equalsIgnoreCase(condOperator)) {
                        whereBuilder.append(colCode);
                    }
                    // 拼接条件 arithmeticCond
                    if (KeyValues.YES_VALUE_1.equals(conditionQo.getIsAcct())) {
                        String[] periodArray = condValue.contains("~") ? condValue.split("~") : condValue.split(",");
                        String[] currentPeriod = new String[periodArray.length + 1];
                        currentPeriod[0] = condOperator;
                        System.arraycopy(periodArray, 0, currentPeriod, 1, currentPeriod.length - 1);
                        // 账期字段
                        Column periodColumn = this.builder.allPeriod.get(currMetric.getTableId());
                        // 术语的账期
                        if (StringUtils.isEmpty(colCode)) {
                            whereBuilder.append(getAlias(currMetric)).append(SqlUtils.STR_POINT)
                                    .append(periodColumn.getColumnCode());
                        }

                        boolean isStr = SqlBuilderHelper.isStringType(dbType, periodColumn.getColumnType());
                        BuildSqlUtil.appendSimpleCondOfPeriod(whereBuilder, currentPeriod, isStr);
                    } else {
                        // 为sql时，静态有账期格式的数据不需要格式化dateFormat
                        BuildSqlUtil.appendSimpleCond(whereBuilder, conditionQo.getCondOperator(),
                                conditionQo.getCondValue(), conditionQo.getColumnType(), conditionQo.getCycleType(),
                                conditionQo.getDateFormat(), isSql, abstractSqlParser);
                    }
                }
            }
        }
        return whereBuilder.toString();
    }

    protected String getDefaultValue() {
        String defaultValue = "0";
        List<String> countFuncs = Arrays.asList(SqlUtils.STR_FUNC_COUNT, SqlUtils.STR_FUNC_COUNT_DISTINCT,
                SqlUtils.STR_FUNC_LOGICCOUNT);
        if (countFuncs.contains(currMetric.getFunc().toUpperCase())) {
            defaultValue = "NULL";
        } else if ((Constants.CALCULATE_TYPE_MAX.equalsIgnoreCase(currMetric.getFunc())
                || Constants.CALCULATE_TYPE_MIN.equalsIgnoreCase(currMetric.getFunc()))
                && !KeyValues.DATA_TYPE_INT_COLLECTIONS.get(dbType).contains(currMetric.getColumnType().toUpperCase())) {
            defaultValue = "NULL";
        }
        return defaultValue;
    }

    /**
     * 函数拼接
     *
     * @param sql
     */
    protected void appendFunc(StringBuilder sql) {
        String currMetricPeriod = currMetricDefaultPeriod();
        if (builder.getDbType().equals(KeyValues.DS_GP) || builder.getDbType().equals(KeyValues.DS_POSTGRESQL)
                && currMetric.getSrcColumnType().equalsIgnoreCase(KeyValues.GP_DATA_TYPE_VARCHAR)
                && (Constants.CALCULATE_TYPE_SUM.equalsIgnoreCase(currMetric.getFunc())
                || Constants.CALCULATE_TYPE_AVG.equalsIgnoreCase(currMetric.getFunc()))) {

            sql.append(currMetric.getFunc());
            sql.append(SqlUtils.STR_LEFT_BRACKET.trim());
            if (StringUtils.isNotBlank(currMetricPeriod)) {
                sql.append("CASE WHEN ").append(currMetricPeriod).append(" THEN ").append(this.absOutField())
                        .append(" ELSE ").append("0").append(" END");
            } else {
                sql.append(this.absOutField());
            }
            sql.append(SqlUtils.STR_RIGHT_BRACKET.trim());
        } else {
            sql.append(currMetric.getFunc()).append(SqlUtils.STR_LEFT_BRACKET.trim());
            if (StringUtils.isNotBlank(currMetricPeriod)) {
                sql.append("CASE WHEN ").append(currMetricPeriod).append(" THEN ").append(this.absOutField())
                        .append(" ELSE ").append(getDefaultValue()).append(" END");
            } else {
                sql.append(this.absOutField());
            }
            sql.append(SqlUtils.STR_RIGHT_BRACKET.trim());
        }
    }

    /**
     * 检查同一分组度量下是否有账期条件
     *
     * @return true/false
     */
    public boolean checkOtherMetricsHasPeriod() {
        for (DatasetColumnQo columnQo : metrics) {
            if (!currMetric.getDimensionType().equalsIgnoreCase(columnQo.getDimensionType())) {
                continue;
            }
            List<DatasetConditionQo> metricCond = columnQo.getCondList();
            if (!CollectionUtils.isEmpty(metricCond)) {
                List<DatasetConditionQo> collect = metricCond.stream()
                        .filter(obj -> KeyValues.YES_VALUE_1.equals(obj.getIsAcct())).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(collect)) {
                    return true;
                }
            }
            if (SqlBuilderHelper.isTotal(columnQo.getFunc())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取当前度量的默认账期条件
     */
    public String currMetricDefaultPeriod() {
        // 全局条件的账期
        List<DatasetConditionQo> condList = this.builder.params.getCondList();
        List<DatasetConditionQo> acct = CollectionUtils.isEmpty(condList) ? Collections.emptyList()
                : condList.stream().filter(obj -> KeyValues.YES_VALUE_1.equals(obj.getIsAcct()))
                .collect(Collectors.toList());
        Column columnPeriod = this.builder.allPeriod.get(currMetric.getTableId());
        // 其他度量上有账期
        boolean hasPeriods = checkOtherMetricsHasPeriod();
        // 全局条件无账期条件、同一分组下其他度量有账期条件、配置默认一个账期(与全局条件一样的账期)
        if (null != columnPeriod && hasPeriods) {
            StringBuilder sql = new StringBuilder();
            // 有全局账期条件
            sql.append(getAlias(currMetric)).append(SqlUtils.STR_POINT).append(columnPeriod.getColumnCode());
            if (!CollectionUtils.isEmpty(acct)) {
                DatasetConditionQo conditionQo = acct.get(0);
                BuildSqlUtil.appendSimpleCond(sql, conditionQo.getCondOperator(), conditionQo.getCondValue());
                return sql.toString();
            }
            String period = AcctTimeUtil.getAcctValAddQuotOutPubMode(this.builder.scheduleType,
                    columnPeriod.getCycleType(), columnPeriod.getColumnType(), this.builder.params.getOutPutMode());
            sql.append(SqlUtils.STR_EQUAL).append(period);
            return sql.toString();
        }
        return null;
    }

    public boolean hasPeriod(List<DatasetConditionQo> condList) {
        if (CollectionUtils.isEmpty(condList)) {
            return false;
        }
        for (DatasetConditionQo condition : condList) {
            if (KeyValues.YES_VALUE_1.equals(condition.getIsAcct())) {
                return true;
            }
        }
        return false;
    }

    public abstract void setOutField(StringBuilder sql);

    public String getDateOffset(String cycleType) {
        if (StringUtils.isNotEmpty(cycleType)) {
            CycleInfo cycleInfo = AcctTimeUtil.getCycleInfo().get(cycleType);
            return cycleInfo.getCycleVal();
        }
        return Constants.ACCT_CODE_EXP;
    }
}
