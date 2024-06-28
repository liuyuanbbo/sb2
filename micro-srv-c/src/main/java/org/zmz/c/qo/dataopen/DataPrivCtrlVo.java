package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;
import org.zmz.c.pojo.dataopen.ObjInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class DataPrivCtrlVo implements Serializable {

    /**
     * 数据是否需要权限控制
     */
    private boolean isDataPrivCtrl;

    /**
     * 最细粒度的组织维度对象（划小架构）
     */
    private ObjInfo orgDimensionObjInfo;

    /**
     * 最细粒度的组织维度对象主表
     */
    private ModelInfo orgDimensionModelInfo;

    /**
     * 组织维度对象列表
     */
    public List<ObjInfo> orgDimensionObjInfoList;

    /**
     * 组织维度对象主表列表
     */
    public List<ModelInfo> orgDimensionModelInfoList;

    /**
     * 组织明细对象主表（组织明细表，同一数据源类型唯一）
     */
    private ModelInfo orgModelInfo;

    /**
     * 分析字段中已选择的最细粒度的组织维度字段，若为null，则没有选择
     */
    private DatasetColumnQo existOrgDimensionColumn;

    /**
     * 需要增加的组织维度字段，为null就不需要额外加，直接用existOrgDimensionColumn
     */
    private DatasetColumnQo addOrgDimensionColumn;

    /**
     * 需要进行数据权限控制的表
     */
    private List<ModelInfo> dataPrivModelList;

    /**
     * 表和组织明细表关联关系map
     */
    private Map<Long, MetricsDimensionPathVo> orgInfoPathsMap;
}