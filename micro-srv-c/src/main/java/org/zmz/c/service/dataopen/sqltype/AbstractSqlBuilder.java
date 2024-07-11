package org.zmz.c.service.dataopen.sqltype;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.zmz.c.mapper.dataopen.DatasetObjMapper;
import org.zmz.c.qo.dataopen.BusinessAttr;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DataPrivCtrlVo;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetConditionQo;
import org.zmz.c.qo.dataopen.MetaDataInfo;
import org.zmz.c.qo.dataopen.MetricsDimensionPathVo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaVo;
import org.zmz.c.qo.dataopen.OrgDimension;
import org.zmz.c.service.dataopen.dataset.SqlComponent;
import org.zmz.c.utils.AccountUtil;
import org.zmz.c.utils.AcctTimeUtil;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.SqlConvertUtils;
import org.zmz.c.utils.SqlUtils;
import org.zmz.c.vo.dataopen.common.LoginInfo;
import org.zmz.c.vo.dataopen.dataset.CycleInfo;
import org.zmz.c.vo.dataopen.dataset.ResultSql;
import org.zmz.c.vo.dataopen.dataset.TimeGranularityVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractSqlBuilder extends AbstractRelativeAndLevelSqlBuilder implements SqlBuildeI {

    /**
     * 所有维度
     */
    protected List<DatasetColumnQo> dimensions = new ArrayList<>();

    /**
     * 所有度量
     */
    protected List<DatasetColumnQo> metrics = new ArrayList<>();

    /**
     * 账期是否日、月混用
     */
    protected boolean isMixed = false;

    /**
     * 权限控制信息
     */
    private DataPrivCtrlVo dataPrivCtrlInfo = null;

    /**
     * 是否有最细粒度组织字段
     */
    private boolean isHaveOrgId = false;

    protected int tbIndex = 0;

    /**
     * 是否有计算字段
     */
    private boolean isCal = false;

    /**
     * sql拼接业务
     *
     * @return sql
     */
    @Override
    public String sqlParse() {
        ResultSql result = new ResultSql();
        if (CollUtil.isNotEmpty(this.metrics)) {
            // 按度量归属表进行分组
            result.metricsGroup = this.metrics.stream().collect(Collectors.groupingBy(DatasetColumnQo::getTableId));
            // 计算字段不能single
            result.isSingle = result.metricsGroup.size() == 1;
            // 调度模式的sql。1、生成维度临时表多个，2、维度临时表与度量关联
            if (Constants.SQL_TASK.equals(params.getSqlMode())) {
                result.isSingle = !this.hasCalMetric(params.getColumnList());
                if (CollUtil.isNotEmpty(this.params.getTempTablesMap())) {
                    // 临时表不为空
                    this.scheduleSqlBuilder(result);
                    return scheduleSql(result);
                }
            }
        }
        result.isGroupBy = CollUtil.isNotEmpty(this.metrics);
        generalSqlBuilder(result);
        if (Constants.SQL_TASK.equals(params.getSqlMode())) {
            return scheduleSql(result);
        }
        return previewSql(result);
    }

    /**
     * 有临时表的调度sql构建
     *
     * @param result 返回参数引用
     */
    protected void scheduleSqlBuilder(ResultSql result) {
        if (autoLevelGroup) {
            DatasetColumnQo currLevelColumn = maxLevelColumn(this.dimensions);
            // 多表的层级字段
            if (iteratorColumnMap.size() > 1) {
                result.isSingle = false;
                iteratorColumnMap.forEach((key, value) -> scheduleSqlAppend(result, this.params.getColumnList(), params.getCondList(), value.get(0)));
                return;
            }
            // 单表层级字段
            List<OrgDimension> orgDimensions = iteratorColumnMap.get(currLevelColumn.getTableId());
            result.isSingle = orgDimensions.size() == 1;
            orgDimensions.forEach(t -> scheduleSqlAppend(result, this.params.getColumnList(), params.getCondList(), t));
            return;
        }
        scheduleSqlAppend(result, this.params.getColumnList(), params.getCondList(), null);
    }

    /**
     * 没有临时表的调度sql构建、如果是单个sql就需要添加上输出字段的注释(度量归属表分组只有一个、层级字段一个)
     */
    private void generalSqlBuilder(ResultSql result) {
        if (autoLevelGroup) {
            // 有层级字段
            DatasetColumnQo currLevelColumn = maxLevelColumn(this.dimensions);
            if (MapUtil.isNotEmpty(result.metricsGroup)) {
                // 有度量
                for (Map.Entry<Long, List<DatasetColumnQo>> entry : result.metricsGroup.entrySet()) {
                    if (iteratorColumnMap.size() > 1) {
                        result.isSingle = false;
                        // 多表的层级字段
                        iteratorColumnMap.forEach(
                                (key, value) -> appendRelativeMetric(result, entry.getValue(), params.getColumnList(), params.getCondList(), value.get(0))
                        );
                        return;
                    }
                    // 单表的层级字段
                    List<OrgDimension> orgDimensions = iteratorColumnMap.get(currLevelColumn.getTableId());
                    result.isSingle = (result.isSingle && result.metricsGroup.size() == 1 && orgDimensions.size() == 1);
                    for (OrgDimension t : orgDimensions) {
                        appendRelativeMetric(result, entry.getValue(), params.getColumnList(), params.getCondList(), t);
                    }
                }
                return;
            }
            // 没有度量 一般不会有这样的配置
            if (iteratorColumnMap.size() > 1) {
                result.isSingle = false;
                // 多表的层级字段
                iteratorColumnMap.forEach(
                        (key, value) -> appendRelativeMetric(result, params.getCustomMetrics(), params.getColumnList(), params.getCondList(), value.get(0))
                );
                return;
            }
            // 单表的层级字段、没有度量 一般不会有这样的配置
            List<OrgDimension> orgDimensions = iteratorColumnMap.get(currLevelColumn.getTableId());
            result.isSingle = (result.isSingle && orgDimensions.size() == 1);
            orgDimensions.forEach(
                    t -> appendRelativeMetric(result, params.getCustomMetrics(), params.getColumnList(), params.getCondList(), t)
            );
            return;
        }

        // 没有层级字段
        if (MapUtil.isNotEmpty(result.metricsGroup)) {
            // 有度量 没有层级字段
            result.isSingle = (result.isSingle && result.metricsGroup.size() == 1);
            result.metricsGroup.forEach(
                    (key, value) -> appendRelativeMetric(result, value, params.getColumnList(), params.getCondList(), null)
            );
            return;
        }
        // 没有度量 没有层级字段，此时不需要group by
        appendRelativeMetric(result, params.getCustomMetrics(), params.getColumnList(), params.getCondList(), null);
    }

    /**
     * 子查询;子查询;子查询;子查询合并语句;删除语句(sql1,sql2,sql3)
     *
     * @param result 组装sql的参数
     * @return 子查询;子查询;子查询;子查询合并语句;删除语句(sql1,sql2,sql3)
     */
    private String scheduleSql(ResultSql result) {
        if (result.mergeSqls.isEmpty() && result.sqlLists.isEmpty()) {
            log.error("result.mergeSqls 或者 result.sqlLists 子查询为空");
            throw new IllegalStateException("子查询为空");
        }
        List<String> sqlArr = new ArrayList<>(16);
        String tbName = "t1";
        // 用于计算字段获取别名
        StringBuilder dropSql = new StringBuilder();
        SqlComponent component = new SqlComponent();
        int flag = 1;
        // 有临时表的创建(维度表)
        for (Map.Entry<String, String> entry : result.tmpTableSql.entrySet()) {
            String sqlStr = (StringUtils.isNotBlank(this.scheduleType) && "O".equalsIgnoreCase(this.scheduleType))
                    ? acctSqlService.formatAcct(entry.getValue(), this.scheduleType)
                    : entry.getValue();
            sqlArr.add(createTmpTable(result.tmpTableNames.get(entry.getKey()), sqlStr));
            dropSql.append(result.tmpTableNames.get(entry.getKey())).append(",");
        }

        if (!result.sqlLists.isEmpty()) {
            // 没有临时表的创建
            for (String sqlList : result.sqlLists) {
                String sqlStr;
                if (StringUtils.isNotBlank(this.scheduleType) && "O".equalsIgnoreCase(this.scheduleType)) {
                    sqlStr = acctSqlService.formatAcct(sqlList, this.scheduleType);
                } else {
                    sqlStr = sqlList;
                }
                // 主表关联
                String tmpName = generateSubTmpTableName(this.scheduleType);
                component.join.append("select * from ").append(tmpName);
                sqlArr.add(createTmpTable(tmpName, sqlStr));
                dropSql.append(tmpName);
                if (flag < result.sqlLists.size()) {
                    component.join.append(SqlUtils.SQL_UNION_ALL);
                    dropSql.append(",");
                }
                flag++;
            }
        } else {
            // 子查询临时表创建和调度sql拼接(维度与度量拼接的sql)
            for (String sqlStr : result.mergeSqls) {
                String sql = (StringUtils.isNotBlank(this.scheduleType) && "O".equalsIgnoreCase(this.scheduleType))
                        ? acctSqlService.formatAcct(sqlStr, this.scheduleType)
                        : sqlStr;
                String tmpName = generateSubTmpTableName(this.scheduleType);
                // 度量子查询sql临时表创建
                sqlArr.add(createTmpTable(tmpName, sql));
                // 子查询连接
                component.join.append("select * from ").append(tmpName);
                // 删除度量子查询sql临时表
                dropSql.append(tmpName);
                if (flag < result.mergeSqls.size()) {
                    component.join.append(SqlUtils.SQL_UNION_ALL);
                    dropSql.append(",");
                }
                flag++;
            }
        }
        // 为1的时候不用封装一层,有计算字段需要封装；周期性的需要处理账期字段
        if (!isCal && (result.sqlLists.size() == 1 || result.mergeSqls.size() == 1)
                && Constants.SCHEDULE_LOOP_TYPE_O.equals(params.getScheduleType())) {
            sqlArr.add(component.join.toString());
        } else {
            // 度量包含精度换算和null的话传为0
            this.outFieldsJoin(component.field, tbName);
            checkPrivCtrl(component.field, tbName);
            checkScheduleType(component.field);
            this.outSideGroupBy(component.group, tbName);
            sqlArr.add(component.unionSql(tbName));
        }
        sqlArr.add(dropSql.toString());
        return StringUtils.join(sqlArr, ";");
    }

    /**
     * sql
     *
     * @param result 预览主视图的sql参数
     * @return sql
     */
    private String previewSql(ResultSql result) {
        if (result.sqlLists.isEmpty()) {
            log.error("result.sqlLists 子查询为空");
            throw new IllegalStateException("子查询为空");
        }

        // 关联时间维度表
        StringBuilder sql = new StringBuilder();

        if (result.sqlLists.size() == 1) {
            String sqlStr = "O".equalsIgnoreCase(this.scheduleType)
                    ? acctSqlService.formatAcct(result.sqlLists.get(0), this.scheduleType)
                    : result.sqlLists.get(0);
            sql.append(sqlStr);
        } else {
            String tbName = "t1";
            // 用于计算字段获取别名
            SqlComponent component = new SqlComponent();
            for (int i = 0; i < result.sqlLists.size(); i++) {
                // 账期替换
                String sqlStr = (StringUtils.isNotBlank(this.scheduleType) && "O".equalsIgnoreCase(this.scheduleType))
                        ? acctSqlService.formatAcct(result.sqlLists.get(i), this.scheduleType)
                        : result.sqlLists.get(i);
                sqlStr = i < (result.sqlLists.size() - 1) ? sqlStr + SqlUtils.SQL_UNION_ALL : sqlStr;
                component.join.append(sqlStr);
            }
            // 度量包含精度换算和null的话传为0
            this.outFieldsJoin(component.field, tbName);
            checkPrivCtrl(component.field, tbName);
            checkScheduleType(component.field);
            // 校验sql,传递条件1=2
            if (Constants.SQL_CHECK.equals(params.getSqlMode())) {
                component.where.append("1=2");
            }
            this.outSideGroupBy(component.group, tbName);
            this.orderByColumnList(component.order, tbName);
            sql.append(component.unionSql(tbName));
        }
        sql = acctSqlService.joinTimeDimension(params, sql);
        return sql.toString();
    }

    /**
     * 外层的分组条件
     */
    private void outSideGroupBy(StringBuilder sql, String tbName) {
        StringBuilder groupSql = new StringBuilder();
        if (SqlBuilderHelper.checkDataPriv(this.dataPrivCtrlInfo)) {
            StringBuilder privCtrl = new StringBuilder();
            if (!this.isHaveOrgId) {
                privCtrl.append(tbName).append(SqlUtils.STR_POINT).append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.STR_DOT);
            }
            privCtrl.append(tbName).append(SqlUtils.STR_POINT).append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.STR_DOT);
            groupSql.insert(0, privCtrl);
        }

        if (!CollectionUtils.isEmpty(this.dimensions) && !CollectionUtils.isEmpty(this.metrics)) {
            boolean hasAppenedLevel = false;
            for (DatasetColumnQo dimension : this.dimensions) {
                if (Constants.YES_VALUE_1.equals(dimension.getIsAcct()) && !"O".equals(this.scheduleType)) {
                    // 周期性的账期字段忽略分组
                    continue;
                }
                if (autoLevelGroup && isLevelColumn(dimension)) {
                    if (hasAppenedLevel) {
                        continue;
                    }
                    groupSql.append(tbName).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_ID)
                            .append(SqlUtils.STR_DOT);
                    groupSql.append(tbName).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_NAME)
                            .append(SqlUtils.STR_DOT);
                    groupSql.append(tbName).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_LEVEL)
                            .append(SqlUtils.STR_DOT);
                    hasAppenedLevel = true;
                } else {
                    groupSql.append("t1").append(SqlUtils.STR_POINT).append(dimension.getAlias())
                            .append(SqlUtils.STR_DOT);
                }
            }
            if (!groupSql.isEmpty()) {
                groupSql.deleteCharAt(groupSql.length() - 1);
                sql.append(groupSql);
            }
        }
    }

    /**
     * 拼接orgId和pathCode
     */
    private void checkPrivCtrl(StringBuilder outFields, String tbName) {
        if (SqlBuilderHelper.checkDataPriv(this.dataPrivCtrlInfo)) {
            StringBuilder privCtrl = new StringBuilder();
            if (!this.isHaveOrgId) {
                // 输出维度字段没有最细粒度的组织id
                privCtrl.append(tbName).append(SqlUtils.STR_POINT).append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.SQL_AS)
                        .append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.STR_DOT);
            }
            privCtrl.append(tbName).append(SqlUtils.STR_POINT).append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.SQL_AS)
                    .append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.STR_DOT);
            outFields.insert(0, privCtrl);
        }
    }

    /**
     * 调度模式的sql，维度没有账期字段则默认拼接
     */
    private void checkScheduleType(StringBuilder outFields) {
        if (Constants.SQL_TASK.equals(params.getSqlMode()) && !"O".equalsIgnoreCase(this.scheduleType)) {
            // O是一次性
            boolean hasPeriod = false;
            for (DatasetColumnQo dimension : this.dimensions) {
                if (Constants.YES_VALUE_1.equals(dimension.getIsAcct())) {
                    hasPeriod = true;
                    break;
                }
            }
            if (!hasPeriod) {
                // 没有账期
                String periodStr;
                CycleInfo cycleInfo = AcctTimeUtil.getCycleInfo().get(this.scheduleType);
                periodStr = cycleInfo.getCycleVal() + " as " + cycleInfo.getCycleExp();
                if (null != periodStr) {
                    if (getDbType().equals(KeyValues.DS_HIVE)) {
                        outFields.append(",").append(periodStr);
                    } else {
                        outFields.insert(0, periodStr + ",");
                    }
                }
            }
        }
    }

    private void outFieldsJoin(StringBuilder outFields, String tbName) {
        // 用于计算字段取表达式
        Map<String, StringBuilder> expMap = new HashMap<>();
        DatasetColumnQo columnQoHive = null;
        boolean hasAppenedLevel = false;
        for (DatasetColumnQo columnQo : this.params.getColumnList()) {
            if (columnQo.isHide()) {
                continue;
            }
            StringBuilder fieldsAppends = new StringBuilder();
            String notes = !getDbType().equals(KeyValues.DS_HIVE) ? SqlBuilderHelper.fieldNotes(columnQo.getDataName())
                    : "";
            if (Constants.YES_VALUE_1.equals(columnQo.getIsAcct())) {
                // hive数据源的账期字段抽取出来放到所有字段最后面
                if (getDbType().equals(KeyValues.DS_HIVE) && !"O".equals(this.scheduleType)) {
                    columnQoHive = columnQo;
                } else {
                    if (!"O".equals(this.scheduleType)) {
                        // 周期性,账期要引号
                        CycleInfo cycleInfo = AcctTimeUtil.getCycleInfo().get(columnQo.getCycleType());
                        fieldsAppends.append(cycleInfo.getCycleVal());
                        outFields.append(cycleInfo.getCycleVal()).append(SqlUtils.SQL_AS).append(columnQo.getAlias())
                                .append(notes).append(SqlUtils.STR_DOT);
                        expMap.put(columnQo.getAlias(), fieldsAppends);
                    } else {
                        fieldsAppends.append(tbName).append(SqlUtils.STR_POINT).append(columnQo.getAlias());
                        outFields.append(fieldsAppends).append(SqlUtils.SQL_AS).append(columnQo.getAlias())
                                .append(notes).append(SqlUtils.STR_DOT);
                        expMap.put(columnQo.getAlias(), fieldsAppends);
                    }
                }
            } else if (Constants.APP_TYPE_DIMENSION.equals(columnQo.getAppType())) {
                if (autoLevelGroup && isLevelColumn(columnQo)) {
                    if (hasAppenedLevel) {
                        continue;
                    }
                    DatasetColumnQo maxLevelColumn = maxLevelColumn(this.dimensions);
                    OrgDimension orgDimension = getOrgLevelDimension(maxLevelColumn);
                    ModelInfo modelInfo = modelInfoMap.get(maxLevelColumn.getTableId());
                    // 区域id
                    Column idColumn = modelInfo.findColumn(orgDimension.getOrgIdColumnCode());
                    String idNote = (!getDbType().equals(KeyValues.DS_HIVE)
                            && StringUtils.isNotBlank(idColumn.getColumnName()))
                            ? SqlBuilderHelper.fieldNotes(idColumn.getColumnName())
                            : "";
                    outFields.append(tbName).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_ID)
                            .append(SqlUtils.SQL_AS).append(Constants.DATASET_AREA_ID).append(idNote)
                            .append(SqlUtils.STR_DOT);
                    // 区域名称
                    Column nameColumn = modelInfo.findColumn(orgDimension.getOrgNameColumnCode());
                    String nameNote = (!getDbType().equals(KeyValues.DS_HIVE)
                            && StringUtils.isNotBlank(nameColumn.getColumnName()))
                            ? SqlBuilderHelper.fieldNotes(nameColumn.getColumnName())
                            : "";
                    outFields.append(tbName).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_NAME)
                            .append(SqlUtils.SQL_AS).append(Constants.DATASET_AREA_NAME).append(nameNote)
                            .append(SqlUtils.STR_DOT);
                    // 区域层级
                    outFields.append(tbName).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_LEVEL)
                            .append(SqlUtils.SQL_AS).append(Constants.DATASET_AREA_LEVEL).append(SqlUtils.STR_DOT);
                    hasAppenedLevel = true;
                } else {
                    fieldsAppends.append(tbName).append(SqlUtils.STR_POINT).append(columnQo.getAlias());
                    outFields.append(fieldsAppends).append(SqlUtils.SQL_AS).append(columnQo.getAlias()).append(notes)
                            .append(SqlUtils.STR_DOT);
                }

                expMap.put(columnQo.getAlias(), fieldsAppends);
            } else if (Constants.APP_TYPE_METRICS.equals(columnQo.getAppType())
                    && CollectionUtils.isEmpty(columnQo.getColumnGroup())) {
                if ("MAX".equalsIgnoreCase(columnQo.getFunc()) || "MIN".equalsIgnoreCase(columnQo.getFunc())) {
                    outFields.append(columnQo.getFunc());
                } else {
                    outFields.append("SUM");
                }
                fieldsAppends.append(tbName).append(SqlUtils.STR_POINT).append(columnQo.getAlias());
                outFields.append(SqlUtils.STR_LEFT_BRACKET.trim()).append(tbName).append(SqlUtils.STR_POINT)
                        .append(columnQo.getAlias()).append(SqlUtils.STR_RIGHT_BRACKET.trim()).append(SqlUtils.SQL_AS)
                        .append(columnQo.getAlias()).append(notes).append(SqlUtils.STR_DOT);
                expMap.put(columnQo.getAlias(), fieldsAppends);
            } else {
                List<DatasetColumnQo> columnGroup = columnQo.getColumnGroup();
                String expression = SqlBuilderHelper.getColumnGroup(columnGroup, expMap, tbName);
                String divisionConvert = SqlConvertUtils.divisionConvert(expression);
                String convertMetric = this.metricIfNull("(" + divisionConvert + ")", columnQo);
                fieldsAppends.append(convertMetric);
                outFields.append(fieldsAppends).append(SqlUtils.SQL_AS).append(columnQo.getAlias()).append(notes)
                        .append(SqlUtils.STR_DOT);
                expMap.put(columnQo.getAlias(), fieldsAppends);
            }
        }
        if (null != columnQoHive) {
            CycleInfo cycleInfo = AcctTimeUtil.getCycleInfo().get(columnQoHive.getCycleType());
            outFields.append(cycleInfo.getCycleVal()).append(SqlUtils.SQL_AS).append(columnQoHive.getAlias())
                    .append(SqlUtils.STR_DOT);
        }
        // 删掉逗号
        if (!outFields.isEmpty()) {
            outFields.deleteCharAt(outFields.length() - 1);
        }
    }

    /**
     * 字段输出排序
     */
    private void orderByColumnList(StringBuilder result, String bName) {
        if (!CollectionUtils.isEmpty(this.params.getColumnList())) {
            StringBuilder sql = new StringBuilder();
            for (DatasetColumnQo qo : this.params.getColumnList()) {
                if (StringUtils.isNoneBlank(qo.getSortOrder())) {
                    // 度量字段还需要处理长度精度，带表别名会报错
                    if (!Constants.APP_TYPE_METRICS.equals(qo.getAppType())) {
                        sql.append(bName).append(SqlUtils.STR_POINT);
                    }
                    sql.append(qo.getAlias()).append(SqlUtils.STR_BLANK).append(qo.getSortOrder())
                            .append(SqlUtils.STR_DOT);
                }
            }
            if (!sql.isEmpty()) {
                sql.deleteCharAt(sql.length() - 1);
                result.append(sql);
            }
        }
    }

    /**
     * 省公司（region=-1）预览数据，加上默认过滤地市（如版纳）条件，提高查询速度
     */
    private boolean checkLan() {
        if (Constants.SQL_PREVIEW.equals(this.params.getSqlMode()) && AccountUtil.getPostLanId() != null) {
            String defLan = staticDataService.getDcSystemParamByCache(Constants.CONSUME_DEF_LAN_ID);
            return StringUtils.isNotEmpty(defLan);
        }
        return false;
    }

    /**
     * 省份条件
     */
    private boolean checkProvince() {
        String provinceId = AccountUtil.getProvinceId();
        return StringUtils.isNotEmpty(provinceId) && !Constants.ROOT_ID.equals(provinceId);
    }

    @Override
    protected void appendTableSql(boolean isPriv,
                                  Map.Entry<String, List<MetricsDimensionPathVo>> entry,
                                  Map<Long, String> hasAppend,
                                  boolean needAppendPeriod,
                                  boolean hasOrgTable,
                                  Map<String, String> tableAlias,
                                  SqlComponent component) {
        String tbPrefix = "tb";
        // 全局条件中的账期值范围
        List<MetricsDimensionPathVo> metricsDimensionPathVoList = entry.getValue();
        Map<Long, String> periodMaps = getPeriodFromPathsAndCondList(metricsDimensionPathVoList);
        for (MetricsDimensionPathVo path : metricsDimensionPathVoList) {
            // 过滤掉度量已经关联过的表、关联度量到维度之间的表
            Long srcTableId = path.getSrcTableId();
            Long tgtTableId = path.getTgtTableId();
            String srcTableIdStr = String.valueOf(srcTableId);
            String tgtTableIdStr = String.valueOf(tgtTableId);
            String srcTableAlias = tableAlias.get(srcTableIdStr);
            String tgtTableAlias = tableAlias.get(tgtTableIdStr);
            if (StringUtils.isNoneBlank(srcTableAlias) && StringUtils.isNoneBlank(tgtTableAlias)) {
                continue;
            }
            String srcSchemaCode = this.getSchemaCodeByTableId(srcTableId);
            // 首次拼接sql主表别名为空
            if (ObjectUtils.isEmpty(srcTableAlias)) {
                String srcName = tbPrefix + getIncrementTbIndex();
                tableAlias.put(srcTableIdStr, srcName);
                // 只有一张主表
                if (metricsDimensionPathVoList.size() == 1 &&
                        (tgtTableId == null || path.getTgtTableCode() == null)
                ) {
                    component.join.append(srcSchemaCode).append(SqlUtils.STR_POINT).append(path.getSrcTableCode())
                            .append(SqlUtils.STR_BLANK).append(srcName);
                    // 数据过滤拼接lanId
                    if (checkLan()) {
                        // 添加where条件
                        ModelInfo table = this.modelInfoMap.get(srcTableId);
                        appendLan(true, table, component.join, component.where, srcName);
                    }
                    // 数据过滤拼接provinceId
                    if (checkProvince()) {
                        // 添加where条件
                        ModelInfo table = this.modelInfoMap.get(srcTableId);
                        appendProvince(true, table, component.join, component.where, srcName);
                    }
                    // 权限控制拼接组织明细表
                    if (SqlBuilderHelper.checkDataPriv(this.dataPrivCtrlInfo)) {
                        appendOrgDetailsTable(hasOrgTable, component, tableAlias, hasAppend, tbPrefix, path, isPriv);
                    }
                } else {
                    if (tgtTableId == null) {
                        continue;
                    }
                    String tgtSchemaCode = this.getSchemaCodeByTableId(tgtTableId);
                    String tgtName = tgtTableAlias;
                    if (StringUtils.isBlank(tgtName)) {
                        tgtName = tbPrefix + getIncrementTbIndex();
                        tableAlias.put(tgtTableIdStr, tgtName);
                    }
                    // schemaCode.表1 ta1 left join schemaCode.表2 tb2 on ta1.字段=ta2.字段
                    List<String> multiColumns = path.getMultiColumns();
                    if (CollUtil.isNotEmpty(multiColumns)) {
                        // 多维指标内部表关联
                        appendInnerJoinSql(component, srcSchemaCode, srcName, tgtSchemaCode, tgtName, path);
                        for (String columnCode : multiColumns) {
                            component.join.append(SqlUtils.STR_BLANK)
                                    .append(srcName)
                                    .append(SqlUtils.STR_POINT)
                                    .append(columnCode)
                                    .append(SqlUtils.STR_EQUAL)
                                    .append(tgtName)
                                    .append(SqlUtils.STR_POINT)
                                    .append(columnCode)
                                    .append(" and");
                        }
                        component.join.delete(component.join.lastIndexOf("and"), component.join.length());
                    } else {
                        appendInnerJoinSql(component, srcSchemaCode, srcName, tgtSchemaCode, tgtName, path);
                        this.appendKeyColumns(path.getKeyColumnRelas(), component.join, srcName, tgtName);
                    }

                    // 关联两表的账期字段
                    this.appendPeriodCond(component.join, tableAlias, path, periodMaps, needAppendPeriod);

                    // 数据过滤拼接lanId
                    if (checkLan()) {
                        // 添加where条件
                        ModelInfo srcTable = this.modelInfoMap.get(srcTableId);
                        appendLan(true, srcTable, component.join, component.where, srcName);
                        ModelInfo tgtTable = this.modelInfoMap.get(tgtTableId);
                        appendLan(false, tgtTable, component.join, component.where, tgtName);
                    }
                    // 数据过滤拼接provinceId
                    if (checkProvince()) {
                        // 添加where条件
                        ModelInfo srcTable = this.modelInfoMap.get(srcTableId);
                        appendProvince(true, srcTable, component.join, component.where, srcName);
                        ModelInfo tgtTable = this.modelInfoMap.get(tgtTableId);
                        appendProvince(false, tgtTable, component.join, component.where, tgtName);
                    }
                    // 权限控制拼接组织明细表
                    if (SqlBuilderHelper.checkDataPriv(this.dataPrivCtrlInfo)) {
                        appendOrgDetailsTable(hasOrgTable, component, tableAlias, hasAppend, tbPrefix, path, isPriv);
                    }
                }
            } else {
                // 拼接从表 表别名缓存
                if (null == tgtTableId) {
                    continue;
                }
                String tgtName = tgtTableAlias;
                if (StringUtils.isBlank(tgtName)) {
                    tgtName = tbPrefix.concat(String.valueOf(getIncrementTbIndex()));
                    tableAlias.put(tgtTableIdStr, tgtName);
                }
                String tgtSchemaCode = this.getSchemaCodeByTableId(tgtTableId);

                // left join schemaCode.表3 tb3 on ta2.字段=ta3.字段
                component.join.append(SqlUtils.SQL_INNER_JOIN).append(tgtSchemaCode).append(SqlUtils.STR_POINT)
                        .append(path.getTgtTableCode()).append(SqlUtils.STR_BLANK).append(tgtName).append(SqlUtils.SQL_ON);
                this.appendKeyColumns(path.getKeyColumnRelas(), component.join,
                        srcTableAlias, tgtName);

                // 关联两表的账期字段
                this.appendPeriodCond(component.join, tableAlias, path, periodMaps, needAppendPeriod);

                // 数据过滤拼接lanId
                if (checkLan()) {
                    ModelInfo tgtTable = this.modelInfoMap.get(tgtTableId);
                    appendLan(false, tgtTable, component.join, component.where, tgtName);
                }
                // 数据过滤拼接provinceId
                if (checkProvince()) {
                    ModelInfo tgtTable = this.modelInfoMap.get(tgtTableId);
                    appendProvince(false, tgtTable, component.join, component.where, tgtName);
                }
                // 权限控制拼接组织明细表
                if (SqlBuilderHelper.checkDataPriv(this.dataPrivCtrlInfo)) {
                    appendOrgDetailsTable(hasOrgTable, component, tableAlias, hasAppend, tbPrefix, path, isPriv);
                }
            }
        }

        // 没有划小架构表 只拼接pathcode一次
        if (!hasOrgTable && component.field.indexOf(CUSTOM_PATH_CODE_ALIAS) == -1
                && SqlBuilderHelper.checkDataPriv(this.dataPrivCtrlInfo)) {
            component.field.append("'").append(getDefOrgPathCode()).append("'").append(SqlUtils.SQL_AS)
                    .append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.STR_DOT);
        }
    }

    private void appendInnerJoinSql(SqlComponent component,
                                    String srcSchemaCode,
                                    String srcName,
                                    String tgtSchemaCode,
                                    String tgtName,
                                    MetricsDimensionPathVo path) {
        component.join.append(srcSchemaCode)
                .append(SqlUtils.STR_POINT)
                .append(path.getSrcTableCode())
                .append(SqlUtils.STR_BLANK)
                .append(srcName)
                .append(SqlUtils.SQL_INNER_JOIN)
                .append(tgtSchemaCode)
                .append(SqlUtils.STR_POINT)
                .append(path.getTgtTableCode())
                .append(SqlUtils.STR_BLANK)
                .append(tgtName)
                .append(SqlUtils.SQL_ON);
    }

    protected ModelInfo getOrgDimensionTable() {
        return this.dataPrivCtrlInfo.getOrgDimensionModelInfo();
    }

    public void appendOrgDetailsTable(Long tableId,
                                      ModelInfo orgDimensionTable,
                                      boolean hasOrgTable,
                                      Map<Long, String> hasAppend,
                                      String tbPrefix,
                                      Map<String, String> map,
                                      boolean isSrcTableId,
                                      SqlComponent component,
                                      boolean isPriv) {
        // 从表获取组织明细
        MetricsDimensionPathVo orgDetailsTgt = getOrgDetails(tableId);
        if (
                !ObjectUtils.isEmpty(orgDetailsTgt) &&
                        StringUtils.isBlank(hasAppend.get(tableId))
        ) {
            String orgName = null;
            if (hasOrgTable && tableId.equals(orgDimensionTable.getMetaDataInfo().getMetaDataId())
                    && isPriv) {
                orgName = joinOrgDetails(component.join, map, tbPrefix, orgDetailsTgt);
                // 输出字段添加path_code
                component.field.append(orgName)
                        .append(SqlUtils.STR_POINT)
                        .append(getOrgDetailsPathCode())
                        .append(SqlUtils.SQL_AS)
                        .append(CUSTOM_PATH_CODE_ALIAS)
                        .append(SqlUtils.STR_DOT);
                component.group.append(orgName)
                        .append(SqlUtils.STR_POINT)
                        .append(getOrgDetailsPathCode())
                        .append(SqlUtils.STR_DOT);
            }

            if (needAppendPathCode(tableId)) {
                if (null == orgName) {
                    orgName = joinOrgDetails(component.join, map, tbPrefix, orgDetailsTgt);
                }
                if (!component.where.isEmpty()) {
                    component.where.append(SqlUtils.SQL_AND);
                }
                component.where.append(orgName)
                        .append(SqlUtils.STR_POINT)
                        .append(getOrgDetailsPathCode())
                        .append(" like '%")
                        .append(Constants.ORG_ID)
                        .append("%'");
            }
            hasAppend.put(tableId, orgName);
            if (isSrcTableId) {
                hasAppend.put(tableId, orgName);
            }
        }
    }

    // 将组织明细表添加到sql中
    @Override
    public void appendOrgDetailsTable(boolean hasOrgTable,
                                      SqlComponent component,
                                      Map<String, String> map,
                                      Map<Long, String> hasAppend,
                                      String tbPrefix,
                                      MetricsDimensionPathVo path,
                                      boolean isPriv) {
        // 划小架构表
        ModelInfo orgDimensionTable = getOrgDimensionTable();

        Long srcTableId = path.getSrcTableId();
        appendOrgDetailsTable(srcTableId, orgDimensionTable, hasOrgTable, hasAppend, tbPrefix, map, true, component, isPriv);

        Long tgtTableId = path.getTgtTableId();
        appendOrgDetailsTable(tgtTableId, orgDimensionTable, hasOrgTable, hasAppend, tbPrefix, map, false, component, isPriv);
    }

    private String joinOrgDetails(StringBuilder joinSql, Map<String, String> map, String tbPrefix,
                                  MetricsDimensionPathVo orgDetails) {
        // 拼接组织明细表
        String orgName = tbPrefix.concat(String.valueOf(getIncrementTbIndex()));
        String orgSchemaCode = this.getSchemaCodeByTableId(orgDetails.getTgtTableId());
        joinSql.append(SqlUtils.SQL_INNER_JOIN).append(orgSchemaCode).append(SqlUtils.STR_POINT)
                .append(orgDetails.getTgtTableCode()).append(SqlUtils.STR_BLANK).append(orgName).append(SqlUtils.SQL_ON);
        this.appendKeyColumns(orgDetails.getKeyColumnRelas(), joinSql,
                map.get(String.valueOf(orgDetails.getSrcTableId())), orgName);
        return orgName;
    }

    @Override
    protected boolean needAppendPathCode(Long tableId) {
        if (!CollectionUtils.isEmpty(this.dataPrivCtrlInfo.getDataPrivModelList())) {
            for (ModelInfo modelInfo : this.dataPrivCtrlInfo.getDataPrivModelList()) {
                if (tableId.equals(modelInfo.getMetaDataInfo().getMetaDataId())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 添加where条件 lan_id=xxx
     */
    private void appendLan(boolean isMain, ModelInfo table, StringBuilder joinSql, StringBuilder whereSql,
                           String tableAlias) {
        if (StringUtils.isNotEmpty(table.getBussinessAttr().getLanField())) {
            Long defLan = AccountUtil.getPostLanId();
            if (Constants.ROOT_ID.equals(defLan + "")) {
                defLan = Long.parseLong(staticDataService.getDcSystemParamByCache(Constants.CONSUME_DEF_LAN_ID));
            }
            if (isMain) {
                if (!whereSql.isEmpty()) {
                    whereSql.append(SqlUtils.SQL_AND);
                }
                whereSql.append(tableAlias).append(SqlUtils.STR_POINT).append(table.getBussinessAttr().getLanField())
                        .append(" = '").append(defLan).append("'");
            } else {
                joinSql.append(SqlUtils.SQL_AND).append(tableAlias).append(SqlUtils.STR_POINT)
                        .append(table.getBussinessAttr().getLanField()).append(" = '").append(defLan).append("'");
            }
        }
    }

    /**
     * 添加省份where条件 province_id=xxx
     */
    private void appendProvince(boolean isMain,
                                ModelInfo table,
                                StringBuilder joinSql,
                                StringBuilder whereSql,
                                String tableAlias) {
        String provinceField = table.getBussinessAttr().getProvinceField();
        if (StringUtils.isNotEmpty(provinceField)) {
            String provinceId = staticDataService.getDcSystemParamByCache(Constants.CONSUME_DEF_PROV_ID);
            if (StringUtils.isEmpty(provinceId)) {
                provinceId = AccountUtil.getProvinceId();
            }
            if (StringUtils.isEmpty(provinceId)) {
                return;
            }
            if (isMain) {
                if (!whereSql.isEmpty()) {
                    whereSql.append(SqlUtils.SQL_AND);
                }
                whereSql.append(tableAlias).append(SqlUtils.STR_POINT).append(provinceField).append(" = '")
                        .append(provinceId).append("'");
            } else {
                joinSql.append(SqlUtils.SQL_AND).append(tableAlias).append(SqlUtils.STR_POINT).append(provinceField)
                        .append(" = '").append(provinceId).append("'");
            }
        }
    }

    @Override
    public String getOrgDetailsPathCode() {
        ModelInfo orgModel = this.dataPrivCtrlInfo.getOrgModelInfo();
        BusinessAttr attr = orgModel.getBussinessAttr();
        return attr.getPathCode();
    }

    @Override
    public String getDefOrgPathCode() {
        String type = staticDataService.getDcSystemParamByCache(Constants.CONSUME_ORG_MODEL);
        return AccountUtil.getPathCodeByType(type, params.getCreator());
    }

    private MetricsDimensionPathVo getOrgDetails(Long tableId) {
        if (null == tableId) {
            return null;
        }
        Map<Long, MetricsDimensionPathVo> orgInfoPathsMap = this.dataPrivCtrlInfo.getOrgInfoPathsMap();
        return orgInfoPathsMap.get(tableId);
    }

    @Override
    protected Map<Long, String> getPeriodFromPathsAndCondList(List<MetricsDimensionPathVo> paths) {
        Map<Long, String> maps = new HashMap<>();
        List<DatasetConditionQo> condList = this.params.getCondList();
        if (CollUtil.isNotEmpty(condList)) {
            for (DatasetConditionQo qo : condList) {
                if (KeyValues.YES_VALUE_1.equals(qo.getIsAcct()) && StringUtils.isNoneBlank(qo.getCycleType())) {
                    StringBuilder strB;
                    for (MetricsDimensionPathVo path : paths) {
                        Long srcTableId = path.getSrcTableId();
                        if (null != srcTableId && null == maps.get(srcTableId)) {
                            Column column = this.allPeriod.get(srcTableId);
                            if (null != column && column.getCycleType().equals(qo.getCycleType())) {
                                strB = new StringBuilder();
                                String value = replaceValues(qo, column);
                                strB.append(column.getColumnCode())
                                        .append(SqlUtils.STR_BLANK)
                                        .append(qo.getCondOperator())
                                        .append(value);
                                maps.put(srcTableId, strB.toString());
                            }
                        }
                        Long tgtTableId = path.getTgtTableId();
                        if (null != tgtTableId && null == maps.get(tgtTableId)) {
                            Column column = this.allPeriod.get(tgtTableId);
                            if (null != column && column.getCycleType().equals(qo.getCycleType())) {
                                strB = new StringBuilder();
                                String value = replaceValues(qo, column);
                                strB.append(column.getColumnCode())
                                        .append(SqlUtils.STR_BLANK)
                                        .append(qo.getCondOperator())
                                        .append(value);
                                maps.put(tgtTableId, strB.toString());
                            }
                        }
                    }
                }
            }
        }
        return maps;
    }

    /**
     * 系统账期类型要添加到 on 关联条件上去
     */
    @Override
    protected String samePeriodType(MetricsDimensionPathVo path, Map<String, String> map) {
        if (ObjectUtils.isEmpty(this.allPeriod.get(path.getSrcTableId()))
                || ObjectUtils.isEmpty(this.allPeriod.get(path.getTgtTableId()))) {
            return null;
        }
        Column srcColumn = this.allPeriod.get(path.getSrcTableId());
        Column tgtColumn = this.allPeriod.get(path.getTgtTableId());
        if (srcColumn.getCycleType().equals(tgtColumn.getCycleType())) {
            StringBuilder sb = new StringBuilder();
            // 账期偏移
            if (this.params.isAcctOffset()) {
                if (this.params.getAcctOffset().getLeftTableIds().contains(path.getSrcTableId())
                        && this.params.getAcctOffset().getRightTableIds().contains(path.getTgtTableId())) {
                    sb.append(map.get(String.valueOf(path.getSrcTableId()))).append(SqlUtils.STR_POINT)
                            .append(srcColumn.getColumnCode()).append(SqlUtils.STR_EQUAL)
                            .append(getDateOffset(tgtColumn.getCycleType(), map.get(String.valueOf(path.getTgtTableId()))
                                    + SqlUtils.STR_POINT + tgtColumn.getColumnCode(), 1, ""));
                    return sb.toString();
                }
                if (this.params.getAcctOffset().getLeftTableIds().contains(path.getTgtTableId())
                        && this.params.getAcctOffset().getRightTableIds().contains(path.getSrcTableId())) {
                    sb.append(map.get(String.valueOf(path.getTgtTableId()))).append(SqlUtils.STR_POINT)
                            .append(tgtColumn.getColumnCode()).append(SqlUtils.STR_EQUAL)
                            .append(getDateOffset(srcColumn.getCycleType(), map.get(String.valueOf(path.getSrcTableId()))
                                    + SqlUtils.STR_POINT + srcColumn.getColumnCode(), 1, ""));
                    return sb.toString();
                }
            }

            sb.append(map.get(String.valueOf(path.getSrcTableId()))).append(SqlUtils.STR_POINT)
                    .append(srcColumn.getColumnCode()).append(SqlUtils.STR_EQUAL)
                    .append(map.get(String.valueOf(path.getTgtTableId()))).append(SqlUtils.STR_POINT)
                    .append(tgtColumn.getColumnCode());
            return sb.toString();
        }
        return null;
    }

    private void searchPeriodColumn() {
        String cycleType = null;
        for (Map.Entry<Long, ModelInfo> entity : this.modelInfoMap.entrySet()) {
            ModelInfo modelInfo = entity.getValue();
            MetaDataInfo info = modelInfo.getMetaDataInfo();
            String dataCycleCode = info.getDataCycleCode();
            if (null != modelInfo.findAcctColumn()) {
                Column period = modelInfo.findAcctColumn();
                period.setCycleType(dataCycleCode);
                this.allPeriod.put(entity.getKey(), period);
            }
            if (null == cycleType && StringUtils.isNoneBlank(dataCycleCode)) {
                cycleType = dataCycleCode;
            }
            // 标记为日月账期混用
            if (null != cycleType && StringUtils.isNoneBlank(dataCycleCode)
                    && !cycleType.equals(dataCycleCode)) {
                this.isMixed = true;
            }
            initTimeGranularity(modelInfo);
        }
    }

    /**
     * 封装时间粒度
     */
    private void initTimeGranularity(ModelInfo m) {
        BusinessAttr busi = m.getBussinessAttr();
        if (busi == null) {
            return;
        }
        TimeGranularityVo vo = new TimeGranularityVo();
        vo.setTableId(m.getMetaDataInfo().getMetaDataId());
        vo.setTimeGranularity(busi.getTimeGranularity());
        vo.setTimeGranularityColumn(busi.getTimeGranularityColumn());
        vo.setTimeGranularityValueColumn(busi.getTimeGranularityValueColumn());
        if (!CollectionUtils.isEmpty(m.getColumnList())) {
            for (Column c : m.getColumnList()) {
                if (c.getColumnCode().equalsIgnoreCase(vo.getTimeGranularityColumn())) {
                    vo.getColumnMap().put("timeFreq", c);
                } else if (c.getColumnCode().equalsIgnoreCase(vo.getTimeGranularityValueColumn())) {
                    vo.getColumnMap().put("timeFreqValue", c);
                }
            }
        }
        if (!vo.getColumnMap().isEmpty()) {
            this.allTimeGranularity.put(vo.getTableId(), vo);
        }
    }

    private void initParseParams(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
        this.modelInfoMap = modelInfoMap;
        this.params = params;
        this.scheduleType = params.getScheduleType();
        this.sqlMode = params.getSqlMode();
        this.outPutMode = params.getOutPutMode();

        if (Constants.SQL_TASK.equals(sqlMode)) {
            String flag = staticDataService.getDcSystemParamByCache("DATA_CONSUMPTION_SUB_QUERY_TMP_TABLE");
            this.subQueryToTmTab = StringUtils.isEmpty(flag) || Boolean.parseBoolean(flag);
        }
        // 配置数据源schemaCode
        for (Map.Entry<Long, ModelInfo> entry : modelInfoMap.entrySet()) {
            MetaDataInfo metaDataInfo = entry.getValue().getMetaDataInfo();
            this.schemaMap.put(metaDataInfo.getMetaDataId(), metaDataInfo.getSchemaCode());
        }

        List<DatasetColumnQo> columns = params.getColumnList();
        for (DatasetColumnQo column : columns) {
            if (Constants.APP_TYPE_METRICS.equals(column.getAppType())) {
                if (CollectionUtils.isEmpty(column.getColumnGroup())) {
                    metrics.add(column);
                } else {
                    isCal = true;
                }
            }
            if (Constants.APP_TYPE_DIMENSION.equals(column.getAppType())) {
                dimensions.add(column);
            }
        }
        if (params.judgeIndexView()) {
            DatasetObjMapper mapper = SpringUtil.getBean(DatasetObjMapper.class);
            Map<String, Object> query = new HashMap<>(2);
            String referType = "metaIndex";
            query.put("referType", referType);
            LoginInfo loginInfo = AccountUtil.getLoginInfo();
            if (!LoginInfo.ACCOUNT_TYPE_SUPER_ADMIN.equalsIgnoreCase(loginInfo.getAccountType())) {
                query.put("comAcctId", loginInfo.getUserInfo().getComAcctId());
            }
            List<ObjKeyTableRelaVo> relList = mapper.selectObjKeyTableColumnRel(query);
            for (ObjKeyTableRelaVo v : relList) {
                this.relMap.put(v.getMetaDataId() + "_" + v.getRelaKeyObjectId(), v);
            }
        } else {
            //检查维度字段是否有层级字段配置自动向上汇总配置
            hasAutoLevel(dimensions);
        }
        searchPeriodColumn();
        if (!ObjectUtils.isEmpty(params.getDataPrivCtrlInfo())) {
            this.dataPrivCtrlInfo = params.getDataPrivCtrlInfo();
        }
    }

    public AbstractSqlBuilder() {
    }

    public AbstractSqlBuilder(DatasetColumnAndConditionQo params, Map<Long, ModelInfo> modelInfoMap) {
        // 初始化参数
        initParseParams(params, modelInfoMap);
    }

    @Override
    public List<DatasetColumnQo> getMetrics() {
        return this.metrics;
    }

    @Override
    public DataPrivCtrlVo getDataPrivCtrlInfo() {
        return this.dataPrivCtrlInfo;
    }

    @Override
    public boolean checkHaveOrgId() {
        return this.isHaveOrgId;
    }

    @Override
    public void haveOrgId(boolean have) {
        this.isHaveOrgId = have;
    }

    @Override
    protected int getIncrementTbIndex() {
        this.tbIndex++;
        return this.tbIndex;
    }

    @Override
    protected boolean checkMixed() {
        return isMixed;
    }

    /**
     * 表达式转类型
     */
    @Override
    public String castColumnType(Column column, String columnExp, String columnType) {
        StringBuilder fieldSql = new StringBuilder();
        fieldSql.append(columnExp);
        if (!column.getColumnType().equalsIgnoreCase(columnType)) {
            fieldSql.insert(0, SqlUtils.STR_LEFT_BRACKET).insert(0, SqlUtils.STR_FUNC_CAST).append(SqlUtils.SQL_AS)
                    .append(columnType).append(SqlUtils.STR_RIGHT_BRACKET);
        }
        return fieldSql.toString();
    }

    /**
     * 表达式转类型
     */
    public String castColumnType(String columnExp, String columnType) {
        StringBuilder fieldSql = new StringBuilder();
        fieldSql.append(columnExp);
        if (StringUtils.isNotEmpty(columnType)) {
            fieldSql.insert(0, SqlUtils.STR_LEFT_BRACKET).insert(0, SqlUtils.STR_FUNC_CAST).append(SqlUtils.SQL_AS)
                    .append(columnType).append(SqlUtils.STR_RIGHT_BRACKET);
        }
        return fieldSql.toString();
    }

    /**
     * mysql日期偏移计算
     *
     * @param type type "year"年、"month"月、"day"日
     */
    public String getDateOffset(String cycleType, String col, Integer offset, String type) {
        StringBuilder funcDateOffset = new StringBuilder();
        if (Constants.SCHEDULE_LOOP_TYPE_M.equalsIgnoreCase(cycleType)) {
            type = StringUtils.defaultIfEmpty(type, "MONTH");
            funcDateOffset.append("DATE_FORMAT(DATE_ADD(CONCAT(").append(col).append(",'01'),INTERVAL ").append(-offset)
                    .append(" ").append(type).append("),'%Y%m')");
        }
        if (Constants.SCHEDULE_LOOP_TYPE_D.equalsIgnoreCase(cycleType)) {
            type = StringUtils.defaultIfEmpty(type, "DAY");
            funcDateOffset.append("DATE_FORMAT(DATE_ADD(").append(col).append(",INTERVAL ").append(-offset).append(" ")
                    .append(type).append("),'%Y%m%d')");
        }
        if (Constants.SCHEDULE_LOOP_TYPE_H.equalsIgnoreCase(cycleType)) {
            type = StringUtils.defaultIfEmpty(type, "HOUR");
            funcDateOffset.append("DATE_FORMAT(DATE_ADD(").append(col).append(",INTERVAL ").append(-offset).append(" ")
                    .append(type).append("),'%Y%m%d%H')");
        }
        if (Constants.SCHEDULE_LOOP_TYPE_F.equalsIgnoreCase(cycleType)) {
            type = StringUtils.defaultIfEmpty(type, "MINUTE");
            funcDateOffset.append("DATE_FORMAT(DATE_ADD(").append(col).append(",INTERVAL ").append(-offset).append(" ")
                    .append(type).append("),'%Y%m%d%H%i')");
        }
        return funcDateOffset.toString();
    }
}