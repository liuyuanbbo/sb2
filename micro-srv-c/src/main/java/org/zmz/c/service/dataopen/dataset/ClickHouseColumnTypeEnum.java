package org.zmz.c.service.dataopen.dataset;

public enum ClickHouseColumnTypeEnum implements ColumnType {
    // 无符号整型8位
    UInt8(false, false, null, null),
    // 无符号整型16位
    UInt16(false, false, null, null),
    // 无符号整型32位
    UInt32(false, false, null, null),
    // 无符号整型64位
    UInt64(false, false, null, null),
    // 无符号整型128位
    UInt128(false, false, null, null),
    // 无符号整型256位
    UInt256(false, false, null, null),
    // 有符号整型8位
    Int8(false, false, null, null),
    // 有符号整型8位
    Int16(false, false, null, null),
    // 有符号整型16位
    Int32(false, false, null, null),
    // 有符号整型64位
    Int64(false, false, null, null),
    // 有符号整型128位
    Int128(true, false, null, null),
    // 有符号整型256位
    Int256(true, false, null, null),
    // 浮点数32位
    Float32(false, false, null, null),
    // 浮点数64位
    Float64(false, false, null, null),
    // 高精度类型 Decimal(m,n)
    Decimal(true, true, 38L, 38L),
    // Decimal32(s):整数+小数位置9位，s表示小数位置的数量
    Decimal32(true, true, 9L, 9L),
    // Decimal64(s):整数+小数位置共18位，s表示小数位置的数量
    Decimal64(true, true, 18L, 18L),
    // Decimal128(s):整数+小数位置共38位，s表示小数位置的数量
    Decimal128(true, true, 38L, 38L),
    // Decimal128:整数+小数位置共76位，s表示小数位置的数量,
    Decimal256(true, true, 76L, 76L),
    // 字符串类类型
    String(false, false, null, null),
    // 固定长度字段类型
    FixedString(false, false, null, null),
    // 日期类型，以YYYY-MM-DD格式存储
    Date(false, false, null, null),
    // 日期时间类型，包括日期和时间，以YYYY-MM-DD HH:MM:SS格式存储
    Datetime(false, false, null, null),
    // DateTime64(N, M)：带有精度的日期时间类型，其中N表示秒的精度，M表示小数部分的位数
    Datetime64(false, false, null, null),
    // Array(T)：包含类型为T的数组
    Array(false, false, null, null),
    // 枚举
    Enum(false, false, null, null),
    // 元组类型由1~n个元素组成，每个元素之间允许设置不同的数据类型，且彼此之间不要求兼容
    Tuple(false, false, null, null),
    // 嵌套数据结构，可以包含复杂的数据类型
    Nested(false, false, null, null);

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

    ClickHouseColumnTypeEnum(boolean declareLength, boolean declareAccuracy, Long maxLength, Long maxAccuracy) {
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
        return this.maxLength;
    }

    @Override
    public Long getMaxAccuracy() {
        return this.maxAccuracy;
    }
}