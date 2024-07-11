package org.zmz.c.service.dataopen.sqltype;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DataPrivCtrlVo;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetConditionQo;
import org.zmz.c.qo.dataopen.MetricsDimensionPathVo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.qo.dataopen.ObjKeyColumnRelaVo;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaVo;
import org.zmz.c.qo.dataopen.ObjRelaTreeColumnVo;
import org.zmz.c.qo.dataopen.OrgDimension;
import org.zmz.c.qo.dataopen.OutPutMode;
import org.zmz.c.qo.dataopen.SubQuerySqlQo;
import org.zmz.c.service.dataopen.common.StaticDataService;
import org.zmz.c.service.dataopen.dataset.AcctSqlService;
import org.zmz.c.service.dataopen.dataset.EngineType;
import org.zmz.c.service.dataopen.dataset.SqlComponent;
import org.zmz.c.service.dataopen.dataset.SqlFuncEnum;
import org.zmz.c.service.dataopen.remote.DataCommonService;
import org.zmz.c.service.dataopen.sqlfunc.AbstractFuncParser;
import org.zmz.c.service.dataopen.sqlfunc.PeriodExpression;
import org.zmz.c.service.dataopen.sqlfunc.SqlBuilderFactory;
import org.zmz.c.utils.AcctTimeUtil;
import org.zmz.c.utils.BuildSqlUtil;
import org.zmz.c.utils.JsonUtil;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.OrganizationUtil;
import org.zmz.c.utils.SqlConvertUtils;
import org.zmz.c.utils.SqlUtils;
import org.zmz.c.vo.dataopen.dataset.ResultSql;
import org.zmz.c.vo.dataopen.dataset.TimeGranularityVo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author fyh
 * {@code @date} 2022-09-05 19:08
 * {@code @description} 生成sqlMode为task的sql拼接
 */
@Slf4j
public abstract class AbstractSqlBuilderBase {
    /**
     * 前端的传参
     */
    public DatasetColumnAndConditionQo params = null;

    /**
     * 来源表
     */
    protected Map<Long, ModelInfo> modelInfoMap = new HashMap<>();

    protected static final String CUSTOM_ORG_ID_ALIAS = "consume_org_id";

    protected static final String CUSTOM_PATH_CODE_ALIAS = "consume_path_code";

    /**
     * 数据源schemaCode
     */
    protected Map<Long, String> schemaMap = new HashMap<>();

    /**
     * 所有账期字段
     */
    public Map<Long, Column> allPeriod = new HashMap<>();

    /**
     * 所有时间粒度字段
     */
    public Map<Long, TimeGranularityVo> allTimeGranularity = new HashMap<>();

    public String scheduleType = null;

    public String sqlMode = null;

    /**
     * 输出模式 默认是table,或者sql
     */
    public String outPutMode = OutPutMode.TABLE;

    protected Map<Long, List<OrgDimension>> iteratorColumnMap = new LinkedHashMap<>();

    protected boolean autoLevelGroup = false;

    protected AcctSqlService acctSqlService;

    protected StaticDataService staticDataService;

    protected DataCommonService dataCommonService;

    /**
     * 子查询转换为临时表
     **/
    protected boolean subQueryToTmTab = false;

    /**
     * 实例预览--关联关系
     */
    protected Map<String, ObjKeyTableRelaVo> relMap = new HashMap<>();

    {
        acctSqlService = SpringUtil.getBean(AcctSqlService.class);
        staticDataService = SpringUtil.getBean(StaticDataService.class);
        dataCommonService = SpringUtil.getBean(DataCommonService.class);
    }

    protected void scheduleSqlAppend(ResultSql result, List<DatasetColumnQo> columnList,
                                     List<DatasetConditionQo> condList, OrgDimension replaceLevelColumn) {
        // 缓存临时表路径对象集合
        Map<String, List<MetricsDimensionPathVo>> cacheTempPath = new HashMap<>();
        // 分组过后的度量的path路径参数是一样的,一个分组一个子查询
        result.isSingle = result.isSingle && result.metricsGroup.size() == 1;
        for (Map.Entry<Long, List<DatasetColumnQo>> entry : result.metricsGroup.entrySet()) {
            // 以相对维度字段进行分组 目前最多两组(包含/include,排除/exclude)
            Map<List<String>, List<DatasetColumnQo>> dimGroup = getRelativeDimensionGroup(entry.getValue(), columnList);
            // 单条sql有临时表时，临时表也要抽取出来
            // 多分组时不能单条sql，会导致拼接任务sql不正确
            String mainSql = appendScheduleSql(
                    result.isSingle && dimGroup.isEmpty() && this.params.getTempTablesMap().isEmpty(), entry.getValue(),
                    Constants.DimensionType.TYPE_MAIN, columnList, condList, result, cacheTempPath, replaceLevelColumn);
            if (MapUtil.isNotEmpty(dimGroup)) {
                List<SubQuerySqlQo> subSqlList = new ArrayList<>();
                for (Map.Entry<List<String>, List<DatasetColumnQo>> dimGroupEntry : dimGroup.entrySet()) {
                    String dimensionType = dimGroupEntry.getValue().get(0).getDimensionType();
                    List<DatasetColumnQo> relativedimensionLists = columnList.stream()
                            .filter(t -> dimGroupEntry.getKey().contains(t.getAlias())).collect(Collectors.toList());
                    List<DatasetColumnQo> metricGroup = dimGroupEntry.getValue();
                    String subSql = appendScheduleSql(false, metricGroup, dimensionType, relativedimensionLists,
                            condList, result, cacheTempPath, replaceLevelColumn);
                    SubQuerySqlQo relativeDimension = new SubQuerySqlQo();
                    relativeDimension.setDimensionList(relativedimensionLists);
                    relativeDimension.setSql(subSql);
                    relativeDimension.setMetricList(metricGroup);
                    relativeDimension.setDimensionType(dimensionType);
                    subSqlList.add(relativeDimension);
                }
                // 主视图与相对维度视图合并
                result.mergeSqls.add(mergeRelativeDims(false, columnList, mainSql, subSqlList, replaceLevelColumn,
                        subQueryToTmTab ? result : null));
            } else {
                // 没有相对维度视图
                result.mergeSqls.add(mainSql);
            }
        }
    }

    /**
     * 调度关联表拼接
     */
    protected String subJoinTables(SqlComponent component, Map<String, Map<String, String>> mainTbPathAlias,
                                   Map<String, String> tempTbPathAlias, boolean hasOrgTable, ResultSql result, DatasetColumnQo metric,
                                   Map<String, List<MetricsDimensionPathVo>> cacheTempPath,
                                   Map<String, Map<String, List<MetricsDimensionPathVo>>> tempTablesMap,
                                   Map<String, Map<String, MetricsDimensionPathVo>> mainTablesToTempTablesMap,
                                   Map<String, Map<String, List<MetricsDimensionPathVo>>> mainTablesMap, boolean needAppendPeriod) {
        // 度量的所有到维度路径上抽出来的临时表
        String path = metric.getPath();
        Map<String, List<MetricsDimensionPathVo>> tempTbPaths = tempTablesMap.get(path);
        // 度量的所有维度路径被抽取后的路径
        Map<String, List<MetricsDimensionPathVo>> mainTbPaths = mainTablesMap.get(path);
        // 主表到临时表关联字段关系
        Map<String, MetricsDimensionPathVo> mainTbToTempTbPaths = mainTablesToTempTablesMap.get(path);

        // 遍历度量到维度的所有路径,先生成临时表然后与度量表关联
        List<MetricsDimensionPathVo> hasJoinList = new ArrayList<>();
        String tmpTbName = null;
        Map<Long, String> hasAppend = new HashMap<>();
        Map<String, String> publicAlias = new HashMap<>();
        for (Map.Entry<String, List<MetricsDimensionPathVo>> pathEntry : metric.getPathsMap().entrySet()) {
            // 全局条件中的账期值范围
            Map<Long, String> periodMaps = getPeriodFromPathsAndCondList(pathEntry.getValue());
            String dimPath = pathEntry.getKey();
            // 未被抽取的度量到维度的对象路径
            // 拿到抽取出来的临时表路径对象
            List<MetricsDimensionPathVo> tempTbs = tempTbPaths.get(dimPath);
            List<MetricsDimensionPathVo> mainTbs = mainTbPaths.get(dimPath);
            boolean isPriv = dimPath.equals(metric.getDataPrivPathKey());
            Map<String, String> dimTbAlias = new HashMap<>();
            if (MapUtil.isNotEmpty(publicAlias)) {
                dimTbAlias.putAll(publicAlias);
            }
            if (CollUtil.isNotEmpty(mainTbs) && CollUtil.isNotEmpty(tempTbs)) {
                MetricsDimensionPathVo objRelation = mainTbToTempTbPaths.get(dimPath);
                // 1 有临时表和度量表
                // 1.1 分别构建临时表sql和度量表sql
                // 检查临时表是否可复用,不可复用就创建临时表
                String cacheTempDimPath = checkTempTablePath(cacheTempPath, tempTbs, path + dimPath);
                if (StringUtils.isBlank(cacheTempDimPath)) {
                    // 临时表构建
                    tempTableJoin(hasOrgTable, hasAppend, path, dimPath, tempTbs, objRelation,
                            result.tmpTableSql, result.tmpTableNames, metric, periodMaps, needAppendPeriod, isPriv);
                    tmpTbName = result.tmpTableNames.get(path + dimPath);
                } else {
                    tmpTbName = result.tmpTableNames.get(cacheTempDimPath);
                }
                // 1.2 合并临时表sql和度量表sql
                metricTempTableJoin(hasJoinList, component, dimTbAlias, mainTbs, hasAppend, periodMaps,
                        needAppendPeriod, isPriv);
                // 拼上临时表
                if (StringUtils.isNotBlank(tmpTbName)) {
                    String tgtName = createTableAlias();
                    component.join.append(SqlUtils.SQL_INNER_JOIN).append(tmpTbName).append(SqlUtils.STR_BLANK)
                            .append(tgtName).append(SqlUtils.SQL_ON);
                    this.appendKeyColumns(objRelation.getKeyColumnRelas(), component.join,
                            dimTbAlias.get(String.valueOf(objRelation.getSrcTableId())), tgtName);
                    tempTbPathAlias.put(dimPath, tgtName);
                }
            } else {
                List<MetricsDimensionPathVo> mains = !CollectionUtils.isEmpty(mainTbs) ? mainTbs : tempTbs;
                metricTableJoin(hasJoinList, component, dimTbAlias, mains, hasAppend, periodMaps, needAppendPeriod,
                        isPriv);
            }
            SqlBuilderHelper.copyCommonAlias(dimTbAlias, pathEntry.getValue(), publicAlias);
            mainTbPathAlias.put(dimPath, dimTbAlias);
        }
        return tmpTbName;
    }

    private String appendScheduleSql(boolean singleSql, List<DatasetColumnQo> metricList, String dimensionType,
                                     List<DatasetColumnQo> dimensionList, List<DatasetConditionQo> condList, ResultSql result,
                                     Map<String, List<MetricsDimensionPathVo>> cacheTempPath, OrgDimension replaceLevelColumn) {
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

        StringBuilder fields = mergeField(singleSql, metricList, dimensionType, dimensionList, mainTbPathAlias,
                tempTbPathAlias, hasOrgTable, tmpTbName, replaceLevelColumn, null);
        component.field.append(fields);

        this.appendWhere(singleSql, component.where, metricList, dimensionType, condList, mainTbPathAlias, mainTbPaths,
                tempTbPathAlias, null);
        this.mergeGroupBy(metricList, dimensionList, component.group, mainTbPathAlias, tempTbPathAlias, hasOrgTable,
                tmpTbName, replaceLevelColumn);

        // 检查是否有同环比和月/年累计
        List<DatasetColumnQo> growthOrTotalsMetric = checkGrowthOrTotal(metricList, dimensionType);
        boolean single = singleSql && CollectionUtils.isEmpty(growthOrTotalsMetric);
        if (single) {
            // 排序
            orderByColumnList(component.order, metricList, dimensionList, mainTbPathAlias);
            return component.swapSql().toString();
        }

        // 生成同环比或者月/年累计的子查询、相同粒度下的同一统计函数
        List<SubQuerySqlQo> subSqlList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(growthOrTotalsMetric)) {
            Map<String, List<DatasetColumnQo>> funcGroups = growthOrTotalsMetric.stream()
                    .collect(Collectors.groupingBy(DatasetColumnQo::getFunc));
            funcGroups.forEach((key, values) -> {
                // 有同环比或者月/年累计
                subScheduleGrowthOrTotal(subSqlList, false, values, dimensionType, dimensionList, condList, result,
                        cacheTempPath, replaceLevelColumn);
            });
            // 合并汇总表，组织层级字段已经取过别名
            if (replaceLevelColumn != null) {
                replaceLevelColumn.setAlias(true);
            }
        }
        // 合并同环比或者月年累计
        return mergeRelativeDims(singleSql, dimensionList, component.swapSql().toString(), subSqlList,
                replaceLevelColumn, subQueryToTmTab ? result : null);
    }

    protected void metricTempTableJoin(List<MetricsDimensionPathVo> hasJoinList, SqlComponent component,
                                       Map<String, String> dimAlias, List<MetricsDimensionPathVo> mainTbs, Map<Long, String> hasAppend,
                                       Map<Long, String> periodMaps, boolean needAppendPeriod, boolean isPriv) {
        // 度量表关联临时表
        if (component.join.isEmpty()) {
            // 路径第一次拼接
            for (MetricsDimensionPathVo mainTb : mainTbs) {
                if (hasJoinList.contains(mainTb)) {
                    continue;
                }
                joinSrcAndTgt(component, dimAlias, mainTb, hasAppend, periodMaps, needAppendPeriod, isPriv);
                hasJoinList.add(mainTb);
            }
        } else {
            // 路径第二条之后的拼接
            for (MetricsDimensionPathVo mainTb : mainTbs) {
                if (hasJoinList.contains(mainTb)) {
                    continue;
                }
                joinSrcAndTgt(component, dimAlias, mainTb, hasAppend, periodMaps, needAppendPeriod, isPriv);
                hasJoinList.add(mainTb);
            }
        }
    }

    protected void metricTableJoin(List<MetricsDimensionPathVo> hasJoinList, SqlComponent component,
                                   Map<String, String> dimAlias, List<MetricsDimensionPathVo> mainTbs, Map<Long, String> hasAppend,
                                   Map<Long, String> periodMaps, boolean needAppendPeriod, boolean isPriv) {
        // 只有度量路径或者组织维表的临时表
        for (MetricsDimensionPathVo mainTb : mainTbs) {
            if (hasJoinList.contains(mainTb)) {
                continue;
            }
            joinSrcAndTgt(component, dimAlias, mainTb, hasAppend, periodMaps, needAppendPeriod, isPriv);
            hasJoinList.add(mainTb);
        }
    }

    protected String getSchemaCodeByTableId(Long metaDataId) {
        ModelInfo modelInfo = this.modelInfoMap.get(metaDataId);
        if (modelInfo != null) {
            return modelInfo.getMetaDataInfo().getSchemaCode();
        }
        ModelInfo model = dataCommonService.queryAllModelInfoPro(metaDataId);
        if (model != null) {
            this.modelInfoMap.put(model.getMetaDataInfo().getMetaDataId(), model);
            return model.getMetaDataInfo().getSchemaCode();
        }
        // 获取模型详情失败
        throw new RuntimeException("根据 metaDataId " + metaDataId + " 获取模型详情失败");
    }

    private void joinSrcAndTgt(SqlComponent component, Map<String, String> dimAlias, MetricsDimensionPathVo mainTb,
                               Map<Long, String> hasAppend, Map<Long, String> periodMaps, boolean needAppendPeriod, boolean isPriv) {
        if (StringUtils.isBlank(dimAlias.get(String.valueOf(mainTb.getSrcTableId())))) {
            // 主表
            String srcName = createTableAlias();
            dimAlias.put(String.valueOf(mainTb.getSrcTableId()), srcName);
            component.join.append(this.getSchemaCodeByTableId(mainTb.getSrcTableId())).append(SqlUtils.STR_POINT)
                    .append(mainTb.getSrcTableCode()).append(SqlUtils.STR_BLANK).append(srcName);
            // 权限过滤条件
            this.joinOrgDetails(component, dimAlias, mainTb.getSrcTableId(), hasAppend, isPriv);

            // 从表
            if (null != mainTb.getTgtTableId()
                    && StringUtils.isBlank(dimAlias.get(String.valueOf(mainTb.getTgtTableId())))) {
                String tgtName = createTableAlias();
                dimAlias.put(String.valueOf(mainTb.getTgtTableId()), tgtName);
                component.join.append(SqlUtils.SQL_INNER_JOIN)
                        .append(this.getSchemaCodeByTableId(mainTb.getTgtTableId())).append(SqlUtils.STR_POINT)
                        .append(mainTb.getTgtTableCode()).append(SqlUtils.SQL_AS).append(tgtName).append(SqlUtils.SQL_ON);

                if (!CollectionUtils.isEmpty(mainTb.getMultiColumns())) {
                    // 多维指标内部表关联
                    component.join.append(this.getSchemaCodeByTableId(mainTb.getSrcTableId()))
                            .append(SqlUtils.STR_POINT).append(mainTb.getSrcTableCode()).append(SqlUtils.STR_BLANK)
                            .append(srcName).append(SqlUtils.SQL_INNER_JOIN)
                            .append(this.getSchemaCodeByTableId(mainTb.getTgtTableId())).append(SqlUtils.STR_POINT)
                            .append(mainTb.getTgtTableCode()).append(SqlUtils.STR_BLANK).append(tgtName)
                            .append(SqlUtils.SQL_ON);
                    for (String columnCode : mainTb.getMultiColumns()) {
                        component.join.append(SqlUtils.STR_BLANK).append(srcName).append(SqlUtils.STR_POINT)
                                .append(columnCode).append(SqlUtils.STR_EQUAL).append(tgtName).append(SqlUtils.STR_POINT)
                                .append(columnCode).append(" and");
                    }
                    component.join.deleteCharAt(component.join.lastIndexOf("and"));
                } else {
                    this.appendKeyColumns(mainTb.getKeyColumnRelas(), component.join, srcName, tgtName);
                }

                // 关联账期字段
                this.appendPeriodCond(component.join, dimAlias, mainTb, periodMaps, needAppendPeriod);
                // 省份
                // 权限过滤条件
                this.joinOrgDetails(component, dimAlias, mainTb.getTgtTableId(), hasAppend, isPriv);
            }
        } else {
            if (null != mainTb.getTgtTableId()
                    && StringUtils.isBlank(dimAlias.get(String.valueOf(mainTb.getTgtTableId())))) {
                String srcName = dimAlias.get(String.valueOf(mainTb.getSrcTableId()));
                String tgtName = createTableAlias();
                dimAlias.put(String.valueOf(mainTb.getTgtTableId()), tgtName);
                component.join.append(SqlUtils.SQL_INNER_JOIN)
                        .append(this.getSchemaCodeByTableId(mainTb.getTgtTableId())).append(SqlUtils.STR_POINT)
                        .append(mainTb.getTgtTableCode()).append(SqlUtils.SQL_AS).append(tgtName).append(SqlUtils.SQL_ON);
                this.appendKeyColumns(mainTb.getKeyColumnRelas(), component.join, srcName, tgtName);

                // 关联账期字段
                this.appendPeriodCond(component.join, dimAlias, mainTb, periodMaps, needAppendPeriod);
                // 权限过滤条件
                this.joinOrgDetails(component, dimAlias, mainTb.getTgtTableId(), hasAppend, isPriv);
            }
        }
    }

    private void joinOrgDetails(SqlComponent component, Map<String, String> dimAlias, Long tableId,
                                Map<Long, String> hasAppend, boolean isPriv) {
        MetricsDimensionPathVo orgDetailsTgt = getDataPrivCtrlInfo().getOrgInfoPathsMap().get(tableId);
        if (!ObjectUtils.isEmpty(orgDetailsTgt) && StringUtils.isBlank(hasAppend.get(tableId)) && isPriv) {
            String orgName = joinOrgDetails(component.join, dimAlias, orgDetailsTgt);
            if (!component.where.isEmpty()) {
                component.where.append(SqlUtils.SQL_AND);
            }
            dimAlias.put(String.valueOf(orgDetailsTgt.getTgtTableId()), orgName);
            component.where.append(orgName).append(SqlUtils.STR_POINT).append(getOrgDetailsPathCode())
                    .append(" like '%").append(Constants.ORG_ID).append("%'");
            hasAppend.put(tableId, dimAlias.get(String.valueOf(tableId)));
        }
    }

    protected abstract Map<Long, String> getPeriodFromPathsAndCondList(List<MetricsDimensionPathVo> paths);

    /**
     * 系统账期类型要添加到 on 关联条件上去
     */
    protected abstract String samePeriodType(MetricsDimensionPathVo path, Map<String, String> map);

    protected void appendPeriodCond(StringBuilder join, Map<String, String> tableAlias, MetricsDimensionPathVo path,
                                    Map<Long, String> periodMaps, boolean needAppendPeriod) {
        // 关联两表的账期字段类型一样
        String samePeriod = samePeriodType(path, tableAlias);
        if (StringUtils.isNoneBlank(samePeriod)) {
            join.append(SqlUtils.SQL_AND).append(samePeriod);
        }
        Long tgtTableId = path.getTgtTableId();
        String tgtName = tableAlias.get(String.valueOf(tgtTableId));
        // 主表有账期字段是配置到where条件中。从表账期字段全局条件中有值的优先配置、否则用默认的。
        if (StringUtils.isNoneBlank(periodMaps.get(tgtTableId)) && needAppendPeriod) {
            join.append(SqlUtils.SQL_AND)
                    .append(tgtName)
                    .append(SqlUtils.STR_POINT)
                    .append(periodMaps.get(tgtTableId));
        } else {
            if (!this.params.isAcctOffset()) {
                String periodTgt = getDefultPeriod(tgtTableId, tableAlias);
                if (StringUtils.isNoneBlank(periodTgt) && needAppendPeriod) {
                    join.append(SqlUtils.SQL_AND).append(periodTgt);
                }
            }
        }
    }

    /**
     * 临时表构建
     */
    private void tempTableJoin(boolean hasOrgTable, Map<Long, String> hasAppend, String metricPath, String dimPath,
                               List<MetricsDimensionPathVo> tempTbs, MetricsDimensionPathVo objRelation, Map<String, String> tmpTableSql,
                               Map<String, String> tmpTableNames, DatasetColumnQo metric, Map<Long, String> periodMaps,
                               boolean needAppendPeriod, boolean isPriv) {
        Map<String, String> tempAlias = new HashMap<>();
        SqlComponent component = new SqlComponent();
        for (MetricsDimensionPathVo tempTb : tempTbs) {

            Long srcTableId = tempTb.getSrcTableId();
            String srcTableIdStr = String.valueOf(srcTableId);
            if (StringUtils.isBlank(tempAlias.get(srcTableIdStr))) {
                String srcName = createTableAlias();
                tempAlias.put(srcTableIdStr, srcName);
                component.join.append(this.schemaMap.get(srcTableId))
                        .append(SqlUtils.STR_POINT)
                        .append(tempTb.getSrcTableCode())
                        .append(SqlUtils.STR_BLANK)
                        .append(srcName);

                Long tgtTableId = tempTb.getTgtTableId();
                String tgtTableIdStr = String.valueOf(tgtTableId);
                if (null != tgtTableId && StringUtils.isBlank(tempAlias.get(tgtTableIdStr))) {
                    String tgtName = createTableAlias();
                    tempAlias.put(tgtTableIdStr, tgtName);
                    component.join.append(SqlUtils.SQL_INNER_JOIN)
                            .append(this.schemaMap.get(tgtTableId))
                            .append(SqlUtils.STR_POINT)
                            .append(tempTb.getTgtTableCode())
                            .append(SqlUtils.STR_BLANK)
                            .append(tgtName)
                            .append(SqlUtils.SQL_ON);
                    this.appendKeyColumns(tempTb.getKeyColumnRelas(), component.join, srcName, tgtName);

                    // 账期条件
                    this.appendPeriodCond(component.join, tempAlias, tempTb, periodMaps, needAppendPeriod);
                }
                if (SqlBuilderHelper.checkDataPriv(getDataPrivCtrlInfo())) {
                    appendOrgDetails(isPriv, hasOrgTable, component, tempAlias, hasAppend, tempTb, dimPath);
                }
            } else {
                if (null == tempTb.getTgtTableId()) {
                    continue;
                }
                String srcName = tempAlias.get(srcTableIdStr);
                String tgtName = createTableAlias();
                tempAlias.put(String.valueOf(tempTb.getTgtTableId()), tgtName);
                component.join.append(SqlUtils.SQL_INNER_JOIN).append(this.schemaMap.get(tempTb.getTgtTableId()))
                        .append(SqlUtils.STR_POINT).append(tempTb.getTgtTableCode()).append(SqlUtils.STR_BLANK)
                        .append(tgtName).append(SqlUtils.SQL_ON);
                this.appendKeyColumns(tempTb.getKeyColumnRelas(), component.join, srcName, tgtName);

                // 账期条件
                this.appendPeriodCond(component.join, tempAlias, tempTb, periodMaps, needAppendPeriod);

                if (SqlBuilderHelper.checkDataPriv(getDataPrivCtrlInfo())) {
                    appendOrgDetails(isPriv, hasOrgTable, component, tempAlias, hasAppend, tempTb, dimPath);
                }
            }
        }
        tempTableOutField(this.params, dimPath, objRelation, component.field, tempAlias, metric);
        tmpTableSql.put(metricPath + dimPath, component.swapSql().toString());
        // 避免重复生成临时表名
        tmpTableNames.putIfAbsent(metricPath + dimPath, generateSubTmpTableName(this.scheduleType));
    }

    private void appendOrgDetails(boolean isPriv, boolean hasOrgTable, SqlComponent component,
                                  Map<String, String> tempAlias, Map<Long, String> hasAppend, MetricsDimensionPathVo path, String dimPath) {
        log.info("getDataPrivCtrlInfo()-->:{}--->MetricsDimensionPathVo:{}",
                JsonUtil.toJson(getDataPrivCtrlInfo()), JsonUtil.toJson(path));
        ModelInfo orgDimensionTable = getDataPrivCtrlInfo().getOrgDimensionModelInfo();
        MetricsDimensionPathVo orgDetailsSrc = getDataPrivCtrlInfo().getOrgInfoPathsMap().get(path.getSrcTableId());

        // 需要添加数据权限过滤条件,目前临时表只加权限控制条件
        if (component.where.isEmpty()) {
            ModelInfo orgDimModel = getDataPrivCtrlInfo().getOrgDimensionModelInfoList().stream()
                    .filter(model -> path.getSrcTableId().equals(model.getMetaDataInfo().getMetaDataId()))
                    .findFirst()
                    .orElse(new ModelInfo());
            if (StringUtils.isNotEmpty(orgDimModel.getBussinessAttr().getOrgField())) {
                String orgName = joinOrgDetails(component.join, tempAlias, orgDetailsSrc);
                component.where.append(orgName).append(SqlUtils.STR_POINT).append(getOrgDetailsPathCode())
                        .append(" like '%").append(Constants.ORG_ID).append("%'");
            }
        }

        if (!ObjectUtils.isEmpty(orgDetailsSrc) && !ObjectUtils.isEmpty(orgDimensionTable)
                && StringUtils.isBlank(hasAppend.get(path.getSrcTableId()))) {
            if (hasOrgTable && path.getSrcTableId().equals(orgDimensionTable.getMetaDataInfo().getMetaDataId())
                    && isPriv) {
                appendOrgId(component.field, tempAlias, dimPath);
                // 拼接组织明细
                String orgName = joinOrgDetails(component.join, tempAlias, orgDetailsSrc);
                // 输出字段添加path_code
                component.field.append(orgName).append(SqlUtils.STR_POINT).append(getOrgDetailsPathCode())
                        .append(SqlUtils.SQL_AS).append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.STR_DOT);
                hasAppend.put(path.getSrcTableId(), orgName);
            }
        }

        MetricsDimensionPathVo orgDetailsTgt = getDataPrivCtrlInfo().getOrgInfoPathsMap().get(path.getTgtTableId());
        if (!ObjectUtils.isEmpty(orgDetailsTgt) && !ObjectUtils.isEmpty(orgDimensionTable)
                && StringUtils.isBlank(hasAppend.get(path.getTgtTableId()))) {
            if (hasOrgTable && path.getTgtTableId().equals(orgDimensionTable.getMetaDataInfo().getMetaDataId())
                    && isPriv) {
                // 输出字段拼接最细consume_org_id
                appendOrgId(component.field, tempAlias, dimPath);
                String orgName = joinOrgDetails(component.join, tempAlias, orgDetailsTgt);
                // 输出字段添加path_code
                component.field.append(orgName).append(SqlUtils.STR_POINT).append(getOrgDetailsPathCode())
                        .append(SqlUtils.SQL_AS).append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.STR_DOT);
                hasAppend.put(path.getTgtTableId(), orgName);
            }
        }
    }

    protected void appendOrgId(StringBuilder field, Map<String, String> tempAlias, String dimPath) {
        // 划小架构表最细粒度字段
        DatasetColumnQo orgDimColumn = SqlBuilderHelper.getOrgDimensionMinColumn(getDataPrivCtrlInfo());
        boolean hasOrgId = false;
        if (!ObjectUtils.isEmpty(orgDimColumn)) {
            List<DatasetColumnQo> collectDim = this.params.getColumnList().stream()
                    .filter(obj -> !Constants.APP_TYPE_METRICS.equals(obj.getAppType())
                            && !KeyValues.YES_VALUE_1.equals(obj.getIsAcct()) && dimPath.equals(obj.getPath()))
                    .toList();
            if (!CollectionUtils.isEmpty(collectDim)) {
                for (DatasetColumnQo columnQo : collectDim) {
                    if (orgDimColumn.getColumnId().equals(columnQo.getColumnId())) {
                        hasOrgId = true;
                        break;
                    }
                }
            }
        }

        StringBuilder orgStr = new StringBuilder();
        // 为空、则没有拖到组织维度表的字段
        if (ObjectUtils.isEmpty(orgDimColumn)) {
            orgStr.append("'").append(OrganizationUtil.getOrgIdByUserId(params.getCreator())).append("'")
                    .append(SqlUtils.SQL_AS).append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.STR_DOT);
            field.append(orgStr);
        } else {
            // 拖到组织维度表字段了、但是没有拖到组织id
            if (!hasOrgId) {
                String tbName = tempAlias.get(String.valueOf(orgDimColumn.getTableId()));
                orgStr.append(tbName).append(SqlUtils.STR_POINT).append(orgDimColumn.getColumnCode())
                        .append(SqlUtils.SQL_AS).append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.STR_DOT);
                field.append(orgStr);
            }
        }
    }

    private String joinOrgDetails(StringBuilder joinSql, Map<String, String> map, MetricsDimensionPathVo orgDetails) {
        // 拼接组织明细表
        String orgName = createTableAlias();
        joinSql.append(SqlUtils.SQL_INNER_JOIN).append(this.getSchemaCodeByTableId(orgDetails.getTgtTableId()))
                .append(SqlUtils.STR_POINT).append(orgDetails.getTgtTableCode()).append(SqlUtils.STR_BLANK).append(orgName)
                .append(SqlUtils.SQL_ON);

        this.appendKeyColumns(orgDetails.getKeyColumnRelas(), joinSql,
                map.get(String.valueOf(orgDetails.getSrcTableId())), orgName);
        return orgName;
    }

    // 创建表 别名(tb+自增数字)
    protected String createTableAlias() {
        return "tb" + getIncrementTbIndex();
    }

    /**
     * 检查是否有相同路径
     *
     * @param cacheTempPath      缓存的临时表路径
     * @param pathVos            当前临时表路径
     * @param metricKeyAndDimKey 当前度量和维度path相加
     * @return metricKeyAndDimKey 已经有临时表的度量和维度path相加
     */
    private String checkTempTablePath(Map<String, List<MetricsDimensionPathVo>> cacheTempPath,
                                      List<MetricsDimensionPathVo> pathVos, String metricKeyAndDimKey) {
        for (Map.Entry<String, List<MetricsDimensionPathVo>> entry : cacheTempPath.entrySet()) {
            if (entry.getValue().equals(pathVos)) {
                return entry.getKey();
            }
        }
        cacheTempPath.put(metricKeyAndDimKey, pathVos);
        return null;
    }

    private void tempTableOutField(DatasetColumnAndConditionQo params, String dimPath,
                                   MetricsDimensionPathVo objRelation, StringBuilder field, Map<String, String> tempAlias,
                                   DatasetColumnQo currMetric) {
        List<DatasetColumnQo> collectDim = new ArrayList<>();
        for (DatasetColumnQo columnQo : params.getColumnList()) {
            if (!Constants.APP_TYPE_METRICS.equals(columnQo.getAppType())
                    && !KeyValues.YES_VALUE_1.equals(columnQo.getIsAcct())) {
                String path;
                if (currMetric.getTableId().equals(columnQo.getTableId())) {
                    path = currMetric.getPath();
                } else {
                    path = SqlBuilderHelper.getConvertDimPath(currMetric, columnQo);
                }
                if (dimPath.startsWith(path)) {
                    collectDim.add(columnQo);
                }
            }
        }

        Map<Long, String> hasAppend = new HashMap<>();
        for (DatasetColumnQo columnQo : collectDim) {
            if (null == tempAlias.get(String.valueOf(columnQo.getTableId()))) {
                continue;
            }
            field.append(tempAlias.get(String.valueOf(columnQo.getTableId()))).append(SqlUtils.STR_POINT)
                    .append(columnQo.getColumnCode()).append(SqlUtils.STR_DOT);
            hasAppend.put(columnQo.getColumnId(), tempAlias.get(String.valueOf(columnQo.getTableId())));
        }
        // 多表的层级汇总，需要补全id和name字段
        List<String> appendCols = collectDim.stream().map(DatasetColumnQo::getColumnCode).toList();
        if (!CollectionUtils.isEmpty(iteratorColumnMap)) {
            iteratorColumnMap.forEach((key, value) -> {
                OrgDimension orgDimension = value.get(0);
                if (!appendCols.contains(orgDimension.getOrgIdColumnCode())) {
                    field.append(tempAlias.get(String.valueOf(orgDimension.getMetaDataId()))).append(SqlUtils.STR_POINT)
                            .append(orgDimension.getOrgIdColumnCode()).append(SqlUtils.STR_DOT);
                    this.modelInfoMap.get(orgDimension.getMetaDataId()).getColumnList().stream()
                            .filter(c -> orgDimension.getOrgIdColumnCode().equalsIgnoreCase(c.getColumnCode())).findFirst().ifPresent(column -> hasAppend.put(column.getColumnId(),
                                    tempAlias.get(String.valueOf(orgDimension.getMetaDataId()))));
                }
                if (!appendCols.contains(orgDimension.getOrgNameColumnCode())) {
                    field.append(tempAlias.get(String.valueOf(orgDimension.getMetaDataId()))).append(SqlUtils.STR_POINT)
                            .append(orgDimension.getOrgNameColumnCode()).append(SqlUtils.STR_DOT);
                    this.modelInfoMap.get(orgDimension.getMetaDataId()).getColumnList().stream()
                            .filter(c -> orgDimension.getOrgNameColumnCode().equalsIgnoreCase(c.getColumnCode()))
                            .findFirst().ifPresent(column -> hasAppend.put(column.getColumnId(),
                                    tempAlias.get(String.valueOf(orgDimension.getMetaDataId()))));
                }
            });
        }
        // 2、全局条件中的字段
        List<DatasetConditionQo> condList = params.getCondList();
        if (!CollectionUtils.isEmpty(condList)) {
            for (DatasetConditionQo cond : condList) {
                String aliasName = tempAlias.get(String.valueOf(cond.getTableId()));
                if (StringUtils.isNotBlank(aliasName) && StringUtils.isBlank(hasAppend.get(cond.getColumnId()))) {
                    field.append(aliasName).append(SqlUtils.STR_POINT).append(cond.getColumnCode())
                            .append(SqlUtils.STR_DOT);
                    hasAppend.put(cond.getColumnId(), aliasName);
                }
            }
        }
        // 3、度量上的过滤字段
        for (DatasetColumnQo metric : getMetrics()) {
            if (!CollectionUtils.isEmpty(metric.getCondList())) {
                for (DatasetConditionQo columnQo : metric.getCondList()) {
                    String aliasName = tempAlias.get(String.valueOf(columnQo.getTableId()));
                    if (StringUtils.isNotBlank(aliasName)
                            && StringUtils.isBlank(hasAppend.get(columnQo.getColumnId()))) {
                        field.append(aliasName).append(SqlUtils.STR_POINT).append(columnQo.getColumnCode())
                                .append(SqlUtils.STR_DOT);
                        hasAppend.put(columnQo.getColumnId(), aliasName);
                    }
                }
            }
        }
        // 4、维度临时表与度量主干表的关联字段
        List<ObjRelaTreeColumnVo> relaColumns = objRelation.getRelaColumns();
        if (relaColumns.isEmpty()) {
            List<ObjKeyColumnRelaVo> keyColumnRelas = objRelation.getKeyColumnRelas();
            for (ObjKeyColumnRelaVo keyColumnRela : keyColumnRelas) {
                if (null != keyColumnRela.getRelaColumnId()
                        && StringUtils.isBlank(hasAppend.get(keyColumnRela.getRelaColumnId()))) {
                    String aliasName = tempAlias.get(String.valueOf(objRelation.getTgtTableId()));
                    field.append(aliasName).append(SqlUtils.STR_POINT).append(keyColumnRela.getRelaColumnCode())
                            .append(SqlUtils.STR_DOT);
                    hasAppend.put(keyColumnRela.getRelaColumnId(), aliasName);
                }
            }
        } else {
            for (ObjRelaTreeColumnVo relaColumn : relaColumns) {
                if (null != relaColumn.getRelaColumnId()
                        && StringUtils.isBlank(hasAppend.get(relaColumn.getRelaColumnId()))) {
                    String aliasName = tempAlias.get(String.valueOf(objRelation.getTgtTableId()));
                    field.append(aliasName).append(SqlUtils.STR_POINT).append(relaColumn.getRelaColumnCode())
                            .append(SqlUtils.STR_DOT);
                    hasAppend.put(relaColumn.getRelaColumnId(), aliasName);
                }
            }
        }
        // 数据权限管理，可能team_id被别名为consume_org_id,需要判断
        if (params.getDataPrivCtrlInfo().isDataPrivCtrl()
                && params.getDataPrivCtrlInfo().getAddOrgDimensionColumn() != null
                && !hasAppend.containsKey(params.getDataPrivCtrlInfo().getAddOrgDimensionColumn().getColumnId())) {
            DatasetColumnQo addOrgDimensionColumn = params.getDataPrivCtrlInfo().getAddOrgDimensionColumn();
            String aliasName = tempAlias.get(String.valueOf(addOrgDimensionColumn.getTableId()));
            if (StringUtils.isNotEmpty(aliasName)) {
                // 还要判断用户拖拽的字段是否有相同编码但字段id不一样的
                field.append(aliasName).append(SqlUtils.STR_POINT).append(addOrgDimensionColumn.getColumnCode())
                        .append(SqlUtils.STR_DOT);
                hasAppend.put(addOrgDimensionColumn.getColumnId(), aliasName);
            }
        }

        // 删除逗号
        if (!field.isEmpty()) {
            field.deleteCharAt(field.length() - 1);
        }
    }

    protected StringBuilder mergeField(boolean singleSql, List<DatasetColumnQo> metrics, String dimensionType,
                                       List<DatasetColumnQo> dimensionList, Map<String, Map<String, String>> aliasMetric,
                                       Map<String, String> temTableAlias, boolean hasOrgTable, String tmpTbName, OrgDimension replaceLevelColumn,
                                       SqlFuncEnum funcEnum) {
        StringBuilder fieldSql = new StringBuilder();
        // 划小架构表最细粒度字段
        DatasetColumnQo orgDimColumn = SqlBuilderHelper.getOrgDimensionMinColumn(getDataPrivCtrlInfo());
        boolean haveOrgId = checkHaveOrgId();
        // 用于计算字段取表达式
        boolean hasPeriod = false;
        Map<String, StringBuilder> expMap = new HashMap<>();
        StringBuilder hivePeriodDim = new StringBuilder();
        // 不需要注释 的 数据源类型
        Set<String> ignoreNotesTypeSets = Set.of(KeyValues.DS_HIVE, KeyValues.DS_WHALEHOUSE);
        for (DatasetColumnQo dimension : dimensionList) {

            // 隐藏字段过滤
            if (dimension.isHide() && singleSql) {
                continue;
            }
            StringBuilder dimExp = new StringBuilder();
            // 单个sql的时候需要注释
            String notes = !ignoreNotesTypeSets.contains(getDbType()) && singleSql
                    ? SqlBuilderHelper.fieldNotes(dimension.getDataName())
                    : "";
            // 是否有拖到最细细度组织维度字段
            if (SqlBuilderHelper.checkDataPriv(getDataPrivCtrlInfo()) && !ObjectUtils.isEmpty(orgDimColumn)
                    && orgDimColumn.getColumnId().equals(dimension.getColumnId())) {
                haveOrgId(true);
                haveOrgId = true;
            }
            if (Constants.YES_VALUE_1.equals(dimension.getIsAcct())) {
                hasPeriod = true;
                // 账期字段,前端只能拖一个账期
                Column periodColumn = getPeriodColumnFromMetric(metrics.get(0), dimension);
                if (null != periodColumn) {
                    String tbName = SqlBuilderHelper.getAliasFromMetricOrTemp(metrics.get(0), aliasMetric,
                            temTableAlias, dimension);
                    String columnExp = tbName + SqlUtils.STR_POINT + periodColumn.getColumnCode();
                    // 如果是同比，环比，账期字段需要特殊处理
                    dimExp.append(this.getAcctColumnExp(columnExp, funcEnum, periodColumn));
                } else {
                    fieldPeriod(dimExp, dimension);
                }
                // hive数据源的账期字段抽取出来放到所有字段最后面
                if (getDbType().equals(KeyValues.DS_HIVE) && !"O".equals(this.scheduleType)) {
                    hivePeriodDim.append(dimExp).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                            .append(SqlUtils.STR_DOT);
                    continue;
                }
                fieldSql.append(dimExp).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                        .append(SqlUtils.STR_DOT);
            } else if (Constants.APP_TYPE_DIMENSION.equals(dimension.getAppType())) {
                // 维度字段
                String dimTableAlias = SqlBuilderHelper.getAliasFromMetricOrTemp(metrics.get(0), aliasMetric,
                        temTableAlias, dimension);
                // 最细层级字段替换
                if (autoLevelGroup && isLevelColumn(dimension)) {
                    if (dimension.getTableId().equals(replaceLevelColumn.getMetaDataId())
                            && (dimension.getColumnCode().equals(replaceLevelColumn.getOrgIdColumnCode())
                            || dimension.getColumnCode().equals(replaceLevelColumn.getOrgNameColumnCode()))) {
                        appendLevelColumnField(dimExp, replaceLevelColumn, dimTableAlias);
                    } else {
                        continue;
                    }
                    fieldSql.append(dimExp).append(SqlUtils.STR_DOT);
                } else {
                    dimExp.append(dimTableAlias).append(SqlUtils.STR_POINT).append(dimension.getColumnCode());
                    fieldSql.append(dimExp).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                            .append(SqlUtils.STR_DOT);
                }
            } else if (Constants.APP_TYPE_METRICS.equals(dimension.getAppType())
                    && dimensionType.equalsIgnoreCase(dimension.getDimensionType())
                    && CollectionUtils.isEmpty(dimension.getColumnGroup())) {
                List<String> funs = metrics.stream().map(DatasetColumnQo::getFunc).toList();
                List<Long> columnIds = metrics.stream().map(DatasetColumnQo::getColumnId).toList();
                List<String> columnCodes = metrics.stream().map(DatasetColumnQo::getColumnCode).toList();
                List<Long> tablesIds = metrics.stream().map(DatasetColumnQo::getTableId).toList();
                // 度量字段，没有的用0输出
                if (funs.contains(dimension.getFunc()) &&
                        columnIds.contains(dimension.getColumnId()) &&
                        columnCodes.contains(dimension.getColumnCode()) &&
                        tablesIds.contains(dimension.getTableId())) {
                    AbstractFuncParser parser = SqlBuilderFactory.getFuncParser(dimension);
                    parser.initParams(this, getDbType(), dimension, metrics, aliasMetric, temTableAlias);
                    parser.setOutField(dimExp);
                } else {
                    dimExp.append("0");
                }
                fieldSql.append(dimExp).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                        .append(SqlUtils.STR_DOT);
            } else if (Constants.APP_TYPE_METRICS.equals(dimension.getAppType())
                    && !dimensionType.equalsIgnoreCase(dimension.getDimensionType())
                    && CollectionUtils.isEmpty(dimension.getColumnGroup())) {
                dimExp.append("0");
                fieldSql.append(dimExp).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                        .append(SqlUtils.STR_DOT);
            } else if (singleSql && !CollectionUtils.isEmpty(dimension.getColumnGroup())) {
                String expression = SqlBuilderHelper.getColumnGroup(dimension.getColumnGroup(), expMap, null);
                String convertMetric = this.metricIfNull("(" + SqlConvertUtils.divisionConvert(expression) + ")",
                        dimension);
                dimExp.append(convertMetric);
                fieldSql.append(dimExp).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                        .append(SqlUtils.STR_DOT);
            }
            expMap.put(dimension.getAlias(), dimExp);
        }
        // hive的账期维度放后面
        if (!hivePeriodDim.isEmpty()) {
            fieldSql.append(hivePeriodDim);
        }
        // 权限控制
        if (SqlBuilderHelper.checkDataPriv(getDataPrivCtrlInfo())) {
            if (hasOrgTable) {
                String privPathKey = metrics.get(0).getDataPrivPathKey();
                if (StringUtils.isBlank(tmpTbName) && !ObjectUtils.isEmpty(orgDimColumn)) {
                    StringBuilder orgStr = new StringBuilder();
                    // 有组织维表，且所有表是被抽到了临时表了
                    MetricsDimensionPathVo orgDetailsSrc = getDataPrivCtrlInfo().getOrgInfoPathsMap()
                            .get(orgDimColumn.getTableId());
                    Long detailsTableId = orgDetailsSrc.getTgtTableId();
                    Map<String, String> mapAlias = aliasMetric.get(privPathKey);
                    String dimAlias = mapAlias.get(String.valueOf(orgDimColumn.getTableId()));
                    String detailsAlias = mapAlias.get(String.valueOf(detailsTableId));
                    if (!haveOrgId) {
                        orgStr.append(dimAlias).append(SqlUtils.STR_POINT).append(orgDimColumn.getColumnCode())
                                .append(SqlUtils.SQL_AS).append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.STR_DOT);
                    }
                    orgStr.append(detailsAlias).append(SqlUtils.STR_POINT).append(getOrgDetailsPathCode())
                            .append(SqlUtils.SQL_AS).append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.STR_DOT);
                    fieldSql.insert(0, orgStr);
                } else {
                    String aliasName = temTableAlias.get(privPathKey);
                    String orgPathCode = aliasName + SqlUtils.STR_POINT + CUSTOM_PATH_CODE_ALIAS + SqlUtils.SQL_AS
                            + CUSTOM_PATH_CODE_ALIAS + SqlUtils.STR_DOT;
                    fieldSql.insert(0, orgPathCode);

                    if (!haveOrgId) {
                        StringBuilder orgStr = new StringBuilder();

                        orgStr.append(aliasName).append(SqlUtils.STR_POINT).append(CUSTOM_ORG_ID_ALIAS)
                                .append(SqlUtils.STR_DOT);
                        fieldSql.insert(0, orgStr);
                    }
                }
            } else {
                StringBuilder privStr = new StringBuilder();
                privStr.append("'").append(OrganizationUtil.getOrgIdByUserId(params.getCreator())).append("'")
                        .append(SqlUtils.SQL_AS).append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.STR_DOT);
                privStr.append("'").append(getDefOrgPathCode()).append("'").append(SqlUtils.SQL_AS)
                        .append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.STR_DOT);
                fieldSql.insert(0, privStr);
            }
        }

        // 没有配置账期维度的调度sql且只有一个sql
        buildSingleTaskSql(singleSql, hasPeriod, fieldSql);

        return fieldSql;
    }

    // 抽取 没有配置账期维度 && sqlMode 为调度类型 && 单SQL
    protected void buildSingleTaskSql(boolean singleSql, boolean hasPeriod, StringBuilder fieldSql) {
        if (singleSql &&
                Constants.SQL_TASK.equals(this.sqlMode) &&
                !hasPeriod &&
                !"O".equalsIgnoreCase(this.scheduleType)) {
            // 没有账期
            String periodStr;
            if ("D".equals(this.scheduleType)) {
                periodStr = "${day_id} as day_id,";
            } else {
                periodStr = "${month_id} as month_id,";
            }

            if (getDbType().equals(KeyValues.DS_HIVE)) {
                fieldSql.append(periodStr);
            } else {
                fieldSql.insert(0, periodStr);
            }
        }

        // 删掉逗号
        if (BuildSqlUtil.sbIsNotEmpty(fieldSql)) {
            fieldSql.deleteCharAt(fieldSql.length() - 1);
        }
    }

    protected void appendLevelColumnField(StringBuilder fieldSql, OrgDimension replaceLevelColumn, String tbAlias) {
        // area_id
        if (replaceLevelColumn.isAlias()) {
            fieldSql.append(tbAlias).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_ID)
                    .append(SqlUtils.STR_DOT);
            // area_name
            fieldSql.append(tbAlias).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_NAME)
                    .append(SqlUtils.STR_DOT);
            // area_level
            fieldSql.append(tbAlias).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_LEVEL);
        } else {
            fieldSql.append(tbAlias).append(SqlUtils.STR_POINT).append(replaceLevelColumn.getOrgIdColumnCode())
                    .append(SqlUtils.SQL_AS).append(Constants.DATASET_AREA_ID).append(SqlUtils.STR_DOT);
            // area_name
            fieldSql.append(tbAlias).append(SqlUtils.STR_POINT).append(replaceLevelColumn.getOrgNameColumnCode())
                    .append(SqlUtils.SQL_AS).append(Constants.DATASET_AREA_NAME).append(SqlUtils.STR_DOT);
            // area_level
            fieldSql.append(replaceLevelColumn.getOrgLevel()).append(SqlUtils.SQL_AS)
                    .append(Constants.DATASET_AREA_LEVEL);
        }
    }

    /**
     * 从路径中的表获取账期字段
     *
     * @param metric    度量字段
     * @param dimension 账期维度
     * @return 返回账期字段
     */
    protected Column getPeriodColumnFromMetric(DatasetColumnQo metric, DatasetColumnQo dimension) {
        Column periodColumn = this.allPeriod.get(metric.getTableId());
        if (null != periodColumn && periodColumn.getCycleType().equals(dimension.getCycleType())) {
            return periodColumn;
        }
        return null;
    }

    /**
     * union all 的where条件拼接
     */
    protected abstract void appendWhere(boolean singleSql, StringBuilder sqlWhere, List<DatasetColumnQo> metrics,
                                        String dimensionType, List<DatasetConditionQo> condList, Map<String, Map<String, String>> aliasMap,
                                        Map<String, List<MetricsDimensionPathVo>> mainTbPaths, Map<String, String> temTableAlias, SqlFuncEnum funcEnum);

    /**
     * 临时表和度量合并的分组条件拼接
     *
     * @param metrics       度量参数
     * @param groupSql      分组sql参数
     * @param aliasMap      别名map
     * @param temTableAlias 临时表别名
     */
    protected void mergeGroupBy(List<DatasetColumnQo> metrics,
                                List<DatasetColumnQo> dimensionList,
                                StringBuilder groupSql,
                                Map<String, Map<String, String>> aliasMap,
                                Map<String, String> temTableAlias,
                                boolean hasOrgTable,
                                String tmpTbName,
                                OrgDimension replaceLevelColumn) {
        // 权限控制，划小架构维度字段id拼接。维度拖到了组织维度表的字段，但是不包含组织id
        boolean haveOrgId = checkHaveOrgId();
        DatasetColumnQo orgDimColumn = SqlBuilderHelper.getOrgDimensionMinColumn(getDataPrivCtrlInfo());

        List<DatasetColumnQo> dimensions = dimensionList.stream()
                .filter(entity -> Constants.APP_TYPE_DIMENSION.equals(entity.getAppType())).collect(Collectors.toList());
        for (DatasetColumnQo dimension : dimensions) {
            // 是否有拖到最细细度组织维度字段
            if (SqlBuilderHelper.checkDataPriv(getDataPrivCtrlInfo()) && !ObjectUtils.isEmpty(orgDimColumn)
                    && orgDimColumn.getColumnId().equals(dimension.getColumnId())) {
                haveOrgId(true);
                haveOrgId = true;
            }
            if (Constants.YES_VALUE_1.equals(dimension.getIsAcct())) {
                /*
                 * if (!"O".equals(this.scheduleType)) { // 周期性的账期维度不做分组 continue; }
                 */
                // 账期字段,前端只能拖一个虚拟的账期 账期字段出至于主干关联的表
                Column periodColumn = getPeriodColumnFromMetric(metrics.get(0), dimension);
                if (null != periodColumn) {
                    String tbName = SqlBuilderHelper.getAliasFromMetricOrTemp(metrics.get(0), aliasMap, temTableAlias,
                            dimension);
                    groupSql.append(tbName).append(SqlUtils.STR_POINT).append(periodColumn.getColumnCode())
                            .append(SqlUtils.STR_DOT);
                }
                // 如果没有账期字段就不用拼接进分组里面
            } else if (Constants.APP_TYPE_DIMENSION.equals(dimension.getAppType())) {
                String dimTableAlias = SqlBuilderHelper.getAliasFromMetricOrTemp(metrics.get(0), aliasMap,
                        temTableAlias, dimension);
                if (autoLevelGroup && isLevelColumn(dimension)) {
                    if (dimension.getTableId().equals(replaceLevelColumn.getMetaDataId())
                            && (dimension.getColumnCode().equals(replaceLevelColumn.getOrgIdColumnCode())
                            || dimension.getColumnCode().equals(replaceLevelColumn.getOrgNameColumnCode()))) {
                        appendLevelColumnGroup(groupSql, replaceLevelColumn, dimTableAlias);
                    }
                } else {
                    groupSql.append(dimTableAlias).append(SqlUtils.STR_POINT).append(dimension.getColumnCode())
                            .append(SqlUtils.STR_DOT);
                }
            }
        }
        if (SqlBuilderHelper.checkDataPriv(getDataPrivCtrlInfo())) {
            if (hasOrgTable && !ObjectUtils.isEmpty(orgDimColumn)) {
                String key = metrics.get(0).getDataPrivPathKey();
                String aliasName;
                // pathCode
                if (StringUtils.isBlank(tmpTbName)) {
                    // 没有临时表
                    Long orgTableId = orgDimColumn.getTableId();
                    MetricsDimensionPathVo orgDetailVo = getDataPrivCtrlInfo().getOrgInfoPathsMap().get(orgTableId);
                    Map<String, String> dimTbAlias = aliasMap.get(key);
                    aliasName = dimTbAlias.get(String.valueOf(orgDetailVo.getTgtTableId()));
                    String orgPathCode = aliasName + SqlUtils.STR_POINT + getOrgDetailsPathCode() + SqlUtils.STR_DOT;
                    groupSql.insert(0, orgPathCode);

                    // 组织id
                    if (!haveOrgId) {
                        aliasName = dimTbAlias.get(String.valueOf(orgDimColumn.getTableId()));
                        String orgId = aliasName + SqlUtils.STR_POINT + orgDimColumn.getColumnCode() + SqlUtils.STR_DOT;
                        groupSql.insert(0, orgId);
                    }
                } else {
                    aliasName = temTableAlias.get(key);
                    String orgPathCode = aliasName + SqlUtils.STR_POINT + CUSTOM_PATH_CODE_ALIAS + SqlUtils.STR_DOT;
                    groupSql.insert(0, orgPathCode);
                    if (!haveOrgId) {
                        String orgId = aliasName + SqlUtils.STR_POINT + CUSTOM_ORG_ID_ALIAS + SqlUtils.STR_DOT;
                        groupSql.insert(0, orgId);
                    }
                }
            }
        }
        if (!groupSql.isEmpty()) {
            groupSql.deleteCharAt(groupSql.length() - 1);
        }
    }

    protected void appendLevelColumnGroup(StringBuilder groupSql, OrgDimension replaceLevelColumn, String tbAlias) {
        StringBuilder levelGroupSql = new StringBuilder();
        levelGroupSql.append(tbAlias).append(SqlUtils.STR_POINT).append(replaceLevelColumn.getOrgIdColumnCode())
                .append(SqlUtils.STR_DOT);
        levelGroupSql.append(tbAlias).append(SqlUtils.STR_POINT).append(replaceLevelColumn.getOrgNameColumnCode())
                .append(SqlUtils.STR_DOT);
        // 避免重复
        if (groupSql.indexOf(levelGroupSql.toString()) == -1) {
            groupSql.append(levelGroupSql);
        }
    }

    protected String createTmpTable(String tmpTable, String sql) {
        if (KeyValues.DS_CLICKHOUSE.equalsIgnoreCase(this.getDbType())) {
            return "CREATE TABLE " + tmpTable + " ENGINE=" + EngineType.MEMORY + " AS " + sql;
        } else if (KeyValues.DS_WHALEHOUSE.equalsIgnoreCase(this.getDbType())) {
            return "CREATE VIEW " + tmpTable + " AS " + sql;
        } else {
            return "CREATE TABLE " + tmpTable + " AS " + sql;
        }
    }

    protected String getAlias(Map<String, Map<String, String>> aliasMap, Long id) {
        String tbName = null;
        for (Map.Entry<String, Map<String, String>> mapEntry : aliasMap.entrySet()) {
            tbName = mapEntry.getValue().get(String.valueOf(id));
            if (StringUtils.isNotBlank(tbName)) {
                break;
            }
        }
        return tbName;
    }

    protected String appendPrePeriod(String tbName,
                                     Column column,
                                     PeriodExpression condPeriodExp,
                                     SqlFuncEnum funcEnum) {
        String cond;
        // 如果sql输出类型的，且为动态条件，不需要拼引号 静态的可能需要拼引号
        if (OutPutMode.SQL.equalsIgnoreCase(outPutMode) && Constants.YES_VALUE_1.equals(condPeriodExp.getIsDynamic())) {
            condPeriodExp.setPeriodScope(condPeriodExp.getPeriodScope(), false);
        } else {
            // 字符型的账期需要加单引号
            condPeriodExp.setPeriodScope(condPeriodExp.getPeriodScope(),
                    SqlBuilderHelper.isStringType(getDbType(), column.getColumnType()));
        }

        if (condPeriodExp.getOperator().toUpperCase().contains("BETWEEN")) {
            // 动态账期条件
            String condOperator = condPeriodExp.getOperator().contains("_dynamic")
                    ? condPeriodExp.getOperator().replace("_dynamic", "")
                    : condPeriodExp.getOperator();
            cond = condOperator + SqlUtils.STR_BLANK + condPeriodExp.getPeriodScope().get(0) + SqlUtils.SQL_AND
                    + condPeriodExp.getPeriodScope().get(1);
        } else if (condPeriodExp.getOperator().toUpperCase().contains("IN")) {
            cond = condPeriodExp.getOperator() + SqlUtils.STR_LEFT_BRACKET
                    + String.join(",", condPeriodExp.getPeriodScope()) + SqlUtils.STR_RIGHT_BRACKET;
        } else if ("EXPRESSION".equalsIgnoreCase(condPeriodExp.getOperator())) {
            cond = condPeriodExp.getPeriodScope().get(0);
        } else if ("SQL".equalsIgnoreCase(condPeriodExp.getOperator())) {
            cond = SqlUtils.STR_LEFT_BRACKET + condPeriodExp.getPeriodScope().get(0) + SqlUtils.STR_RIGHT_BRACKET;
        } else {
            cond = condPeriodExp.getOperator() + condPeriodExp.getPeriodScope().get(0);
        }
        if ("SQL".equalsIgnoreCase(condPeriodExp.getOperator())) {
            // SQL类型不需要拼接字段
            log.info("SQL类型条件：{}", condPeriodExp.getPeriodScope().get(0));
            return SqlUtils.STR_BLANK + cond;
        }
        String columnExp = tbName + SqlUtils.STR_POINT + column.getColumnCode();
        return this.getAcctColumnExp(columnExp, funcEnum, column) + SqlUtils.STR_BLANK + cond;
    }

    /**
     * 读取度量上的账期条件
     *
     * @param metrics       度量
     * @param dimensionType 度量类型
     */
    protected List<PeriodExpression> getPeriodExpressionFromMetrics(List<DatasetColumnQo> metrics,
                                                                    List<DatasetConditionQo> acctConds, String dimensionType) {
        List<PeriodExpression> list = new ArrayList<>();
        for (DatasetColumnQo metric : metrics) {
            if (!dimensionType.equalsIgnoreCase(metric.getDimensionType())) {
                continue;
            }
            if (!CollectionUtils.isEmpty(metric.getCondList())) {
                List<DatasetConditionQo> collect = metric.getCondList().stream()
                        .filter(obj -> KeyValues.YES_VALUE_1.equals(obj.getIsAcct())).toList();
                if (!collect.isEmpty()) {
                    DatasetConditionQo conditionQo = collect.get(0);
                    PeriodExpression exp = new PeriodExpression();
                    exp.setCycleType(conditionQo.getCycleType());
                    exp.setIsDynamic(conditionQo.getIsDynamic());
                    exp.setOperator(conditionQo.getCondOperator());
                    String[] split = conditionQo.getCondValue().contains("~") ? conditionQo.getCondValue().split("~")
                            : conditionQo.getCondValue().split(",");
                    exp.setPeriodScope(new LinkedList<>(Arrays.asList(split)));
                    list.add(exp);
                }
            }
            // 全局账期条件没有，则需要为度量添加默认账期条件
            else if (CollectionUtils.isEmpty(acctConds)) {
                // 增加默认账期条件
                PeriodExpression exp = new PeriodExpression();
                exp.setCycleType(metric.getCycleType());
                exp.setOperator("=");
                exp.setIsDynamic(Constants.YES_VALUE_1);
                String period = StringUtils.isNotEmpty(metric.getFunc())
                        ? SqlBuilderFactory.getFuncParser(metric.getFunc()).getDateOffset(metric.getCycleType())
                        : AcctTimeUtil.getAcctValOutPubMode(this.scheduleType, metric.getCycleType(), outPutMode);
                exp.setPeriodScope(new LinkedList<>(Collections.singletonList(period)));
                list.add(exp);
            }
        }
        return list;
    }

    /**
     * 临时表名称生成
     *
     * @return 临时表名
     */
    protected synchronized String generateSubTmpTableName(String cycleType) {
        // 任意获取一个库名？
        String tmpTable = this.schemaMap.entrySet().iterator().next().getValue() + SqlUtils.STR_POINT;
        // 一次性临时表不需要加账期
        String subfix = Constants.SCHEDULE_LOOP_TYPE_O.equalsIgnoreCase(cycleType) ? "" : "_${acct}";
        // oracle表名不能长于30，生成8位随机码
        tmpTable = (tmpTable + "tmp_".concat(String.valueOf(RandomUtil.getSecureRandom().nextInt(90000000) + 10000000)).concat("_")
                .concat(String.valueOf(getIncrementTbIndex())).concat(subfix));
        return tmpTable;
    }

    /**
     * 检查分组度量里面是否有同比、环比和度量的账期条件
     *
     * @return 从表on条件上是否需要拼接账期字段
     */
    public boolean needSlaveTablePeriod(List<DatasetColumnQo> metrics, List<DatasetColumnQo> columnList) {
        for (DatasetColumnQo columnQo : columnList) {
            // 有账期作维度
            if (Constants.APP_TYPE_DIMENSION.equalsIgnoreCase(columnQo.getAppType()) &&
                    KeyValues.YES_VALUE_1.equals(columnQo.getIsAcct())) {
                return false;
            }
        }

        for (DatasetColumnQo metric : metrics) {
            List<DatasetConditionQo> metricCond = metric.getCondList();
            // 度量上面有账期条件
            if (CollUtil.isNotEmpty(metricCond)) {
                List<DatasetConditionQo> collect = metricCond.stream()
                        .filter(obj -> KeyValues.YES_VALUE_1.equals(obj.getIsAcct())).toList();
                if (CollUtil.isNotEmpty(collect)) {
                    return false;
                }
            }
            // 是否有同环比或者年月累计 有的话 返回 false
            if (SqlBuilderHelper.isGrowthOrTotal(metric.getFunc())) {
                return false;
            }
        }
        return true;
    }

    protected boolean isLevelColumn(DatasetColumnQo dimension) {
        Integer isOrgDimension = dimension.getIsOrgDimension();
        Integer orgLevel = dimension.getOrgLevel();
        return autoLevelGroup && isOrgDimension != null && isOrgDimension > 0 && orgLevel != null && orgLevel > 0;
    }

    protected String replaceValues(DatasetConditionQo qo, Column periodColumn) {
        String[] splitArray;
        if (qo.getCondValue().contains("~")) {
            splitArray = qo.getCondValue().split("~");
        } else {
            splitArray = qo.getCondValue().split(",");
        }
        boolean isStr = SqlBuilderHelper.isStringType(getDbType(), periodColumn.getColumnType());
        for (int i = 0; i < splitArray.length; i++) {
            String str = splitArray[i];
            splitArray[i] = isStr ? "'" + str + "'" : str;
        }
        String condOperator = qo.getCondOperator();
        if (condOperator.startsWith("BETWEEN")) {
            qo.setCondOperator("BETWEEN");
        } else if ("IN".equalsIgnoreCase(condOperator)) {
            return "(" + StringUtils.join(",", splitArray) + ")";
        }
        return StringUtils.join(" and ", splitArray);
    }

    /**
     * 字段关联条件on
     */
    protected void appendKeyColumns(List<ObjKeyColumnRelaVo> relaColumns, StringBuilder exp, String srcTableAlias,
                                    String tgtTableAlias) {
        if (CollectionUtils.isEmpty(relaColumns)) {
            log.error("表关联关系为空，请检查 ");
            return;
        }
        for (ObjKeyColumnRelaVo relaColumn : relaColumns) {
            String columnCode = srcTableAlias + SqlUtils.STR_POINT + relaColumn.getColumnCode();
            String relaColumnCode = tgtTableAlias + SqlUtils.STR_POINT + relaColumn.getRelaColumnCode();
            // 关联字段加工
            if (StringUtils.isNotEmpty(relaColumn.getFunc())) {
                columnCode = relaColumn.getFunc().replaceAll("\\$", columnCode);
            }
            if (StringUtils.isNotEmpty(relaColumn.getRelaFunc())) {
                relaColumnCode = relaColumn.getRelaFunc().replaceAll("\\$", relaColumnCode);
            }
            exp.append(SqlUtils.STR_BLANK).append(columnCode).append(SqlUtils.STR_EQUAL).append(relaColumnCode)
                    .append(" and");
        }
        exp.delete(exp.lastIndexOf("and"), exp.length());
    }

    /**
     * 是对维度分组
     *
     * @param metrics       度量
     * @param dimensionList 所有维度
     * @return 分组维度
     */
    protected abstract Map<List<String>, List<DatasetColumnQo>> getRelativeDimensionGroup(List<DatasetColumnQo> metrics,
                                                                                          List<DatasetColumnQo> dimensionList);

    /**
     * 主视图与相对维度视图合并
     */
    protected abstract String mergeRelativeDims(boolean mergeRelativeDims,
                                                List<DatasetColumnQo> dimensionList,
                                                String mainSql,
                                                List<SubQuerySqlQo> subSqlList,
                                                OrgDimension replaceLevelColumn,
                                                ResultSql result);

    protected abstract String getDefOrgPathCode();

    protected abstract String getOrgDetailsPathCode();

    protected abstract boolean needAppendPathCode(Long tableId);

    /**
     * 是否月和日账期混用
     *
     * @return 是/否
     */
    protected abstract boolean checkMixed();

    /**
     * 获取表别名索引
     *
     * @return 表索引值
     */
    protected abstract int getIncrementTbIndex();

    /**
     * 获取度量
     *
     * @return 所有度量参数
     */
    protected abstract List<DatasetColumnQo> getMetrics();

    /**
     * 标记有组织字段
     *
     * @param have 设置是否有组织Id标识
     */
    protected abstract void haveOrgId(boolean have);

    /**
     * 是否有组织字段
     *
     * @return 获取是否有组织标识
     */
    protected abstract boolean checkHaveOrgId();

    protected abstract void appendTableSql(boolean isPriv,
                                           Map.Entry<String, List<MetricsDimensionPathVo>> entry,
                                           Map<Long, String> hasAppend,
                                           boolean needAppendPeriod,
                                           boolean hasOrgTable,
                                           Map<String, String> tableAlias,
                                           SqlComponent component);

    /**
     * 获取权限相关参数
     *
     * @return 权限信息
     */
    protected abstract DataPrivCtrlVo getDataPrivCtrlInfo();

    /**
     * 获取数据源类型
     *
     * @return 数据源类型
     */
    public abstract String getDbType();

    public abstract void appendOrgDetailsTable(boolean hasOrgTable, SqlComponent component, Map<String, String> map,
                                               Map<Long, String> hasAppend, String tbPrefix, MetricsDimensionPathVo path, boolean appendOrgDetailsTable);

    public abstract DatasetColumnQo minLevelColumn(List<DatasetColumnQo> dimensions);

    public abstract DatasetColumnQo maxLevelColumn(List<DatasetColumnQo> dimensions);

    protected abstract void fieldPeriod(StringBuilder fieldSql, DatasetColumnQo dimension);

    protected abstract void hasAutoLevel(List<DatasetColumnQo> dimensions);

    protected abstract String metricIfNull(String metric, DatasetColumnQo columnQo);

    protected abstract void orderByColumnList(StringBuilder result, List<DatasetColumnQo> dimensionList,
                                              List<DatasetColumnQo> metrics, Map<String, Map<String, String>> alias);

    protected abstract List<DatasetColumnQo> checkGrowthOrTotal(List<DatasetColumnQo> metrics, String dimensionType);

    protected abstract void subScheduleGrowthOrTotal(List<SubQuerySqlQo> subSqlList, boolean singleSql,
                                                     List<DatasetColumnQo> metricList, String dimensionType, List<DatasetColumnQo> dimensionList,
                                                     List<DatasetConditionQo> condList, ResultSql result, Map<String, List<MetricsDimensionPathVo>> cacheTempPath,
                                                     OrgDimension replaceLevelColumn);

    protected abstract String getDefultPeriod(Long tableId, Map<String, String> tableAlias);

    protected abstract void periodDistinct(PeriodExpression condPeriodExp,
                                           List<PeriodExpression> periodExpressionFromMetrics);

    public abstract String castColumnType(Column column, String columnExp, String columnType);

    public abstract String castColumnType(String columnExp, String columnType);

    public abstract String getDateOffset(String cycleType, String col, Integer offset, String type);

    public abstract String getAcctColumnExp(String columnExp, SqlFuncEnum funcEnum, Column periodColumn);
}