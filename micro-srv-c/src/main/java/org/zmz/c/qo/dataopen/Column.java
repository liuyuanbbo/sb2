package org.zmz.c.qo.dataopen;

import cn.hutool.core.map.MapUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Setter
@Getter
public class Column implements Serializable {

    /**
     * 逻辑字段编码
     */
    private String columnCode;

    /**
     * 逻辑字段名称
     */
    private String columnName;

    /**
     * 数据类型
     */
    private String columnType;

    /**
     * 字段分类
     */
    private String columnClassify;

    /**
     * 字段描述
     */
    private String columnDesc;

    private Long dataColumnId;

    private Long columnId;

    private Long metaDataId;

    private Long comAcctId;

    private Integer columnSort;

    /**
     * 是否账期，1是，0否
     */
    private String period;

    /**
     * 是否业务主键
     */
    private String businessKey;

    private String tableCode;

    /**
     * 字段长度
     */
    private Integer columnLength;

    /**
     * 字段精度
     */
    private Integer columnAccuracy;

    /**
     * 日期格式，当columnType为日期格式时设置
     */
    private String dateFormat;

    /**
     * 是否组织字段
     */
    private String orgField;

    /**
     * 账期类型-(M-月,D-天,Y-年)
     */
    private String cycleType;

    /**
     * 是否分区
     */
    private String isPartition;

    /**
     * 是否省份标识字段
     */
    private String provinceField;

    /**
     * 术语字段
     */
    private String termCode;

    private String isDistributed;

    private String isNotNull;

    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc != null ? columnDesc.replace("'", "") : null;
    }

    public Column() {
    }

    public Column(String columnCode, String columnName, String columnType, Long metaDataId, String tableCode,
                  String columnClassify, Integer columnSort) {
        this.columnCode = columnCode;
        this.columnName = columnName;
        this.columnDesc = columnName != null ? columnName.replace("'", "") : null;
        this.columnType = columnType;
        this.metaDataId = metaDataId;
        this.tableCode = tableCode;
        this.columnClassify = columnClassify;
        this.columnSort = columnSort;
    }

    public Column(String columnCode, String columnName, String columnType, Long metaDataId, String tableCode,
                  String columnClassify, Integer columnSort, Map<String, Map<?, ?>> datatypesMap) {
        this.columnCode = columnCode;
        this.columnName = columnName;
        this.columnDesc = columnName != null ? columnName.replace("'", "") : null;
        this.columnType = columnType;
        this.metaDataId = metaDataId;
        this.tableCode = tableCode;
        this.columnClassify = columnClassify;
        this.columnSort = columnSort;
        // 处理字段类型映射
        if (MapUtil.isNotEmpty(datatypesMap)) {
            Map<?, ?> datatypeMap = datatypesMap.get(this.columnType.toLowerCase());
            Integer columnLength = MapUtil.getInt(datatypeMap, "columnLength");
            if (columnLength != null && columnLength != 0) {
                this.columnLength = columnLength;
            }
            Integer columnAccuracy = MapUtil.getInt(datatypeMap, "columnAccuracy");
            if (columnAccuracy != null && columnAccuracy != 0) {
                this.columnAccuracy = columnAccuracy;
            }
        }
    }
}