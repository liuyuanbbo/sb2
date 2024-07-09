package org.zmz.c.service.dataopen.sqlfunc;

import org.zmz.c.qo.dataopen.Constants;

/**
 * 环比：比上期：（本期实际值 - 上期实际值）
 */
public class MomGrowthFuncParser extends TotalOrGrowthFuncParser {
    @Override
    public String getDateOffset(String cycleType) {
        if (Constants.SCHEDULE_LOOP_TYPE_M.equalsIgnoreCase(cycleType)) {
            // 月周期的环比
            return "$add_month(${month_id},-1)";
        }
        if (Constants.SCHEDULE_LOOP_TYPE_D.equalsIgnoreCase(cycleType)) {
            // 日周期的环比
            return "$add_day(${day_id},-1)";
        }
        return Constants.ACCT_CODE_EXP;
    }

}
