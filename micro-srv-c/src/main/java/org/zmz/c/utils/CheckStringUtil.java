package org.zmz.c.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CheckStringUtil {

    private CheckStringUtil() {
    }

    /**
     * 检查字符串是否包含{},如果包含则为动态字sql
     *
     * @param sql 检查sql
     * @return boolean
     */
    public static boolean isDynamicSql(String sql) {
        // 检查字符串中是否包含{},且不包括${},其中 {} 中可以包含任意字符
        Pattern pattern = Pattern.compile("\\{(?!(\\$\\{.*}))[^\\{\\}]*\\}");
        Matcher matcher = pattern.matcher(sql);
        return matcher.find();
    }

    /**
     * @param sql 获取待替换中的{}内容
     * @return List<String>
     */
    public static List<String> getSqlColumnList(String sql) {

        if (StringUtils.isEmpty(sql)) {
            return Collections.emptyList();
        }

        // 检查字符串中是否包含{},且不包括${},其中 {} 中可以包含任意字符
        List<String> resultList = new ArrayList<>(10);

        Pattern pattern = Pattern.compile("\\{([^$][^}]*)\\}");
        Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) {
            resultList.add(matcher.group(1));
        }

        return resultList;
    }

}