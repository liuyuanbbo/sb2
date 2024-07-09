package org.zmz.c.service.dataopen.sqlenum;

import org.zmz.c.service.dataopen.dataset.ColumnType;

public enum DorisColumnTypeEnum implements ColumnType {

    /**
     * mysql字段类型
     */
    TINYINT(true, false, 255L, null),
    SMALLINT(true, false, 255L, null),
    BIGINT(true, false, 255L, null),
    DECIMAL(true, true, 65L, 30L),
    FLOAT(true, true, 255L, 30L),
    DOUBLE(true, true, 255L, 30L),
    VARCHAR(true, false, 4294967295L, null),
    STRING(true, false, 4294967295L, null),
    DATE(false, false, null, null),
    DATETIME(true, false, 6L, null),
    TIMESTAMP(true, false, 6L, null);

    /**
     * 该字段是否能声明字段长度
     */
    private boolean declareLength;

    /**
     * 该字段是否能声明字段精度
     */
    private boolean declareAccuracy;

    /**
     * 字段声明的最大长度，该长度为建字段时SQL不会报错的最大长度，并非实际字段类型的最大长度
     */
    private Long maxLength;

    /**
     * 字段的最大精度
     */
    private Long maxAccuracy;

    DorisColumnTypeEnum(boolean declareLength, boolean declareAccuracy, Long maxLength, Long maxAccuracy) {
        this.declareLength = declareLength;
        this.declareAccuracy = declareAccuracy;
        this.maxLength = maxLength;
        this.maxAccuracy = maxAccuracy;
    }


    @Override
    public boolean canDeclareLength() {
        return this.declareLength;
    }

    @Override
    public boolean canDeclareAccuracy() {
        return this.declareAccuracy;
    }

    @Override
    public Long getMaxLength() {
        return maxLength;
    }

    @Override
    public Long getMaxAccuracy() {
        return maxAccuracy;
    }
}