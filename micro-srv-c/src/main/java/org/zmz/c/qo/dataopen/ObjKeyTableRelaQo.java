package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ObjKeyTableRelaQo extends QueryObject {

    private Long objectId;

    private Long metaDataId;

    /**
     * 关联对象查询
     */
    private Long relaKeyObjectId;

    private Long srcRelaId;

    private List<Long> objectIds;

    /**
     * 关联对象查询
     */
    private List<Long> relaKeyObjectIds;

    private List<Long> srcRelaIds;

    /**
     * 状态
     */
    private String statusCd;

    private String relaType;

    private String referType;

    private Long relaId;

    public ObjKeyTableRelaQo() {
    }
}