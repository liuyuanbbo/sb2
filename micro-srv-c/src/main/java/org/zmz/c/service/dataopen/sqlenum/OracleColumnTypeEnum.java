package org.zmz.c.service.dataopen.sqlenum;

import org.zmz.c.service.dataopen.dataset.ColumnType;

public enum OracleColumnTypeEnum implements ColumnType {

    /**
     * ORACLE字段类型
     */
    // int相当于number(22)
    INT(false, false, 22L,
            null),
    NUMBER(true, true, 38L, 127L),
    DECIMAL(true, true, 38L,
            127L),
    INTEGER(false, false, null, null),
    BINARY_FLOAT(false, false, null, null),
    BINARY_DOUBLE(false, false, null, null),
    FLOAT(true, false, 126L, null),
    CHAR(true, false, 2000L, null),
    NCHAR(true, false, 1000L, null),
    VARCHAR(true, false, 4000L, null),
    VARCHAR2(true, false, 4000L, null),
    NVARCHAR2(true, false, 2000L, null),
    DATE(false, false, null, null),
    TIMESTAMP(true, false, 9L, null),
    BLOB(false, false, null, null),
    CLOB(false, false, null, null),
    NCLOB(false, false, null, null),
    LONG(false, false, null, null),
    BFILE(false, false, null, null);

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

    OracleColumnTypeEnum(boolean declareLength, boolean declareAccuracy, Long maxLength, Long maxAccuracy) {
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