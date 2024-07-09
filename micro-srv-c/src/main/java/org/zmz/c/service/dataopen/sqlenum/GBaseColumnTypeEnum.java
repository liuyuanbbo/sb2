package org.zmz.c.service.dataopen.sqlenum;

import org.zmz.c.service.dataopen.dataset.ColumnType;

public enum GBaseColumnTypeEnum implements ColumnType {

    // 小数类型
    SMALLINT(true, false, 255L, null),
    // 整形
    INT8(true, false, 255L, null), INT(true, false, 255L, null),
    // 整形
    INTEGER(true, false, 255L, null),
    // 长整形
    BIGINT(true, false, 255L, null),
    // 小数类型
    DECIMAL(true, true, 65L, 30L),
    // 数字
    NUMERIC(true, true, 65L, 30L),
    // 浮点小数
    FLOAT(true, true, 255L, 30L),
    // 双精度浮点
    DOUBLE(true, true, 255L, 30L),
    // 字符形
    CHAR(true, false, 255L, null),
    // 字符串
    VARCHAR(true, false, 4294967295L, null),
    // 拔萝卜
    BLOB(true, false, 4294967295L, null),
    // 大字符
    CLOB(false, false, null, null),
    // 文本
    TEXT(true, false, 4294967295L, null),
    // 日期
    DATE(false, false, null, null),
    // 精确时间类型
    DATETIME(true, false, 6L, null),
    // 时间戳
    TIMESTAMP(true, false, 6L, null),
    // 布尔类型
    BOOLEAN(false, false, 6L, null);

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

    GBaseColumnTypeEnum(boolean declareLength, boolean declareAccuracy, Long maxLength, Long maxAccuracy) {
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