package org.zmz.c.service.dataopen.dataset;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zmz.c.mapper.dataopen.ObjColumnRelaMapper;
import org.zmz.c.mapper.dataopen.ObjInfoMapper;
import org.zmz.c.mapper.dataopen.ObjTableRelaMapper;
import org.zmz.c.pojo.dataopen.ObjColumnRela;
import org.zmz.c.pojo.dataopen.ObjInfo;
import org.zmz.c.pojo.dataopen.ObjTableRela;
import org.zmz.c.qo.dataopen.Column;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DataPrivCtrlVo;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetConditionQo;
import org.zmz.c.qo.dataopen.DatasetDetail;
import org.zmz.c.qo.dataopen.DatasetObjColumnVo;
import org.zmz.c.qo.dataopen.MetricsDimensionPathVo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.qo.dataopen.ModelInfoQo;
import org.zmz.c.qo.dataopen.ObjKeyColumnRelaVo;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaVo;
import org.zmz.c.service.dataopen.remote.DataCommonService;
import org.zmz.c.service.dataopen.sqltype.SqlBuilderHelper;
import org.zmz.c.utils.JsonUtil;
import org.zmz.c.utils.KeyValues;
import org.zmz.c.utils.MyStringUtil;
import org.zmz.c.vo.dataopen.dataset.ObjRelaTreeVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DatasetParamsBuildService {

    @Resource
    DatasetTermService datasetTermService;

    @Resource
    DatasetCacheService datasetCacheService;

    @Resource
    DataCommonService dataCommonService;

    @Resource
    ObjInfoMapper objInfoMapper;

    @Resource
    ObjTableRelaMapper objTableRelaMapper;

    @Resource
    ObjColumnRelaMapper objColumnRelaMapper;

    @Resource
    DatasetDataPrivService datasetDataPrivService;

    private final ThreadLocal<Map<Long, ObjRelaTreeVo>> metricsObjTree = new ThreadLocal<>();

    /**
     * 度量字段到所有维度的路径
     */
    private final ThreadLocal<Map<Long, Map<String, List<ObjRelaTreeVo>>>> metricsDimensionPathMap = new ThreadLocal<>();

    public void paramsBuild(DatasetDetail params) {
        // 术语处理，虚拟对象处理
        datasetTermService.buildDatasetColumns(params);
        // 第一组权限控制信息
        DataPrivCtrlVo firstDataPrivctrl = null;
        for (DatasetColumnAndConditionQo groupQo : params.getGroups()) {
            groupQo.setFirstDataPrivCtrlInfo(firstDataPrivctrl);
            this.paramsBuild(groupQo);
            // 多路径的组织字段以第一分组的为准
            if (firstDataPrivctrl == null && groupQo.getDataPrivCtrlInfo() != null) {
                firstDataPrivctrl = groupQo.getDataPrivCtrlInfo();
            }
        }
    }

    /**
     * sql拼接前的参数构建
     *
     * @param params 前端传参
     */
    public void paramsBuild(DatasetColumnAndConditionQo params) {
        List<DatasetColumnQo> dimensionList = new ArrayList<>();
        List<DatasetColumnQo> metricsList = new ArrayList<>();
        // 抽取度量和维度（共同的，不包含度量条件）
        extractMetricsAndDimensions(params, dimensionList, metricsList);

        // 所有对象关系数据
        // Long comAcctId = AccountUtil.getComAcctId();
        long comAcctId = 1021L;
        List<ObjKeyTableRelaVo> objKeyTableRelaList = datasetCacheService.getDatasetKeyRelaListInRedis(comAcctId);

        if (metricsList.isEmpty()) {
            // 只拖选了维度的时候需要找所有维度的共同起点路径
            metricsList = findMetrics(dimensionList, params);
            params.getCustomMetrics().addAll(metricsList);
        }

        // 构建度量对象树、获取relaId
        buildMetricObjTree(metricsList, objKeyTableRelaList);
        // 构建度量到所有维度的路径
        buildMetricToDimsPaths(metricsList, dimensionList);

        // 所有表的所有字段
        List<Column> allColumns = getColumns();

        // 表多主键
        Map<Long, List<Column>> allPrimaryMapLists = allColumns.stream()
            .filter(c -> KeyValues.YES_VALUE_1.equals(c.getBusinessKey()))
            .collect(Collectors.groupingBy(Column::getMetaDataId));
        // 所有字段
        Map<Long, Column> allColumnMap = allColumns.stream().collect(Collectors.toMap(Column::getColumnId, c -> c));
        // 表->表字段列表的map
        Map<Long, List<Column>> tableIdToAllColumnMap = allColumns.stream()
            .collect(Collectors.groupingBy(Column::getMetaDataId));

        if (CollUtil.isNotEmpty(metricsList)) {
            // 按度量归属表分组后的维度集合
            Map<Long, List<DatasetColumnQo>> metricDimensions = new HashMap<>();
            Map<Long, List<DatasetColumnQo>> metricGroups = metricsList.stream()
                .collect(Collectors.groupingBy(DatasetColumnQo::getTableId));
            Set<Long> tableIds = dimensionList.stream().map(DatasetColumnQo::getTableId).collect(Collectors.toSet());
            metricGroups.forEach((currMetricId, metricList) -> {
                Set<Long> dimTableIds = new HashSet<>(tableIds);
                List<DatasetColumnQo> metricToDimensions = new ArrayList<>(dimensionList);
                for (DatasetColumnQo metric : metricList) {
                    // 度量上的字段维度信息
                    if (CollUtil.isNotEmpty(metric.getCondList())) {
                        for (DatasetConditionQo t : metric.getCondList()) {
                            if ("simpleCond".equals(t.getCondType())) {
                                if (t.getColumnId() != null && !KeyValues.YES_VALUE_1.equals(t.getIsAcct())
                                    && !dimTableIds.contains(t.getTableId())
                                    && !metric.getTableId().equals(t.getTableId())) {
                                    dimTableIds.add(t.getTableId());
                                    DatasetColumnQo newDatasetColumnQo = new DatasetColumnQo();
                                    BeanUtils.copyProperties(t, newDatasetColumnQo,
                                        KeyValues.ENTITY_COPY_IGNORE_FIELDS);
                                    metricToDimensions.add(newDatasetColumnQo);
                                }
                            }
                        }
                    }
                }
                metricDimensions.put(currMetricId, metricToDimensions);
            });

            Map<Long, Map<String, List<MetricsDimensionPathVo>>> metricPathsMap = new HashMap<>();
            Map<Long, String> metricsPath = new HashMap<>();
            for (DatasetColumnQo metric : metricsList) {
                if (null != metricPathsMap.get(metric.getTableId())) {
                    metric.setPathsMap(metricPathsMap.get(metric.getTableId()));
                    metric.setPath(metricsPath.get(metric.getTableId()));
                    continue;
                }
                // step 1.一个度量到所有维度路径的对象集合。
                Map<String, List<MetricsDimensionPathVo>> pathsMap = metric.getPathsMap();
                List<MetricsDimensionPathVo> paths = metric.getPaths();
                Map<String, List<ObjRelaTreeVo>> dimensionToMetricMap = getDimensionPathFromThread(metric);

                List<DatasetColumnQo> allDims = metricDimensions.get(metric.getTableId());
                List<DatasetColumnQo> newAllDims = new ArrayList<>();
                // 防止同一个度量多次出现，path变化
                if (CollectionUtils.isEmpty(allDims)) {
                    // 维度为空
                    newAllDims.add(metric);
                }
                else {
                    newAllDims.addAll(allDims);
                }

                for (DatasetColumnQo dimension : newAllDims) {
                    List<MetricsDimensionPathVo> pathVoList = pathConverts(dimensionToMetricMap, metric, dimension,
                        allColumnMap, allPrimaryMapLists, params.getObjectId());
                    // 构建维度路径、这方法很重要
                    String path = SqlBuilderHelper.getConvertDimPath(metric, dimension);
                    // 主表和从表的维度，path一样，但是表关联不一样，因此要舍短取长
                    this.mergePathsMap(pathsMap, path, pathVoList);
                    paths.addAll(pathVoList);
                }
                // 去重、path有多个的去掉跟自己关联的path
                paths = paths.stream().distinct().collect(Collectors.toList());
                if (paths.size() > 1) {
                    paths.removeIf(p -> p.getTgtTableId() == null && p.getMultiColumns().isEmpty());
                }
                // step 2.找出度量到各个维度的最长路径(实际是去掉被包含的路径) -> 从表被包含的时候，会被路径包含pass掉
                List<String> longestPathList = findLongestPathList(pathsMap);
                // step 3.删掉多余的关联
                deleteLessPath(pathsMap, longestPathList);
                Map<String, List<MetricsDimensionPathVo>> newPathsMap = new HashMap<>(0);
                for (String path : longestPathList) {
                    newPathsMap.put(path, pathsMap.get(path));
                }
                // step 4.判断维度是否来自度量所在的路径
                // 构建度量的路径、这方法很重要、与方法SqlBuilderHelper.getConvertDimPath()强关联。
                String metricPath = SqlBuilderHelper.getMetricPath(metric);
                String longestMetricsPath = longestPathList.stream().filter(p -> p.startsWith(metricPath))
                    .max(Comparator.comparingInt(String::length)).orElse(null);
                List<String> otherPathKeyList = longestPathList.stream().filter(p -> !p.startsWith(metricPath))
                    .collect(Collectors.toList());
                // N端不为空就使用N端最长路径，N端包含了1端的最长路径
                if (CollUtil.isNotEmpty(otherPathKeyList)) {
                    // 度量来自选择对象或者1端或者主分析对象
                    if ("1".equals(metric.getRelaType()) || "0".equals(metric.getRelaType())) {
                        // 合并最长的多个维度路径
                        List<MetricsDimensionPathVo> totalLongestPathVos = new ArrayList<>(0);
                        longestPathList.forEach(key -> {
                            totalLongestPathVos.addAll(newPathsMap.get(key));
                        });
                        // 去重
                        List<MetricsDimensionPathVo> distinctLongestPathVos = totalLongestPathVos.stream().distinct()
                            .collect(Collectors.toList());
                        // path有多个的话，可以去掉跟自己关联的path
                        if (distinctLongestPathVos.size() > 1) {
                            distinctLongestPathVos
                                .removeIf(p -> p.getTgtTableId() == null && p.getMultiColumns().isEmpty());
                        }
                        for (MetricsDimensionPathVo longestPathVo : distinctLongestPathVos) {
                            longestPathVo.setMetricsInnerPath(Constants.YES_VALUE_1);
                        }
                        newPathsMap.clear();
                        for (String otherPath : otherPathKeyList) {
                            newPathsMap.put(otherPath, distinctLongestPathVos);
                        }
                        //
                        newPathsMap.put(longestMetricsPath, distinctLongestPathVos);
                        metric.setPathsMap(newPathsMap);
                    }
                    else {
                        // N端
                        metric.setPathsMap(newPathsMap);
                    }
                }
                metric.setPathsMap(newPathsMap);
                metric.setPath(metricPath);
            }
        }

        List<DatasetColumnQo> totalMetricsList = new ArrayList<>(metricsList);
        // 处理数据权限路径
        this.handleDataPrivPath(totalMetricsList, params, tableIdToAllColumnMap);
        log.info("权限控制之后的pathMap：{}", JSONObject.toJSONString(params));
        // 用组织维度表路径组成临时表的路径
        // 各个度量关联的组织维表为起点到所有维度表的路径构建成一个临时表
        this.handleTempPath(totalMetricsList, params);
        this.removeThreadParams();
    }

    /**
     * 从缓存中读取度量到维度的路径集合
     *
     * @param metrics 度量字段
     * @return 度量到所有维度的对象路径集合
     */
    private Map<String, List<ObjRelaTreeVo>> getDimensionPathFromThread(DatasetColumnQo metrics) {
        Map<Long, Map<String, List<ObjRelaTreeVo>>> metricsMap = metricsDimensionPathMap.get();
        if (MapUtil.isEmpty(metricsMap)) {
            return Collections.emptyMap();
        }
        return metricsMap.get(metrics.getTableId());
    }

    /**
     * 对象树转表关联路径(对象内表之间用表的主键关联、对象之间用表obj_key_table_rela中关系字段关联,可能是从表字段或主表字段关联)
     *
     * @param dimensionToMetricMap 所有维度到一个度量的路径
     * @param metric 度量
     * @param dimension 维度
     * @param allColumnMap 所有字段
     * @param allPrimaryMapLists 所有表的多主键字段
     * @param currObjId 当前主分析对象id
     * @return 返回表关联路径
     */
    private List<MetricsDimensionPathVo> pathConverts(Map<String, List<ObjRelaTreeVo>> dimensionToMetricMap,
        DatasetColumnQo metric, DatasetColumnQo dimension, Map<Long, Column> allColumnMap,
        Map<Long, List<Column>> allPrimaryMapLists, Long currObjId) {
        List<MetricsDimensionPathVo> pathVoList = new ArrayList<>();
        // 取出一条路径遍历创建路径path
        String dimPath = StringUtils.isBlank(dimension.getPath()) ? "-" + dimension.getTableId() : dimension.getPath();
        List<ObjRelaTreeVo> relaTreeVos = dimensionToMetricMap.get(dimPath);
        // 只有一个则维度与度量同一对象
        if (relaTreeVos.size() == 1) {
            // 度量与维度同一对象、同对象不同表用主键字段关联
            firstNode(metric, dimension, allPrimaryMapLists, pathVoList);
        }
        else {
            // 维度跟度量不是同一个对象
            Long preObjId = null;
            Long preTableId = null;
            String preTableCode = null;
            for (int i = 0; i < relaTreeVos.size(); i++) {
                ObjRelaTreeVo objRelaTreeVo = relaTreeVos.get(i);
                // 关联对象开始、第一个是根节点对象要跳过
                if (0 == i) {
                    continue;
                }
                // 度量表跟起点的对象关联表不是同一表用主键关联
                if ((1 == i) && !metric.getTableId().equals(objRelaTreeVo.getOneToNTableId())) {
                    // 多维指标a->b,a->c
                    if ("2".equals(metric.getRelaType()) && DatasetObjColumnVo.INDEX.equals(metric.getDataType())) {
                        // 多维指标起点关联
                        pathVoList.add(multiDimPath(metric, allColumnMap, objRelaTreeVo));
                    }
                    // 如果是相同一端的2端，a->b,c->b（a和c同一对象，a为从表c为主表）暂时根据字段类型区分
                    else {
                        ObjRelaTreeVo objRelaVo = new ObjRelaTreeVo();

                        List<Column> primaryKeys = allPrimaryMapLists.get(metric.getTableId());
                        List<Column> onePrimaryKeys = allPrimaryMapLists.get(objRelaTreeVo.getOneToNTableId());
                        List<ObjKeyColumnRelaVo> primaryKeysColumn = transformPrimaryKeys(primaryKeys, onePrimaryKeys);
                        objRelaVo.setKeyColumnRelas(primaryKeysColumn);
                        objRelaVo.setOneToNObjectId(metric.getObjectId());
                        objRelaVo.setOneToNTableId(metric.getTableId());
                        objRelaVo.setOneToNTableCode(metric.getTableCode());

                        objRelaVo.setRelaKeyObjectId(objRelaTreeVo.getObjectId());
                        objRelaVo.setRelaTableId(objRelaTreeVo.getOneToNTableId());
                        objRelaVo.setRelaTableCode(objRelaTreeVo.getOneToNTableCode());

                        pathVoList.add(srcTableJoinTgtTable("1", objRelaVo));
                    }
                }

                // 对象之间关联前一对象和下一对象时候不是同一张表用他们的主键关联(同一个对象)
                if (objRelaTreeVo.getOneToNObjectId().equals(preObjId)
                    && !objRelaTreeVo.getOneToNTableId().equals(preTableId)) {
                    ObjRelaTreeVo newObjRelaTreeVo = new ObjRelaTreeVo();
                    // 对象与上一个对象关联的表主键字段
                    List<Column> primaryKeys = allPrimaryMapLists.get(preTableId);
                    List<Column> onePrimaryKeys = allPrimaryMapLists.get(objRelaTreeVo.getOneToNTableId());

                    List<ObjKeyColumnRelaVo> primaryKeysColumn = transformPrimaryKeys(primaryKeys, onePrimaryKeys);
                    newObjRelaTreeVo.setKeyColumnRelas(primaryKeysColumn);
                    newObjRelaTreeVo.setOneToNObjectId(preObjId);
                    newObjRelaTreeVo.setOneToNTableId(preTableId);
                    newObjRelaTreeVo.setOneToNTableCode(preTableCode);

                    newObjRelaTreeVo.setRelaKeyObjectId(preObjId);
                    newObjRelaTreeVo.setRelaTableId(objRelaTreeVo.getRelaTableId());
                    newObjRelaTreeVo.setRelaTableCode(objRelaTreeVo.getRelaTableCode());

                    pathVoList.add(srcTableJoinTgtTable("0", newObjRelaTreeVo));
                }

                // 添加对象关联
                String innerPath = "0";
                if (currObjId.equals(objRelaTreeVo.getObjectId())) {
                    // 对象内部关联
                    innerPath = "1";
                }
                if (objRelaTreeVo.getRelaTableId() == null) {
                    // 虚拟对象内部关联
                    innerPath = "1";
                    DatasetColumnQo virtualObjColumn = getVirtualObjColumn(objRelaTreeVo);
                    metric.getVirtualObjColumnMap().put(objRelaTreeVo.getRelaKeyObjectId(), virtualObjColumn);
                }
                pathVoList.add(srcTableJoinTgtTable(innerPath, objRelaTreeVo));

                // 暂存下一个对象的关联字段
                preObjId = objRelaTreeVo.getObjectId();
                preTableId = objRelaTreeVo.getRelaTableId();
                preTableCode = objRelaTreeVo.getRelaTableCode();

                // 关联对象到最后一个需要判断维度是否为对象的主表，否则需要主表跟维度归属表关联
                if (i == relaTreeVos.size() - 1) {
                    // 维度对象内表之间用主键关联
                    if (objRelaTreeVo.getRelaTableId() != null
                        && !objRelaTreeVo.getRelaTableId().equals(dimension.getTableId())) {
                        ObjRelaTreeVo newObjRelaTreeVo = new ObjRelaTreeVo();

                        List<Column> primaryKeys = allPrimaryMapLists.get(objRelaTreeVo.getRelaTableId());
                        List<Column> onePrimaryKeys = allPrimaryMapLists.get(dimension.getTableId());
                        List<ObjKeyColumnRelaVo> primaryKeysColumn = transformPrimaryKeys(primaryKeys, onePrimaryKeys);
                        newObjRelaTreeVo.setKeyColumnRelas(primaryKeysColumn);
                        newObjRelaTreeVo.setOneToNObjectId(objRelaTreeVo.getRelaKeyObjectId());
                        newObjRelaTreeVo.setOneToNTableId(objRelaTreeVo.getRelaTableId());
                        newObjRelaTreeVo.setOneToNTableCode(objRelaTreeVo.getRelaTableCode());

                        newObjRelaTreeVo.setRelaKeyObjectId(dimension.getObjectId());
                        newObjRelaTreeVo.setRelaTableId(dimension.getTableId());
                        newObjRelaTreeVo.setRelaTableCode(dimension.getTableCode());

                        pathVoList.add(srcTableJoinTgtTable("0", newObjRelaTreeVo));
                    }
                }
            }
        }
        return pathVoList;
    }

    /**
     * 多维指标对象内部表关联
     *
     * @param metric 多维度量
     * @param allColumnMap 所有字段
     * @param objRelaTreeVo 对象关系
     * @return 路径对象
     */
    private MetricsDimensionPathVo multiDimPath(DatasetColumnQo metric, Map<Long, Column> allColumnMap,
        ObjRelaTreeVo objRelaTreeVo) {
        ObjInfo objInfo = objInfoMapper.selectById(metric.getObjectId());
        String primaryKeys = objInfo.getPrimaryKey();
        String[] primaryIds = primaryKeys.split(",");

        MetricsDimensionPathVo vo = new MetricsDimensionPathVo();
        vo.setSrcObjectId(metric.getObjectId());
        vo.setSrcTableId(metric.getTableId());
        vo.setSrcTableCode(metric.getTableCode());

        log.info("objRelaTreeVo.getObjectId {}", objRelaTreeVo.getObjectId());
        vo.setTgtObjectId(metric.getObjectId());
        vo.setTgtTableId(objRelaTreeVo.getRelaTableId());
        vo.setTgtTableCode(objRelaTreeVo.getRelaTableCode());
        vo.setMetricsInnerPath("1");
        for (String primaryId : primaryIds) {
            Column primaryKey = allColumnMap.get(Long.parseLong(primaryId));
            vo.getMultiColumns().add(primaryKey.getColumnCode());
        }
        return vo;
    }

    private void firstNode(DatasetColumnQo metric, DatasetColumnQo dimension,
        Map<Long, List<Column>> allPrimaryMapLists, List<MetricsDimensionPathVo> pathVoList) {
        // 虚拟对象
        ObjInfo objInfo = objInfoMapper.selectById(metric.getObjectId());
        if (!metric.getTableId().equals(dimension.getTableId())
            && !ObjCreateType.OBJ_CREATE_TYPE_VIRTUAL.equalsIgnoreCase(objInfo.getCreateType())) {
            ObjRelaTreeVo objRelaVo = new ObjRelaTreeVo();

            List<Column> primaryKeys = allPrimaryMapLists.get(metric.getTableId());
            List<Column> onePrimaryKeys = allPrimaryMapLists.get(dimension.getTableId());
            List<ObjKeyColumnRelaVo> primaryKeysColumn = transformPrimaryKeys(primaryKeys, onePrimaryKeys);
            objRelaVo.setKeyColumnRelas(primaryKeysColumn);
            objRelaVo.setOneToNObjectId(metric.getObjectId());
            objRelaVo.setOneToNTableId(metric.getTableId());
            objRelaVo.setOneToNTableCode(metric.getTableCode());

            objRelaVo.setRelaKeyObjectId(dimension.getObjectId());
            objRelaVo.setRelaTableId(dimension.getTableId());
            objRelaVo.setRelaTableCode(dimension.getTableCode());

            pathVoList.add(srcTableJoinTgtTable("1", objRelaVo));
        }
        else {
            // 度量和维度来自于同一表
            MetricsDimensionPathVo path = new MetricsDimensionPathVo();
            path.setMetricsInnerPath("1");
            path.setSrcTableId(metric.getTableId());
            path.setSrcTableCode(metric.getTableCode());
            path.setSrcObjectId(metric.getObjectId());

            pathVoList.add(path);

            if (ObjCreateType.OBJ_CREATE_TYPE_VIRTUAL.equalsIgnoreCase(objInfo.getCreateType())) {
                DatasetColumnQo virtualObjColumn = getVirtualObjColumn(metric, dimension);
                if (virtualObjColumn != null) {
                    metric.getVirtualObjColumnMap().put(metric.getObjectId(), virtualObjColumn);
                }
            }
        }
    }

    private DatasetColumnQo getVirtualObjColumn(DatasetColumnQo metric, DatasetColumnQo dimension) {
        ObjColumnRela objColumnRela = objColumnRelaMapper.getObjColumnByCondition(metric.getObjectId(),
            metric.getTableId(), dimension.getColumnCode());
        if (objColumnRela != null) {
            DatasetColumnQo virtualObjColumn = new DatasetColumnQo();
            virtualObjColumn.setAppType(Constants.APP_TYPE_DIMENSION);
            virtualObjColumn.setTableId(metric.getTableId());
            virtualObjColumn.setTableCode(metric.getTableCode());
            virtualObjColumn.setColumnId(objColumnRela.getColumnId());
            virtualObjColumn.setColumnCode(dimension.getColumnCode());
            return virtualObjColumn;
        }
        return null;
    }

    private static DatasetColumnQo getVirtualObjColumn(ObjRelaTreeVo objRelaTreeVo) {
        DatasetColumnQo virtualObjColumn = new DatasetColumnQo();
        ObjKeyColumnRelaVo keyColumnRela = objRelaTreeVo.getKeyColumnRelas().get(0);
        virtualObjColumn.setAppType(Constants.APP_TYPE_DIMENSION);
        virtualObjColumn.setTableId(objRelaTreeVo.getOneToNTableId());
        virtualObjColumn.setTableCode(objRelaTreeVo.getOneToNTableCode());

        virtualObjColumn.setColumnId(keyColumnRela.getColumnId());
        virtualObjColumn.setColumnCode(keyColumnRela.getColumnCode());
        return virtualObjColumn;
    }

    /**
     * 对象关联对象
     *
     * @param innerPath 1/0
     * @param objRelaTreeVo 对象关联对象
     * @return 返回对象路径关系
     */
    private MetricsDimensionPathVo srcTableJoinTgtTable(String innerPath, ObjRelaTreeVo objRelaTreeVo) {
        MetricsDimensionPathVo path = new MetricsDimensionPathVo();
        path.setMetricsInnerPath(innerPath);
        path.setSrcTableId(objRelaTreeVo.getOneToNTableId());
        path.setSrcTableCode(objRelaTreeVo.getOneToNTableCode());
        path.setSrcObjectId(objRelaTreeVo.getOneToNObjectId());

        path.setTgtTableId(objRelaTreeVo.getRelaTableId());
        path.setTgtTableCode(objRelaTreeVo.getRelaTableCode());
        path.setTgtObjectId(objRelaTreeVo.getRelaKeyObjectId());

        path.setKeyColumnRelas(objRelaTreeVo.getKeyColumnRelas());
        return path;
    }

    private List<ObjKeyColumnRelaVo> transformPrimaryKeys(List<Column> primaryKeys, List<Column> onePrimaryKeys) {
        if (primaryKeys.size() == 1) {
            ObjKeyColumnRelaVo vo = new ObjKeyColumnRelaVo();
            vo.setColumnId(primaryKeys.get(0).getColumnId());
            vo.setColumnCode(primaryKeys.get(0).getColumnCode());
            vo.setRelaColumnId(onePrimaryKeys.get(0).getColumnId());
            vo.setRelaColumnCode(onePrimaryKeys.get(0).getColumnCode());
            List<ObjKeyColumnRelaVo> singletonList = new ArrayList<>();
            singletonList.add(vo);
            // 这个集合会导致后续不能进行remove/add等操作Collections.singletonList(vo);
            return singletonList;
        }
        Map<String, Long> oneColumnMap = onePrimaryKeys.stream()
            .collect(Collectors.toMap(Column::getColumnCode, Column::getColumnId));
        return primaryKeys.stream().map(input -> {
            ObjKeyColumnRelaVo vo = new ObjKeyColumnRelaVo();
            vo.setColumnId(input.getColumnId());
            vo.setColumnCode(input.getColumnCode());
            vo.setRelaColumnId(oneColumnMap.get(input.getColumnCode()));
            vo.setRelaColumnCode(input.getColumnCode());
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 找出度量到所有维度的最长路径
     *
     * @param pathsMap 路径map
     * @return 最长路径集合
     */
    private List<String> findLongestPathList(Map<String, List<MetricsDimensionPathVo>> pathsMap) {
        // 判断如果有重复的包含的过滤掉

        List<String> pathKeyList = new ArrayList<>(pathsMap.keySet());
        List<String> lists = new ArrayList<>();
        while (!CollectionUtils.isEmpty(pathKeyList)) {
            List<String> deletePathKeyList = new ArrayList<>();
            String longestPathKey = pathKeyList.get(0);
            deletePathKeyList.add(longestPathKey);
            for (String pathKey : pathKeyList) {
                // path包含即是相同的起点
                // 有相同的起点后,保留长路径那条放到集合中,不是相同起点的保留下一次遍历 -> 路径包含，可能短的有从表，长的没有，此时不能删
                if (longestPathKey.startsWith(pathKey)) {
                    // 合并路径包含的表关系集合
                    this.mergePathsMap(pathsMap, longestPathKey, pathsMap.get(pathKey));
                    // 短的被包含的删掉
                    deletePathKeyList.add(pathKey);
                }
                if (pathKey.startsWith(longestPathKey)) {
                    // 合并路径包含的表关系集合
                    this.mergePathsMap(pathsMap, pathKey, pathsMap.get(longestPathKey));
                    // 长的保留
                    longestPathKey = pathKey;
                    deletePathKeyList.add(pathKey);
                }

            }
            // 最长一条路径放到集合
            lists.add(longestPathKey);
            // 删除短的可能多条路径
            pathKeyList.removeAll(deletePathKeyList);
        }
        return lists;

    }

    /**
     * 输出缓存参数
     */
    private void removeThreadParams() {
        this.metricsDimensionPathMap.remove();
        this.metricsObjTree.remove();
    }

    /**
     * 不同的路径上的组织维表，组成临时表的路径
     *
     * @param metricsList 度量
     * @param params 前端请求参数
     */
    public void handleTempPath(List<DatasetColumnQo> metricsList, DatasetColumnAndConditionQo params) {
        DataPrivCtrlVo dataPrivCtrlInfo = params.getDataPrivCtrlInfo();
        // 不需要权限控制就不用搞临时表了
        if (!dataPrivCtrlInfo.isDataPrivCtrl() || !"task".contentEquals(params.getSqlMode())) {
            return;
        }

        List<ModelInfo> orgDimensionModelInfoList = dataPrivCtrlInfo.getOrgDimensionModelInfoList();
        log.info("handleTempPath里的dataPrivCtrlInfo信息：{}", JsonUtil.toJson(dataPrivCtrlInfo));
        // 找出组织维表id列表
        List<Long> orgDimensionMetaDataIdList = orgDimensionModelInfoList.stream()
            .map(m -> m.getMetaDataInfo().getMetaDataId()).toList();

        // 度量中各个路径用到的临时表
        Map<String, Map<String, List<MetricsDimensionPathVo>>> tempTablesMap = new HashMap<>();
        Map<String, Map<String, List<MetricsDimensionPathVo>>> mainTablesMap = new HashMap<>();
        // 度量中各个路径与临时表关联的对象关系
        Map<String, Map<String, MetricsDimensionPathVo>> mainTablesToTempTablesMap = new HashMap<>();

        // 遍历度量拿出所有到维度的路径，抽出路径上组织维表的那一段作为临时表
        for (DatasetColumnQo metrics : metricsList) {
            Map<String, List<MetricsDimensionPathVo>> tempTablePaths = new HashMap<>();
            Map<String, List<MetricsDimensionPathVo>> mainTablePaths = new HashMap<>();
            Map<String, MetricsDimensionPathVo> mainTablesToTempTablesPaths = new HashMap<>();

            for (Map.Entry<String, List<MetricsDimensionPathVo>> entry : metrics.getPathsMap().entrySet()) {
                String dimPath = entry.getKey();
                // 找出用到的组织维表
                List<MetricsDimensionPathVo> deletePathList = new ArrayList<>();
                for (MetricsDimensionPathVo pathVo : entry.getValue()) {
                    if (orgDimensionMetaDataIdList.contains(pathVo.getSrcTableId())) {
                        deletePathList.add(pathVo);
                    }
                }
                List<MetricsDimensionPathVo> mainTbPaths = copyPathList(entry.getValue());
                // 从主表路径mainTbPaths中删除组织维表
                if (CollUtil.isNotEmpty(deletePathList)) {
                    mainTbPaths.removeAll(deletePathList);
                    // 维度路径,临时表
                    tempTablePaths.put(dimPath, deletePathList);

                    // 如果只剩下与组织维度表的关联，把tgt的置空，防止他关联组织维度表
                    if (mainTbPaths.size() == 1) {
                        MetricsDimensionPathVo pathVo = mainTbPaths.get(0);
                        MetricsDimensionPathVo mainToTempPath = (MetricsDimensionPathVo) SerializationUtils
                            .clone(pathVo);
                        if (orgDimensionMetaDataIdList.contains(pathVo.getTgtTableId())) {
                            this.setPathVoProperty(pathVo);
                        }
                        mainTablesToTempTablesPaths.put(dimPath, mainToTempPath);
                    }
                    // 如果最后一个path是与组织维度表的关联，就去掉这个path，防止他关联组织维度表
                    if (mainTbPaths.size() > 1) {
                        MetricsDimensionPathVo pathVo = mainTbPaths.get(mainTbPaths.size() - 1);
                        if (pathVo.getTgtTableId() != null
                            && orgDimensionMetaDataIdList.contains(pathVo.getTgtTableId())) {
                            mainTbPaths.remove(pathVo);
                            mainTablesToTempTablesPaths.put(dimPath, pathVo);
                        }
                        else {
                            // 当有临时表，维度和度量有相同表的，这里要特殊处理，防止取到相同度量表和维度表的空关联关系
                            pathVo = mainTbPaths.get(mainTbPaths.size() - 2);
                            if (pathVo.getTgtTableId() != null
                                && orgDimensionMetaDataIdList.contains(pathVo.getTgtTableId())) {
                                mainTbPaths.remove(pathVo);
                                mainTablesToTempTablesPaths.put(dimPath, pathVo);
                            }
                        }
                    }
                }
                else {
                    // 若只关联了一个组织维度表，该表是目标表
                    MetricsDimensionPathVo pathVo = mainTbPaths.stream()
                        .filter(p -> orgDimensionMetaDataIdList.contains(p.getTgtTableId())).findFirst().orElse(null);
                    if (pathVo != null) {
                        MetricsDimensionPathVo mainToTempPath = (MetricsDimensionPathVo) SerializationUtils
                            .clone(pathVo);
                        // BeanUtils.copyProperties(pathVo, mainToTempPath);
                        // 构建组织维度临时表的path（只有一张表）
                        MetricsDimensionPathVo orgDimensionTempPath = new MetricsDimensionPathVo();
                        orgDimensionTempPath.setSrcObjectId(pathVo.getTgtObjectId());
                        orgDimensionTempPath.setSrcTableCode(pathVo.getTgtTableCode());
                        orgDimensionTempPath.setSrcTableId(pathVo.getTgtTableId());
                        orgDimensionTempPath.setMetricsInnerPath(Constants.YES_VALUE_1);
                        // 如果只剩下与组织维度表的关联，把tgt的置空，防止他关联组织维度表
                        if (mainTbPaths.size() == 1) {
                            this.setPathVoProperty(pathVo);
                        }
                        // 如果最后一个path是与组织维度表的关联，就去掉这个path，防止他关联组织维度表
                        if (mainTbPaths.size() > 1) {
                            pathVo = mainTbPaths.get(mainTbPaths.size() - 1);
                            if (pathVo.getTgtTableId() != null
                                && orgDimensionMetaDataIdList.contains(pathVo.getTgtTableId())) {
                                mainTbPaths.remove(pathVo);
                            }
                        }

                        mainTablesToTempTablesPaths.put(dimPath, mainToTempPath);
                        tempTablePaths.put(dimPath, Collections.singletonList(orgDimensionTempPath));
                    }
                }
                // 删除后的mainTbPaths为度量主干表
                mainTablePaths.put(dimPath, mainTbPaths);
            }
            tempTablesMap.put(metrics.getPath(), tempTablePaths);
            mainTablesMap.put(metrics.getPath(), mainTablePaths);
            mainTablesToTempTablesMap.put(metrics.getPath(), mainTablesToTempTablesPaths);
        }
        params.setTempTablesMap(tempTablesMap);
        params.setMainTablesMap(mainTablesMap);
        params.setMainTablesToTempTablesMap(mainTablesToTempTablesMap);
    }

    /**
     * 拷贝路径
     *
     * @param pathVoList 源路径
     * @return 返回拷贝的新路径
     */
    private List<MetricsDimensionPathVo> copyPathList(List<MetricsDimensionPathVo> pathVoList) {
        List<MetricsDimensionPathVo> tempPathVoList = new ArrayList<>();
        for (MetricsDimensionPathVo pathVo : pathVoList) {
            tempPathVoList.add(SerializationUtils.clone(pathVo));
        }
        return tempPathVoList;
    }

    /**
     * 路径相同，但是可能有的表关系有从表，保留从表； 路径包含，但是可能短的关系有从表，长的没有，这时候要合并从表
     */
    private void mergePathsMap(Map<String, List<MetricsDimensionPathVo>> pathsMap, String path,
        List<MetricsDimensionPathVo> pathVoList) {
        // 去重、path有多个的去掉跟自己关联的path
        List<MetricsDimensionPathVo> distinctPathVoList = pathVoList.stream().distinct().collect(Collectors.toList());
        if (distinctPathVoList.size() > 1) {
            distinctPathVoList.removeIf(p -> p.getTgtTableId() == null && p.getMultiColumns().isEmpty());
        }
        // 主表和从表的维度，path一样，但是表关联不一样，因此要舍短取长
        List<MetricsDimensionPathVo> paths = pathsMap.getOrDefault(path, new ArrayList<>());
        paths.addAll(distinctPathVoList);
        paths = paths.stream().distinct().collect(Collectors.toList());
        pathsMap.put(path, paths);
    }

    /**
     * 根据数据权限增加组织明细路径
     */
    public void handleDataPrivPath(List<DatasetColumnQo> metricsList, DatasetColumnAndConditionQo params,
        Map<Long, List<Column>> tableIdToColumnMap) {
        DataPrivCtrlVo dataPrivCtrlInfo = datasetDataPrivService.isDataPrivCtrl(params);
        if (dataPrivCtrlInfo.isDataPrivCtrl()) {
            DatasetColumnQo existOrgDimensionColumn = dataPrivCtrlInfo.getExistOrgDimensionColumn();
            DatasetColumnQo addOrgDimensionColumn = dataPrivCtrlInfo.getAddOrgDimensionColumn();

            ModelInfo orgDimensionModelInfo = dataPrivCtrlInfo.getOrgDimensionModelInfo();
            ModelInfo orgModelInfo = dataPrivCtrlInfo.getOrgModelInfo();
            // 组织明细表，默认它单主键
            Column orgModelPrimaryColumn = orgModelInfo.findBusinessPrimaryColumns().get(0);
            // 需要处理数据权限的表
            List<ModelInfo> dataPrivModelList = dataPrivCtrlInfo.getDataPrivModelList();
            Map<Long, ModelInfo> dataPrivModelMap = dataPrivModelList.stream()
                .collect(Collectors.toMap(d -> d.getMetaDataInfo().getMetaDataId(), d -> d));
            // 没有选择组织维度字段的情况，直接用表的组织字段与组织明细表进行关联
            if (existOrgDimensionColumn == null) {
                for (DatasetColumnQo metrics : metricsList) {
                    Map<Long, MetricsDimensionPathVo> orgInfoPathsMap = metrics.getOrgInfoPathsMap();
                    List<MetricsDimensionPathVo> paths = metrics.getPaths();
                    for (MetricsDimensionPathVo path : paths) {
                        if (dataPrivModelMap.containsKey(path.getSrcTableId())) {
                            appendAuthObj(orgModelInfo, orgModelPrimaryColumn, orgInfoPathsMap,
                                dataPrivModelMap.get(path.getSrcTableId()),
                                tableIdToColumnMap.get(path.getSrcTableId()));
                        }
                        if (dataPrivModelMap.containsKey(path.getTgtTableId())) {
                            appendAuthObj(orgModelInfo, orgModelPrimaryColumn, orgInfoPathsMap,
                                dataPrivModelMap.get(path.getTgtTableId()),
                                tableIdToColumnMap.get(path.getTgtTableId()));
                        }
                    }

                    // 判断出拥有组织字段的表是来自哪个路径，只需要在该路径上sql进行权限控制
                    Map<String, List<MetricsDimensionPathVo>> pathsMap = metrics.getPathsMap();
                    String dataPrivPathKey = null;
                    for (Map.Entry<String, List<MetricsDimensionPathVo>> entry : pathsMap.entrySet()) {
                        List<MetricsDimensionPathVo> value = entry.getValue();
                        for (MetricsDimensionPathVo pathVo : value) {
                            if (dataPrivModelMap.containsKey(pathVo.getSrcTableId())
                                || dataPrivModelMap.containsKey(pathVo.getTgtTableId())) {
                                dataPrivPathKey = entry.getKey();
                                metrics.setDataPrivPathKey(dataPrivPathKey);
                                break;
                            }
                        }
                    }

                    // 重新调整map里的路径顺序，把需要权限控制的路径放在第一个位置
                    this.hanelPrivPathOrder(metrics, pathsMap, dataPrivPathKey);
                }
            }
            // 选择了组织维度字段
            else {
                // 如果有多个组织维度表，不能直接用当前表直接关联组织维度表了
                if (dataPrivCtrlInfo.getOrgDimensionModelInfoList().size() > 1) {
                    // 组织层级表，默认单主键
                    Column orgDimensionModelPrimaryColumn = orgDimensionModelInfo.findBusinessPrimaryColumns().get(0);
                    for (DatasetColumnQo metrics : metricsList) {
                        // 判断出组织维度字段是来自哪个路径，只需要在该路径上sql进行权限控制
                        this.setDataPrivPathKey(metrics, existOrgDimensionColumn);

                        Map<Long, MetricsDimensionPathVo> orgInfoPathsMap = metrics.getOrgInfoPathsMap();
                        // 1、与最细的组织维度表关联
                        this.setDataPrivPathProperty(orgDimensionModelInfo, orgModelInfo, orgModelPrimaryColumn,
                            orgDimensionModelPrimaryColumn, orgInfoPathsMap);

                        // 2、与有组织字段的表进行关联
                        List<MetricsDimensionPathVo> paths = metrics.getPaths();
                        for (MetricsDimensionPathVo path : paths) {
                            if (dataPrivModelMap.containsKey(path.getSrcTableId())) {
                                appendAuthObj(orgModelInfo, orgModelPrimaryColumn, orgInfoPathsMap,
                                    dataPrivModelMap.get(path.getSrcTableId()),
                                    tableIdToColumnMap.get(path.getSrcTableId()));
                            }
                            if (dataPrivModelMap.containsKey(path.getTgtTableId())) {
                                appendAuthObj(orgModelInfo, orgModelPrimaryColumn, orgInfoPathsMap,
                                    dataPrivModelMap.get(path.getTgtTableId()),
                                    tableIdToColumnMap.get(path.getTgtTableId()));
                            }
                        }
                    }
                }
                else {
                    for (DatasetColumnQo metrics : metricsList) {
                        // 判断出组织维度字段是来自哪个路径，只需要在该路径上sql进行权限控制
                        this.setDataPrivPathKey(metrics, existOrgDimensionColumn);

                        Map<Long, MetricsDimensionPathVo> orgInfoPathsMap = metrics.getOrgInfoPathsMap();
                        List<MetricsDimensionPathVo> paths = metrics.getPaths();
                        // 找出用户表和组织维度表的关联关系，取出组织维度表的关联字段
                        MetricsDimensionPathVo orgDimensionRelaPath = paths.stream().filter(p -> {
                            return dataPrivModelMap.containsKey(p.getSrcTableId())
                                && p.getTgtTableId().equals(orgDimensionModelInfo.getMetaDataInfo().getMetaDataId());
                        }).findFirst().orElse(null);

                        if (orgDimensionRelaPath != null) {
                            List<ObjKeyColumnRelaVo> relaColumns = orgDimensionRelaPath.getKeyColumnRelas();
                            List<String> relaColumnLists = relaColumns.stream()
                                .map(ObjKeyColumnRelaVo::getRelaColumnCode).toList();
                            // 如果组织维度拖选的最细粒度与用户表配置的组织关联层级一样时，组织明细表只需要拼接一次（与组织维度表关联）
                            // todo 与维度组织表关联，会导致不拼用户表权限条件,暂时将||改成&&，使以下if不执行
                            if (relaColumnLists.contains(existOrgDimensionColumn.getColumnCode())
                                && (addOrgDimensionColumn != null
                                    && relaColumnLists.contains(addOrgDimensionColumn.getColumnCode()))) {

                                MetricsDimensionPathVo dataPrivPath = new MetricsDimensionPathVo();
                                dataPrivPath.setSrcObjectId(null);
                                dataPrivPath.setSrcTableId(orgDimensionRelaPath.getTgtTableId());
                                dataPrivPath.setSrcTableCode(orgDimensionRelaPath.getTgtTableCode());

                                dataPrivPath.setTgtObjectId(null);
                                dataPrivPath.setTgtTableId(orgModelInfo.getMetaDataInfo().getMetaDataId());
                                dataPrivPath.setTgtTableCode(orgModelInfo.getMetaDataInfo().getMetaDataCode());

                                ObjKeyColumnRelaVo relaColumn = new ObjKeyColumnRelaVo();
                                relaColumn
                                    .setColumnCode(addOrgDimensionColumn != null ? addOrgDimensionColumn.getColumnCode()
                                        : existOrgDimensionColumn.getColumnCode());
                                relaColumn
                                    .setColumnId(addOrgDimensionColumn != null ? addOrgDimensionColumn.getColumnId()
                                        : existOrgDimensionColumn.getColumnId());
                                relaColumn.setRelaColumnCode(orgModelPrimaryColumn.getColumnCode());
                                relaColumn.setRelaColumnId(orgModelPrimaryColumn.getColumnId());

                                dataPrivPath.getKeyColumnRelas().add(relaColumn);

                                orgInfoPathsMap.put(dataPrivPath.getSrcTableId(), dataPrivPath);
                            }
                            // 如果组织维度拖选的最细粒度与用户表配置的组织关联层级不一样，组织明细表需要拼接两次
                            else {
                                DatasetColumnQo orgDimensionColumn = existOrgDimensionColumn;
                                if (addOrgDimensionColumn != null) {
                                    orgDimensionColumn = addOrgDimensionColumn;
                                }
                                // 1、与组织维度表关联
                                MetricsDimensionPathVo dataPrivPath1 = new MetricsDimensionPathVo();
                                dataPrivPath1.setSrcObjectId(null);
                                dataPrivPath1.setSrcTableId(orgDimensionColumn.getTableId());
                                dataPrivPath1.setSrcTableCode(orgDimensionColumn.getTableCode());

                                dataPrivPath1.setTgtObjectId(null);
                                dataPrivPath1.setTgtTableId(orgModelInfo.getMetaDataInfo().getMetaDataId());
                                dataPrivPath1.setTgtTableCode(orgModelInfo.getMetaDataInfo().getMetaDataCode());

                                ObjKeyColumnRelaVo relaColumn1 = new ObjKeyColumnRelaVo();
                                relaColumn1.setColumnCode(orgDimensionColumn.getColumnCode());
                                relaColumn1.setColumnId(orgDimensionColumn.getColumnId());
                                relaColumn1.setRelaColumnCode(orgModelPrimaryColumn.getColumnCode());
                                relaColumn1.setRelaColumnId(orgModelPrimaryColumn.getColumnId());
                                dataPrivPath1.getKeyColumnRelas().add(relaColumn1);

                                orgInfoPathsMap.put(dataPrivPath1.getSrcTableId(), dataPrivPath1);

                                // 2、与用户表关联
                                MetricsDimensionPathVo dataPrivPath2 = new MetricsDimensionPathVo();
                                dataPrivPath2.setSrcObjectId(null);
                                dataPrivPath2.setSrcTableId(orgDimensionRelaPath.getSrcTableId());
                                dataPrivPath2.setSrcTableCode(orgDimensionRelaPath.getSrcTableCode());

                                dataPrivPath2.setTgtObjectId(null);
                                dataPrivPath2.setTgtTableId(orgModelInfo.getMetaDataInfo().getMetaDataId());
                                dataPrivPath2.setTgtTableCode(orgModelInfo.getMetaDataInfo().getMetaDataCode());

                                ObjKeyColumnRelaVo relaColumn2 = new ObjKeyColumnRelaVo();
                                ObjKeyColumnRelaVo oneVo = orgDimensionRelaPath.getKeyColumnRelas().get(0);
                                relaColumn2.setColumnCode(oneVo.getColumnCode());
                                relaColumn2.setColumnId(oneVo.getColumnId());
                                relaColumn2.setRelaColumnCode(orgModelPrimaryColumn.getColumnCode());
                                relaColumn2.setRelaColumnId(orgModelPrimaryColumn.getColumnId());
                                dataPrivPath2.getKeyColumnRelas().add(relaColumn2);

                                orgInfoPathsMap.put(dataPrivPath2.getSrcTableId(), dataPrivPath2);
                            }
                        }
                    }
                }
            }

            Map<Long, MetricsDimensionPathVo> dataPrivCtrlOrgInfoPathsMap = new HashMap<>();
            // 自定义度量(只拖了维度)的时候需要取表的组织明细
            for (DatasetColumnQo metric : metricsList) {
                Map<Long, MetricsDimensionPathVo> orgInfoPathsMap = metric.getOrgInfoPathsMap();
                if (MapUtil.isNotEmpty(orgInfoPathsMap)) {
                    for (Map.Entry<Long, MetricsDimensionPathVo> entry : orgInfoPathsMap.entrySet()) {
                        dataPrivCtrlOrgInfoPathsMap.computeIfAbsent(entry.getKey(), k -> entry.getValue());
                    }
                }
            }
            // 将每个相同表的组织明细表路径加起来
            List<DatasetColumnQo> columnList = params.getColumnList();
            if (CollUtil.isNotEmpty(columnList)) {
                for (DatasetColumnQo datasetColumnQo : columnList) {
                    Map<Long, MetricsDimensionPathVo> orgInfoPathsMap = datasetColumnQo.getOrgInfoPathsMap();
                    if (MapUtil.isNotEmpty(orgInfoPathsMap)) {
                        for (Map.Entry<Long, MetricsDimensionPathVo> entry : orgInfoPathsMap.entrySet()) {
                            dataPrivCtrlOrgInfoPathsMap.computeIfAbsent(entry.getKey(), k -> entry.getValue());
                        }
                    }
                }
            }
            if (MapUtil.isNotEmpty(dataPrivCtrlOrgInfoPathsMap)) {
                dataPrivCtrlInfo.setOrgInfoPathsMap(dataPrivCtrlOrgInfoPathsMap);
            }
        }
        params.setDataPrivCtrlInfo(dataPrivCtrlInfo);
    }

    private void appendAuthObj(ModelInfo orgModelInfo, Column orgModelPrimaryColumn,
        Map<Long, MetricsDimensionPathVo> orgInfoPathsMap, ModelInfo dataPrivModelInfo, List<Column> columnList) {
        Column orgField = columnList.stream()
            .filter(c -> c.getColumnCode().equals(dataPrivModelInfo.getBussinessAttr().getOrgField())).findAny()
            .orElse(null);
        this.setDataPrivPathProperty(dataPrivModelInfo, orgModelInfo, orgModelPrimaryColumn, orgField, orgInfoPathsMap);
    }

    /**
     * 设置数据权限路径
     */
    private void setDataPrivPathProperty(ModelInfo orgDimensionModelInfo, ModelInfo orgModelInfo,
        Column orgModelPrimaryColumn, Column orgField, Map<Long, MetricsDimensionPathVo> orgInfoPathsMap) {
        MetricsDimensionPathVo dataPrivPath = new MetricsDimensionPathVo();
        dataPrivPath.setSrcTableId(orgDimensionModelInfo.getMetaDataInfo().getMetaDataId());
        dataPrivPath.setSrcTableCode(orgDimensionModelInfo.getMetaDataInfo().getMetaDataCode());
        dataPrivPath.setSrcObjectId(null);

        dataPrivPath.setTgtTableId(orgModelInfo.getMetaDataInfo().getMetaDataId());
        dataPrivPath.setTgtTableCode(orgModelInfo.getMetaDataInfo().getMetaDataCode());
        dataPrivPath.setTgtObjectId(null);

        // 关联字段
        ObjKeyColumnRelaVo columnMapping = new ObjKeyColumnRelaVo();
        columnMapping.setColumnCode(orgField.getColumnCode());
        columnMapping.setColumnId(orgField.getColumnId());
        columnMapping.setRelaColumnCode(orgModelPrimaryColumn.getColumnCode());
        columnMapping.setRelaColumnId(orgModelPrimaryColumn.getColumnId());
        dataPrivPath.getKeyColumnRelas().add(columnMapping);
        orgInfoPathsMap.put(dataPrivPath.getSrcTableId(), dataPrivPath);
    }

    /**
     * 构建度量到所有维度的路径写入缓存
     *
     * @param metricsList 所有度量
     * @param dimensionList 所有维度
     */
    private void buildMetricToDimsPaths(List<DatasetColumnQo> metricsList, List<DatasetColumnQo> dimensionList) {
        for (DatasetColumnQo metrics : metricsList) {
            ObjRelaTreeVo objRelaTree = getMetricsObjTreeFromThread(metrics.getObjectId());
            List<DatasetColumnQo> allDims = getAllDimension(metrics, dimensionList);
            if (CollectionUtils.isEmpty(allDims)) {
                // 增加维度为空支持
                allDims.add(metrics);
            }
            for (DatasetColumnQo dimension : allDims) {
                List<ObjRelaTreeVo> objRelaList = findObjPathFromObjRelaTree(objRelaTree, metrics, dimension);
                if (objRelaList.isEmpty()) {
                    throw new IllegalStateException("对象路径不能为空 metrics getDataName  " + metrics.getDataName()
                        + " dimension getDataName  " + dimension.getDataName());
                }
                putDimensionPathToThread(metrics, dimension, objRelaList);
            }
        }
    }

    private List<DatasetColumnQo> getAllDimension(DatasetColumnQo metrics, List<DatasetColumnQo> dimensionList) {
        List<DatasetColumnQo> allDims = new ArrayList<>(dimensionList);
        if (!CollectionUtils.isEmpty(metrics.getCondList())) {
            for (DatasetConditionQo datasetConditionQo : metrics.getCondList()) {
                // 虚拟出来的账期字段不用当维度
                if ("simpleCond".equals(datasetConditionQo.getCondType())) {
                    if (datasetConditionQo.getColumnId() != null
                        && !KeyValues.YES_VALUE_1.equals(datasetConditionQo.getIsAcct())
                        && !metrics.getTableId().equals(datasetConditionQo.getTableId())) {
                        DatasetColumnQo newDatasetColumnQo = new DatasetColumnQo();
                        BeanUtils.copyProperties(datasetConditionQo, newDatasetColumnQo,
                            KeyValues.ENTITY_COPY_IGNORE_FIELDS);
                        allDims.add(newDatasetColumnQo);
                    }
                }
            }
        }
        return allDims;
    }

    /**
     * 缓存度量对应的表到所有维度的路径集合
     *
     * @param metrics 度量字段
     * @param dimension 维度字段
     * @param objRelaList 度量到所有维度的对象路径集合
     */
    private void putDimensionPathToThread(DatasetColumnQo metrics, DatasetColumnQo dimension,
        List<ObjRelaTreeVo> objRelaList) {
        if (objRelaList.isEmpty()) {
            return;
        }
        String dimPath = StringUtils.isBlank(dimension.getPath()) ? "-" + dimension.getTableId() : dimension.getPath();
        Map<Long, Map<String, List<ObjRelaTreeVo>>> metricDimMap = metricsDimensionPathMap.get();
        if (MapUtil.isEmpty(metricDimMap)) {
            metricDimMap = new HashMap<>();
            Map<String, List<ObjRelaTreeVo>> maps = new HashMap<>();
            maps.put(dimPath, objRelaList);
            metricDimMap.put(metrics.getTableId(), maps);
            metricsDimensionPathMap.set(metricDimMap);
        }
        else {
            Map<String, List<ObjRelaTreeVo>> maps = metricDimMap.get(metrics.getTableId());
            if (MapUtil.isEmpty(maps)) {
                maps = new HashMap<>();
            }
            // 添加一条度量到维度的路径
            maps.put(dimPath, objRelaList);
            metricDimMap.put(metrics.getTableId(), maps);
        }
    }

    /**
     * 查询所有字段信息
     *
     * @return 返回所有字段信息
     */
    private List<Column> getColumns() {
        // 所有对象下的所有表
        List<ObjTableRela> objTableRelaList = getObjTableRelas();
        List<Long> tableIds = objTableRelaList.stream().map(ObjTableRela::getMetaDataId).filter(Objects::nonNull)
            .toList();
        ModelInfoQo modelInfoQo = new ModelInfoQo();
        modelInfoQo.setMetaDataIdList(new ArrayList<>(tableIds));
        List<ModelInfo> modelInfoList = dataCommonService.queryAllModelInfoBatch(modelInfoQo);
        List<Column> allColumns = new ArrayList<>();
        modelInfoList.forEach(model -> allColumns.addAll(model.getColumnList()));
        return allColumns;
    }

    /**
     * @return 返回所有对象下的关联的表
     */
    private List<ObjTableRela> getObjTableRelas() {
        Set<Long> allObjectIds = new HashSet<>(0);
        Map<Long, Map<String, List<ObjRelaTreeVo>>> objDimsMap = this.metricsDimensionPathMap.get();
        for (Map.Entry<Long, Map<String, List<ObjRelaTreeVo>>> entry : objDimsMap.entrySet()) {
            for (Map.Entry<String, List<ObjRelaTreeVo>> listEntry : entry.getValue().entrySet()) {
                for (ObjRelaTreeVo objRelaTreeVo : listEntry.getValue()) {
                    if (null != objRelaTreeVo.getObjectId()) {
                        allObjectIds.add(objRelaTreeVo.getObjectId());
                    }
                    if (null != objRelaTreeVo.getOneToNObjectId()) {
                        allObjectIds.add(objRelaTreeVo.getOneToNObjectId());
                    }
                    if (null != objRelaTreeVo.getRelaKeyObjectId()) {
                        allObjectIds.add(objRelaTreeVo.getRelaKeyObjectId());
                    }
                }
            }
        }
        // 查询对象关联的表
        Map<String, Object> objTableRelaParams = new HashMap<>();
        objTableRelaParams.put("objectIds", new ArrayList<>(allObjectIds));
        objTableRelaParams.put("statusCd", Constants.STATUS_CD_00A);
        return objTableRelaMapper.getObjTableRelaList(objTableRelaParams);
    }

    /**
     * 构建度量对象关系树
     *
     * @param metricsList 度量
     * @param objKeyTableRelaList 所有对象关系
     */
    private void buildMetricObjTree(List<DatasetColumnQo> metricsList, List<ObjKeyTableRelaVo> objKeyTableRelaList) {
        for (DatasetColumnQo metrics : metricsList) {
            ObjRelaTreeVo objRelaTree = buildObjRelaTree(metrics.getObjectId(), objKeyTableRelaList);
            putMetricsObjTreeToThread(metrics.getObjectId(), objRelaTree);
            log.info("度量:{},对象树:{}", metrics.getDataName(), JSONObject.toJSONString(objRelaTree));
        }
    }

    /**
     * 写入度量对象树到缓存
     *
     * @param objId 对象id
     * @param objRelaTree 对象树
     */
    private void putMetricsObjTreeToThread(Long objId, ObjRelaTreeVo objRelaTree) {
        Map<Long, ObjRelaTreeVo> treeVoMap = metricsObjTree.get();
        if (null == treeVoMap) {
            treeVoMap = new HashMap<>();
            metricsObjTree.set(treeVoMap);
        }
        treeVoMap.put(objId, objRelaTree);
    }

    /**
     * 当前对象节点字段补全
     *
     * @param currentObjRela 当前节点
     * @param objKeyTableRelaList 对象关系所有数据
     * @param objectIds 对象id
     */
    private void currentObjRelaTreeField(ObjRelaTreeVo currentObjRela, List<ObjKeyTableRelaVo> objKeyTableRelaList,
        Set<Long> objectIds) {
        // 防止配置对象关系导致的死循环
        if (objectIds.contains(currentObjRela.getObjectId())) {
            return;
        }
        objectIds.add(currentObjRela.getObjectId());
        for (ObjKeyTableRelaVo objKeyTableRela : objKeyTableRelaList) {
            if (objKeyTableRela.getObjectId().equals(currentObjRela.getObjectId())) {
                ObjRelaTreeVo child = new ObjRelaTreeVo();
                child.setObjectId(objKeyTableRela.getRelaKeyObjectId());
                child.setOneToNObjectId(currentObjRela.getObjectId());
                child.setOneToNTableId(objKeyTableRela.getMetaDataId());
                child.setOneToNTableCode(objKeyTableRela.getTableCode());

                child.setRelaKeyObjectId(objKeyTableRela.getRelaKeyObjectId());
                child.setRelaId(objKeyTableRela.getRelaId());

                child.setRelaTableId(objKeyTableRela.getRelaTableId());
                child.setRelaTableCode(objKeyTableRela.getRelaTableCode());

                if (CollUtil.isNotEmpty(objKeyTableRela.getKeyColumnRelas())) {
                    child.getKeyColumnRelas().addAll(objKeyTableRela.getKeyColumnRelas());
                }

                currentObjRela.getChildren().add(child);
            }
        }
    }

    /**
     * 查询维度到度量的路径集合(可能有多条路径)
     *
     * @param objRelaTree 对象树
     * @param dimension 维度
     * @return 度量到维度的对象集合 维度ObjectId-List(List)
     */
    private List<List<ObjRelaTreeVo>> metricsToDimensionObjRelaMap(ObjRelaTreeVo objRelaTree,
        DatasetColumnQo dimension) {
        List<List<ObjRelaTreeVo>> paths = new ArrayList<>();
        List<ObjRelaTreeVo> path = new ArrayList<>();
        // 迭代查找所有度量到维度的对象路径
        iterateObjRelaTreeFindDim(objRelaTree, paths, path, dimension);
        return paths;
    }

    /**
     * 根据维度迭代对象树获取当前维度的路径树
     *
     * @param objRelaTree 迭代当前对象树
     * @param paths 路径个数集合
     * @param path 当前路径
     * @param dimension 维度
     */
    private void iterateObjRelaTreeFindDim(ObjRelaTreeVo objRelaTree, List<List<ObjRelaTreeVo>> paths,
        List<ObjRelaTreeVo> path, DatasetColumnQo dimension) {
        // 复制当前节点到路径里面
        ObjRelaTreeVo copyObjRela = new ObjRelaTreeVo();
        BeanUtils.copyProperties(objRelaTree, copyObjRela, "children");
        path.add(copyObjRela);

        // 跟维度对象id一致了就是一条对象路径
        if (dimension.getObjectId().equals(objRelaTree.getObjectId())) {
            List<ObjRelaTreeVo> onePath = new ArrayList<>(path);
            paths.add(onePath);
        }
        if (CollUtil.isNotEmpty(objRelaTree.getChildren())) {
            for (ObjRelaTreeVo child : objRelaTree.getChildren()) {
                iterateObjRelaTreeFindDim(child, paths, new ArrayList<>(path), dimension);
            }
        }
    }

    /**
     * 查询度量到维度的对象路径(叶子节点的集合)
     *
     * @param objRelaTree 对象树
     * @param metrics 度量
     * @param dimension 维度
     * @return 度量到维度的对象集合
     */
    private List<ObjRelaTreeVo> findObjPathFromObjRelaTree(ObjRelaTreeVo objRelaTree, DatasetColumnQo metrics,
        DatasetColumnQo dimension) {
        // 度量到某一个维度的路径、可能有多条路径
        List<List<ObjRelaTreeVo>> pathLists = metricsToDimensionObjRelaMap(objRelaTree, dimension);
        // 度量和维度来自于同对象
        if (pathLists.size() == 1 && (metrics.getRelaType().equals(dimension.getRelaType())
            || Constants.OBJ_TREE_RELA_TYPE_V.equalsIgnoreCase(dimension.getRelaType()))) {
            return pathLists.get(0);
        }
        Map<String, List<ObjRelaTreeVo>> matchPaths = new HashMap<>();
        for (List<ObjRelaTreeVo> paths : pathLists) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size(); i++) {
                if (i == 0) {
                    continue;
                }
                sb.append(paths.get(i).getRelaId()).append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            String thePath = sb.toString();
            matchPaths.put(thePath, paths);
        }

        String strPath;
        if ("n".equalsIgnoreCase(metrics.getRelaType())) {
            // 度量path被构建成了n+度量tableId+度量path
            String oldMetricPath = metrics.getPath();
            if (oldMetricPath.startsWith("n")) {
                oldMetricPath = MyStringUtil.getSubStr(oldMetricPath, ",", 2);
            }
            // N端度量到1端维度
            if ("0".equals(dimension.getRelaType())) {
                strPath = oldMetricPath;
            }
            // N端字段与其一端树上的一端（relaType=n）多路径
            else if ("n".equals(dimension.getRelaType())) {
                strPath = dimension.getPath();
            }
            else {
                strPath = oldMetricPath + "," + dimension.getPath();
            }
        }
        else if ("2".equalsIgnoreCase(metrics.getRelaType())) {
            // relaType为2(切换对象的套餐度量或者多维指标度量)
            // 与主分析对象拥有相同一端的对象度量到1端维度
            if (matchPaths.size() == 1) {
                return matchPaths.entrySet().iterator().next().getValue();
            }
            // 用户主分析对象，度量选套餐，选择划小架构作维度（套餐-渠道-划小结构，套餐-最小单元-划小架构，套餐-揽收人-渠道-划小架构），选择到了用户-划小架构路径的情况，此时路径匹配为空，随机取一条
            // 后期可以优化，前端弹框选择具体分析路径，或者从拖拽逻辑杜绝
            // 取最短的路径

            // 与主分析对象拥有相同一端的对象度量到1端维度
            strPath = MyStringUtil.getHighMatchStr(dimension.getPath(), matchPaths.keySet());
            if (StringUtils.isEmpty(strPath)) {
                List<Map.Entry<String, List<ObjRelaTreeVo>>> list = new ArrayList<>(matchPaths.entrySet());
                list.sort(Comparator.comparingInt(o -> o.getValue().size()));
                return list.get(0).getValue();
            }
        }
        else {
            // 1端度量不会有到N端的维度(目录树做了拖拽限制)、只有1端度量到1端维度、直接用维度的路径即可
            strPath = dimension.getPath();
            // 可能一端度量到维度有多条路径，因此要判断包含关系
            if (!matchPaths.containsKey(strPath)) {
                strPath = MyStringUtil.getHighMatchStr(strPath, matchPaths.keySet());
                if (StringUtils.isEmpty(strPath)) {
                    List<Map.Entry<String, List<ObjRelaTreeVo>>> list = new ArrayList<>(matchPaths.entrySet());
                    list.sort(Comparator.comparingInt(o -> o.getValue().size()));
                    return list.get(0).getValue();
                }
            }
        }
        if (matchPaths.containsKey(strPath)) {
            return matchPaths.get(strPath);
        }

        return Collections.emptyList();
    }

    /**
     * 构建对象树(都是当前对象1端的对象树) A-B-C-D-E A-A1-B 上面有两条路径(A-B-C-D-E)和(A-A1-B-C-D-E) 需要产品侧抉择选哪一条路径
     *
     * @param currentObjectId 当前度量对象id
     * @param objKeyTableRelaList 对象所有关系数据
     * @return 返回对象树
     */
    private ObjRelaTreeVo buildObjRelaTree(Long currentObjectId, List<ObjKeyTableRelaVo> objKeyTableRelaList) {
        // 根节点对象
        ObjRelaTreeVo currentObjRela = new ObjRelaTreeVo();
        currentObjRela.setObjectId(currentObjectId);
        Set<Long> objectIds = new HashSet<>();
        currentObjRelaTreeField(currentObjRela, objKeyTableRelaList, objectIds);
        iterateObjRelaTreeField(currentObjRela, objKeyTableRelaList, objectIds);
        return currentObjRela;
    }

    /**
     * 迭代当前对象节点的下一个节点
     *
     * @param currentObjRela 当前节点
     * @param objKeyTableRelaList 对象关系所有数据
     * @param objectIds 对象id
     */
    private void iterateObjRelaTreeField(ObjRelaTreeVo currentObjRela, List<ObjKeyTableRelaVo> objKeyTableRelaList,
        Set<Long> objectIds) {
        for (ObjRelaTreeVo child : currentObjRela.getChildren()) {
            // 防止对象配置错误进入死循环
            Set<Long> onePathObjIds = new HashSet<>(objectIds);
            currentObjRelaTreeField(child, objKeyTableRelaList, onePathObjIds);
            iterateObjRelaTreeField(child, objKeyTableRelaList, onePathObjIds);
        }
    }

    /**
     * 从缓存中读取度量对象树
     *
     * @param objId 对象的id
     * @return 对象树
     */
    private ObjRelaTreeVo getMetricsObjTreeFromThread(Long objId) {
        Map<Long, ObjRelaTreeVo> treeVoMap = metricsObjTree.get();
        return treeVoMap.get(objId);
    }

    /**
     * 只拖了维度,需要查找一个维度作为路径的起点(度量)
     *
     * @param dimensionList 维度集合
     * @param params 参数
     * @return 返回度量集合
     */
    private List<DatasetColumnQo> findMetrics(List<DatasetColumnQo> dimensionList, DatasetColumnAndConditionQo params) {
        List<DatasetColumnQo> metricsList = new ArrayList<>();
        Map<String, List<DatasetColumnQo>> relaTypeGroup = dimensionList.stream()
            .collect(Collectors.groupingBy(DatasetColumnQo::getRelaType));
        List<DatasetColumnQo> lists;
        if (relaTypeGroup.containsKey(Constants.OBJ_TREE_RELA_TYPE_N)
            || relaTypeGroup.containsKey(Constants.OBJ_TREE_RELA_TYPE_2)) {
            // 有n端(切换对象)的relaType && (主分析对象||1端对象)
            lists = relaTypeGroup.get(Constants.OBJ_TREE_RELA_TYPE_N);
            if (CollectionUtils.isEmpty(lists)) {
                lists = relaTypeGroup.get(Constants.OBJ_TREE_RELA_TYPE_2);
            }
            // 选择一个维度作为路径的起点(作为度量)
            metricsList.add(lists.get(0));
        }
        else if (relaTypeGroup.containsKey(Constants.OBJ_TREE_RELA_TYPE_0)) {
            // 有主分析对象||1端对象,以主分析任意一个字段为起点
            lists = relaTypeGroup.get(Constants.OBJ_TREE_RELA_TYPE_0);
            // 选择一个维度作为路径的起点(作为度量)
            metricsList.add(lists.get(0));
        }
        else if (relaTypeGroup.containsKey(Constants.OBJ_TREE_RELA_TYPE_V)) {
            // 虚拟对象不能做度量
            log.info("虚拟对象不能做度量");
        }
        else {
            // 只有1端对象
            lists = relaTypeGroup.get(Constants.OBJ_TREE_RELA_TYPE_1);
            // 需要找到与所有维度有共同的那一个维度对象作为起点(度量)。
            if (lists.size() == 1) {
                metricsList.addAll(lists);
            }
            else {
                List<DatasetColumnQo> metrics = findStartMetric(lists);
                if (CollectionUtils.isEmpty(metrics)) {
                    // 创建一个主分析对象作为度量的起点
                    ObjInfo mainObj = objInfoMapper.selectById(params.getObjectId());
                    DatasetColumnQo column = new DatasetColumnQo();
                    column.setRelaType(Constants.OBJ_TREE_RELA_TYPE_0);
                    column.setTableId(mainObj.getMetaTableId());
                    column.setTableCode(mainObj.getTableCode());
                    column.setObjectId(params.getObjectId());
                    column.setPath("");
                    column.setDataName("自定义的度量(只拖了维度)");

                    metricsList.add(column);
                }
                else {
                    metricsList.addAll(metrics);
                }
            }
        }
        return metricsList;
    }

    /**
     * 查找度量
     *
     * @param lists 维度集合
     * @return 返回度量集合
     */
    private List<DatasetColumnQo> findStartMetric(List<DatasetColumnQo> lists) {
        // 查找有共同起点后取共同起点的维度作为度量
        List<DatasetColumnQo> sameList = new ArrayList<>();
        boolean isSameStart = true;
        for (int i = 0; i < lists.size(); i++) {
            String path1 = lists.get(i).getPath();
            for (int j = i + 1; j < lists.size(); j++) {
                String path2 = lists.get(j).getPath();
                String[] split1 = path1.split(",");
                String[] split2 = path2.split(",");
                if (!split1[0].equals(split2[0])) {
                    isSameStart = false;
                    break;
                }
            }
        }
        if (isSameStart) {
            lists.sort(Comparator.comparingInt(o -> o.getPath().length()));
            sameList.add(lists.get(0));
        }
        return sameList;
    }

    /**
     * 抽取维度和度量
     *
     * @param params 前端参数
     * @param dimensionList 维度集合
     * @param metricsList 度量集合
     */
    private void extractMetricsAndDimensions(DatasetColumnAndConditionQo params, List<DatasetColumnQo> dimensionList,
        List<DatasetColumnQo> metricsList) {
        List<DatasetColumnQo> columnList = params.getColumnList();
        List<DatasetConditionQo> condList = params.getCondList();
        // 前端传的维度和度量字段
        if (CollUtil.isNotEmpty(columnList)) {
            for (DatasetColumnQo columnQo : columnList) {
                // 维度&度量
                if (CollectionUtils.isEmpty(columnQo.getColumnGroup())) {
                    String appType = columnQo.getAppType();
                    String isAcct = columnQo.getIsAcct();
                    if (Constants.APP_TYPE_DIMENSION.equals(appType) && !KeyValues.YES_VALUE_1.equals(isAcct)) {
                        dimensionList.add(columnQo);
                    }
                    else if (Constants.APP_TYPE_METRICS.equals(appType)) {
                        metricsList.add(columnQo);
                        // 度量上面的条件字段作为维度
                    }
                }
                else {
                    // 计算字段需要区分拖拽的度量字段是否已存在。(判断条件为度量字段id和度量的条件)
                    for (DatasetColumnQo columnGroup : columnQo.getColumnGroup()) {
                        if ("arithmeticCondItem".equals(columnGroup.getCondType())) {
                            metricsList.add(columnGroup);
                        }
                    }
                }
            }
        }
        // 前端传的全局条件
        if (CollUtil.isNotEmpty(condList)) {
            // 与度量表相同或度量相同的全局条件，不需要设置为维度
            Set<String> dimTableIdPaths = new HashSet<>();
            for (DatasetColumnQo dim : dimensionList) {
                if (dim.getTableId() != null) {
                    dimTableIdPaths.add(dim.getTableId() + ";" + dim.getPath());
                }
            }
            for (DatasetConditionQo cond : condList) {
                // 虚拟出来的账期字段不用当维度
                if ("simpleCond".equals(cond.getCondType()) && !KeyValues.YES_VALUE_1.equals(cond.getIsAcct())) {
                    // 与度量表相同或度量相同的全局条件，不需要设置为维度
                    // 如果全局条件与维度路径不同，需要当作维度
                    if (CollUtil.isNotEmpty(dimTableIdPaths) && cond.getColumnId() != null
                        && dimTableIdPaths.contains(cond.getTableId() + ";" + cond.getPath())) {
                        continue;
                    }
                    // 虚拟对象条件需要当作维度
                    DatasetColumnQo newDatasetColumnQo = new DatasetColumnQo();
                    BeanUtils.copyProperties(cond, newDatasetColumnQo, KeyValues.ENTITY_COPY_IGNORE_FIELDS);
                    dimensionList.add(newDatasetColumnQo);
                }
            }
        }
    }

    /**
     * 设置路径属性
     */
    private void setPathVoProperty(MetricsDimensionPathVo pathVo) {
        pathVo.setTgtObjectId(null);
        pathVo.setTgtTableCode(null);
        pathVo.setTgtTableId(null);
        pathVo.getKeyColumnRelas().clear();
        pathVo.setMetricsInnerPath(Constants.YES_VALUE_1);
    }

    private void setDataPrivPathKey(DatasetColumnQo metrics, DatasetColumnQo existOrgDimensionColumn) {
        // 判断出组织维度字段是来自哪个路径，只需要在该路径上sql进行权限控制
        Map<String, List<MetricsDimensionPathVo>> pathsMap = metrics.getPathsMap();
        String dataPrivPathKey = null;
        for (String pathKey : pathsMap.keySet()) {
            if (pathKey.contains(existOrgDimensionColumn.getPath())) {
                dataPrivPathKey = pathKey;
                metrics.setDataPrivPathKey(dataPrivPathKey);
            }
        }

        // 重新调整map里的路径顺序，把需要权限控制的路径放在第一个位置
        this.hanelPrivPathOrder(metrics, pathsMap, dataPrivPathKey);
    }

    /**
     * 处理路径权限问题
     */
    private void hanelPrivPathOrder(DatasetColumnQo metrics, Map<String, List<MetricsDimensionPathVo>> pathsMap,
        String dataPrivPathKey) {
        Map<String, List<MetricsDimensionPathVo>> newPathsMap = new LinkedHashMap<>();
        if (dataPrivPathKey != null) {
            newPathsMap.put(dataPrivPathKey, pathsMap.get(dataPrivPathKey));
        }
        for (Map.Entry<String, List<MetricsDimensionPathVo>> entry : pathsMap.entrySet()) {
            if (entry.getKey().equals(dataPrivPathKey)) {
                continue;
            }
            newPathsMap.put(entry.getKey(), entry.getValue());
        }
        metrics.setPathsMap(newPathsMap);
    }

    /**
     * 删除多余无用的起点路径
     */
    private void deleteLessPath(Map<String, List<MetricsDimensionPathVo>> pathsMap, List<String> longestPathList) {
        if (longestPathList.size() > 1) {
            // 多个路径起点只有一个表的路径集合
            List<String> deletePathList = longestPathList.stream().filter(key -> {
                List<MetricsDimensionPathVo> pathVos = pathsMap.get(key);
                MetricsDimensionPathVo oneObj = pathVos.get(0);
                return null == oneObj.getTgtTableId() && oneObj.getMultiColumns().isEmpty();
            }).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(deletePathList)) {
                // 最长路径中包含了deletePathList又2次以上就需要删除起点只有一个表的路径
                deletePathList.forEach(deletePath -> {
                    final int[] num = {
                        0
                    };
                    longestPathList.forEach(longestPath -> {
                        if (longestPath.startsWith(deletePath)) {
                            num[0]++;
                        }
                    });
                    if (num[0] > 1) {
                        longestPathList.remove(deletePath);
                    }
                });
            }
        }
    }
}
