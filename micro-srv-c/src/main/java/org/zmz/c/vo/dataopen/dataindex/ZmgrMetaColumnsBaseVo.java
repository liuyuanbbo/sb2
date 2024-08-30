package org.zmz.c.vo.dataopen.dataindex;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Zmz
 */
@Getter
@Setter
public class ZmgrMetaColumnsBaseVo {
    /**
     * 字段ID
     */
    private Long columnId;

    /**
     * 字段编码
     */
    private String columnCode;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 是否主键字段
     */
    private String isPrimary;

    /**
     * 是否是帐字段
     */
    private String isAcct;

    /**
     * 是否是分区字段 1:是 0:否
     */
    private String isPartition = "0";
}
