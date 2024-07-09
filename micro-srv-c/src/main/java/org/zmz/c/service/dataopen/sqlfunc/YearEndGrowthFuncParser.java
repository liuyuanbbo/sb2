package org.zmz.c.service.dataopen.sqlfunc;

import org.zmz.c.qo.dataopen.Constants;

public class YearEndGrowthFuncParser extends TotalOrGrowthFuncParser {

    @Override
    public String getDateOffset(String cycleType) {
        if (Constants.SCHEDULE_LOOP_TYPE_M.equalsIgnoreCase(cycleType)) {
            // 月周期的上年末
            return "$add_month(${month_id},-1,L) ";
        }
        if (Constants.SCHEDULE_LOOP_TYPE_D.equalsIgnoreCase(cycleType)) {
            // 日周期的上年末
            return "$add_day(${day_id},-1,0,L)";
        }
        return Constants.ACCT_CODE_EXP;
    }
}
