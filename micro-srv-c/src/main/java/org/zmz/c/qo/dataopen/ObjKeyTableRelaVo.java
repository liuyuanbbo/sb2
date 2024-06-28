package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;
import org.zmz.c.pojo.dataopen.ObjKeyTableRela;

import java.util.List;

@Getter
@Setter
public class ObjKeyTableRelaVo extends ObjKeyTableRela {
    /**
     * n端对象名称
     */
    private String objectName;

    /**
     * 一端对象名称
     */
    private String relaObjectName;

    /**
     * 一端对象编码
     */
    private String relaObjectCode;

    /**
     * 一端对象主表编码
     */
    private String relaTableCode;

    /**
     * 派生指标来源关系
     */
    public Long srcRelaId;

    private List<ObjKeyColumnRelaVo> keyColumnRelas;

    @Override
    public String toString() {
        return "ObjKeyTableRelaVo{" + "relaId=" + this.getRelaId() + ", objectId=" + this.getObjectId() +
                ", metaDataId=" + this.getMetaDataId() + ", tableCode='" + this.getTableCode() + '\'' +
                ", datasourceCode='" + this.getDatasourceCode() + '\'' + ", relaType='" + this.getRelaType() +
                '\'' + ", relaTableId=" + this.getRelaTableId() + ", objectName='" + objectName + '\''
                + ", relaObjectName='" + relaObjectName + '\'' + ", relaTableCode='" + relaTableCode + '\'' + '}';
    }
}
