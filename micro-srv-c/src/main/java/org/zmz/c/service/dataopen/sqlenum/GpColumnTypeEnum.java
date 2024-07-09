package org.zmz.c.service.dataopen.sqlenum;

import org.zmz.c.service.dataopen.dataset.ColumnType;

public enum GpColumnTypeEnum implements ColumnType {

    /**
     * GP字段类型
     */
    SMALLINT(false, false, null, null),
    INTEGER(false, false, null, null),
    BIGINT(false, false, null, null),
    DECIMAL(true, true, 1000L, 1000L),
    NUMERIC(true, true, 1000L, 1000L),
    REAL(false, false, null, null),
    SERIAL(false, false, null, null),
    SERIAL4(false, false, null, null),
    SERIAL8(false, false, null, null),
    BIGSERIAL(false, false, null, null),
    CHARACTER(true, false, 10485760L, null),
    TEXT(false, false, null, null),
    INTERVAL(true, false, 6L, null),
    DATE(false, false, null, null),
    TIME(true, false, 6L, null),
    TIMETZ(true, false, 6L, null),
    TIMESTAMP(true, false, 6L, null),
    TIMESTAMPTZ(true, false, 6L, null),
    BIT(true, false, 83886080L, null),
    BOOL(false, false, null, null),
    BOOLEAN(false, false, null, null),
    BOX(false, false, null, null),
    BYTEA(false, false, null, null),
    CHAR(true, false, 10485760L, null),
    BPCHAR(true, false, 10485760L, null),
    CIDR(false, false, null, null),
    CIRCLE(false, false, null, null),
    FLOAT4(false, false, null, null),
    FLOAT8(false, false, null, null),
    INET(false, false, null, null),
    INT(false, false, null, null),
    INT2(false, false, null, null),
    INT4(false, false, null, null),
    INT8(false, false, null, null),
    LINE(false, false, null, null),
    LSEG(false, false, null, null),
    MACADDR(false, false, null, null),
    MONEY(false, false, null, null),
    PATH(false, false, null, null),
    POINT(false, false, null, null),
    POLYGON(false, false, null, null),
    TSQUERY(false, false, null, null),
    TSVECTOR(false, false, null, null),
    TXID_SNAPSHOT(false, false, null, null),
    UUID(false, false, null, null),
    VARBIT(true, false, 83886080L, null),
    VARCHAR(true, false, 10485760L, null),
    XML(false, false, null, null),
    NUMBER(true, true, 1000L, 1000L);


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

    GpColumnTypeEnum(boolean declareLength, boolean declareAccuracy, Long maxLength, Long maxAccuracy) {
        this.declareLength = declareLength;
        this.declareAccuracy = declareAccuracy;
        this.maxLength = maxLength;
        this.maxAccuracy = maxAccuracy;
    }

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