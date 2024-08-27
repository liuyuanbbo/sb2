package org.zmz.c.service.dataopen.dataset;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.zmz.c.qo.dataopen.DatasetColumnAndConditionQo;
import org.zmz.c.qo.dataopen.DatasetColumnQo;
import org.zmz.c.qo.dataopen.DatasetConditionQo;
import org.zmz.c.qo.dataopen.DatasetDetail;
import org.zmz.c.qo.dataopen.DatasetRelaQo;
import org.zmz.c.qo.dataopen.MetricsDimensionPathVo;
import org.zmz.c.qo.dataopen.ModelInfo;
import org.zmz.c.qo.dataopen.ObjKeyColumnRelaVo;
import org.zmz.c.service.dataopen.remote.DataCommonService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DatasetModelService {

    @Resource
    private DataCommonService dataCommonService;

    /**
     * 获取使用到的模型
     */
    public Map<Long, ModelInfo> getModelInfoMap(DatasetDetail datasetDetail) {
        Set<Long> metaTableIds;
        if (StrUtil.isNotEmpty(datasetDetail.getSqlRelTables())) {
            metaTableIds = Arrays.stream(datasetDetail.getSqlRelTables().split("\\|")).map(Long::parseLong)
                .collect(Collectors.toSet());
        }
        else {
            DatasetRelaQo datasetRelaQo = this.getDatasetRelas(datasetDetail);
            metaTableIds = new HashSet<>(datasetRelaQo.getTableIds());
        }
        // 汇总表
        return dataCommonService.queryAllModelInfoMap(metaTableIds);
    }

    /**
     * 获取使用到的模型
     */
    public Map<Long, ModelInfo> getModelInfoMap(DatasetColumnAndConditionQo columnAndConditionQo) {
        DatasetRelaQo datasetRelaQo = this.getDatasetRelas(columnAndConditionQo);
        return dataCommonService.queryAllModelInfoMap(datasetRelaQo.getTableIds());
    }

    /**
     * 获取数据集引用的对象、表、字段、指标等id
     */
    public DatasetRelaQo getDatasetRelas(DatasetDetail datasetDetail) {
        DatasetRelaQo datasetRelaQo = new DatasetRelaQo();
        datasetRelaQo.getObjectIds().add(datasetDetail.getObjectId());

        for (DatasetColumnAndConditionQo groupQo : datasetDetail.getGroups()) {
            datasetRelaQo.addDatasetRelas(this.getDatasetRelas(groupQo));
        }
        return datasetRelaQo;
    }

    /**
     * 获取用到的数据id
     */
    public DatasetRelaQo getDatasetRelas(DatasetColumnAndConditionQo groupQo) {
        Set<Long> objectIds = new HashSet<>();
        Set<Long> metaTableIds = new HashSet<>();
        Set<Long> columnIds = new HashSet<>();
        Set<Long> proIndexIds = new HashSet<>();
        Set<Long> dimIndexIds = new HashSet<>();

        // 1、处理所有字段
        for (DatasetColumnQo columnQo : groupQo.getColumnList()) {
            this.addDatasetColumnRelas(objectIds, metaTableIds, columnIds, proIndexIds, dimIndexIds, columnQo);
        }

        // 2、只选择了维度
        if (CollUtil.isNotEmpty(groupQo.getCustomMetrics())) {
            for (DatasetColumnQo columnQo : groupQo.getCustomMetrics()) {
                // 可能有多余关联的表
                this.addDatasetPathRelas(objectIds, metaTableIds, columnIds, columnQo);
            }
        }

        // 3、全局条件上的表id
        if (CollUtil.isNotEmpty(groupQo.getCondList())) {
            for (DatasetConditionQo condQo : groupQo.getCondList()) {
                this.addDatasetRelas(objectIds, metaTableIds, columnIds, proIndexIds, dimIndexIds, condQo);
            }
        }
        // 4、组织明细表id
        if (!ObjectUtils.isEmpty(groupQo.getDataPrivCtrlInfo())) {
            Map<Long, MetricsDimensionPathVo> maps = groupQo.getDataPrivCtrlInfo().getOrgInfoPathsMap();
            if (!MapUtil.isEmpty(maps)) {
                for (Map.Entry<Long, MetricsDimensionPathVo> entry : maps.entrySet()) {
                    this.addDatasetRelas(objectIds, metaTableIds, columnIds, entry.getValue());
                }
            }
        }
        DatasetRelaQo datasetRelaQo = new DatasetRelaQo();
        datasetRelaQo.getObjectIds().addAll(objectIds);
        datasetRelaQo.getTableIds().addAll(metaTableIds);
        datasetRelaQo.getColumnIds().addAll(columnIds);
        datasetRelaQo.getProIndexIds().addAll(proIndexIds);
        datasetRelaQo.getDimIndexIds().addAll(dimIndexIds);
        return datasetRelaQo;
    }

    private void addDatasetRelas(Set<Long> objectIds, Set<Long> metaTableIds, Set<Long> columnIds,
        MetricsDimensionPathVo metricsDimensionPathVo) {
        if (metricsDimensionPathVo.getSrcObjectId() != null) {
            objectIds.add(metricsDimensionPathVo.getSrcObjectId());
        }
        if (metricsDimensionPathVo.getTgtObjectId() != null) {
            objectIds.add(metricsDimensionPathVo.getTgtObjectId());
        }
        if (metricsDimensionPathVo.getSrcTableId() != null) {
            metaTableIds.add(metricsDimensionPathVo.getSrcTableId());
        }
        if (metricsDimensionPathVo.getTgtTableId() != null) {
            metaTableIds.add(metricsDimensionPathVo.getTgtTableId());
        }
        if (CollUtil.isNotEmpty(metricsDimensionPathVo.getKeyColumnRelas())) {
            for (ObjKeyColumnRelaVo keyColumnRelaVo : metricsDimensionPathVo.getKeyColumnRelas()) {
                if (keyColumnRelaVo.getColumnId() != null) {
                    columnIds.add(keyColumnRelaVo.getColumnId());
                }
                if (keyColumnRelaVo.getRelaColumnId() != null) {
                    columnIds.add(keyColumnRelaVo.getRelaColumnId());
                }
            }
        }
    }

    private void addDatasetColumnRelas(Set<Long> objectIds, Set<Long> metaTableIds, Set<Long> columnIds,
        Set<Long> proIndexIds, Set<Long> dimIndexIds, DatasetColumnQo columnQo) {
        this.addDatasetRelas(objectIds, metaTableIds, columnIds, proIndexIds, dimIndexIds, columnQo);

        if (CollUtil.isNotEmpty(columnQo.getColumnGroup())) {
            for (DatasetColumnQo columnGroup : columnQo.getColumnGroup()) {
                this.addDatasetRelas(objectIds, metaTableIds, columnIds, proIndexIds, dimIndexIds, columnGroup);
            }
        }
        if (CollUtil.isNotEmpty(columnQo.getCondList())) {
            for (DatasetConditionQo condQo : columnQo.getCondList()) {
                this.addDatasetRelas(objectIds, metaTableIds, columnIds, proIndexIds, dimIndexIds, condQo);
            }
        }
        // 可能有多余关联的表
        if (CollUtil.isNotEmpty(columnQo.getPaths())) {
            for (MetricsDimensionPathVo metricsDimensionPathVo : columnQo.getPaths()) {
                this.addDatasetRelas(objectIds, metaTableIds, columnIds, metricsDimensionPathVo);
            }
        }
    }

    private void addDatasetPathRelas(Set<Long> objectIds, Set<Long> metaTableIds, Set<Long> columnIds,
        DatasetColumnQo columnQo) {
        if (CollUtil.isNotEmpty(columnQo.getPaths())) {
            for (MetricsDimensionPathVo metricsDimensionPathVo : columnQo.getPaths()) {
                this.addDatasetRelas(objectIds, metaTableIds, columnIds, metricsDimensionPathVo);
            }
        }
    }

    private void addDatasetRelas(Set<Long> objectIds, Set<Long> metaTableIds, Set<Long> columnIds,
        Set<Long> proIndexIds, Set<Long> dimIndexIds, DatasetColumnQo columnQo) {
        if (columnQo.getObjectId() != null) {
            objectIds.add(columnQo.getObjectId());
        }
        if (columnQo.getTableId() != null) {
            metaTableIds.add(columnQo.getTableId());
        }
        if (columnQo.getColumnId() != null) {
            columnIds.add(columnQo.getColumnId());
        }
        if (columnQo.getProIndexId() != null) {
            proIndexIds.add(columnQo.getProIndexId());
        }
        if (columnQo.getDimIndexId() != null) {
            dimIndexIds.add(columnQo.getDimIndexId());
        }
    }

    private void addDatasetRelas(Set<Long> objectIds, Set<Long> metaTableIds, Set<Long> columnIds,
        Set<Long> proIndexIds, Set<Long> dimIndexIds, DatasetConditionQo conditionQo) {
        if (conditionQo.getObjId() != null) {
            objectIds.add(conditionQo.getObjId());
        }
        if (conditionQo.getTableId() != null) {
            metaTableIds.add(conditionQo.getTableId());
        }
        if (conditionQo.getColumnId() != null) {
            columnIds.add(conditionQo.getColumnId());
        }
        if (conditionQo.getProIndexId() != null) {
            proIndexIds.add(conditionQo.getProIndexId());
        }
        if (conditionQo.getDimIndexId() != null) {
            dimIndexIds.add(conditionQo.getDimIndexId());
        }
    }

}
