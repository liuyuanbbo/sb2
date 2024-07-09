package org.zmz.c.service.dataopen.sqlenum;

import org.zmz.c.service.dataopen.dataset.ColumnType;

public enum HiveColumnTypeEnum implements ColumnType {

    /**
     * HIVE字段类型
     */
    TINYINT(false, false, null, null),
    SMALLINT(false, false, null, null),
    INT(false, false, null, null),
    INTEGER(false, false, null, null),
    BIGINT(false, false, null, null),
    DECIMAL(true, true, 1000L, 38L),
    FLOAT(false, false, 1000L, 4L),
    DOUBLE(false, false, 1000L, 8L),
    NUMERIC(true, true, 1000L, 1000L),
    INTERVAL(true, false, 6L, null),
    DATE(false, false, null, null),
    TIMESTAMP(true, false, 6L, null),
    BOOLEAN(false, false, null, null),
    BINARY(false, false, null, null),
    CHAR(true, false, 10485760L, null),
    VARCHAR(true, false, 10485760L, null),
    STRING(false, false, null, null);

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

    HiveColumnTypeEnum(boolean declareLength, boolean declareAccuracy, Long maxLength, Long maxAccuracy) {
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