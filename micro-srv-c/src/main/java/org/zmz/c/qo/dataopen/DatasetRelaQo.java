package org.zmz.c.qo.dataopen;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import lombok.Getter;
import lombok.Setter;
import org.zmz.c.pojo.dataopen.ObjInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class DatasetRelaQo {

    /**
     * 数据集关联的对象
     */
    private List<Long> objectIds = new ArrayList<>();

    private Map<Long, ObjInfo> objInfoMap = new HashMap<>();

    /**
     * 数据集关联的表
     */
    private List<Long> tableIds = new ArrayList<>();

    private Map<Long, ModelInfo> modelInfoMap = new HashMap<>();

    /**
     * 数据集关联的字段
     */
    private List<Long> columnIds = new ArrayList<>();

    /**
     * 数据集关联的原子指标
     */
    private List<Long> proIndexIds = new ArrayList<>();

    /**
     * 数据集关联的派生指标
     */
    private List<Long> dimIndexIds = new ArrayList<>();

    public void addDatasetRelas(DatasetRelaQo addDatasetRela) {
        if (CollUtil.isNotEmpty(addDatasetRela.getObjectIds())) {
            this.objectIds.addAll(addDatasetRela.getObjectIds());
        }
        if (CollUtil.isNotEmpty(addDatasetRela.getTableIds())) {
            this.tableIds.addAll(addDatasetRela.getTableIds());
        }
        if (CollUtil.isNotEmpty(addDatasetRela.getColumnIds())) {
            this.columnIds.addAll(addDatasetRela.getColumnIds());
        }
        if (CollUtil.isNotEmpty(addDatasetRela.getProIndexIds())) {
            this.proIndexIds.addAll(addDatasetRela.getProIndexIds());
        }
        if (CollUtil.isNotEmpty(addDatasetRela.getDimIndexIds())) {
            this.dimIndexIds.addAll(addDatasetRela.getDimIndexIds());
        }
        if (MapUtil.isNotEmpty(addDatasetRela.getObjInfoMap())) {
            this.objInfoMap.putAll(addDatasetRela.getObjInfoMap());
        }
        if (MapUtil.isNotEmpty(addDatasetRela.getModelInfoMap())) {
            this.modelInfoMap.putAll(addDatasetRela.getModelInfoMap());
        }
    }
}