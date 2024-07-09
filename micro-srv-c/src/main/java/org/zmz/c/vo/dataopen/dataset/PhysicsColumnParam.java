package org.zmz.c.vo.dataopen.dataset;

import lombok.Data;

@Data
public class PhysicsColumnParam {

    /**
     * 字段长度
     */
    public static final String COLUMN_LENGTH = "columnLength";

    /**
     * 字段精度
     */
    public static final String COLUMN_ACCURACY = "columnAccuracy";

    /**
     * 字段精度
     */
    public static final String COLUMN_EMPTY = "columnEmpty";

    /**
     * 字段精度
     */
    public static final String IS_NULL = "isNull";

    /**
     * 是否省份
     */
    public static final String PROVINCE_FIELD = "provinceField";

    private Long paramId;

    private Long columnId;

    private String paramCode;

    private String paramDesc;

    private String paramValue;

}