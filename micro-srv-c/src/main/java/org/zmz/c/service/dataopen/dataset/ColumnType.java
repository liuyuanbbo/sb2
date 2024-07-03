package org.zmz.c.service.dataopen.dataset;

public interface ColumnType {

    /**
     * 建字段SQL是否可以声明长度
     */
    boolean canDeclareLength();

    /**
     * 建字段SQL是否可以声明精度
     */
    boolean canDeclareAccuracy();

    /**
     * 获取字段声明的最大长度，该长度为建字段时SQL不会报错的最大长度，不一定是实际字段类型的最大长度
     *
     * @return 最大长度
     */
    Long getMaxLength();

    /**
     * 获取字段声明的最大精度，该长度为建字段时SQL不会报错的最大精度，不一定是实际字段类型的最大精度
     *
     * @return 最大长度
     */
    Long getMaxAccuracy();
}