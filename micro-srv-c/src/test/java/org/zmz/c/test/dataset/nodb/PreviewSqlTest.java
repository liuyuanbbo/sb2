//package org.zmz.c.test.dataset.nodb;
//
//import cn.hutool.core.collection.ListUtil;
//import cn.hutool.core.lang.TypeReference;
//import cn.hutool.core.map.MapUtil;
//import cn.hutool.core.util.RandomUtil;
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.json.JSONUtil;
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.jupiter.api.Test;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.ObjectUtils;
//import org.zmz.c.qo.dataopen.Column;
//import org.zmz.c.qo.dataopen.Constants;
//import org.zmz.c.qo.dataopen.DataPrivCtrlVo;
//import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
//import org.zmz.c.qo.dataopen.DatasetColumnQo;
//import org.zmz.c.qo.dataopen.DatasetConditionQo;
//import org.zmz.c.qo.dataopen.MetricsDimensionPathVo;
//import org.zmz.c.qo.dataopen.ModelInfo;
//import org.zmz.c.qo.dataopen.OrgDimension;
//import org.zmz.c.qo.dataopen.OutPutMode;
//import org.zmz.c.qo.dataopen.SubQuerySqlQo;
//import org.zmz.c.service.dataopen.dataset.GpColumnTypeEnum;
//import org.zmz.c.service.dataopen.dataset.SqlBuilderHelper;
//import org.zmz.c.service.dataopen.dataset.SqlComponent;
//import org.zmz.c.service.dataopen.dataset.SqlFuncEnum;
//import org.zmz.c.utils.AcctTimeUtil;
//import org.zmz.c.utils.SqlConvertUtils;
//import org.zmz.c.utils.SqlUtils;
//import org.zmz.c.vo.dataopen.dataset.ResultSql;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import static org.zmz.c.qo.dataopen.Constants.CUSTOM_ORG_ID_ALIAS;
//import static org.zmz.c.qo.dataopen.Constants.CUSTOM_PATH_CODE_ALIAS;
//
//@Slf4j
//public class PreviewSqlTest {
//
//    protected int tbIndex = 0;
//
//    protected int getIncrementTbIndex() {
//        this.tbIndex++;
//        return this.tbIndex;
//    }
//
//    /**
//     * 子查询转换为临时表
//     **/
//    protected boolean subQueryToTmTab = false;
//
//    public String scheduleType = null;
//
//    /**
//     * 数据源schemaCode
//     */
//    protected Map<Long, String> schemaMap = new HashMap<>();
//
//    protected boolean autoLevelGroup = false;
//
//    /**
//     * 是否有最细粒度组织字段
//     */
//    private boolean isHaveOrgId = false;
//
//
//    /**
//     * 所有账期字段
//     */
//    public Map<Long, Column> allPeriod = new HashMap<>();
//
//    /**
//     * 输出模式 默认是table,或者sql
//     */
//    protected String outPutMode = OutPutMode.TABLE;
//
//    public String sqlMode = null;
//
//    public DatasetColumnAndConditionQo params = null;
//
//    @Test
//    public void testPreviewSqlStep_1() throws IOException {
//        Map<Long, ModelInfo> modelInfoMap = buildModelMap();
//        DatasetColumnAndConditionQo groupQo = buildDatasetColumnAndConditionQo();
//
//        log.info(">>> 开始进入预览 >>>");
//
//        // initParams
//
//    }
//
//    public static void initParams() {
//
//    }
//
//    public static Map<Long, ModelInfo> buildModelMap() throws IOException {
//        Map<Long, ModelInfo> map = new HashMap<>();
//
//        String json1 = Files.readString(Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\test\\resources\\json\\modelInfo1.json"));
//        ModelInfo modelInfo1 = JSONUtil.toBean(json1, ModelInfo.class);
//        map.put(800L, modelInfo1);
//
//        String json2 = Files.readString(Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\test\\resources\\json\\modelInfo2.json"));
//        ModelInfo modelInfo2 = JSONUtil.toBean(json2, ModelInfo.class);
//        map.put(795L, modelInfo2);
//
//        return map;
//    }
//
//    public static DatasetColumnAndConditionQo buildDatasetColumnAndConditionQo() throws IOException {
//        String json = Files.readString(Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\test\\resources\\json\\group0Qo.json"));
//        return JSON.parseObject(json, DatasetColumnAndConditionQo.class);
//    }
//
//    //-------------------------------------------------------------------------------------------------------------------
//
//
//    @Test
//    public void testPreviewSqlStep_2() throws IOException {
//        //1. 从 json 中获取 ResultSql 对象
//        String json = Files.readString(Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\test\\resources\\json\\result_input_param.json"));
//        ResultSql resultSql = JSONUtil.toBean(json, ResultSql.class);
//
//        //2.对于有层级字段 计算最大层级
//        String maxColumnLevelJson = Files.readString(Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\test\\resources\\json\\maxColumnLevel.json"));
//        List<DatasetColumnQo> dimensions = JSONUtil.toBean(maxColumnLevelJson, new TypeReference<>() {
//        }, false);
//        DatasetColumnQo currLevelColumn = maxLevelColumn(dimensions);
//
//        log.info("1 2 两步没有问题");
//
//        Map<Long, List<DatasetColumnQo>> metricsGroup = resultSql.metricsGroup;
//        if (MapUtil.isNotEmpty(metricsGroup)) {
//            // 3. 从 json 中读取 iteratorColumnMap
//            String iteratorColumnMapJson = Files.readString(Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\test\\resources\\json\\iteratorColumnMap.json"));
//            Map<Long, List<OrgDimension>> iteratorColumnMap = JSONUtil.toBean(iteratorColumnMapJson, new TypeReference<>() {
//            }, false);
//
//            // 4. 从 json 中读取 DatasetColumnAndConditionQo this.params
//            String DataSetColumnAndConditionQoJson
//                    = Files.readString(Path.of("D:\\worksp\\ideaProjects\\sb2\\micro-srv-c\\src\\test\\resources\\json\\DataSetColumnAndConditionQo.json"));
//            DatasetColumnAndConditionQo qo = JSONUtil.toBean(DataSetColumnAndConditionQoJson, new TypeReference<>() {
//            }, false);
//
//            log.info("3 4 两步没有问题");
//            // 有度量
//            for (Map.Entry<Long, List<DatasetColumnQo>> entry : metricsGroup.entrySet()) {
////                if (iteratorColumnMap.size() > 1) {
////                    result.isSingle = false;
////                    // 多表的层级字段
////                    iteratorColumnMap.forEach((key, value) -> {
////                        appendRelativeMetric(result, entry.getValue(), params.getColumnList(), params.getCondList(),
////                                value.get(0));
////                    });
////                    return;
////                }
//                // 单表的层级字段
//                List<OrgDimension> orgDimensions = iteratorColumnMap.get(currLevelColumn.getTableId());
//                resultSql.isSingle = (resultSql.isSingle &&
//                        resultSql.metricsGroup.size() == 1 &&
//                        orgDimensions.size() == 1);
//                // 5. 循环 根据关系拼接 SQL
//                for (OrgDimension orgDimension : orgDimensions) {
//                    appendRelativeMetric(resultSql, entry.getValue(), qo.getColumnList(), qo.getCondList(), orgDimension);
//                }
//            }
//            return;
//        }
//    }
//
//    // 调用 level_1
//    private void appendRelativeMetric(ResultSql resultSql,
//                                      List<DatasetColumnQo> metrics,
//                                      List<DatasetColumnQo> columnList,
//                                      List<DatasetConditionQo> condList,
//                                      OrgDimension replaceLevelColumn) {
//        // 检查分组度量里面是否有同比、环比和度量的账期条件
//        boolean mainSlaveTabPeriod = needSlaveTablePeriod(metrics, columnList);
//        // 以相对维度字段进行分组 目前最多两组(包含/include,排除/exclude),后续可能冗余fix类型
//        Map<List<String>, List<DatasetColumnQo>> dimGroup = getRelativeDimensionGroup(metrics, columnList);
//        // 主视图sql(可能没有度量)
//        String mainSql = appendSqlConvert(dimGroup.isEmpty() && resultSql.isSingle,
//                metrics,
//                Constants.DimensionType.TYPE_MAIN,
//                columnList,
//                condList,
//                mainSlaveTabPeriod,
//                replaceLevelColumn,
//                resultSql);
//        // 相对维度子查询
//        if (MapUtil.isNotEmpty(dimGroup)) {
//            List<SubQuerySqlQo> subSqlList = new ArrayList<>();
//            for (Map.Entry<List<String>, List<DatasetColumnQo>> dimGroupEntry : dimGroup.entrySet()) {
//                String dimensionType = dimGroupEntry.getValue().get(0).getDimensionType();
//                // 相对维度
//                List<DatasetColumnQo> dimensionList = columnList.stream()
//                        .filter(t -> dimGroupEntry.getKey().contains(t.getAlias()))
//                        .collect(Collectors.toList());
//                // 相对度量(可能多个相对度量相同维度)
//                List<DatasetColumnQo> relativeMetrics = dimGroupEntry.getValue();
//                boolean subSlaveTabPeriod = needSlaveTablePeriod(relativeMetrics, dimensionList);
//                String subSql = appendSqlConvert(false, relativeMetrics, dimensionType, dimensionList, condList,
//                        subSlaveTabPeriod, replaceLevelColumn, resultSql);
//                SubQuerySqlQo relativeDimension = new SubQuerySqlQo();
//                relativeDimension.setDimensionList(dimensionList);
//                relativeDimension.setSql(subSql);
//                relativeDimension.setMetricList(relativeMetrics);
//                relativeDimension.setDimensionType(dimensionType);
//                subSqlList.add(relativeDimension);
//            }
//            ResultSql result = subQueryToTmTab ? resultSql : null;
//            String s = mergeRelativeDims(resultSql.isSingle, columnList, mainSql, subSqlList, replaceLevelColumn, result);
//            resultSql.sqlLists.add(s);
//        } else {
//            resultSql.sqlLists.add(mainSql);
//        }
//    }
//
//    /**
//     * level_1_1
//     * 检查分组度量里面是否有同比、环比和度量的账期条件
//     *
//     * @return 从表on条件上是否需要拼接账期字段
//     */
//    public boolean needSlaveTablePeriod(List<DatasetColumnQo> metrics, List<DatasetColumnQo> columnList) {
//        for (DatasetColumnQo columnQo : columnList) {
//            // 有账期作维度
//            if (Constants.APP_TYPE_DIMENSION.equalsIgnoreCase(columnQo.getAppType())
//                    && Constants.YES_VALUE_1.equals(columnQo.getIsAcct())) {
//                return false;
//            }
//        }
//
//        for (DatasetColumnQo metric : metrics) {
//            List<DatasetConditionQo> metricCond = metric.getCondList();
//            // 度量上面有账期条件
//            if (!CollectionUtils.isEmpty(metricCond)) {
//                List<DatasetConditionQo> collect = metricCond.stream()
//                        .filter(obj -> Constants.YES_VALUE_1.equals(obj.getIsAcct())).toList();
//                if (!CollectionUtils.isEmpty(collect)) {
//                    return false;
//                }
//            }
//            // 同环比或者月年累计
//            SqlFuncEnum funcEnum = SqlFuncEnum.getFuncByName(metric.getFunc());
//            if (funcEnum.equals(SqlFuncEnum.yoyGrowth) || funcEnum.equals(SqlFuncEnum.momGrowth)
//                    || funcEnum.equals(SqlFuncEnum.mmGrowth) || funcEnum.equals(SqlFuncEnum.yearTotal)
//                    || funcEnum.equals(SqlFuncEnum.monthTotal) || funcEnum.equals(SqlFuncEnum.pp)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * level_1_2
//     * 是对维度分组
//     *
//     * @param metrics       度量
//     * @param dimensionList 所有维度
//     * @return 分组维度
//     */
//    protected Map<List<String>, List<DatasetColumnQo>> getRelativeDimensionGroup(List<DatasetColumnQo> metrics,
//                                                                                 List<DatasetColumnQo> dimensionList) {
//        List<DatasetColumnQo> collectFilter = metrics.stream().filter(t -> {
//            String dimensionType = t.getDimensionType();
//            String appType = t.getAppType();
//            return Constants.APP_TYPE_METRICS.equalsIgnoreCase(appType) &&
//                    !Constants.DimensionType.TYPE_MAIN.equals(dimensionType);
//        }).toList();
//        if (!CollectionUtils.isEmpty(collectFilter)) {
//            List<String> metricsAlias = metrics.stream().map(DatasetColumnQo::getAlias).toList();
//            return collectFilter.stream()
//                    .collect(Collectors.groupingBy(
//                            t -> {
//                                List<String> aliasList = Arrays.asList(t.getColumnExpression().split(","));
//                                return dimensionList.stream().filter(c -> {
//                                    if (metricsAlias.contains(c.getAlias())) {
//                                        return true;
//                                    } else {
//                                        String dimensionType = t.getDimensionType();
//                                        if (dimensionType.equalsIgnoreCase(Constants.DimensionType.TYPE_INCLUDE)) {
//                                            return aliasList.contains(c.getAlias());
//                                        } else if (dimensionType.equalsIgnoreCase(Constants.DimensionType.TYPE_EXCLUDE)) {
//                                            return !aliasList.contains(c.getAlias());
//                                        }
//                                    }
//                                    return false;
//                                }).map(DatasetColumnQo::getAlias).collect(Collectors.toList());
//                            })
//                    );
//        }
//        return new HashMap<>();
//    }
//
//    /**
//     * level_1_3
//     * 主视图sql(可能没有度量)
//     */
//    protected String appendSqlConvert(boolean singleSql,
//                                      List<DatasetColumnQo> metrics,
//                                      String dimensionType,
//                                      List<DatasetColumnQo> dimensionList,
//                                      List<DatasetConditionQo> condList,
//                                      boolean needAppendPeriod,
//                                      OrgDimension replaceLevelColumn,
//                                      ResultSql result) {
//        // 检查是否有同环比和月/年累计
//        List<DatasetColumnQo> growthOrTotalsMetric = checkGrowthOrTotal(metrics, dimensionType);
//        boolean single = singleSql && CollectionUtils.isEmpty(growthOrTotalsMetric);
//        SqlComponent component = new SqlComponent();
//        Map<String, List<MetricsDimensionPathVo>> pathsMap = metrics.get(0).getPathsMap();
//        String dataPrivPathKey = metrics.get(0).getDataPrivPathKey();
//        Map<String, Map<String, String>> alias = joinTables(pathsMap, dataPrivPathKey, needAppendPeriod, component);
//        if (params.getDetailTableId() != null) {
//            String tbName = SqlBuilderHelper.getAliasName(alias, params.getDetailTableId());
//            component.field.append(tbName).append(SqlUtils.STR_POINT).append("*");
//        } else {
//            this.appendOutField(single, metrics, dimensionType, dimensionList, alias, component.field,
//                    replaceLevelColumn, null, false);
//        }
//        this.appendWhere(single, component.where, metrics, dimensionType, condList, alias, null, null, null);
//        if (result.isGroupBy) {
//            this.appendGroupBy(metrics, dimensionList, component.group, alias, replaceLevelColumn, false);
//        }
//        if (single) {
//            // 排序
//            orderByColumnList(component.order, metrics, dimensionList, alias);
//            StringBuilder sql = acctSqlService.joinTimeDimension(params, component.swapSql());
//            // this.getPage(sql);
//            return sql.toString();
//        }
//
//        // 生成同环比或者月/年累计的子查询、相同粒度下的同一统计函数
//        List<SubQuerySqlQo> subSqlList = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(growthOrTotalsMetric)) {
//            Map<String, List<DatasetColumnQo>> funcGroups = growthOrTotalsMetric.stream()
//                    .collect(Collectors.groupingBy(DatasetColumnQo::getFunc));
//
//            funcGroups.forEach((key, values) -> {
//                // 有同环比或者月/年累计
//                subSqlGrowthOrTotal(subSqlList, false, values, dimensionType, dimensionList, condList, needAppendPeriod,
//                        replaceLevelColumn, scheduleType);
//            });
//            // 年累计/月累计，没有其他表字段时，不需要left join两段子查询
//            if (growthOrTotalsMetric.size() == metrics.size()) {
//                Set<String> totalSet = new HashSet<>();
//                totalSet.add(SqlFuncEnum.monthTotal.name());
//                totalSet.add(SqlFuncEnum.yearTotal.name());
//                // 同比、环比
//                if (totalSet.containsAll(funcGroups.keySet())) {
//                    int subSqlSize = subSqlList.size();
//                    if (subSqlSize == 1) {
//                        return subSqlList.get(0).getSql();
//                    }
//                    // 合并月年累计
//                    return mergeRelativeDims(singleSql, dimensionList, subSqlList.get(0).getSql(),
//                            subSqlList.subList(0, subSqlSize - 1), replaceLevelColumn, subQueryToTmTab ? result : null);
//                }
//            }
//            // 合并汇总表，组织层级字段已经取过别名
//            if (replaceLevelColumn != null) {
//                replaceLevelColumn.setAlias(true);
//            }
//        }
//
//        // 层级向上汇总，可以少嵌套一层
//        if (CollectionUtils.isEmpty(growthOrTotalsMetric) && autoLevelGroup) {
//            return component.swapSql().toString();
//        }
//        // 合并同环比或者月年累计
//        return mergeRelativeDims(singleSql, dimensionList, component.swapSql().toString(), subSqlList,
//                replaceLevelColumn, subQueryToTmTab ? result : null);
//    }
//
//    /**
//     * level_1_3_1
//     * 是否有同环比、月/年累计
//     *
//     * @param metrics 度量
//     * @return true/false
//     */
//    protected List<DatasetColumnQo> checkGrowthOrTotal(List<DatasetColumnQo> metrics, String dimensionType) {
//        List<DatasetColumnQo> growthOrTotalMetric = new ArrayList<>();
//        for (DatasetColumnQo metric : metrics) {
//            if (StringUtils.isBlank(metric.getFunc()) || !dimensionType.equalsIgnoreCase(metric.getDimensionType())) {
//                continue;
//            }
//            SqlFuncEnum funcEnum = SqlFuncEnum.getFuncByName(metric.getFunc());
//            // 同比、环比
//            if (funcEnum.equals(SqlFuncEnum.yoyGrowth) ||
//                    funcEnum.equals(SqlFuncEnum.momGrowth) ||
//                    funcEnum.equals(SqlFuncEnum.mmGrowth) ||
//                    funcEnum.equals(SqlFuncEnum.monthTotal) ||
//                    funcEnum.equals(SqlFuncEnum.yearTotal) ||
//                    funcEnum.equals(SqlFuncEnum.pp)) {
//                growthOrTotalMetric.add(metric);
//            }
//        }
//        return growthOrTotalMetric;
//    }
//
//    /**
//     * level_1_4
//     * 主视图与相对维度视图合并
//     *
//     * @param dimensionList 维度
//     * @param mainSql       主视图sql
//     * @param subSqlList    相对视图的sql集合
//     * @return 组装的sql
//     */
//    protected String mergeRelativeDims(boolean singleSql,
//                                       List<DatasetColumnQo> dimensionList,
//                                       String mainSql,
//                                       List<SubQuerySqlQo> subSqlList,
//                                       OrgDimension replaceLevelColumn,
//                                       ResultSql result) {
//        SqlComponent component = new SqlComponent();
//        String mainTb = "tb" + getIncrementTbIndex();
//        // 主视图sql
//        String tmpName;
//        if (result != null && !CollectionUtils.isEmpty(subSqlList)) {
//            tmpName = generateSubTmpTableName(scheduleType);
//            // 区分中间表，需要存放数据，因为LEFT JOIN拆分，需要创建临时表
//            result.tmpTableNames.put(tmpName, tmpName);
//            result.tmpTableSql.put(tmpName, mainSql);
//            component.join.append(tmpName).append(SqlUtils.STR_BLANK).append(mainTb);
//        } else {
//            component.join.append(SqlUtils.STR_LEFT_BRACKET).append(mainSql).append(SqlUtils.STR_RIGHT_BRACKET)
//                    .append(mainTb);
//        }
//        // 相对视图从表sql拼接
//        for (SubQuerySqlQo subQuerySqlQo : subSqlList) {
//            String relativeTb = "tb" + getIncrementTbIndex();
//            subQuerySqlQo.setTbAlisa(relativeTb);
//            if (result != null) {
//                tmpName = generateSubTmpTableName(scheduleType);
//                // 区分中间表，需要存放数据，因为LEFT JOIN拆分，需要创建临时表
//                result.tmpTableNames.put(tmpName, tmpName);
//                result.tmpTableSql.put(tmpName, subQuerySqlQo.getSql());
//                component.join.append(SqlUtils.SQL_LEFT_JOIN).append(SqlUtils.STR_BLANK).append(tmpName)
//                        .append(SqlUtils.STR_BLANK).append(relativeTb).append(SqlUtils.SQL_ON);
//            } else {
//                component.join.append(SqlUtils.SQL_LEFT_JOIN).append(SqlUtils.STR_LEFT_BRACKET)
//                        .append(subQuerySqlQo.getSql()).append(SqlUtils.STR_RIGHT_BRACKET).append(relativeTb)
//                        .append(SqlUtils.SQL_ON);
//            }
//
//            // on关联条件
//            StringBuilder on = new StringBuilder();
//            // 组织维度字段
//            appendPathcode(on, mainTb, relativeTb, dimensionList);
//            // 层级字段
//            if (null != replaceLevelColumn) {
//                if (!on.isEmpty()) {
//                    on.append(SqlUtils.SQL_AND);
//                }
//                on.append(mainTb).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_ID)
//                        .append(SqlUtils.STR_EQUAL).append(relativeTb).append(SqlUtils.STR_POINT)
//                        .append(Constants.DATASET_AREA_ID).append(SqlUtils.SQL_AND).append(mainTb)
//                        .append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_NAME).append(SqlUtils.STR_EQUAL)
//                        .append(relativeTb).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_NAME);
//            }
//            List<DatasetColumnQo> relativeDimensionLists = subQuerySqlQo.getDimensionList().stream()
//                    .filter(entity -> Constants.APP_TYPE_DIMENSION.equals(entity.getAppType()))
//                    .toList();
//            for (DatasetColumnQo dimensionQo : relativeDimensionLists) {
//                if (autoLevelGroup && isLevelColumn(dimensionQo)) {
//                    // 汇总字段不需要重复拼到on条件
//                    if (replaceLevelColumn != null &&
//                            (!dimensionQo.getTableId().equals(replaceLevelColumn.getMetaDataId())
//                                    || replaceLevelColumn.getOrgIdColumnCode().equalsIgnoreCase(dimensionQo.getColumnCode())
//                                    || replaceLevelColumn.getOrgNameColumnCode().equalsIgnoreCase(dimensionQo.getColumnCode()))) {
//                        continue;
//                    }
//                }
//                if (!on.isEmpty()) {
//                    on.append(SqlUtils.SQL_AND);
//                }
//                on.append(mainTb).append(SqlUtils.STR_POINT).append(dimensionQo.getAlias()).append(SqlUtils.STR_EQUAL)
//                        .append(relativeTb).append(SqlUtils.STR_POINT).append(dimensionQo.getAlias());
//            }
//            if (StringUtils.isEmpty(on)) {
//                on.append(" 1=1 ");
//            }
//            component.join.append(on);
//        }
//        mergeRelativeOutField(singleSql, mainTb, component.field, dimensionList, subSqlList, replaceLevelColumn);
//        return component.swapSql().toString();
//    }
//
//    /**
//     * 临时表名称生成
//     *
//     * @return 临时表名
//     */
//    protected synchronized String generateSubTmpTableName(String cycleType) {
//        // 任意获取一个库名？
//        String tmpTable = this.schemaMap.entrySet().iterator().next().getValue() + SqlUtils.STR_POINT;
//        // 一次性临时表不需要加账期
//        String suffix = Constants.SCHEDULE_LOOP_TYPE_O.equalsIgnoreCase(cycleType) ? "" : "_${acct}";
//        // oracle表名不能长于30，生成8位随机码
//        tmpTable = (tmpTable + "tmp_".concat(String.valueOf(RandomUtil.getSecureRandom().nextInt(90000000) + 10000000)).concat("_")
//                .concat(String.valueOf(getIncrementTbIndex())).concat(suffix));
//        return tmpTable;
//    }
//
//    /**
//     * 获取权限相关参数
//     */
//    protected DataPrivCtrlVo getDataPrivCtrlInfo() {
//        return null;
//    }
//
//
//    /**
//     * 组织维度字段
//     */
//    private void appendPathcode(StringBuilder sql,
//                                String mainTb,
//                                String relativeTb,
//                                List<DatasetColumnQo> dimensionList) {
//        boolean hasOrgId = false;
//        DatasetColumnQo orgDimColumn = SqlBuilderHelper.getOrgDimensionMinColumn(getDataPrivCtrlInfo());
//        for (DatasetColumnQo dimension : dimensionList) {
//            // 是否有拖到最细细度组织维度字段
//            if (!ObjectUtils.isEmpty(orgDimColumn) && orgDimColumn.getColumnId().equals(dimension.getColumnId())) {
//                hasOrgId = true;
//            }
//        }
//        if (SqlBuilderHelper.checkDataPriv(getDataPrivCtrlInfo())) {
//            // 为空、则没有拖到组织维度表的字段
//            if (!ObjectUtils.isEmpty(orgDimColumn) && !hasOrgId) {
//                sql.append(mainTb).append(SqlUtils.STR_POINT).append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.STR_EQUAL)
//                        .append(relativeTb).append(SqlUtils.STR_POINT).append(CUSTOM_ORG_ID_ALIAS);
//            }
//            if (!ObjectUtils.isEmpty(orgDimColumn)) {
//                if (!sql.isEmpty()) {
//                    sql.append(SqlUtils.SQL_AND);
//                }
//                sql.append(mainTb).append(SqlUtils.STR_POINT).append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.STR_EQUAL)
//                        .append(relativeTb).append(SqlUtils.STR_POINT).append(CUSTOM_PATH_CODE_ALIAS);
//            }
//        }
//    }
//
//    protected boolean isLevelColumn(DatasetColumnQo dimension) {
//        return autoLevelGroup &&
//                null != dimension.getIsOrgDimension() &&
//                dimension.getIsOrgDimension() > 0 &&
//                null != dimension.getOrgLevel() &&
//                dimension.getOrgLevel() > 0;
//    }
//
//    public DatasetColumnQo maxLevelColumn(List<DatasetColumnQo> dimensions) {
//        for (DatasetColumnQo dimension : dimensions) {
//            Integer isOrgDimension = dimension.getIsOrgDimension();
//            String autoLevelGroup = dimension.getAutoLevelGroup();
//            if (null != isOrgDimension &&
//                    isOrgDimension > 0 &&
//                    Constants.YES_VALUE_1.equals(autoLevelGroup)) {
//                return dimension;
//            }
//        }
//        return null;
//    }
//
//    private void mergeRelativeOutField(boolean singleSql,
//                                       String mainTb,
//                                       StringBuilder sql,
//                                       List<DatasetColumnQo> dimensionList,
//                                       List<SubQuerySqlQo> subSqlList,
//                                       OrgDimension replaceLevelColumn) {
//        StringBuilder fields = new StringBuilder();
//        // 划小架构表最细粒度字段
//        DatasetColumnQo orgDimColumn = SqlBuilderHelper.getOrgDimensionMinColumn(getDataPrivCtrlInfo());
//
//        DatasetColumnQo metric = null;
//        if (!CollectionUtils.isEmpty(subSqlList)) {
//            metric = subSqlList.get(0).getMetricList().get(0);
//        } else {
//            // subSqlList为空，临时fix
//            metric = dimensionList.stream().filter(col -> Constants.APP_TYPE_METRICS.equals(col.getAppType()))
//                    .findFirst().orElse(dimensionList.get(0));
//        }
//        boolean hasPeriod = false;
//        // 用于计算字段取表达式
//        Map<String, StringBuilder> expMap = new HashMap<>();
//        StringBuilder hivePeriodDim = new StringBuilder();
//        // 不需要注释 的 数据源类型
//        Set<String> ignoreNotesTypeSets = Set.of(Constants.DS_HIVE, Constants.DS_WHALEHOUSE);
//        for (DatasetColumnQo dimension : dimensionList) {
//            // 隐藏字段过滤
//            StringBuilder dimSb = new StringBuilder();
//            String dbType = getDbType();
//            // 单个sql的时候需要注释
//            String notes = !ignoreNotesTypeSets.contains(dbType) && singleSql
//                    ? SqlBuilderHelper.fieldNotes(dimension.getDataName())
//                    : "";
//            // 是否有拖到最细细度组织维度字段
//            if (!ObjectUtils.isEmpty(orgDimColumn) && orgDimColumn.getColumnId().equals(dimension.getColumnId())) {
//                haveOrgId(true);
//            }
//            if (Constants.YES_VALUE_1.equals(dimension.getIsAcct())) {
//                hasPeriod = true;
//                // 账期字段,前端只能拖一个账期
//                Column periodColumn = getPeriodColumnFromMetric(metric, dimension);
//                if (null != periodColumn) {
//                    dimSb.append(mainTb).append(SqlUtils.STR_POINT).append(dimension.getAlias());
//                } else {
//                    fieldPeriod(dimSb, dimension);
//                }
//                // 缓存
//                expMap.put(dimension.getAlias(), dimSb);
//                // hive数据源的账期字段抽取出来放到所有字段最后面
//                if (getDbType().equals(Constants.DS_HIVE) && !"O".equals(this.scheduleType)) {
//                    hivePeriodDim.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias())
//                            .append(SqlUtils.STR_DOT);
//                    continue;
//                }
//                fields.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
//                        .append(SqlUtils.STR_DOT);
//            } else if (Constants.APP_TYPE_DIMENSION.equals(dimension.getAppType())) {
//                // 维度字段
//                if (autoLevelGroup && isLevelColumn(dimension)) {
//                    if (dimension.getTableId().equals(replaceLevelColumn.getMetaDataId())
//                            && (dimension.getColumnCode().equals(replaceLevelColumn.getOrgIdColumnCode())
//                            || dimension.getColumnCode().equals(replaceLevelColumn.getOrgNameColumnCode()))) {
//                        appendLevelColumnField(dimSb, replaceLevelColumn, mainTb);
//                    } else {
//                        continue;
//                    }
//                    fields.append(dimSb).append(SqlUtils.STR_DOT);
//                } else {
//                    dimSb.append(mainTb).append(SqlUtils.STR_POINT).append(dimension.getAlias());
//                    fields.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
//                            .append(SqlUtils.STR_DOT);
//                }
//            } else if (Constants.APP_TYPE_METRICS.equals(dimension.getAppType())
//                    && CollectionUtils.isEmpty(dimension.getColumnGroup())) {
//                String tabName = mainTb;
//                SqlFuncEnum funcEnum = SqlFuncEnum.getFuncByName(dimension.getFunc());
//                if (funcEnum.equals(SqlFuncEnum.yoyGrowth) || funcEnum.equals(SqlFuncEnum.momGrowth)
//                        || funcEnum.equals(SqlFuncEnum.mmGrowth) || funcEnum.equals(SqlFuncEnum.pp)) {
//                    if (CollectionUtils.isEmpty(subSqlList)) {
//                        dimSb.append(tabName).append(SqlUtils.STR_POINT).append(dimension.getAlias());
//                    } else if (funcEnum.equals(SqlFuncEnum.pp)) {
//                        String subTabName = findRelativeAlias(dimension, subSqlList);
//                        String expression = SqlUtils.STR_LEFT_BRACKET + tabName + SqlUtils.STR_POINT
//                                + dimension.getAlias() + "-" + subTabName + SqlUtils.STR_POINT + dimension.getAlias()
//                                + SqlUtils.STR_RIGHT_BRACKET;
//                        // 默认值和小数点处理
//                        String convertMetric = this.metricIfNull(expression, dimension);
//                        dimSb.append(convertMetric);
//                    } else {
//                        String subTabName = findRelativeAlias(dimension, subSqlList);
//                        if (StringUtils.isNotEmpty(subTabName)) {
//                            String expression = SqlUtils.STR_LEFT_BRACKET + tabName + SqlUtils.STR_POINT
//                                    + dimension.getAlias() + "-" + subTabName + SqlUtils.STR_POINT + dimension.getAlias()
//                                    + SqlUtils.STR_RIGHT_BRACKET + "/" + subTabName + SqlUtils.STR_POINT
//                                    + dimension.getAlias();
//                            // 默认值和小数点处理
//                            String convertMetric = this
//                                    .metricIfNull("(" + SqlConvertUtils.divisionConvert(expression) + ")", dimension);
//                            dimSb.append(convertMetric);
//                        } else {
//                            dimSb.append(0);
//                        }
//                    }
//                } else if (funcEnum.equals(SqlFuncEnum.monthTotal) || funcEnum.equals(SqlFuncEnum.yearTotal)) {
//                    // 月/年累计
//                    if (!CollectionUtils.isEmpty(subSqlList)) {
//                        tabName = findRelativeAlias(dimension, subSqlList);
//                    }
//                    dimSb.append(tabName).append(SqlUtils.STR_POINT).append(dimension.getAlias());
//                } else if (!Constants.DimensionType.TYPE_MAIN.equalsIgnoreCase(dimension.getDimensionType())) {
//                    // fix 表名null
//                    if (!CollectionUtils.isEmpty(subSqlList)) {
//                        tabName = findRelativeAlias(dimension, subSqlList);
//                    }
//                    dimSb.append(tabName).append(SqlUtils.STR_POINT).append(dimension.getAlias());
//                } else {
//                    dimSb.append(tabName).append(SqlUtils.STR_POINT).append(dimension.getAlias());
//                }
//                if (!dimension.isHide() || !singleSql) {
//                    fields.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
//                            .append(SqlUtils.STR_DOT);
//                }
//            } else if (singleSql && !CollectionUtils.isEmpty(dimension.getColumnGroup())) {
//                String expression = SqlBuilderHelper.getColumnGroup(dimension.getColumnGroup(), expMap, null,
//                        singleSql);
//                String convertMetric = this.metricIfNull("(" + SqlConvertUtils.divisionConvert(expression) + ")",
//                        dimension);
//                dimSb.append(convertMetric);
//                fields.append(dimSb).append(SqlUtils.SQL_AS).append(dimension.getAlias()).append(notes)
//                        .append(SqlUtils.STR_DOT);
//            }
//            // 缓存
//            expMap.put(dimension.getAlias(), dimSb);
//        }
//
//        // hive的账期维度放后面
//        if (!hivePeriodDim.isEmpty()) {
//            fields.append(hivePeriodDim);
//        }
//        if (SqlBuilderHelper.checkDataPriv(getDataPrivCtrlInfo())) {
//            StringBuilder orgStr = new StringBuilder();
//            // 为空、则没有拖到组织维度表的字段
//            if (!checkHaveOrgId()) {
//                orgStr.append(mainTb).append(SqlUtils.STR_POINT).append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.SQL_AS)
//                        .append(CUSTOM_ORG_ID_ALIAS).append(SqlUtils.STR_DOT);
//            }
//            orgStr.append(mainTb).append(SqlUtils.STR_POINT).append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.SQL_AS)
//                    .append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.STR_DOT);
//            fields.insert(0, orgStr);
//        }
//
//        // 没有配置账期维度的调度sql且只有一个sql
//        if (singleSql && !hasPeriod && Constants.SQL_TASK.equals(this.sqlMode)
//                && !"O".equalsIgnoreCase(this.scheduleType)) {
//            // 没有账期
//            String periodStr;
//            if ("D".equals(this.scheduleType)) {
//                periodStr = "${day_id} as day_id,";
//            } else {
//                periodStr = "${month_id} as month_id,";
//            }
//
//            if (getDbType().equals(Constants.DS_HIVE)) {
//                fields.append(periodStr);
//            } else {
//                fields.insert(0, periodStr);
//            }
//        }
//
//        // 删掉逗号
//        if (!fields.isEmpty()) {
//            fields.deleteCharAt(fields.length() - 1);
//        }
//        sql.append(fields);
//    }
//
//    /**
//     * 获取数据源类型
//     *
//     * @return 数据源类型
//     */
//    public String getDbType() {
//        return null;
//    }
//
//    public void haveOrgId(boolean have) {
//        this.isHaveOrgId = have;
//    }
//
//    /**
//     * 从路径中的表获取账期字段
//     *
//     * @param metric    度量字段
//     * @param dimension 账期维度
//     * @return 返回账期字段
//     */
//    protected Column getPeriodColumnFromMetric(DatasetColumnQo metric, DatasetColumnQo dimension) {
//        Column periodColumn = this.allPeriod.get(metric.getTableId());
//        if (null != periodColumn && periodColumn.getCycleType().equals(dimension.getCycleType())) {
//            return periodColumn;
//        }
//        return null;
//    }
//
//    protected void fieldPeriod(StringBuilder fieldSql, DatasetColumnQo dimension) {
//        String periodStr = AcctTimeUtil.getAcctValAddQuotOutPubMode(this.scheduleType, dimension.getCycleType(),
//                dimension.getColumnType(), outPutMode);
//        fieldSql.append(periodStr);
//    }
//
//    protected void appendLevelColumnField(StringBuilder fieldSql, OrgDimension replaceLevelColumn, String tbAlias) {
//        // area_id
//        if (replaceLevelColumn.isAlias()) {
//            fieldSql.append(tbAlias).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_ID)
//                    .append(SqlUtils.STR_DOT);
//            // area_name
//            fieldSql.append(tbAlias).append(SqlUtils.STR_POINT).append(Constants.DATASET_AREA_NAME);
//        } else {
//            fieldSql.append(tbAlias).append(SqlUtils.STR_POINT).append(replaceLevelColumn.getOrgIdColumnCode())
//                    .append(SqlUtils.SQL_AS).append(Constants.DATASET_AREA_ID).append(SqlUtils.STR_DOT);
//            // area_name
//            fieldSql.append(tbAlias).append(SqlUtils.STR_POINT).append(replaceLevelColumn.getOrgNameColumnCode())
//                    .append(SqlUtils.SQL_AS).append(Constants.DATASET_AREA_NAME);
//        }
//    }
//
//    protected String metricIfNull(String metric, DatasetColumnQo column) {
//        StringBuilder outField = new StringBuilder();
//        GpColumnTypeEnum typeEnum = GpColumnTypeEnum.valueOf(column.getColumnType().toUpperCase());
//        if (!CollectionUtils.isEmpty(column.getColumnGroup())) {
//            if (typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.DECIMAL.name())
//                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.NUMERIC.name())) {
//                outField.append("round(").append("COALESCE(").append("cast((").append(metric).append(") as ")
//                        .append(column.getColumnType()).append(")").append(",0),").append(column.getColumnAccuracy())
//                        .append(")");
//            } else if (typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.INT.name())
//                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.INTEGER.name())
//                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.SMALLINT.name())
//                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.BIGINT.name())) {
//                outField.append("COALESCE(").append("cast((").append(metric).append(") as ")
//                        .append(column.getColumnType()).append(")").append(",0)");
//            } else {
//                outField.append("cast((").append(metric).append(") as ").append(column.getColumnType()).append(")");
//            }
//        } else {
//            if (!ObjectUtils.isEmpty(column.getColumnAccuracy())) {
//                outField.append("round(").append("COALESCE(").append(metric).append(",0),")
//                        .append(column.getColumnAccuracy()).append(")");
//            } else if (typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.INT.name())
//                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.INTEGER.name())
//                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.SMALLINT.name())
//                    || typeEnum.name().equalsIgnoreCase(GpColumnTypeEnum.BIGINT.name())) {
//                outField.append("COALESCE(").append(metric).append(",0)");
//            } else {
//                outField.append(metric);
//            }
//        }
//        return outField.toString();
//    }
//
//    private String findRelativeAlias(DatasetColumnQo dimension, List<SubQuerySqlQo> subSqlList) {
//        for (SubQuerySqlQo subQuerySqlQo : subSqlList) {
//            for (DatasetColumnQo columnQo : subQuerySqlQo.getMetricList()) {
//                if (dimension.getDimensionType().equals(subQuerySqlQo.getDimensionType())
//                        && columnQo.getAlias().equals(dimension.getAlias())) {
//                    return subQuerySqlQo.getTbAlisa();
//                }
//            }
//        }
//        return null;
//    }
//
//    public boolean checkHaveOrgId() {
//        return this.isHaveOrgId;
//    }
//
//    protected Map<String, Map<String, String>> joinTables(Map<String, List<MetricsDimensionPathVo>> pathsMap,
//                                                          String dataPrivPathKey, boolean needAppendPeriod, SqlComponent component) {
//        Map<String, Map<String, String>> alias = new HashMap<>();
//        // 判断是否有维表关联
//        boolean hasOrgTable = SqlBuilderHelper.hasOrgTable(getDataPrivCtrlInfo(), pathsMap);
//        // 遍历拼接表关联、组织权限字段输出和分组
//        Map<Long, String> hasAppend = new HashMap<>();
//        Map<String, String> publicAlias = new HashMap<>();
//        for (Map.Entry<String, List<MetricsDimensionPathVo>> entry : pathsMap.entrySet()) {
//            String pathKey = entry.getKey();
//            List<MetricsDimensionPathVo> pathVos = entry.getValue();
//            Map<String, String> tableAlias = new HashMap<>();
//            if (!MapUtil.isEmpty(publicAlias)) {
//                tableAlias.putAll(publicAlias);
//            }
//            boolean isPriv = entry.getKey().equals(dataPrivPathKey);
//            appendTableSql(isPriv, entry, hasAppend, needAppendPeriod, hasOrgTable, tableAlias, component);
//            SqlBuilderHelper.copyCommonAlias(tableAlias, pathVos, publicAlias);
//            alias.put(pathKey, tableAlias);
//        }
//        // 把公共别名设置进去
//        SqlBuilderHelper.copyPublicToPathAlias(publicAlias, alias);
//        return alias;
//    }
//
//    protected void appendTableSql(boolean isPriv,
//                                  Map.Entry<String, List<MetricsDimensionPathVo>> entry,
//                                  Map<Long, String> hasAppend,
//                                  boolean needAppendPeriod,
//                                  boolean hasOrgTable,
//                                  Map<String, String> tableAlias,
//                                  SqlComponent component) {
//        String tbPrefix = "tb";
//        // 全局条件中的账期值范围
//        Map<Long, String> periodMaps = getPeriodFromPathsAndCondList(entry.getValue());
//        for (MetricsDimensionPathVo path : entry.getValue()) {
//            // 过滤掉度量已经关联过的表、关联度量到维度之间的表
//            if (StringUtils.isNoneBlank(tableAlias.get(String.valueOf(path.getSrcTableId())))
//                    && StringUtils.isNoneBlank(tableAlias.get(String.valueOf(path.getTgtTableId())))) {
//                continue;
//            }
//            // TODO 固定一个 用于测试
//            String srcSchemaCode = "1";
//            // 首次拼接sql主表别名为空
//            if (ObjectUtils.isEmpty(tableAlias.get(String.valueOf(path.getSrcTableId())))) {
//                String srcName = tbPrefix.concat(String.valueOf(getIncrementTbIndex()));
//                tableAlias.put(String.valueOf(path.getSrcTableId()), srcName);
//                // 只有一张主表
//                if (entry.getValue().size() == 1 && (null == path.getTgtTableId() || null == path.getTgtTableCode())) {
//                    component.join.append(srcSchemaCode).append(SqlUtils.STR_POINT).append(path.getSrcTableCode())
//                            .append(SqlUtils.STR_BLANK).append(srcName);
//                    // 数据过滤拼接lanId
//                    if (checkLan()) {
//                        // 添加where条件
//                        ModelInfo table = this.modelInfoMap.get(path.getSrcTableId());
//                        appendLan(true, table, component.join, component.where, srcName);
//                    }
//                    // 数据过滤拼接provinceId
//                    if (checkProvince()) {
//                        // 添加where条件
//                        ModelInfo table = this.modelInfoMap.get(path.getSrcTableId());
//                        appendProvince(true, table, component.join, component.where, srcName);
//                    }
//                    // 权限控制拼接组织明细表
//                    if (SqlBuilderHelper.checkDataPriv(this.dataPrivCtrlInfo)) {
//                        appendOrgDetailsTable(hasOrgTable, component, tableAlias, hasAppend, tbPrefix, path, isPriv);
//                    }
//                } else {
//                    if (path.getTgtTableId() == null) {
//                        continue;
//                    }
//                    String tgtSchemaCode = this.getSchemaCodeByTableId(path.getTgtTableId());
//                    String tgtName = tableAlias.get(String.valueOf(path.getTgtTableId()));
//                    if (StringUtils.isBlank(tgtName)) {
//                        tgtName = tbPrefix.concat(String.valueOf(getIncrementTbIndex()));
//                        tableAlias.put(String.valueOf(path.getTgtTableId()), tgtName);
//                    }
//                    // schemaCode.表1 ta1 left join schemaCode.表2 tb2 on ta1.字段=ta2.字段
//                    if (!CollectionUtils.isEmpty(path.getMultiColumns())) {
//                        // 多维指标内部表关联
//                        component.join.append(srcSchemaCode).append(SqlUtils.STR_POINT).append(path.getSrcTableCode())
//                                .append(SqlUtils.STR_BLANK).append(srcName).append(SqlUtils.SQL_INNER_JOIN)
//                                .append(tgtSchemaCode).append(SqlUtils.STR_POINT).append(path.getTgtTableCode())
//                                .append(SqlUtils.STR_BLANK).append(tgtName).append(SqlUtils.SQL_ON);
//                        for (String columnCode : path.getMultiColumns()) {
//                            component.join.append(SqlUtils.STR_BLANK).append(srcName).append(SqlUtils.STR_POINT)
//                                    .append(columnCode).append(SqlUtils.STR_EQUAL).append(tgtName)
//                                    .append(SqlUtils.STR_POINT).append(columnCode).append(" and");
//                        }
//                        component.join.delete(component.join.lastIndexOf("and"), component.join.length());
//                    } else {
//                        component.join.append(srcSchemaCode).append(SqlUtils.STR_POINT).append(path.getSrcTableCode())
//                                .append(SqlUtils.STR_BLANK).append(srcName).append(SqlUtils.SQL_INNER_JOIN)
//                                .append(tgtSchemaCode).append(SqlUtils.STR_POINT).append(path.getTgtTableCode())
//                                .append(SqlUtils.STR_BLANK).append(tgtName).append(SqlUtils.SQL_ON);
//                        this.appendKeyColumns(path.getKeyColumnRelas(), component.join, srcName, tgtName);
//                    }
//
//                    // 关联两表的账期字段
//                    this.appendPeriodCond(component.join, tableAlias, path, periodMaps, needAppendPeriod);
//
//                    // 数据过滤拼接lanId
//                    if (checkLan()) {
//                        // 添加where条件
//                        ModelInfo srcTable = this.modelInfoMap.get(path.getSrcTableId());
//                        appendLan(true, srcTable, component.join, component.where, srcName);
//                        ModelInfo tgtTable = this.modelInfoMap.get(path.getTgtTableId());
//                        appendLan(false, tgtTable, component.join, component.where, tgtName);
//                    }
//                    // 数据过滤拼接provinceId
//                    if (checkProvince()) {
//                        // 添加where条件
//                        ModelInfo srcTable = this.modelInfoMap.get(path.getSrcTableId());
//                        appendProvince(true, srcTable, component.join, component.where, srcName);
//                        ModelInfo tgtTable = this.modelInfoMap.get(path.getTgtTableId());
//                        appendProvince(false, tgtTable, component.join, component.where, tgtName);
//                    }
//                    // 权限控制拼接组织明细表
//                    if (SqlBuilderHelper.checkDataPriv(this.dataPrivCtrlInfo)) {
//                        appendOrgDetailsTable(hasOrgTable, component, tableAlias, hasAppend, tbPrefix, path, isPriv);
//                    }
//                }
//            } else {
//                // 拼接从表
//                // 表别名缓存
//                if (null == path.getTgtTableId()) {
//                    continue;
//                }
//                String tgtSchemaCode = this.getSchemaCodeByTableId(path.getTgtTableId());
//                String tgtName = tableAlias.get(String.valueOf(path.getTgtTableId()));
//                if (StringUtils.isBlank(tgtName)) {
//                    tgtName = tbPrefix.concat(String.valueOf(getIncrementTbIndex()));
//                    tableAlias.put(String.valueOf(path.getTgtTableId()), tgtName);
//                }
//
//                // left join schemaCode.表3 tb3 on ta2.字段=ta3.字段
//                component.join.append(SqlUtils.SQL_INNER_JOIN).append(tgtSchemaCode).append(SqlUtils.STR_POINT)
//                        .append(path.getTgtTableCode()).append(SqlUtils.STR_BLANK).append(tgtName).append(SqlUtils.SQL_ON);
//                this.appendKeyColumns(path.getKeyColumnRelas(), component.join,
//                        tableAlias.get(String.valueOf(path.getSrcTableId())), tgtName);
//
//                // 关联两表的账期字段
//                this.appendPeriodCond(component.join, tableAlias, path, periodMaps, needAppendPeriod);
//
//                // 数据过滤拼接lanId
//                if (checkLan()) {
//                    ModelInfo tgtTable = this.modelInfoMap.get(path.getTgtTableId());
//                    appendLan(false, tgtTable, component.join, component.where, tgtName);
//                }
//                // 数据过滤拼接provinceId
//                if (checkProvince()) {
//                    ModelInfo tgtTable = this.modelInfoMap.get(path.getTgtTableId());
//                    appendProvince(false, tgtTable, component.join, component.where, tgtName);
//                }
//                // 权限控制拼接组织明细表
//                if (SqlBuilderHelper.checkDataPriv(this.dataPrivCtrlInfo)) {
//                    appendOrgDetailsTable(hasOrgTable, component, tableAlias, hasAppend, tbPrefix, path, isPriv);
//                }
//            }
//        }
//
//        // 没有划小架构表 只拼接pathcode一次
//        if (!hasOrgTable && component.field.indexOf(CUSTOM_PATH_CODE_ALIAS) == -1
//                && SqlBuilderHelper.checkDataPriv(this.dataPrivCtrlInfo)) {
//            component.field.append("'").append(getDefOrgPathCode()).append("'").append(SqlUtils.SQL_AS)
//                    .append(CUSTOM_PATH_CODE_ALIAS).append(SqlUtils.STR_DOT);
//        }
//    }
//
//    protected Map<Long, String> getPeriodFromPathsAndCondList(List<MetricsDimensionPathVo> paths) {
//        Map<Long, String> maps = new HashMap<>();
//        if (!CollectionUtils.isEmpty(this.params.getCondList())) {
//            for (DatasetConditionQo qo : this.params.getCondList()) {
//                if (Constants.YES_VALUE_1.equals(qo.getIsAcct()) && StringUtils.isNoneBlank(qo.getCycleType())) {
//                    StringBuilder strB;
//                    for (MetricsDimensionPathVo path : paths) {
//                        if (null != path.getSrcTableId() && null == maps.get(path.getSrcTableId())) {
//                            Column column = this.allPeriod.get(path.getSrcTableId());
//                            if (null != column && column.getCycleType().equals(qo.getCycleType())) {
//                                strB = new StringBuilder();
//                                String value = replaceValues(qo, column);
//                                strB.append(column.getColumnCode()).append(SqlUtils.STR_BLANK)
//                                        .append(qo.getCondOperator()).append(value);
//                                maps.put(path.getSrcTableId(), strB.toString());
//                            }
//                        }
//                        if (null != path.getTgtTableId() && null == maps.get(path.getTgtTableId())) {
//                            Column column = this.allPeriod.get(path.getTgtTableId());
//                            if (null != column && column.getCycleType().equals(qo.getCycleType())) {
//                                strB = new StringBuilder();
//                                String value = replaceValues(qo, column);
//                                strB.append(column.getColumnCode()).append(SqlUtils.STR_BLANK)
//                                        .append(qo.getCondOperator()).append(value);
//                                maps.put(path.getTgtTableId(), strB.toString());
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return maps;
//    }
//
//    protected String replaceValues(DatasetConditionQo qo, Column periodColumn) {
//        String[] splitArray;
//        if (qo.getCondValue().contains("~")) {
//            splitArray = qo.getCondValue().split("~");
//        } else {
//            splitArray = qo.getCondValue().split(",");
//        }
//        boolean isStr = SqlBuilderHelper.isStringType(getDbType(), periodColumn.getColumnType());
//        for (int i = 0; i < splitArray.length; i++) {
//            String str = splitArray[i];
//            splitArray[i] = isStr ? "'" + str + "'" : str;
//        }
//        String condOperator = qo.getCondOperator();
//        if (condOperator.startsWith("BETWEEN")) {
//            qo.setCondOperator("BETWEEN");
//        } else if ("IN".equalsIgnoreCase(condOperator)) {
//            return "(" + StringUtils.join(",", splitArray) + ")";
//        }
//        return StringUtils.join(" and ", splitArray);
//    }
//
//    /**
//     * 添加where条件 lan_id=xxx
//     */
//    private void appendLan(boolean isMain,
//                           ModelInfo table,
//                           StringBuilder joinSql,
//                           StringBuilder whereSql,
//                           String tableAlias) {
//        if (StrUtil.isNotEmpty(table.getBussinessAttr().getLanField())) {
//            Long defLan = AccountUtil.getPostLanId();
//            if (Constants.ROOT_ID.equals(defLan + "")) {
//                defLan = Long.parseLong(staticDataService.getDcSystemParamByCache(Constants.CONSUME_DEF_LAN_ID));
//            }
//            if (isMain) {
//                if (!whereSql.isEmpty()) {
//                    whereSql.append(SqlUtils.SQL_AND);
//                }
//                whereSql.append(tableAlias).append(SqlUtils.STR_POINT).append(table.getBussinessAttr().getLanField())
//                        .append(" = '").append(defLan).append("'");
//            } else {
//                joinSql.append(SqlUtils.SQL_AND).append(tableAlias).append(SqlUtils.STR_POINT)
//                        .append(table.getBussinessAttr().getLanField()).append(" = '").append(defLan).append("'");
//            }
//        }
//    }
//
//    /**
//     * 添加省份where条件 province_id=xxx
//     */
//    private void appendProvince(boolean isMain, ModelInfo table, StringBuilder joinSql, StringBuilder whereSql,
//                                String tableAlias) {
//        String provinceField = table.getBussinessAttr().getProvinceField();
//        if (StringUtil.isNotEmpty(provinceField)) {
//            String provinceId = staticDataService.getDcSystemParamByCache(Constants.CONSUME_DEF_PROV_ID);
//            if (StringUtil.isEmpty(provinceId)) {
//                provinceId = AccountUtil.getProvinceId();
//            }
//            if (StringUtil.isEmpty(provinceId)) {
//                return;
//            }
//            if (isMain) {
//                if (!whereSql.isEmpty()) {
//                    whereSql.append(SqlUtils.SQL_AND);
//                }
//                whereSql.append(tableAlias).append(SqlUtils.STR_POINT).append(provinceField).append(" = '")
//                        .append(provinceId).append("'");
//            } else {
//                joinSql.append(SqlUtils.SQL_AND).append(tableAlias).append(SqlUtils.STR_POINT).append(provinceField)
//                        .append(" = '").append(provinceId).append("'");
//            }
//        }
//    }
//
//}
