package org.zmz.c.vo.dataopen.dataindex;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Zmz
 */
@Getter
@Setter
public class DimIndexVo implements Serializable {

    private Long indexId;

    private String indexName;

    private String indexCode;

    private Long metaTableId;

    private String tableCode;

    private String state;

    private String indexType;

    private Long comAcctId;

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
     * 是否是分区字段
     */
    private String isPartition;
}