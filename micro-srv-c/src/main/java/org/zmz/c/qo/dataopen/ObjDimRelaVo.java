package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;
import org.zmz.c.pojo.dataopen.ObjKeyTableRela;

@Getter
@Setter
public class ObjDimRelaVo extends ObjKeyTableRela {

    // 派生指标名称
    private String dimIndexName;

    // 派生指标编码
    private String indexCode;

    // 原子指标id
    private Long indexId;

    // 原子指标名称
    private String proIndexName;

    // 原子指标编码
    private String proIndexCode;

    // 分析对象
    private String objectId;

    /**
     * 关联维度，统计粒度
     */
    private String dimObjectCode;

    private String dimObjectName;

    /**
     * 统计粒度表相关
     */
    private String dimTableCode;
}