package org.zmz.c.service.dataopen.sqlenum;

import org.zmz.c.service.dataopen.dataset.ColumnType;

public enum MysqlColumnTypeEnum implements ColumnType {

    /**
     * mysql字段类型
     */
    TINYINT(true, false, 255L, null),
    SMALLINT(true, false, 255L, null),
    MEDIUMINT(true, false, 255L, null),
    INT(true, false, 255L, null),
    INTEGER(true, false, 255L, null),
    BIGINT(true, false, 255L, null),
    DECIMAL(true, true, 65L, 30L),
    NUMERIC(true, true, 65L, 30L),
    FLOAT(true, true, 255L, 30L),
    DOUBLE(true, true, 255L, 30L),
    REAL(true, true, 255L, 30L),
    BIT(true, false, 64L, null),
    CHAR(true, false, 255L, null),
    VARCHAR(true, false, 4294967295L, null),
    BINARY(true, false, 255L, null),
    VARBINARY(true, false, 4294967295L, null),
    BLOB(true, false, 4294967295L, null),
    MEDIUMBLOB(false, false, null, null),
    LONGBLOB(false, false, null, null),
    TEXT(true, false, 4294967295L, null),
    MEDIUMTEXT(false, false, null, null),
    LONGTEXT(false, false, null, null),
    ENUM(false, false, null, null),
    SET(false, false, null, null),
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

    MysqlColumnTypeEnum(boolean declareLength, boolean declareAccuracy, Long maxLength, Long maxAccuracy) {
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