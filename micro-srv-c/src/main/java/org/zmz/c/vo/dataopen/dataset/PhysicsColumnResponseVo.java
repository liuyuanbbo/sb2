package org.zmz.c.vo.dataopen.dataset;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class PhysicsColumnResponseVo {

    private Long comAcctId;

    /**
     * 字段类型，1，2普通，3维度，4指标
     */
    private String columnClassify;

    private Long columnId;

    private Long dataColumnId;

    private Long metaDataId;

    private String columnType;

    private Integer columnSort;

    /**
     * 是否分区
     */
    private String isPartition;

    private String columnCode;

    private String columnDesc;

    private String columnName;

    /**
     * 是否账期字段 1为账期字段
     */
    private String period;

    private String tableCode;

    /**
     * 是否主键
     */
    private String businessKey;

    private String dateFormat;

    /**
     * 字段长度
     */
    private Integer columnLength;

    /**
     * 字段精度
     */
    private Integer columnAccuracy;

    /**
     * 字段属性，长度、精度
     */
    List<PhysicsColumnParam> zmgrMetaColumnsParamsList;

    public Object getParamValue(String columnParam) {
        if (CollUtil.isNotEmpty(this.zmgrMetaColumnsParamsList)) {
            Map<String, Object> datasourceMap = this.zmgrMetaColumnsParamsList.stream().collect(
                    Collectors.toMap(PhysicsColumnParam::getParamCode, PhysicsColumnParam::getParamValue, (v1, v2) -> v1));
            return datasourceMap.get(columnParam);
        }
        return null;
    }
}