package org.zmz.c.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

    // 14位日期时间格式
    public static final String DATE_TIME_FORMAT_14 = "yyyyMMddHHmmss";

    // 12位日期格式
    public static final String DATE_TIME_FORMAT_12 = "yyyyMMddHHmm";

    // 10位日期时间格式
    public static final String DATE_TIME_FORMAT_10 = "yyyyMMddHH";

    // 8位日期格式
    public static final String DATE_FORMAT_8 = "yyyyMMdd";

    // 6位日期格式
    public static final String DATE_FORMAT_6 = "yyyyMM";

    // 4位日期格式
    public static final String DATE_FORMAT_4 = "yyyy";

    public static String getLastAcct(int calendarType) {
        return getLastAcct(new Date(), calendarType);
    }

    /**
     * 返回上一账期
     *
     * @param date         开始日期
     * @param calendarType 账期类型，Calendar.YEAR
     */
    public static String getLastAcct(Date date, int calendarType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 月份减1
        calendar.add(calendarType, -1);
        String pattern = switch (calendarType) {
            case Calendar.YEAR -> DATE_FORMAT_4;
            case Calendar.MONTH -> DATE_FORMAT_6;
            case Calendar.HOUR -> DATE_TIME_FORMAT_10;
            case Calendar.MINUTE -> DATE_TIME_FORMAT_12;
            case Calendar.SECOND -> DATE_TIME_FORMAT_14;
            default -> DATE_FORMAT_8;
        };
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        date = calendar.getTime();
        return simpleDateFormat.format(date);

    }

    public static String getLastMonth(String pattern) {
        Calendar c = Calendar.getInstance();
        // 当前月份减1
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = c.getTime();
        return sdf.format(date);
    }

    /**
     * 返回昨天的日期
     *
     * @param pattern 日期的格式
     */
    public static String getYesterday(String pattern) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = c.getTime();
        return sdf.format(date);
    }

    /**
     * 得到应用服务器的当前时间
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 得到应用服务器当前日期，以8位日期显示。
     */
    public static String getDate() {
        Date date = getCurrentDate();
        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_FORMAT_8);
        return dateFormator.format(date);
    }
}