package org.zmz.c.utils;

import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.OutPutMode;
import org.zmz.c.service.dataopen.dataset.SqlBuilderHelper;

import java.util.HashMap;
import java.util.Map;

public class AcctTimeUtil {
    /**
     * 获取并添加符号
     *
     * @param scheduleType 调度类型
     * @param cycleType    周期类型
     * @param columnType   字段类型
     * @param outPutMode   输出模式
     * @return String
     */
    public static String getAcctValAddQuotOutPubMode(String scheduleType,
                                                     String cycleType,
                                                     String columnType,
                                                     String outPutMode) {
        boolean isStr = SqlBuilderHelper.isStringType(columnType);
        if (OutPutMode.SQL.equalsIgnoreCase(outPutMode)) {
            return SqlBuilderHelper.addQuot(Constants.ACCT_CODE_EXP, isStr);
        } else {
            String acctVal = getAcctVal(scheduleType, cycleType);

            return SqlBuilderHelper.addQuot(acctVal, isStr);
        }
    }

    /**
     * 根据调度周期和表周期获取默认账期条件值
     */
    public static String getAcctVal(String scheduleType, String cycleType) {
        Map<String, String> acctValMap = new HashMap<>();
        if (Constants.SCHEDULE_LOOP_TYPE_M.equalsIgnoreCase(cycleType)) {
            // 一次性sql的减1
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_O, "$add_month(${month_id},-1)");
            // 月表按月/日/时/分调度
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_M, "${month_id}");
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_D, "${month_id}");
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_H, "${month_id}");
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_F, "${month_id}");
        }
        if (Constants.SCHEDULE_LOOP_TYPE_D.equalsIgnoreCase(cycleType)) {
            // 一次性sql的减1
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_O, "$add_day(${day_id},-1)");
            // 日表按月调度
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_M, "$add_day(${month_id},L)");
            // 日表按日/小时/分调度
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_D, "${day_id}");
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_H, "${day_id}");
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_F, "${day_id}");
        }
        if (Constants.SCHEDULE_LOOP_TYPE_H.equalsIgnoreCase(cycleType)) {
            // 一次性sql的减1
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_O, "$add_hour(${hour_id},-1)");
            // 小时表按月调度
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_M, "$add_hour(${month_id},L)");
            // 小时表按日/小时/分调度
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_D, "$add_hour(${day_id},L)");
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_H, "${hour_id}");
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_F, "${hour_id}");
        }
        if (Constants.SCHEDULE_LOOP_TYPE_F.equalsIgnoreCase(cycleType)) {
            // 一次性sql的减1
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_O, "$add_minute(${minute_id},-1)");
            // 分表按月调度
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_M, "$add_minute(${month_id},L)");
            // 分表按日/小时/分调度
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_D, "$add_minute(${day_id},L)");
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_H, "$add_minute(${hour_id},L)");
            acctValMap.put(Constants.SCHEDULE_LOOP_TYPE_F, "${minute_id}");
        }

        return acctValMap.getOrDefault(scheduleType, Constants.ACCT_CODE_EXP);
    }
}
