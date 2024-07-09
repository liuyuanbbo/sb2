package org.zmz.c.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;

@Slf4j
public final class I18nUtil {

    private static final String BASE_NAME = "config.i18n.common";

    private static final Integer TWO = 2;

    /**
     * 中文
     */
    public static final String LANGUAGE_ZH = "zh";

    /**
     * 英文
     */
    public static final String LANGUAGE_EN = "en";

    private I18nUtil() {

    }

    /**
     * 逗号（中文逗号和英文逗号）
     *
     * @return 逗号
     */
    public static String comma() {
        return get("Common_comma");
    }

    /**
     * 根据当前Locale获取resource/i18n/messages_xx_XX.properties的key的值
     *
     * @param key Properties的key
     */
    public static String get(String key) {
        return get(BASE_NAME, key, getLocale());
    }

    /**
     * 根据当前Locale获取baseName的properties的key的值
     *
     * @param baseName application.properties配置的指定国际化Properties的名称
     * @param key      Properties的key
     */
    public static String get(String baseName, String key) {
        return get(baseName, key, getLocale());
    }

    /**
     * 根据locale获取baseName（application.properties配置的）的properties的key的值
     *
     * @param baseName
     * @param key
     * @param locale
     */
    public static String get(String baseName, String key, Locale locale) {
        return ResourceBundle.getBundle(baseName, locale).getString(key);
    }

    /**
     * 获取国际化参数，key-value
     *
     * @param key 参数值
     * @param arg 要替换的可变参数
     * @return String
     */
    public static String getWithArg(String key, Object arg) {
        return getWithArgs(key, new Object[]{
                arg
        });
    }

    /**
     * 获取国际化参数，可变参数
     *
     * @param key  参数值
     * @param args 要替换的可变参数
     * @return String
     */
    public static String getWithArgs(String key, Object... args) {
        return getWithArgs(BASE_NAME, key, getLocale(), args);
    }

    /**
     * 获取国际化参数，数组替换
     *
     * @param key  key
     * @param args 数组
     * @return String
     */
    public static String getWithArray(String key, Object[] args) {
        return getWithArgs(BASE_NAME, key, getLocale(), args);
    }

    /**
     * 获取国际化参数，基础通用方法
     *
     * @param baseName 基础文件名
     * @param key      key
     * @param locale   语言
     * @param args     参数列表
     * @return String
     */
    public static String getWithArgs(String baseName, String key, Locale locale, Object[] args) {
        String value = get(baseName, key, locale);
        return replaceParams(value, args);
    }

    /**
     * 对国际化语言进行替换
     *
     * @param value 值
     * @param args  参数
     * @return String
     */
    private static String replaceParams(String value, Object[] args) {
        if (value == null || args == null || args.length == 0) {
            return value;
        }
        String newValue = value;
        try {
            for (int i = 0; i < args.length; i++) {
                String arg = String.valueOf(args[i]);
                // 出现符号“$”，会按照$12的分组模式进行匹配
                arg = Matcher.quoteReplacement(arg);
                String argKey = "\\{" + i + "\\}";
                newValue = newValue.replaceAll(argKey, arg);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return newValue;
    }

    /**
     * 获取语言配置
     *
     * @return String
     */
    public static String getLanguage() {
        Locale locale = getLocale();
        String language = locale.getLanguage();
        log.info("getLanguage当前语言为：  {}", language);
        if (language != null) {
            log.info("当前语言是否为英文：  {}", language.toLowerCase().startsWith(I18nUtil.LANGUAGE_EN));
            // 考虑到大部分getLanguage返回做英文比较时都是用equal，这里直接返回en如果是en-US之类的
            return language.toLowerCase().startsWith(I18nUtil.LANGUAGE_EN) ? I18nUtil.LANGUAGE_EN
                    : I18nUtil.LANGUAGE_ZH;
        }
        return I18nUtil.LANGUAGE_ZH;
    }

    /**
     * 获取语言配置
     *
     * @return 语言
     */
    public static String getLanguage(String language) {
        if (StringUtils.isNotEmpty(language)) {
            // 考虑到大部分getLanguage返回做英文比较时都是用equal，这里直接返回en如果是en-US之类的
            return language.toLowerCase().startsWith(I18nUtil.LANGUAGE_EN) ? I18nUtil.LANGUAGE_EN
                    : I18nUtil.LANGUAGE_ZH;
        } else {
            return getLanguage();
        }
    }

    /**
     * 获取地区环境
     *
     * @return Locale
     */
    public static Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    /**
     * 从redis缓存中获取语言
     *
     * @return String
     */
    private static String getLanguageFromCache() {
        //String key = RedisUtil.getDcsysParamRedisKey("LOCALE");
        //return RedisUtil.getString(key);
        return "cn";
    }

    /**
     * 设置语言环境
     *
     * @param language 语言
     */
    public static void setLanguage(String language) {
        if (StringUtils.isEmpty(language)) {
            language = getLanguageFromCache();
            log.info("从缓存获取到的语言为：  {}", language);
            if (StringUtils.isEmpty(language)) {
                log.info("默认语言从当前服务器中获取");
                return;
            }
            // 其他情况设置当前语言环境
            setLocale(language);
        } else {
            // 查看当前语言是否能分割
            String[] arr = language.split("-");
            if (arr.length < TWO) {
                setLocale(language);
            } else {
                Locale locale = new Locale(arr[0], arr[1]);
                LocaleContextHolder.setLocale(locale);
            }
        }
    }

    /**
     * 设置本地语言,可能当前语言是zh/en/tw
     *
     * @param language 保存的语言
     */
    private static void setLocale(String language) {
        if (language != null && language.contains("en")) {
            Locale locale = new Locale(language, "US");
            LocaleContextHolder.setLocale(locale);
        } else if (language != null && language.contains("tw")) {
            Locale locale = new Locale(language, "TW");
            LocaleContextHolder.setLocale(locale);
        } else {
            Locale locale = new Locale(language, "CN");
            LocaleContextHolder.setLocale(locale);
        }
    }

}