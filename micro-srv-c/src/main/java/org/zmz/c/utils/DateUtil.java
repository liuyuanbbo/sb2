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
}