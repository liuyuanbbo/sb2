package org.zmz.c.utils;

import cn.hutool.core.collection.CollUtil;
import org.apache.commons.lang3.StringUtils;
import org.zmz.c.qo.dataopen.Constants;
import org.zmz.c.qo.dataopen.OutPutMode;
import org.zmz.c.service.dataopen.sqltype.SqlBuilderHelper;
import org.zmz.c.vo.dataopen.dataset.CycleInfo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    public static Map<String, CycleInfo> getCycleInfo() {
        Map<String, CycleInfo> cycleInfoMap = new HashMap<>();
        // 国际化
        cycleInfoMap.put("Y", new CycleInfo("Y", I18nUtil.get("Y"), Constants.ACCT_CODE_Y, "yyyy"));
        cycleInfoMap.put("M", new CycleInfo("M", I18nUtil.get("M"), Constants.ACCT_CODE_M, "yyyyMM"));
        cycleInfoMap.put("D", new CycleInfo("D", I18nUtil.get("D"), Constants.ACCT_CODE_D, "yyyyMMdd"));
        cycleInfoMap.put("H",
                new CycleInfo("H", I18nUtil.get("H"), Constants.ACCT_CODE_H, "yyyyMMddHH"));
        cycleInfoMap.put("F",
                new CycleInfo("F", I18nUtil.get("F"), Constants.ACCT_CODE_F, "yyyyMMddHHmm"));
        cycleInfoMap.put("O", new CycleInfo("O", I18nUtil.get("O"), "", ""));
        return cycleInfoMap;
    }

    /**
     * 多个周期获取最细的周期类型，默认日
     */
    public static String getCycleType(Collection<String> dataCycles) {
        List<String> allCycle = Arrays.asList(Constants.SCHEDULE_LOOP_TYPE_F, Constants.SCHEDULE_LOOP_TYPE_H,
                Constants.SCHEDULE_LOOP_TYPE_D, Constants.SCHEDULE_LOOP_TYPE_M);
        if (CollUtil.isNotEmpty(dataCycles)) {
            for (String cycle : allCycle) {
                if (!dataCycles.contains(cycle)) {
                    return cycle;
                }
            }
        }
        return Constants.SCHEDULE_LOOP_TYPE_D;
    }

    /**
     * 根据获取账期
     *
     * @param scheduleType 调度类型
     * @param cycleType    周期
     * @param outPutMode   输出模式
     * @return outPutMode
     */
    public static String getAcctValOutPubMode(String scheduleType, String cycleType, String outPutMode) {
        // sql的默认输出账期字段替换
        if (OutPutMode.SQL.equalsIgnoreCase(outPutMode)) {
            return Constants.ACCT_CODE_EXP;
        } else {
            return getAcctVal(scheduleType, cycleType);
        }
    }

    public static String getDefAcctValue(String cycleType) {
        if (isCycle(cycleType)) {
            switch (cycleType.toUpperCase()) {
                case "Y":
                    return DateUtil.getLastAcct(Calendar.YEAR);
                case "M":
                    return DateUtil.getLastMonth(DateUtil.DATE_FORMAT_6);
                case "H":
                    return DateUtil.getLastAcct(Calendar.HOUR);
                case "F":
                    return DateUtil.getLastAcct(Calendar.MINUTE);
                case "D":
                    return DateUtil.getYesterday(DateUtil.DATE_FORMAT_8);
                default:
                    return DateUtil.getDate();
            }
        }
        return "";
    }

    public static boolean isCycle(String dataCycle) {
        return StringUtils.isNotEmpty(dataCycle) && !"O".equalsIgnoreCase(dataCycle);
    }

    /**
     * 返回上月
     *
     * @param pattern 日期的格式
     */
    public static String getLastMonth(String pattern) {
        Calendar c = Calendar.getInstance();
        // 当前月份减1
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = c.getTime();
        return sdf.format(date);
    }
}
