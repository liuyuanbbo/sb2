package org.zmz.c.service.dataopen.dataset;

import cn.hutool.core.util.StrUtil;

public enum SqlFuncEnum {

    /**
     * 空的函数
     */
    nullFunc,
    /**
     * 原生函数
     */
    Native,
    /**
     * 环比
     */
    momGrowth,
    /**
     * 月累计
     */
    monthTotal,
    /**
     * 年累计
     */
    yearTotal,
    /**
     * 年同比
     */
    yoyGrowth,
    /**
     * 日账期的月同比
     */
    mmGrowth, MAX, MIN, COUNT, COUNTDISTINCT, LOGICCOUNT,
    /**
     * 环比的基础上增加新选项环比绝对值，环比绝对值即本期减去上期即可
     */
    pp;

    public static SqlFuncEnum getFuncByName(String func) {
        if (StrUtil.isBlank(func)) {
            return nullFunc;
        }
        for (SqlFuncEnum value : SqlFuncEnum.values()) {
            if (value.toString().equals(func.replace("-", ""))) {
                return value;
            }
        }
        return Native;
    }
}