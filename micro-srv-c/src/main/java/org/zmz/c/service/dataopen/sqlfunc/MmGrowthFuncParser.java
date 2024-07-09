package org.zmz.c.service.dataopen.sqlfunc;

import org.zmz.c.qo.dataopen.Constants;

/**
 * 日表（月同比）：比同期：（本期实际值 -上月同期实际值）--上月同期 $add_day(${day_id},-1,0) --mm
 */
public class MmGrowthFuncParser extends TotalOrGrowthFuncParser {

    @Override
    public String getDateOffset(String cycleType) {
        if (Constants.SCHEDULE_LOOP_TYPE_M.equalsIgnoreCase(cycleType)) {
            // 月周期的月同比
            return "${month_id}";
        }
        if (Constants.SCHEDULE_LOOP_TYPE_D.equalsIgnoreCase(cycleType)) {
            // 日周期的月同比
            return "$add_day(${day_id},-1,0)";
        }
        return Constants.ACCT_CODE_EXP;
    }

}
