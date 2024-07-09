package org.zmz.c.service.dataopen.dataset;

import org.apache.commons.lang3.StringUtils;

public enum SqlFuncEnum {

    /**
     * 空的函数
     */
    nullFunc,
    /**
     * 原生函数
     */
    Native,
    MAX, MIN, COUNT, COUNTDISTINCT, LOGICCOUNT,
    /**
     * 月累计
     */
    monthTotal,
    /**
     * 年累计
     */
    yearTotal,
    /**
     * 环比的基础上增加新选项环比绝对值，本期实际值 - 上期实际值
     */
    pp,
    /**
     * 环比，比上期，(本期实际值 -上期实际值)/上期实际值 x100%
     */
    momGrowth,
    /**
     * 年同比，比同期，本期实际值 -去年同期实际值
     */
    yoy,
    /**
     * 年同比，比同期， (本期实际值 -去年同期实际值)/去年同期实际值 x100%
     */
    yoyGrowth,
    /**
     * 日账期的月同比,本期实际值 -上月同期实际值
     */
    mm,
    /**
     * 月同比，比同期， (本期实际值 -上月同期实际值)/上月同期实际值 x100%
     */
    mmGrowth,
    /**
     * 日：比上年末：(本期实际值 -去年最后一天实际值)
     * 月：比上年末：（本期实际值 -去年12月实际值)
     */
    yearEnd,
    /**
     * 日：比上年末(%)：（本期实际值 -去年最后一天实际值)/去年最后一天实际值 x100%
     * 月：比上年末(%)：(本期实际值 -去年12月实际值)/去年12月实际值 x100%
     */
    yearEndGrowth;

    public static SqlFuncEnum getFuncByName(String func) {
        if (StringUtils.isBlank(func)) {
            return nullFunc;
        }
        /**
         * 自定义输出函数
         */
        for (SqlFuncEnum value : SqlFuncEnum.values()) {
            if (value.toString().equals(func.replace("-", ""))) {
                return value;
            }
        }
        return Native;
    }
}