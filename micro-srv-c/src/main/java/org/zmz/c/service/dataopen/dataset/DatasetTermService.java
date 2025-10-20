package org.zmz.c.service.dataopen.dataset;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zmz.c.dto.dataopen.ObjRelaDTO;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetConditionQo;
import org.zmz.c.qo.dataopen.DatasetDetail;
import org.zmz.c.qo.dataopen.DatasetObjColumnVo;
import org.zmz.c.qo.dataopen.ObjKeyTableRelaVo;
import org.zmz.c.utils.AccountUtil;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DatasetTermService {

    @Resource
    ObjKeyPathService objKeyPathService;

    @Resource
    DatasetCacheService datasetCacheService;

    @Resource
    AccountUtil accountUtil;

    /**
     * 预览数据，需要重新构建入参，将术语替换成字段，找到最优路径
     */
    public void buildDatasetColumns(DatasetDetail datasetDetail) {
        // 所有对象关系
        Map<String, Set<List<Long>>> pathMap = objKeyPathService.getDatasetObjPathMap();
        // 先从redis查询派生指标的统计粒度
        // Long comAcctId = accountUtil.getComAcctId();
        long comAcctId = 1021L;
        Map<Long, List<ObjKeyTableRelaVo>> objDimRelaMap = datasetCacheService.getObjDimRelaMapInRedis(comAcctId);
        for (DatasetColumnAndConditionQo group : datasetDetail.getGroups()) {
            this.buildDatasetColumns(group, pathMap, objDimRelaMap);
        }
    }

    public void buildDatasetColumns(DatasetColumnAndConditionQo group, Map<String, Set<List<Long>>> pathMap,
        Map<Long, List<ObjKeyTableRelaVo>> objDimRelaMap) {
        // 维度术语
        List<DatasetColumnQo> termDimensions = group.getTermColumns(Constants.APP_TYPE_DIMENSION);
        // 度量术语
        List<DatasetColumnQo> termMetrics = group.getTermColumns(Constants.APP_TYPE_METRICS);
        if (CollectionUtils.isEmpty(termDimensions) && CollectionUtils.isEmpty(termMetrics)) {
            return;
        }
        // 术语最佳物理字段
        Map<String, DatasetColumnQo> bestTermMetricColumnMap = new HashMap<>();

        if (CollUtil.isNotEmpty(termMetrics)) {
            for (DatasetColumnQo metricTerm : termMetrics) {
                List<DatasetObjColumnVo> optTermRelaColumns = metricTerm.getOptTermRelaColumns();
                // 保存完成后，不需要再次处理，编辑时可能指标被下架，也不需要匹配
                if (CollectionUtils.isEmpty(optTermRelaColumns)) {
                    continue;
                }
                // 术语对应最合适的物理字段
                DatasetObjColumnVo bestTermMetricColumn;
                // 术语代表的所有字段
                if (optTermRelaColumns.size() == 1) {
                    bestTermMetricColumn = optTermRelaColumns.get(0);
                }
                // 选择出最优字段，将字段信息补充到术语字段，术语字段（虚拟）找到合适的物理模型字段
                else {
                    bestTermMetricColumn = this.getBestTermColumn(group, metricTerm, pathMap, objDimRelaMap);
                    log.info("度量术语 {} 别名： {} 最优字段: {}（对象 {}下表 {} 的字段{}）", metricTerm.getTermCode(),
                        metricTerm.getAlias(), bestTermMetricColumn.getObjectId(), bestTermMetricColumn.getObjectName(),
                        bestTermMetricColumn.getTableCode(), bestTermMetricColumn.getColumnCode());
                }
                // 前端配置内容不能覆盖
                BeanUtils.copyProperties(bestTermMetricColumn, metricTerm, "func", "alias", "dataName", "columnName",
                    "columnType", "columnLength", "columnAccuracy", "seq");
                bestTermMetricColumnMap.put(metricTerm.getAlias(), metricTerm);
            }
            this.replaceTermColumn(group, bestTermMetricColumnMap);
        }
    }

    /**
     * 同一术语下的所有字段到所有维度字段比较，取最优
     */
    private DatasetObjColumnVo getBestTermColumn(DatasetColumnAndConditionQo group, DatasetColumnQo metricTerm,
        Map<String, Set<List<Long>>> pathMap, Map<Long, List<ObjKeyTableRelaVo>> objDimRelaMap) {
        // 维度
        List<Long> allObjIds = group.getDimensionColumns().stream().map(DatasetColumnQo::getObjectId)
            .collect(Collectors.toList());
        // 过滤条件
        allObjIds.addAll(group.getCondObjIdList());
        // 度量过滤条件
        if (CollUtil.isNotEmpty(metricTerm.getCondList())) {
            allObjIds.addAll(metricTerm.getCondList().stream().filter(d -> d.getColumnId() != null)
                .map(DatasetConditionQo::getObjId).collect(Collectors.toList()));
        }
        allObjIds = allObjIds.stream().distinct().collect(Collectors.toList());
        log.info("pathMap : {}", pathMap);

        Map<Long, ObjRelaDTO> termOneObjMap = new HashMap<>();
        ObjRelaDTO objRelaDTO;
        List<DatasetObjColumnVo> optTermRelaColumns = metricTerm.getOptTermRelaColumns();
        for (DatasetObjColumnVo tm : optTermRelaColumns) {
            objRelaDTO = new ObjRelaDTO(tm, allObjIds,
                objDimRelaMap == null ? null : objDimRelaMap.get(tm.getObjectId()));
            termOneObjMap.put(tm.getObjectId(), objRelaDTO);
        }

        if (allObjIds.size() == 1 && termOneObjMap.containsKey(allObjIds.get(0))) {
            log.info("只有一种维度");
            return termOneObjMap.get(allObjIds.get(0)).getObjColumn();
        }
        // 判断直接一端个数取最小，关联维度最多
        List<ObjRelaDTO> list = termOneObjMap.values().stream().sorted(Comparator.comparing(ObjRelaDTO::getOneObjSize)
            .thenComparing(ObjRelaDTO::getRetainObjSize, Comparator.reverseOrder())).collect(Collectors.toList());
        return list.get(0).getObjColumn();
    }

    /**
     * 计算字段引用术语替换，度量条件字段引用术语替换
     */
    private void replaceTermColumn(DatasetColumnAndConditionQo group,
        Map<String, DatasetColumnQo> bestTermMetricColumnMap) {
        for (DatasetColumnQo columnQo : group.getColumnList()) {
            if (!Constants.APP_TYPE_METRICS.equals(columnQo.getAppType())) {
                continue;
            }
            replaceColumnGroup(columnQo.getColumnGroup(), bestTermMetricColumnMap);
            replaceConditions(columnQo.getCondList(), bestTermMetricColumnMap, columnQo.getAlias());
        }
    }

    private void replaceColumnGroup(List<DatasetColumnQo> columnGroup,
        Map<String, DatasetColumnQo> bestTermMetricColumnMap) {
        if (CollUtil.isNotEmpty(columnGroup)) {
            for (DatasetColumnQo columnGroupQo : columnGroup) {
                if (StrUtil.isNotEmpty(columnGroupQo.getTermCode())) {
                    DatasetColumnQo bestTermMetricColumn = bestTermMetricColumnMap.get(columnGroupQo.getAlias());
                    if (bestTermMetricColumn != null) {
                        // 前端配置内容不能覆盖
                        BeanUtils.copyProperties(bestTermMetricColumn, columnGroupQo, "id", "parent", "root",
                            "condType", "condValue", "alias", "seq");
                    }
                }
            }
        }
    }

    private void replaceConditions(List<DatasetConditionQo> conditions,
        Map<String, DatasetColumnQo> bestTermMetricColumnMap, String alias) {
        if (CollUtil.isNotEmpty(conditions)) {
            for (DatasetConditionQo conditionQo : conditions) {
                if (StrUtil.isNotEmpty(conditionQo.getTermCode())) {
                    // 此处没有别名，复杂业务可能会取错术语代表的字段
                    DatasetColumnQo bestTermMetricColumn = bestTermMetricColumnMap.get(alias);
                    if (bestTermMetricColumn != null) {
                        // 前端配置内容不能覆盖
                        BeanUtils.copyProperties(bestTermMetricColumn, conditionQo, "id", "parent", "root", "condType",
                            "condOperator", "condValue", "seq");
                    }
                }
            }
        }
    }

}
