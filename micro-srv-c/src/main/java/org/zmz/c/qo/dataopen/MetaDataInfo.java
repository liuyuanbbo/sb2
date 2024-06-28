package org.zmz.c.qo.dataopen;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class MetaDataInfo implements Serializable {

    private Long catalogId;

    private String tableType;

    private Long metaDataId;

    private String metaDataName;

    private String metaDataCode;

    /**
     * 数据源ID
     */
    private Long schemaId;

    /**
     * 数据源编码
     */
    private String schemaCode;

    /**
     * 数据源编码
     */
    private String datasourceCode;

    private String domainId;

    private String layerId;

    private String objectType;

    private Long periodId;

    /**
     * 数据账期编码，如：M D Y
     */
    private String dataCycleCode;

    /**
     * hive表的字段分隔符
     */
    private String coldelimiter;

    /**
     * hive表的行分隔符
     */
    private String rowdelimiter;

    /**
     * hive表的存储格式
     */
    private String storeFormat;

    /**
     * 模型版本
     */
    private String versionId;

    /**
     * 唯一标识
     */
    private String uuid;

    /**
     * 库名
     */
    private String database;

    /**
     * gp,gbase分区表达式
     */
    private String partitionExecuteSql;

    /**
     * gp分发策略distributed by (col)
     */
    private String distributeType;

    /**
     * 引擎
     */
    private String engine;

    /**
     * doris聚合模型 唯一模型 冗余模型
     */
    private String tableMode;

}