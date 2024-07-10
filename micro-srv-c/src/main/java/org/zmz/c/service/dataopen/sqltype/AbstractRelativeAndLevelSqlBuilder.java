package org.zmz.c.service.dataopen.sqltype;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetConditionQo;
import org.zmz.c.qo.dataopen.MetricsDimensionPathVo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.qo.dataopen.ObjKeyColumnRelaVo;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaVo;
import org.zmz.c.qo.dataopen.OrgDimension;
import org.zmz.c.qo.dataopen.OutPutMode;
import org.zmz.c.qo.dataopen.SubQuerySqlQo;
import org.zmz.c.service.dataopen.dataset.SqlComponent;
import org.zmz.c.service.dataopen.dataset.SqlFuncEnum;
import org.zmz.c.service.dataopen.sql.AbstractSqlParser;
import org.zmz.c.service.dataopen.sql.SqlParserFactory;
import org.zmz.c.service.dataopen.sqlfunc.AbstractFuncParser;
import org.zmz.c.service.dataopen.sqlfunc.PeriodExpression;
import org.zmz.c.service.dataopen.sqlfunc.SqlBuilderFactory;
import org.zmz.c.utils.AcctTimeUtil;
import org.zmz.c.utils.BuildSqlUtil;
import org.zmz.c.utils.DealConditionParamUtils;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.OrganizationUtil;
import org.zmz.c.utils.SqlConvertUtils;
import org.zmz.c.utils.SqlUtils;
import org.zmz.c.vo.dataopen.dataset.ResultSql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author fyh
 * {@code @date} 2023-03-16 21:05
 * {@code @description} 相对维度和层级字段
 */
public abstract class AbstractRelativeAndLevelSqlBuilder extends AbstractGrowthOrTotalSqlBuilder {

    /**
     * 主视图分别与子查询拼接(即有相对维度的度量和同环比(月/年累计))
     *
     * @param result             返回sql集
     * @param metrics            分组后的度量
     * @param columnList         输出字段
     * @param replaceLevelColumn 层级字段替换
     */
    protected void appendRelativeMetric(ResultSql result,
                                        List<DatasetColumnQo> metrics,
                                        List<DatasetColumnQo> columnList,
                                        List<DatasetConditionQo> condList,
                                        OrgDimension replaceLevelColumn) {
        // 检查分组度量里面是否有同比、环比和度量的账期条件
        boolean mainSlaveTabPeriod = needSlaveTablePeriod(metrics, columnList);
        // 以相对维度字段进行分组 目前最多两组(包含/include,排除/exclude),后续可能冗余fix类型
        Map<List<String>, List<DatasetColumnQo>> dimGroup = getRelativeDimensionGroup(metrics, columnList);
        // 是否单条 SQL ,只有层级字段等于1个和度量分组等于1个的时候 isSingle=true
        boolean isSingleSql = (dimGroup.isEmpty() && result.isSingle);
        // 主视图sql(可能没有度量)
        String mainSql = appendSqlConvert(isSingleSql, metrics,
                Constants.DimensionType.TYPE_MAIN, columnList, condList, mainSlaveTabPeriod, replaceLevelColumn, result);
        // 相对维度子查询
        if (MapUtil.isNotEmpty(dimGroup)) {
            List<SubQuerySqlQo> subSqlList = new ArrayList<>();
            for (Map.Entry<List<String>, List<DatasetColumnQo>> dimGroupEntry : dimGroup.entrySet()) {
                String dimensionType = dimGroupEntry.getValue().get(0).getDimensionType();
                // 相对维度
                List<DatasetColumnQo> dimensionList = columnList.stream()
                        .filter(t -> dimGroupEntry.getKey().contains(t.getAlias())).collect(Collectors.toList());
                // 相对度量(可能多个相对度量相同维度)
                List<DatasetColumnQo> relativeMetrics = dimGroupEntry.getValue();
                boolean subSlaveTabPeriod = needSlaveTablePeriod(relativeMetrics, dimensionList);
                String subSql = appendSqlConvert(false, relativeMetrics, dimensionType, dimensionList, condList,
                        subSlaveTabPeriod, replaceLevelColumn, result);
                SubQuerySqlQo relativeDimension = new SubQuerySqlQo();
                relativeDimension.setDimensionList(dimensionList);
                relativeDimension.setSql(subSql);
                relativeDimension.setMetricList(relativeMetrics);
                relativeDimension.setDimensionType(dimensionType);
                subSqlList.add(relativeDimension);
            }
            result.sqlLists.add(mergeRelativeDims(result.isSingle, columnList, mainSql, subSqlList, replaceLevelColumn,
                    subQueryToTmTab ? result : null));
        } else {
            result.sqlLists.add(mainSql);
        }
    }

    /**
     * 获取配置向上汇总的层级字段
     */
    @Override
    public DatasetColumnQo minLevelColumn(List<DatasetColumnQo> dimensions) {
        List<DatasetColumnQo> orgDimensions = new ArrayList<>();
        if (CollUtil.isNotEmpty(dimensions)) {
            orgDimensions = dimensions.stream().filter(t -> {
                Integer isOrgDimension = t.getIsOrgDimension();
                Integer orgLevel = t.getOrgLevel();
                return isOrgDimension != null && isOrgDimension > 0 && orgLevel != null && orgLevel >= 0;
            }).toList();
        }
        if (CollUtil.isNotEmpty(dimensions)) {
            return Collections.min(orgDimensions, Comparator.comparingInt(DatasetColumnQo::getOrgLevel));
        }
        return null;
    }

    /**
     * 获取配置向上汇总的层级字段
     */
    @Override
    public DatasetColumnQo maxLevelColumn(List<DatasetColumnQo> dimensions) {
        for (DatasetColumnQo dimension : dimensions) {
            Integer isOrgDimension = dimension.getIsOrgDimension();
            if (isOrgDimension != null &&
                    isOrgDimension > 0 &&
                    KeyValues.YES_VALUE_1.equals(dimension.getAutoLevelGroup())) {
                return dimension;
            }
        }
        return null;
    }

    protected OrgDimension getOrgLevelDimension(DatasetColumnQo maxLevelColumn) {
        List<OrgDimension> orgDimensions = iteratorColumnMap.get(maxLevelColumn.getTableId());
        return orgDimensions.stream().filter(f -> {
            return f.getMetaDataId().equals(maxLevelColumn.getTableId())
                    && (f.getOrgIdColumnCode().equals(maxLevelColumn.getColumnCode())
                    || f.getOrgNameColumnCode().equals(maxLevelColumn.getColumnCode()));
        }).findFirst().orElse(null);
    }

    /**
     * 检查维度字段是否有层级字段配置自动向上汇总配置
     *
     * @param dimensions 维度字段
     */
    @Override
    public void hasAutoLevel(List<DatasetColumnQo> dimensions) {
        for (DatasetColumnQo dimension : dimensions) {
            Integer isOrgDimension = dimension.getIsOrgDimension();
            if (isOrgDimension != null &&
                    isOrgDimension > 0 &&
                    KeyValues.YES_VALUE_1.equals(dimension.getAutoLevelGroup())) {
                autoLevelGroup = true;
                break;
            }
        }

        // autoLevelGroup 存在 层级字段配置了自动向上汇总
        if (autoLevelGroup) {
            Map<Long, ModelInfo> allModelInfo = this.modelInfoMap;
            DatasetColumnQo minLevelColumn = minLevelColumn(dimensions);
            DatasetColumnQo currLevelColumn = maxLevelColumn(dimensions);
            List<DatasetColumnQo> filterDimensions = dimensions.stream().filter(t -> {
                Integer isOrgDimension = t.getIsOrgDimension();
                Integer orgLevel = t.getOrgLevel();
                return isOrgDimension != null &&
                        isOrgDimension > 0 &&
                        orgLevel != null &&
                        orgLevel > 0 &&
                        allModelInfo.get(t.getTableId()) != null;
            }).toList();
            // 遍历找出要替换的层级字段(层级小于等于配置向上汇总的层级字段)
            for (DatasetColumnQo dimension : filterDimensions) {
                String columnCode = dimension.getColumnCode();
                Long tableId = dimension.getTableId();
                List<OrgDimension> orgDimensionList = allModelInfo.get(tableId)
                        .getBussinessAttr().getOrgDimensionList();

                if (CollUtil.isNotEmpty(orgDimensionList)) {
                    orgDimensionList = orgDimensionList.stream().filter(t -> {
                        String orgLevel = t.getOrgLevel();
                        return (Integer.parseInt(orgLevel) <= currLevelColumn.getOrgLevel()
                                && Integer.parseInt(orgLevel) >= minLevelColumn.getOrgLevel());
                    }).collect(Collectors.toList());
                }

                // 单表模型配置多层级字段的去掉没选到的字段
                if (CollUtil.isNotEmpty(orgDimensionList)) {
                    // 根据 orgLevel 倒序排列
                    orgDimensionList.sort((o1, o2) -> Integer.parseInt(o2.getOrgLevel()) - Integer.parseInt(o1.getOrgLevel()));
                    for (OrgDimension t : orgDimensionList) {
                        if (t.getOrgIdColumnCode().equals(columnCode) ||
                                t.getOrgNameColumnCode().equals(columnCode)) {
                            t.setMetaDataId(tableId);
                            iteratorColumnMap.put(tableId, orgDimensionList);
                        }
                    }
                }
            }
        }
    }

    /**
     * 是对维度分组
     *
     * @param metrics       度量
     * @param dimensionList 所有维度
     * @return 分组维度
     */
    @Override
    protected Map<List<String>, List<DatasetColumnQo>> getRelativeDimensionGroup(List<DatasetColumnQo> metrics,
                                                                                 List<DatasetColumnQo> dimensionList) {
        List<DatasetColumnQo> collectFilter = metrics.stream().filter(t -> {
            return Constants.APP_TYPE_METRICS.equalsIgnoreCase(t.getAppType())
                    && StringUtils.isNotBlank(t.getDimensionType())
                    && !Constants.DimensionType.TYPE_MAIN.equals(t.getDimensionType());
        }).toList();
        if (!CollectionUtils.isEmpty(collectFilter)) {
            List<String> metricsAlias = metrics.stream().map(DatasetColumnQo::getAlias).toList();
            return collectFilter.stream()
                    .collect(Collectors.groupingBy(t -> {
                        List<String> aliasList = Arrays.asList(t.getColumnExpression().split(","));
                        return dimensionList.stream().filter(c -> {
                            if (metricsAlias.contains(c.getAlias())) {
                                return true;
                            } else if (t.getDimensionType().equalsIgnoreCase(Constants.DimensionType.TYPE_INCLUDE)) {
                                return aliasList.contains(c.getAlias());
                            } else if (t.getDimensionType().equalsIgnoreCase(Constants.DimensionType.TYPE_EXCLUDE)) {
                                return !aliasList.contains(c.getAlias());
                            }
                            return false;
                        }).map(DatasetColumnQo::getAlias).collect(Collectors.toList());
                    }));
        }
        return new HashMap<>();
    }

    /**
     * 主视图与相对维度视图合并
     *
     * @param dimensionList 维度
     * @param mainSql       主视图sql
     * @param subSqlList    相对视图的sql集合
     * @return 组装的sql
     */
    @Override
    protected String mergeRelativeDims(boolean singleSql, List<DatasetColumnQo> dimensionList, String mainSql,
                                       List<SubQuerySqlQo> subSqlList, OrgDimension replaceLevelColumn, ResultSql result) {
        SqlComponent component = new SqlComponent();
        String mainTb = "tb" + getIncrementTbIndex();
        // 主视图sql
        String tmpName;
        if (result != null && !CollectionUtils.isEmpty(subSqlList)) {
            tmpName = generateSubTmpTableName(scheduleType);
            // 区分中间表，需要存放数据，因为LEFT JOIN拆分，需要创建临时表
            result.tmpTableNames.put(tmpName, tmpName);
            result.tmpTableSql.put(tmpName, mainSql);
            component.join.append(tmpName).append(SqlUtils.STR_BLANK).append(mainTb);
        } else {
            component.join.append(SqlUtils.STR_LEFT_BRACKET).append(mainSql).append(SqlUtils.STR_RIGHT_BRACKET)
                    .append(mainTb);
        }
        // 相对视图从表sql拼接
        for (SubQuerySqlQo subQuerySqlQo : subSqlList) {
            String relativeTb = "tb" + getIncrementTbIndex();
            subQuerySqlQo.setTbAlisa(relativeTb);
            if (result != null) {
                tmpName = generateSubTmpTableName(scheduleType);
                // 区分中间表，需要存放数据，因为LEFT JOIN拆分，需要创建临时表
                result.tmpTableNames.put(tmpName, tmpName);
                result.tmpTableSql.put(tmpName, subQuerySqlQo.getSql());
                component.join.append(SqlUtils.SQL_LEFT_JOIN).append(SqlUtils.STR_BLANK).append(tmpName)
                        .append(SqlUtils.STR_BLANK).append(relativeTb).append(SqlUtils.SQL_ON);
            } else {
                component.join.append(SqlUtils.SQL_LEFT_JOIN).append(SqlUtils.STR_LEFT_BRACKET)
                        .append(subQuerySqlQo.getSql()).append(SqlUtils.STR_RIGHT_BRACKET).append(relativeTb)
                        .append(SqlUtils.SQL_ON);
            }

            // on关联条件
            StringBuilder on = new StringBuilder();
            // 组织维度字段
            appendPathCode(on, mainTb, relativeTb, dimensionList);
            // 层级字段
            if (null != replaceLevelColumn) {
                if (!on.isEmpty()) {
                    on.append(SqlUtils.SQL_AND);
                }
                on.append(mainTb).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_ID)
                        .append(SqlUtils.STR_EQUAL).append(relativeTb).append(SqlUtils.STR_POINT)
                        .append(Constants.DATASET_AREA_ID).append(SqlUtils.SQL_AND).append(mainTb)
                        .append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_NAME).append(SqlUtils.STR_EQUAL)
                        .append(relativeTb).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_NAME);
            }
            List<DatasetColumnQo> relativeDimensionLists = subQuerySqlQo.getDimensionList().stream()
                    .filter(entity -> Constants.APP_TYPE_DIMENSION.equals(entity.getAppType()))
                    .toList();
            for (DatasetColumnQo dimensionQo : relativeDimensionLists) {
                if (autoLevelGroup && isLevelColumn(dimensionQo)) {
                    // 汇总字段不需要重复拼到on条件
                    continue;
                }
                if (!on.isEmpty()) {
                    on.append(SqlUtils.SQL_AND);
                }
                on.append(mainTb).append(SqlUtils.STR_POINT).append(dimensionQo.getAlias()).append(SqlUtils.STR_EQUAL)
                        .append(relativeTb).append(SqlUtils.STR_POINT).append(dimensionQo.getAlias());
            }
            if (StringUtils.isEmpty(on)) {
                on.append(" 1=1 ");
            }
            component.join.append(on);
        }
        mergeRelativeOutField(singleSql, mainTb, component.field, dimensionList, subSqlList, replaceLevelColumn);
        return component.swapSql().toString();
    }

    private void appendPathCode(StringBuilder sql,
                                String mainTb,
                                String relativeTb,
                                List<DatasetColumnQo> dimensionList) {
        boolean hasOrgId = false;
        DatasetColumnQo orgDimColumn = SqlBuilderHelper.getOrgDimensionMinColumn(getDataPrivCtrlInfo());
        for (DatasetColumnQo dimension : dimensionList) {
            // 是否有拖到最细细度组织维度字段
            if (!ObjectUtils.isEmpty(orgDimColumn) && orgDimColumn.getColumnId().equals(dimension.getColumnId())) {
                hasOrgId = true;
            }
        }
        if (SqlBuilderHelper.checkDataPriv(getDataPrivCtrlInfo())) {
            // 为空、则没有拖到组织维度表的字段
            if (!ObjectUtils.isEmpty(orgDimColumn) && !hasOrgId) {
                sql.append(mainTb).append(SqlUtils.STR_POINT).append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.STR_EQUAL)
                        .append(relativeTb).append(SqlUtils.STR_POINT).append(CUSTOM_ORG_ID_ALIAS);
            }
            if (!ObjectUtils.isEmpty(orgDimColumn)) {
                if (!sql.isEmpty()) {
                    sql.append(SqlUtils.SQL_AND);
                }
                sql.append(mainTb).append(SqlUtils.STR_POINT).append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.STR_EQUAL)
                        .append(relativeTb).append(SqlUtils.STR_POINT).append(CUSTOM_PATH_CODE_ALIAS);
            }
        }
    }

    private void mergeRelativeOutField(boolean singleSql,
                                       String mainTb,
                                       StringBuilder sql,
                                       List<DatasetColumnQo> dimensionList,
                                       List<SubQuerySqlQo> subSqlList,
                                       OrgDimension replaceLevelColumn) {
        StringBuilder fields = new StringBuilder();
        // 划小架构表最细粒度字段
        DatasetColumnQo orgDimColumn = SqlBuilderHelper.getOrgDimensionMinColumn(getDataPrivCtrlInfo());

        DatasetColumnQo metric;
        if (!CollectionUtils.isEmpty(subSqlList)) {
            metric = subSqlList.get(0).getMetricList().get(0);
        } else {
            // subSqlList为空，临时fix
            metric = dimensionList.stream().filter(col -> Constants.APP_TYPE_METRICS.equals(col.getAppType()))
                    .findFirst().orElse(dimensionList.get(0));
        }
        boolean hasPeriod = false;
        // 用于计算字段取表达式
        Map<String, StringBuilder> expMap = new HashMap<>();
        StringBuilder hivePeriodDim = new StringBuilder();
        // 不需要注释 的 数据源类型
        Set<String> ignoreNotesTypeSets = Set.of(KeyValues.DS_HIVE, KeyValues.DS_WHALEHOUSE);
        for (DatasetColumnQo dimension : dimensionList) {
            // 隐藏字段过滤
            StringBuilder dimSb = new StringBuilder();
            String dbType = getDbType();
            // 单个sql的时候需要注释
            String notes = !ignoreNotesTypeSets.contains(dbType) && singleSql
                    ? SqlBuilderHelper.fieldNotes(dimension.getDataName())
                    : "";
            // 是否有拖到最细细度组织维度字段
            if (!ObjectUtils.isEmpty(orgDimColumn) && orgDimColumn.getColumnId().equals(dimension.getColumnId())) {
                haveOrgId(true);
            }
            if (Constants.YES_VALUE_1.equals(dimension.getIsAcct())) {
                hasPeriod = true;
                // 账期字段,前端只能拖一个账期
                Column periodColumn = getPeriodColumnFromMetric(metric, dimension);
                if (null != periodColumn) {
                    dimSb.append(mainTb).append(SqlUtils.STR_POINT).append(dimension.getAlias());
                } else {
                    fieldPeriod(dimSb, dimension);
                }
                // 缓存
                expMap.put(dimension.getAlias(), dimSb);
                // hive数据源的账期字段抽取出来放到所有字段最后面
                if (getDbType().equals(KeyValues.DS_HIVE) && !"O".equals(this.scheduleType)) {
                    hivePeriodDim.append(dimSb)
                            .append(SqlUtils.SQL_AS)
                            .append(dimension.getAlias())
                            .append(SqlUtils.STR_DOT);
                    continue;
                }
                fields.append(dimSb)
                        .append(SqlUtils.SQL_AS)
                        .append(dimension.getAlias())
                        .append(notes)
                        .append(SqlUtils.STR_DOT);
            } else if (Constants.APP_TYPE_DIMENSION.equals(dimension.getAppType())) {
                // 维度字段
                if (autoLevelGroup && isLevelColumn(dimension)) {
                    if (dimension.getTableId().equals(replaceLevelColumn.getMetaDataId())
                            && (dimension.getColumnCode().equals(replaceLevelColumn.getOrgIdColumnCode())
                            || dimension.getColumnCode().equals(replaceLevelColumn.getOrgNameColumnCode()))) {
                        appendLevelColumnField(dimSb, replaceLevelColumn, mainTb);
                    } else {
                        continue;
                    }
                    fields.append(dimSb).append(SqlUtils.STR_DOT);
                } else {
                    dimSb.append(mainTb).append(SqlUtils.STR_POINT).append(dimension.getAlias());
                    fields.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                            .append(SqlUtils.STR_DOT);
                }
            } else if (Constants.APP_TYPE_METRICS.equals(dimension.getAppType())
                    && CollectionUtils.isEmpty(dimension.getColumnGroup())) {
                String tabName = mainTb;
                SqlFuncEnum funcEnum = SqlFuncEnum.getFuncByName(dimension.getFunc());
                if (SqlBuilderHelper.isGrowth(funcEnum)) {
                    if (CollectionUtils.isEmpty(subSqlList)) {
                        dimSb.append(tabName).append(SqlUtils.STR_POINT).append(dimension.getAlias());
                    } else if (funcEnum.equals(SqlFuncEnum.pp)) {
                        String subTabName = findRelativeAlias(dimension, subSqlList);
                        String expression = SqlUtils.STR_LEFT_BRACKET + tabName + SqlUtils.STR_POINT
                                + dimension.getAlias() + "-" + subTabName + SqlUtils.STR_POINT + dimension.getAlias()
                                + SqlUtils.STR_RIGHT_BRACKET;
                        // 默认值和小数点处理
                        String convertMetric = this.metricIfNull(expression, dimension);
                        dimSb.append(convertMetric);
                    } else {
                        String subTabName = findRelativeAlias(dimension, subSqlList);
                        if (StringUtils.isNotEmpty(subTabName)) {
                            String expression = SqlUtils.STR_LEFT_BRACKET + tabName + SqlUtils.STR_POINT
                                    + dimension.getAlias() + "-" + subTabName + SqlUtils.STR_POINT + dimension.getAlias()
                                    + SqlUtils.STR_RIGHT_BRACKET + "/" + subTabName + SqlUtils.STR_POINT
                                    + dimension.getAlias();
                            // 默认值和小数点处理
                            String convertMetric = this
                                    .metricIfNull("(" + SqlConvertUtils.divisionConvert(expression) + ")", dimension);
                            dimSb.append(convertMetric);
                        } else {
                            dimSb.append(0);
                        }
                    }
                } else if (SqlBuilderHelper.isTotal(funcEnum)) {
                    // 月/年累计
                    if (!CollectionUtils.isEmpty(subSqlList)) {
                        tabName = findRelativeAlias(dimension, subSqlList);
                    }
                    dimSb.append(tabName).append(SqlUtils.STR_POINT).append(dimension.getAlias());
                } else if (!Constants.DimensionType.TYPE_MAIN.equalsIgnoreCase(dimension.getDimensionType())) {
                    // fix 表名null
                    if (!CollectionUtils.isEmpty(subSqlList)) {
                        tabName = findRelativeAlias(dimension, subSqlList);
                    }
                    dimSb.append(tabName).append(SqlUtils.STR_POINT).append(dimension.getAlias());
                } else {
                    dimSb.append(tabName).append(SqlUtils.STR_POINT).append(dimension.getAlias());
                }
                if (!dimension.isHide() || !singleSql) {
                    fields.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                            .append(SqlUtils.STR_DOT);
                }
            } else if (singleSql && !CollectionUtils.isEmpty(dimension.getColumnGroup())) {
                String expression = SqlBuilderHelper.getColumnGroup(dimension.getColumnGroup(), expMap, null,
                        singleSql);
                String convertMetric = this.metricIfNull("(" + SqlConvertUtils.divisionConvert(expression) + ")",
                        dimension);
                dimSb.append(convertMetric);
                fields.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                        .append(SqlUtils.STR_DOT);
            }
            // 缓存
            expMap.put(dimension.getAlias(), dimSb);
        }

        // hive的账期维度放后面
        if (!hivePeriodDim.isEmpty()) {
            fields.append(hivePeriodDim);
        }
        if (SqlBuilderHelper.checkDataPriv(getDataPrivCtrlInfo())) {
            StringBuilder orgStr = new StringBuilder();
            // 为空、则没有拖到组织维度表的字段
            if (!checkHaveOrgId()) {
                orgStr.append(mainTb).append(SqlUtils.STR_POINT).append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.SQL_AS)
                        .append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.STR_DOT);
            }
            orgStr.append(mainTb).append(SqlUtils.STR_POINT).append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.SQL_AS)
                    .append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.STR_DOT);
            fields.insert(0, orgStr);
        }

        // 没有配置账期维度的调度sql且只有一个sql
        if (singleSql && !hasPeriod && Constants.SQL_TASK.equals(this.sqlMode)
                && !"O".equalsIgnoreCase(this.scheduleType)) {
            // 没有账期
            String periodStr;
            if ("D".equals(this.scheduleType)) {
                periodStr = "${day_id} as day_id,";
            } else {
                periodStr = "${month_id} as month_id,";
            }

            if (getDbType().equals(KeyValues.DS_HIVE)) {
                fields.append(periodStr);
            } else {
                fields.insert(0, periodStr);
            }
        }

        // 删掉逗号
        if (!fields.isEmpty()) {
            fields.deleteCharAt(fields.length() - 1);
        }
        sql.append(fields);
    }

    private String findRelativeAlias(DatasetColumnQo dimension, List<SubQuerySqlQo> subSqlList) {
        for (SubQuerySqlQo subQuerySqlQo : subSqlList) {
            for (DatasetColumnQo columnQo : subQuerySqlQo.getMetricList()) {
                if (dimension.getDimensionType().equals(subQuerySqlQo.getDimensionType())
                        && columnQo.getAlias().equals(dimension.getAlias())) {
                    return subQuerySqlQo.getTbAlisa();
                }
            }
        }
        return null;
    }

    protected String appendSqlConvert(boolean singleSql,
                                      List<DatasetColumnQo> metrics,
                                      String dimensionType,
                                      List<DatasetColumnQo> dimensionList,
                                      List<DatasetConditionQo> condList,
                                      boolean needAppendPeriod,
                                      OrgDimension replaceLevelColumn,
                                      ResultSql result) {
        List<DatasetColumnQo> growthOrTotalsMetric = null;
        SqlComponent component = new SqlComponent();
        Map<String, Map<String, String>> alias;
        // 此分支可以先不管
        if (this.params.judgeIndexView()) {
            alias = getIndexViewSql(component, metrics, dimensionList);
        } else {
            // 检查是否有同环比和月/年累计
            growthOrTotalsMetric = checkGrowthOrTotal(metrics, dimensionType);
            Map<String, List<MetricsDimensionPathVo>> pathsMap = metrics.get(0).getPathsMap();
            String dataPrivPathKey = metrics.get(0).getDataPrivPathKey();
            alias = joinTables(pathsMap, dataPrivPathKey, needAppendPeriod, component);
        }
        boolean single = (singleSql && CollectionUtils.isEmpty(growthOrTotalsMetric));
        if (params.getDetailTableId() != null) {
            component.field.append(" * ");
        } else {
            this.appendOutField(single, metrics, dimensionType, dimensionList, alias, component.field,
                    replaceLevelColumn, null, false);
        }
        this.appendWhere(single, component.where, metrics, dimensionType, condList, alias, null, null, null);
        if (result.isGroupBy) {
            this.appendGroupBy(metrics, dimensionList, component.group, alias, replaceLevelColumn, false);
        }
        if (single) {
            // 排序
            orderByColumnList(component.order, metrics, dimensionList, alias);
            StringBuilder sql = acctSqlService.joinTimeDimension(params, component.swapSql());
            // this.getPage(sql);
            return sql.toString();
        }

        // 生成同环比或者月/年累计的子查询、相同粒度下的同一统计函数
        List<SubQuerySqlQo> subSqlList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(growthOrTotalsMetric)) {
            Map<String, List<DatasetColumnQo>> funcGroups = growthOrTotalsMetric.stream()
                    .collect(Collectors.groupingBy(DatasetColumnQo::getFunc));

            // 按同环比分类，比上期/比上期%，统计账期条件一样的，应该分为一组
            funcGroups.forEach((key, values) -> {
                // 有同环比或者月/年累计
                subSqlGrowthOrTotal(subSqlList, values, dimensionType, dimensionList, condList, needAppendPeriod, replaceLevelColumn, scheduleType);
            });
            // 年累计/月累计，没有其他表字段时，不需要left join两段子查询
            if (growthOrTotalsMetric.size() == metrics.size()) {
                Set<String> totalSet = new HashSet<>();
                totalSet.add(SqlFuncEnum.monthTotal.name());
                totalSet.add(SqlFuncEnum.yearTotal.name());
                // 同比、环比
                if (totalSet.containsAll(funcGroups.keySet())) {
                    int subSqlSize = subSqlList.size();
                    if (subSqlSize == 1) {
                        return subSqlList.get(0).getSql();
                    }
                    // 合并月年累计
                    return mergeRelativeDims(singleSql, dimensionList, subSqlList.get(0).getSql(),
                            subSqlList.subList(0, subSqlSize - 1), replaceLevelColumn, subQueryToTmTab ? result : null);
                }
            }
            // 合并汇总表，组织层级字段已经取过别名
            if (replaceLevelColumn != null) {
                replaceLevelColumn.setAlias(true);
            }
        }

        // 层级向上汇总，可以少嵌套一层
        if (CollectionUtils.isEmpty(growthOrTotalsMetric) && autoLevelGroup) {
            return component.swapSql().toString();
        }
        // 合并同环比或者月年累计
        return mergeRelativeDims(singleSql, dimensionList, component.swapSql().toString(), subSqlList,
                replaceLevelColumn, subQueryToTmTab ? result : null);
    }

    /**
     * 是否有计算字段
     */
    boolean hasCalMetric(List<DatasetColumnQo> metrics) {
        for (DatasetColumnQo metric : metrics) {
            if (CollUtil.isNotEmpty(metric.getColumnGroup())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected Map<String, Map<String, String>> joinTables(Map<String, List<MetricsDimensionPathVo>> pathsMap,
                                                          String dataPrivPathKey,
                                                          boolean needAppendPeriod,
                                                          SqlComponent component) {
        Map<String, Map<String, String>> alias = new HashMap<>();
        // 判断是否有维表关联
        boolean hasOrgTable = SqlBuilderHelper.hasOrgTable(getDataPrivCtrlInfo(), pathsMap);
        // 遍历拼接表关联、组织权限字段输出和分组
        Map<Long, String> hasAppend = new HashMap<>();
        Map<String, String> publicAlias = new HashMap<>();
        for (Map.Entry<String, List<MetricsDimensionPathVo>> entry : pathsMap.entrySet()) {
            String pathKey = entry.getKey();
            List<MetricsDimensionPathVo> pathVos = entry.getValue();
            Map<String, String> tableAlias = new HashMap<>();
            if (!MapUtil.isEmpty(publicAlias)) {
                tableAlias.putAll(publicAlias);
            }
            boolean isPriv = entry.getKey().equals(dataPrivPathKey);
            appendTableSql(isPriv, entry, hasAppend, needAppendPeriod, hasOrgTable, tableAlias, component);
            SqlBuilderHelper.copyCommonAlias(tableAlias, pathVos, publicAlias);
            alias.put(pathKey, tableAlias);
        }
        // 把公共别名设置进去
        SqlBuilderHelper.copyPublicToPathAlias(publicAlias, alias);
        return alias;
    }

    @Override
    protected void orderByColumnList(StringBuilder result, List<DatasetColumnQo> dimensionList,
                                     List<DatasetColumnQo> metrics, Map<String, Map<String, String>> alias) {
        if (!CollectionUtils.isEmpty(dimensionList)) {
            StringBuilder sql = new StringBuilder();
            for (DatasetColumnQo qo : dimensionList) {
                if (StringUtils.isNoneBlank(qo.getSortOrder())) {
                    if (Constants.YES_VALUE_1.equals(qo.getIsAcct())) {
                        StringBuilder dimSb = new StringBuilder();
                        // 账期字段,前端只能拖一个账期
                        Column periodColumn = getPeriodColumnFromMetric(metrics.get(0), qo);
                        if (null != periodColumn) {
                            String tbName = SqlBuilderHelper.getAliasName(alias, periodColumn.getMetaDataId());
                            dimSb.append(tbName).append(SqlUtils.STR_POINT).append(periodColumn.getColumnCode());
                        } else {
                            fieldPeriod(dimSb, qo);
                        }
                        sql.append(dimSb).append(SqlUtils.STR_BLANK).append(qo.getSortOrder()).append(SqlUtils.STR_DOT);
                    } else if (!Constants.APP_TYPE_METRICS.equals(qo.getAppType())) {
                        String tbName = SqlBuilderHelper.getAliasName(alias, qo.getTableId());
                        sql.append(tbName).append(SqlUtils.STR_POINT).append(qo.getColumnCode())
                                .append(SqlUtils.STR_BLANK).append(qo.getSortOrder()).append(SqlUtils.STR_DOT);
                    }
                }
            }
            if (!sql.isEmpty()) {
                sql.deleteCharAt(sql.length() - 1);
                result.append(sql);
            }
        }
    }

    /**
     * union all 的输出字段拼接
     *
     * @param singleSql          是否为单个sql
     * @param metrics            度量
     * @param dimensionType      相对维度字段别名
     * @param dimensionList      输出字段
     * @param aliasMap           别名
     * @param fieldSql           输出字段拼接
     * @param replaceLevelColumn 层级替换字段
     */
    @Override
    protected void appendOutField(boolean singleSql,
                                  List<DatasetColumnQo> metrics,
                                  String dimensionType,
                                  List<DatasetColumnQo> dimensionList,
                                  Map<String, Map<String, String>> aliasMap,
                                  StringBuilder fieldSql,
                                  OrgDimension replaceLevelColumn,
                                  SqlFuncEnum funcEnum,
                                  boolean joinTimeSql) {
        // 划小架构表最细粒度字段
        DatasetColumnQo orgDimColumn = SqlBuilderHelper.getOrgDimensionMinColumn(getDataPrivCtrlInfo());
        DatasetColumnQo metric = metrics.get(0);
        boolean hasPeriod = false;
        // 用于计算字段取表达式
        Map<String, StringBuilder> expMap = new HashMap<>();
        StringBuilder hivePeriodDim = new StringBuilder();
        // 不需要注释 的 数据源类型
        Set<String> ignoreNotesTypeSets = Set.of(KeyValues.DS_HIVE, KeyValues.DS_WHALEHOUSE);
        for (DatasetColumnQo dimension : dimensionList) {

            StringBuilder dimSb = new StringBuilder();
            // 单个sql的时候需要注释
            String notes = !ignoreNotesTypeSets.contains(getDbType()) && singleSql
                    ? SqlBuilderHelper.fieldNotes(dimension.getDataName())
                    : "";
            // 是否有拖到最细细度组织维度字段
            if (!ObjectUtils.isEmpty(orgDimColumn) && orgDimColumn.getColumnId().equals(dimension.getColumnId())) {
                haveOrgId(true);
            }
            if (Constants.YES_VALUE_1.equals(dimension.getIsAcct())) {
                hasPeriod = true;
                // 账期字段,前端只能拖一个账期
                Column periodColumn = getPeriodColumnFromMetric(metric, dimension);
                if (null != periodColumn) {
                    String columnExp;
                    if (joinTimeSql) {
                        columnExp = "tm.acct";
                    } else {
                        String tbName = SqlBuilderHelper.getAliasName(aliasMap, periodColumn.getMetaDataId());
                        columnExp = tbName + SqlUtils.STR_POINT + periodColumn.getColumnCode();
                    }
                    // 为保证union数据类型一致，账期数据类型转换为数据集账期类型整型
                    String intType = Constants.DATA_TYPE_INT.get(this.getDbType());
                    dimension.setColumnType(intType);
                    // 如果是同比，环比，账期字段需要特殊处理
                    dimSb.append(this.getAcctColumnExp(columnExp, funcEnum, periodColumn));
                } else {
                    fieldPeriod(dimSb, dimension);
                }
                // hive数据源的账期字段抽取出来放到所有字段最后面
                if (getDbType().equals(KeyValues.DS_HIVE) && !"O".equals(this.scheduleType)) {
                    hivePeriodDim.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias())
                            .append(SqlUtils.STR_DOT);
                    continue;
                }
                // 注释
                fieldSql.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                        .append(SqlUtils.STR_DOT);
            } else if (Constants.APP_TYPE_DIMENSION.equals(dimension.getAppType())) {
                // 维度字段
                // 虚拟维度对象字段替换
                this.replaceVColumn(dimension, metric);
                Map<String, String> alias = SqlBuilderHelper.getAliasMap(metric, dimension, aliasMap, !params.judgeIndexView());
                if (autoLevelGroup && isLevelColumn(dimension)) {
                    if (dimension.getTableId().equals(replaceLevelColumn.getMetaDataId())
                            && (dimension.getColumnCode().equals(replaceLevelColumn.getOrgIdColumnCode())
                            || dimension.getColumnCode().equals(replaceLevelColumn.getOrgNameColumnCode()))) {
                        appendLevelColumnField(dimSb, replaceLevelColumn,
                                alias.get(String.valueOf(dimension.getTableId())));
                    } else {
                        continue;
                    }
                    // 避免重复添加
                    if (fieldSql.indexOf(dimSb.toString()) == -1) {
                        fieldSql.append(dimSb).append(SqlUtils.STR_DOT);
                    }
                }
                // 有枚举的维度字段
                else if (!CollectionUtils.isEmpty(dimension.getEnumList())) {
                    fieldSql.append("CASE ");
                    for (Map<?, ?> map : dimension.getEnumList()) {
                        fieldSql.append(" WHEN ").append(alias.get(String.valueOf(dimension.getTableId())))
                                .append(SqlUtils.STR_POINT).append(dimension.getColumnCode()).append(SqlUtils.STR_EQUAL)
                                .append("'").append(map.get("code")).append("'").append(" THEN ").append("'")
                                .append(map.get("name")).append("'");
                    }
                    fieldSql.append(" END AS ").append(dimension.getAlias()).append(SqlUtils.STR_DOT);
                } else {
                    dimSb.append(alias.get(String.valueOf(dimension.getTableId()))).append(SqlUtils.STR_POINT)
                            .append(dimension.getColumnCode());
                    fieldSql.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                            .append(SqlUtils.STR_DOT);
                }
            } else if (Constants.APP_TYPE_METRICS.equals(dimension.getAppType())
                    && dimensionType.equalsIgnoreCase(dimension.getDimensionType())
                    && CollectionUtils.isEmpty(dimension.getColumnGroup())) {
                // 虚拟字段
                replaceVCondition(metric);
                List<String> funs = metrics.stream().map(DatasetColumnQo::getFunc).toList();
                List<Long> columnIds = metrics.stream().map(DatasetColumnQo::getColumnId).toList();
                List<String> columnCodes = metrics.stream().map(DatasetColumnQo::getColumnCode)
                        .toList();
                List<Long> tablesIds = metrics.stream().map(DatasetColumnQo::getTableId).toList();
                // 度量字段，没有的用0输出
                if (funs.contains(dimension.getFunc()) && columnIds.contains(dimension.getColumnId())
                        && columnCodes.contains(dimension.getColumnCode()) && tablesIds.contains(dimension.getTableId())) {
                    AbstractFuncParser parser = SqlBuilderFactory.getFuncParser(dimension);
                    parser.initParams(this, getDbType(), dimension, metrics, aliasMap, new LinkedHashMap<>());
                    parser.setOutField(dimSb);
                } else {
                    dimSb.append("0");
                }
                // 隐藏字段过滤
                if (!dimension.isHide() || !singleSql) {
                    fieldSql.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                            .append(SqlUtils.STR_DOT);
                }
            } else if (Constants.APP_TYPE_METRICS.equals(dimension.getAppType())
                    && !dimensionType.equalsIgnoreCase(dimension.getDimensionType())
                    && CollectionUtils.isEmpty(dimension.getColumnGroup())) {
                dimSb.append("0");
                fieldSql.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                        .append(SqlUtils.STR_DOT);
            } else if (singleSql && !CollectionUtils.isEmpty(dimension.getColumnGroup())) {
                String expression = SqlBuilderHelper.getColumnGroup(dimension.getColumnGroup(), expMap, null,
                        singleSql);
                String convertMetric = this.metricIfNull("(" + SqlConvertUtils.divisionConvert(expression) + ")",
                        dimension);
                dimSb.append(convertMetric);
                fieldSql.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
                        .append(SqlUtils.STR_DOT);
            }
            // 缓存
            expMap.put(dimension.getAlias(), dimSb);
        }

        // hive的账期维度放后面
        if (!hivePeriodDim.isEmpty()) {
            fieldSql.append(hivePeriodDim);
        }

        // 权限控制
        if (SqlBuilderHelper.checkDataPriv(getDataPrivCtrlInfo())) {
            // 为空、则没有拖到组织维度表的字段
            if (ObjectUtils.isEmpty(orgDimColumn)) {
                Long userId = this.params.getCreator();
                StringBuilder orgStr = new StringBuilder();
                orgStr.append("'").append(OrganizationUtil.getOrgIdByUserId(userId)).append("'").append(SqlUtils.SQL_AS)
                        .append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.STR_DOT);
                fieldSql.insert(0, orgStr);
            } else {
                // 拖到组织维度表字段了、但是没有拖到组织id
                if (!checkHaveOrgId()) {
                    StringBuilder orgStr = new StringBuilder();
                    String tbName = SqlBuilderHelper.getAliasName(aliasMap, orgDimColumn.getTableId(),
                            orgDimColumn.getPath());
                    orgStr.append(tbName).append(SqlUtils.STR_POINT).append(orgDimColumn.getColumnCode())
                            .append(SqlUtils.SQL_AS).append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.STR_DOT);
                    fieldSql.insert(0, orgStr);
                }
            }
        }

        // 没有配置账期维度的调度sql且只有一个sql
        if (singleSql && !hasPeriod && Constants.SQL_TASK.equals(this.sqlMode)
                && !"O".equalsIgnoreCase(this.scheduleType)) {
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
        if (!fieldSql.isEmpty()) {
            fieldSql.deleteCharAt(fieldSql.length() - 1);
        }
    }

    @Override
    protected void periodDistinct(PeriodExpression condPeriodExp, List<PeriodExpression> periodExpressionFromMetrics) {
        if ("=".equalsIgnoreCase(condPeriodExp.getOperator().trim())) {
            periodExpressionFromMetrics.removeIf(next -> {
                return next.getOperator().trim().equalsIgnoreCase(condPeriodExp.getOperator().trim())
                        && condPeriodExp.getPeriodScope().get(0).equalsIgnoreCase(next.getPeriodScope().get(0));
            });
        }
    }

    /**
     * union all 的where条件拼接
     */
    @Override
    protected void appendWhere(boolean singleSql,
                               StringBuilder sqlWhere,
                               List<DatasetColumnQo> metrics,
                               String dimensionType,
                               List<DatasetConditionQo> condList,
                               Map<String, Map<String, String>> aliasMap,
                               Map<String, List<MetricsDimensionPathVo>> mainTbPaths,
                               Map<String, String> temTableAlias,
                               SqlFuncEnum funcEnum) {
        DatasetColumnQo datasetColumnQo0 = metrics.get(0);
        Map<String, List<MetricsDimensionPathVo>> pathsMap = datasetColumnQo0.getPathsMap();
        StringBuilder sql = new StringBuilder();
        PeriodExpression condPeriodExp = null;
        // 全局度量条件
        List<DatasetConditionQo> acctConds = CollectionUtils.isEmpty(condList) ? Collections.emptyList()
                : condList.stream().filter(obj -> KeyValues.YES_VALUE_1.equals(obj.getIsAcct()))
                .collect(Collectors.toList());
        // 读取度量上的账期范围
        List<PeriodExpression> periodExpressionFromMetrics = this.getPeriodExpressionFromMetrics(funcEnum, metrics,
                acctConds, dimensionType);
        if (!CollectionUtils.isEmpty(condList)) {
            AbstractSqlParser abstractSqlParser = SqlParserFactory.getViewSqlParser(this.getDbType());
            String isSql = OutPutMode.SQL.equals(this.outPutMode) ? Constants.YES_VALUE_1 : Constants.NO_VALUE_0;
            for (DatasetConditionQo conditionQo : condList) {
                // 账期字段替换 月账/日账
                if (Constants.YES_VALUE_1.equals(conditionQo.getIsAcct())
                        && StringUtils.isNoneBlank(conditionQo.getCycleType())) {
                    StringBuilder periodSql = new StringBuilder();
                    condPeriodExp = new PeriodExpression(conditionQo);
                    // 从paths路径上面的表取出账期字段
                    if (!MapUtil.isEmpty(pathsMap)) {
                        Set<Long> ids = new HashSet<>();
                        for (Map.Entry<String, List<MetricsDimensionPathVo>> entry : pathsMap.entrySet()) {
                            entry.getValue().forEach(t -> {
                                if (null != this.allPeriod.get(t.getSrcTableId())) {
                                    ids.add(t.getSrcTableId());
                                }
                                if (null != this.allPeriod.get(t.getTgtTableId())) {
                                    ids.add(t.getTgtTableId());
                                }
                            });
                        }
                        // 度量账期去重
                        if (!periodExpressionFromMetrics.isEmpty()) {
                            periodDistinct(condPeriodExp, periodExpressionFromMetrics);
                        }
                        List<String> periodConds = new ArrayList<>();
                        // 主从表的账期
                        if (!ids.isEmpty()) {
                            for (Long id : ids) {
                                String tbName = getAlias(aliasMap, id);
                                Column column = this.allPeriod.get(id);
                                periodConds.add(appendPrePeriod(tbName, column, condPeriodExp, funcEnum));
                            }
                        }
                        if (!periodConds.isEmpty()) {
                            if (!periodSql.isEmpty()) {
                                periodSql.append(SqlUtils.SQL_AND);
                            }
                            periodSql.append(SqlUtils.STR_LEFT_BRACKET);
                            periodSql.append(StringUtils.join(" and ", periodConds.toArray(new String[0])));
                            periodSql.append(SqlUtils.STR_RIGHT_BRACKET);
                        }

                        // 度量上面的账期-- 度量条件受全局条件限制 -- 全局账期条件加上度量账期条件，or拼接
                        if (!periodExpressionFromMetrics.isEmpty()) {
                            List<String> periodMetricList = new ArrayList<>();
                            if (!ids.isEmpty()) {
                                for (Long id : ids) {
                                    String tbName = getAlias(aliasMap, id);
                                    Column column = this.allPeriod.get(id);
                                    for (PeriodExpression expression : periodExpressionFromMetrics) {
                                        periodMetricList.add(appendPrePeriod(tbName, column, expression, funcEnum));
                                    }
                                }
                            }
                            if (!periodSql.isEmpty()) {
                                periodSql.append(SqlUtils.SQL_OR);
                            }
                            periodSql.append(SqlUtils.STR_LEFT_BRACKET);
                            periodSql.append(StringUtils.join(" or ", periodMetricList.toArray(new String[0])));
                            periodSql.append(SqlUtils.STR_RIGHT_BRACKET);
                        }
                    } else {
                        Set<Long> ids = DealConditionParamUtils.getTableIdsByMap(aliasMap, this.allPeriod);
                        // 度量账期去重
                        if (!periodExpressionFromMetrics.isEmpty()) {
                            periodDistinct(condPeriodExp, periodExpressionFromMetrics);
                        }
                        List<String> periodConds = new ArrayList<>();
                        // 主从表的账期
                        if (!ids.isEmpty()) {
                            for (Long id : ids) {
                                periodConds.add(appendPrePeriod(getAlias(aliasMap, id), this.allPeriod.get(id), condPeriodExp, funcEnum));
                            }
                        }
                        DealConditionParamUtils.splicingConditionParam(periodConds, periodSql);
                    }
                    if (!periodSql.isEmpty()) {
                        sql.append(periodSql);
                    } else {
                        sql.append("1=1");
                    }
                    continue;
                } else if (StringUtils.isNotEmpty(conditionQo.getBusinessType())) {
                    String timeSql = getWhereSqlByTime(conditionQo, aliasMap);
                    sql.append(!timeSql.isEmpty() ? timeSql : "1 = 1");
                    continue;
                }

                String tableAlias = null;
                // 虚拟对象条件处理
                replaceVCondition(conditionQo, datasetColumnQo0);
                if (null != conditionQo.getTableId() && StringUtils.equals("simpleCond", conditionQo.getCondType())) {
                    DatasetColumnQo newDatasetColumnQo = new DatasetColumnQo();
                    BeanUtils.copyProperties(conditionQo, newDatasetColumnQo, "id", "parent", "root");
                    if (mainTbPaths != null) {
                        tableAlias = SqlBuilderHelper.getAliasFromTempTableAndMetric(datasetColumnQo0, newDatasetColumnQo,
                                mainTbPaths, aliasMap, temTableAlias);
                    } else {
                        Map<String, String> tableAliasMap = SqlBuilderHelper.getAliasMap(datasetColumnQo0,
                                newDatasetColumnQo, aliasMap, !params.judgeIndexView());
                        tableAlias = tableAliasMap.get(String.valueOf(conditionQo.getTableId()));
                    }
                }

                // 非多维指标的度量中全局条件里可能有多维指标的字段、则获取不到表名
                if (null != conditionQo.getTableId() && null == tableAlias) {
                    continue;
                }

                conditionQo.setTableAlias(tableAlias);
                BuildSqlUtil.appendSimpleCond(sql, conditionQo, isSql, abstractSqlParser);
            }
        }

        // 拼接账期 如果度量上有账期条件 (periodMetrics or condPeriods)
        if (null == condPeriodExp) {
            // 主表账期
            Column column = this.allPeriod.get(datasetColumnQo0.getTableId());
            if (column != null) {
                String tbName = getAlias(aliasMap, datasetColumnQo0.getTableId());
                if (!CollectionUtils.isEmpty(periodExpressionFromMetrics)) {
                    // 去除重复账期条件
                    Set<PeriodExpression> set = new TreeSet<>(
                            (o1, o2) -> {
                                if (o1.getOperator().equalsIgnoreCase(o2.getOperator()) &&
                                        new HashSet<>(o1.getPeriodScope()).containsAll(o2.getPeriodScope())) {
                                    return 0;
                                }
                                return 1;
                            }
                    );
                    set.addAll(periodExpressionFromMetrics);

                    if (!sql.isEmpty()) {
                        sql.append(SqlUtils.SQL_AND);
                    }
                    List<String> periodMetricList = new ArrayList<>();
                    for (PeriodExpression expression : set) {
                        periodMetricList.add(appendPrePeriod(tbName, column, expression, funcEnum));
                    }
                    sql.append(SqlUtils.STR_LEFT_BRACKET);
                    sql.append(StringUtils.join(" or ", periodMetricList.toArray(new String[0])));
                    sql.append(SqlUtils.STR_RIGHT_BRACKET);
                } else {
                    condPeriodExp = getMetricPeriodExpression(column, funcEnum);
                    if (null != condPeriodExp) {
                        if (!sql.isEmpty()) {
                            sql.append(SqlUtils.SQL_AND);
                        }
                        sql.append(appendPrePeriod(tbName, column, condPeriodExp, funcEnum));
                    }
                }
            }
        }

        // 校验sql,传递条件1=2
        if (singleSql && Constants.SQL_CHECK.equals(params.getSqlMode())) {
            if (!sqlWhere.isEmpty()) {
                sqlWhere.append(SqlUtils.SQL_AND);
            }
            sqlWhere.append("1=2");
        }

        // 拼接其他的条件
        if (!sqlWhere.isEmpty() && !sql.isEmpty()) {
            sqlWhere.append(SqlUtils.SQL_AND).append(sql);
        } else {
            sqlWhere.append(sql);
        }
    }

    /**
     * 读取度量上的账期范围
     */
    private List<PeriodExpression> getPeriodExpressionFromMetrics(SqlFuncEnum funcEnum,
                                                                  List<DatasetColumnQo> metrics,
                                                                  List<DatasetConditionQo> acctConds,
                                                                  String dimensionType) {
        List<PeriodExpression> periodExpressionFromMetrics = new ArrayList<>();
        if (SqlBuilderHelper.isTotal(funcEnum)) {
            periodExpressionFromMetrics = new ArrayList<>();
        } else if (funcEnum == null) {
            List<DatasetColumnQo> metricList = metrics.stream()
                    .filter(m -> !SqlBuilderHelper.isGrowthOrTotal(m.getFunc())).collect(Collectors.toList());
            periodExpressionFromMetrics
                    .addAll(this.getPeriodExpressionFromMetrics(metricList, acctConds, dimensionType));
        } else {
            periodExpressionFromMetrics = this.getPeriodExpressionFromMetrics(metrics, acctConds, dimensionType);
        }
        return periodExpressionFromMetrics;
    }

    /**
     * union all 的分组条件拼接
     */
    @Override
    protected void appendGroupBy(List<DatasetColumnQo> metrics,
                                 List<DatasetColumnQo> dimensionList,
                                 StringBuilder groupSql,
                                 Map<String, Map<String, String>> aliasMap,
                                 OrgDimension replaceLevelColumn,
                                 boolean joinTimeSql) {
        // 权限控制，划小架构维度字段id拼接。维度拖到了组织维度表的字段，但是不包含组织id
        DatasetColumnQo orgDimColumn = SqlBuilderHelper.getOrgDimensionMinColumn(getDataPrivCtrlInfo());
        if (!ObjectUtils.isEmpty(orgDimColumn) && !checkHaveOrgId()) {
            String tbName = SqlBuilderHelper.getAliasName(aliasMap, orgDimColumn.getTableId(), orgDimColumn.getPath());
            StringBuilder sql = new StringBuilder();
            sql.append(tbName).append(SqlUtils.STR_POINT).append(orgDimColumn.getColumnCode()).append(SqlUtils.STR_DOT);
            if (!groupSql.isEmpty()) {
                groupSql.insert(0, sql);
            } else {
                groupSql.append(sql);
            }
        }

        List<DatasetColumnQo> dimensionLists = dimensionList.stream()
                .filter(entity -> Constants.APP_TYPE_DIMENSION.equals(entity.getAppType()))
                .toList();
        for (DatasetColumnQo dimension : dimensionLists) {
            if (Constants.YES_VALUE_1.equals(dimension.getIsAcct())) {
                // 账期字段,前端只能拖一个虚拟的账期
                Column periodColumn = getPeriodColumnFromMetric(metrics.get(0), dimension);
                if (null != periodColumn) {
                    if (joinTimeSql) {
                        // 如果是一次性年/月累计，带有账期维度的，需要关联时间维表查询，group by要换成时间维表的字段
                        groupSql.append("tm.acct").append(SqlUtils.STR_DOT);
                    } else {
                        String tbName = SqlBuilderHelper.getAliasName(aliasMap, periodColumn.getMetaDataId());
                        groupSql.append(tbName).append(SqlUtils.STR_POINT).append(periodColumn.getColumnCode())
                                .append(SqlUtils.STR_DOT);
                    }
                }
                // 如果没有账期字段就不用拼接进分组里面
            } else if (Constants.APP_TYPE_DIMENSION.equals(dimension.getAppType())) {
                // 虚拟对象
                this.replaceVColumn(dimension, metrics.get(0));
                Map<String, String> alias = SqlBuilderHelper.getAliasMap(metrics.get(0), dimension, aliasMap, !params.judgeIndexView());
                if (autoLevelGroup && isLevelColumn(dimension)) {
                    if (dimension.getTableId().equals(replaceLevelColumn.getMetaDataId())
                            && (dimension.getColumnCode().equals(replaceLevelColumn.getOrgIdColumnCode())
                            || dimension.getColumnCode().equals(replaceLevelColumn.getOrgNameColumnCode()))) {
                        appendLevelColumnGroup(groupSql, replaceLevelColumn,
                                alias.get(String.valueOf(dimension.getTableId())));
                    }
                } else {
                    groupSql.append(alias.get(String.valueOf(dimension.getTableId()))).append(SqlUtils.STR_POINT)
                            .append(dimension.getColumnCode()).append(SqlUtils.STR_DOT);
                }
            }
        }
        if (!groupSql.isEmpty()) {
            groupSql.deleteCharAt(groupSql.length() - 1);
        }
    }

    @Override
    protected void fieldPeriod(StringBuilder fieldSql, DatasetColumnQo dimension) {
        String periodStr = AcctTimeUtil.getAcctValAddQuotOutPubMode(this.scheduleType, dimension.getCycleType(),
                dimension.getColumnType(), outPutMode);
        fieldSql.append(periodStr);
    }

    /**
     * 账期替换
     *
     * @param qo         虚拟账期字段
     * @param paths      度量路径上的表参数
     * @param tableAlias 表别名
     * @return 全局条件账期
     */
    protected Map<String, String> condListPeriods(DatasetConditionQo qo, List<MetricsDimensionPathVo> paths,
                                                  Map<String, String> tableAlias) {
        Map<String, String> map = new HashMap<>();
        StringBuilder strB;
        for (MetricsDimensionPathVo path : paths) {
            if (null != path.getSrcTableId() && null == map.get(String.valueOf(path.getSrcTableId()))) {
                Column column = this.allPeriod.get(path.getSrcTableId());
                if (null != column && column.getCycleType().equals(qo.getCycleType())) {
                    strB = new StringBuilder();
                    String value = replaceValues(qo, column);
                    String tableName = tableAlias.get(String.valueOf(path.getSrcTableId()));
                    strB.append(tableName).append(SqlUtils.STR_POINT).append(column.getColumnCode())
                            .append(SqlUtils.STR_BLANK).append(qo.getCondOperator()).append(SqlUtils.STR_BLANK)
                            .append(value);
                    map.put(String.valueOf(path.getSrcTableId()), strB.toString());
                }
            }
            if (null != path.getTgtTableId() && null == map.get(String.valueOf(path.getTgtTableId()))) {
                Column column = this.allPeriod.get(path.getTgtTableId());
                if (null != column && column.getCycleType().equals(qo.getCycleType())) {
                    strB = new StringBuilder();
                    String value = replaceValues(qo, column);
                    String tableName = tableAlias.get(String.valueOf(path.getTgtTableId()));
                    strB.append(tableName).append(SqlUtils.STR_POINT).append(column.getColumnCode())
                            .append(SqlUtils.STR_BLANK).append(qo.getCondOperator()).append(SqlUtils.STR_BLANK)
                            .append(value);
                    map.put(String.valueOf(path.getTgtTableId()), strB.toString());
                }
            }
        }
        return map;
    }

    /**
     * 分页sql拼接
     *
     * @param sql 拼接分页参数
     */
    public abstract void getPage(StringBuilder sql);

    /**
     * 替换维度字段
     */
    private void replaceVCondition(DatasetConditionQo condition, DatasetColumnQo metric) {
        if (condition.getObjId() != null
                && Constants.OBJ_TREE_RELA_TYPE_V.equalsIgnoreCase(condition.getRelaType())) {
            DatasetColumnQo virtualObjColumn = metric.getVirtualObjColumnMap().get(condition.getObjId());
            if (virtualObjColumn != null) {
                condition.setTableId(virtualObjColumn.getTableId());
                condition.setTableCode(virtualObjColumn.getTableCode());
                condition.setColumnId(virtualObjColumn.getColumnId());
                condition.setColumnCode(virtualObjColumn.getColumnCode());
            }
        }
    }

    /**
     * 替换条件
     */
    private void replaceVColumn(DatasetColumnQo dimension, DatasetColumnQo metric) {
        if (dimension.getObjectId() != null
                && Constants.OBJ_TREE_RELA_TYPE_V.equalsIgnoreCase(dimension.getRelaType())) {
            DatasetColumnQo virtualObjColumn = metric.getVirtualObjColumnMap().get(dimension.getObjectId());
            if (virtualObjColumn != null) {
                dimension.setTableId(virtualObjColumn.getTableId());
                dimension.setTableCode(virtualObjColumn.getTableCode());
                dimension.setColumnId(virtualObjColumn.getColumnId());
                dimension.setColumnCode(virtualObjColumn.getColumnCode());
            }
        }
    }

    /**
     * 替换度量条件
     */
    private void replaceVCondition(DatasetColumnQo metric) {
        if (!CollectionUtils.isEmpty(metric.getCondList())) {
            for (DatasetConditionQo subCond : metric.getCondList()) {
                if (subCond.getObjId() != null
                        && Constants.OBJ_TREE_RELA_TYPE_V.equalsIgnoreCase(subCond.getRelaType())) {
                    this.replaceVCondition(subCond, metric);
                }
            }
        }
    }

    private Map<String, Map<String, String>> getIndexViewSql(SqlComponent component, List<DatasetColumnQo> metrics,
                                                             List<DatasetColumnQo> dimensionList) {
        Map<String, Map<String, String>> alias = new HashMap<>(8);
        Map<String, String> alia = new HashMap<>(2);
        ObjKeyTableRelaVo relVo;
        Long tableId = metrics.get(0).getTableId();
        String mainTb = "tb" + getIncrementTbIndex();
        alia.put(tableId.toString(), mainTb);
        alias.put(tableId.toString(), alia);
        String fromTableName;
        component.join.append(getTableName(metrics.get(0).getTableCode(), metrics.get(0).getTableId()))
                .append(SqlUtils.STR_BLANK).append(mainTb);
        for (DatasetColumnQo q : dimensionList) {
            // 不是维度/是主表/虚拟维度的不需要追加left join
            if (!Constants.APP_TYPE_DIMENSION.equals(q.getAppType()) || tableId.equals(q.getTableId())) {
                continue;
            }
            if (Constants.OBJ_TREE_RELA_TYPE_V.equalsIgnoreCase(q.getRelaType())) {
                if (!alias.containsKey(q.getTableId().toString())) {
                    alia = new HashMap<>(2);
                    alia.put(q.getTableId().toString(), mainTb);
                    alia.put("srcTableId", tableId.toString());
                    alias.put(q.getTableId().toString(), alia);
                }
                continue;
            }
            relVo = relMap.get(tableId + "_" + q.getObjectId());
            if (relVo == null || CollectionUtils.isEmpty(relVo.getKeyColumnRelas())) {
                continue;
            }
            if (!alias.containsKey(q.getTableId().toString())) {
                alia = new HashMap<>(2);
                fromTableName = "tb" + getIncrementTbIndex();
                alia.put(q.getTableId().toString(), fromTableName);
                alias.put(q.getTableId().toString(), alia);
                component.join.append(SqlUtils.SQL_LEFT_JOIN).append(getTableName(q.getTableCode(), q.getTableId()))
                        .append(SqlUtils.STR_BLANK).append(fromTableName).append(SqlUtils.SQL_ON)
                        .append(getLeftJoinOn(relVo.getKeyColumnRelas(), mainTb, fromTableName));
            }
        }
        return alias;
    }

    private String getTableName(String tableCode, Long tableId) {
        String schemaCode = this.schemaMap.get(tableId);
        if (StringUtils.isNotEmpty(schemaCode)) {
            schemaCode = schemaCode + SqlUtils.STR_POINT;
        }
        return schemaCode + tableCode;
    }

    private String getLeftJoinOn(List<ObjKeyColumnRelaVo> relList, String t1, String t2) {
        StringBuilder on = new StringBuilder();
        for (ObjKeyColumnRelaVo v : relList) {
            on.append(t1).append(SqlUtils.STR_POINT).append(v.getColumnCode()).append(SqlUtils.STR_EQUAL).append(t2)
                    .append(SqlUtils.STR_POINT).append(v.getRelaColumnCode()).append(SqlUtils.SQL_AND);
        }
        // 移除最后一个" AND "
        if (on.toString().endsWith(SqlUtils.SQL_AND)) {
            on.delete(on.length() - 5, on.length() - 1);
        }
        return on.toString();
    }

    private String getWhereSqlByTime(DatasetConditionQo qo, Map<String, Map<String, String>> aliasMap) {
        Set<Long> ids = DealConditionParamUtils.getTableIdsByMap(aliasMap, this.allTimeGranularity);
        List<String> list = new ArrayList<>();
        if (ids.isEmpty()) {
            return "";
        }
        PeriodExpression exp = new PeriodExpression(qo);
        Column vo;
        for (Long id : ids) {
            vo = this.allTimeGranularity.get(id).getColumnMap().get(qo.getBusinessType());
            if (vo != null) {
                list.add(appendPrePeriod(getAlias(aliasMap, id), vo, exp, null));
            }
        }
        StringBuilder sql = new StringBuilder();
        DealConditionParamUtils.splicingConditionParam(list, sql);
        return sql.toString();
    }
}
