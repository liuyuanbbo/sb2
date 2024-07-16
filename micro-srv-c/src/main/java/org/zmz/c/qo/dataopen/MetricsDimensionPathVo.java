package org.zmz.c.qo.dataopen;

import cn.hutool.core.collection.CollUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class MetricsDimensionPathVo implements Serializable {

    /**
     * 上级表所属对象（度量所属对象）
     */
    private Long srcObjectId;

    /**
     * 上级表标识（度量所属表）
     */
    private Long srcTableId;

    /**
     * 上级表编码（度量所属表）
     */
    private String srcTableCode;

    /**
     * 下级关联表所属对象
     */
    private Long tgtObjectId;

    /**
     * 下级关联表标识
     */
    private Long tgtTableId;

    /**
     * 下级关联表编码
     */
    private String tgtTableCode;

    /**
     * 是否为度量内部的关联，1为是，0为否
     */
    private String metricsInnerPath = "0";

    /**
     * 多维指标对象内部关联字段
     */
    private List<String> multiColumns = new ArrayList<>();

    private List<ObjRelaTreeColumnVo> relaColumns = new ArrayList<>();

    private List<ObjKeyColumnRelaVo> keyColumnRelas = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MetricsDimensionPathVo that = (MetricsDimensionPathVo) o;
        return Objects.equals(srcObjectId, that.srcObjectId) &&
                Objects.equals(srcTableId, that.srcTableId) &&
                Objects.equals(srcTableCode, that.srcTableCode) &&
                Objects.equals(tgtObjectId, that.tgtObjectId) &&
                Objects.equals(tgtTableId, that.tgtTableId) &&
                Objects.equals(tgtTableCode, that.tgtTableCode) &&
                CollUtil.isEqualList(multiColumns, that.multiColumns) &&
                CollUtil.isEqualList(keyColumnRelas, that.keyColumnRelas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(srcObjectId, srcTableId, srcTableCode, tgtObjectId, tgtTableId, tgtTableCode, multiColumns, keyColumnRelas);
    }
}