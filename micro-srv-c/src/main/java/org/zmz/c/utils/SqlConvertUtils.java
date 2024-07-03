package org.zmz.c.utils;

import org.apache.commons.lang3.StringUtils;
import org.zmz.c.qo.dataopen.Constants;

import java.util.regex.Pattern;

public class SqlConvertUtils {

    private static final Pattern PATTERN = Pattern.compile("-?[0-9]+.?[0-9]*");

    /**
     * 除法转换成case when
     *
     * @param exp 表达式
     * @return 转换后的表达式
     */
    public static String divisionConvert(String exp) {
        if (StringUtils.isBlank(exp) || !exp.contains(SqlUtils.STR_DIV)) {
            return exp;
        }
        return convertHandle(exp).replaceAll("@", "/");
    }

    private static String convertHandle(String exp) {
        StringBuilder newExp = new StringBuilder();
        int firstIndex = exp.indexOf(SqlUtils.STR_DIV);
        // 包含分母的字符串
        String dividendSub = exp.substring(firstIndex + 1);
        int divisorendIndex = captureDividend(dividendSub);
        String divisorendOtherStr = null;
        String divisorend;
        if (divisorendIndex != dividendSub.length() - 1) {
            divisorend = dividendSub.substring(0, divisorendIndex);
            divisorendOtherStr = dividendSub.substring(divisorendIndex);
        } else {
            // 全部字符为分母
            divisorend = dividendSub;
        }
        // 包含分子的字符串
        String divisorSub = exp.substring(0, firstIndex);
        int divisorIndex = captureDivisor(divisorSub);
        String divisorOtherStr = null;
        String divisor;
        if (divisorIndex > 0) {
            divisor = divisorSub.substring(divisorIndex + 1);
            divisorOtherStr = divisorSub.substring(0, divisorIndex + 1);
        } else {
            // 全部字符为分子
            divisor = divisorSub.substring(divisorIndex);
        }

        if (null != divisorOtherStr) {
            newExp.append(divisorOtherStr);
        }
        boolean isNum = PATTERN.matcher(divisorend).matches();
        if (divisorend.startsWith(Constants.BRACKET_LEFT)) {
            if (null != divisorendOtherStr) {
                newExp.append("(case when ").append(divisorend).append(" = 0 then 0 else ").append(divisor).append("@")
                        .append(divisorend).append(" end)").append(divisorendOtherStr);
            } else {
                if ("0".equalsIgnoreCase(divisorend)) {
                    newExp.append("0");
                } else if (isNum) {
                    newExp.append(divisor).append("@").append(divisorend);
                } else {
                    newExp.append("case when ").append(divisorend).append(" = 0 then 0 else ").append(divisor).append("@")
                            .append(divisorend).append(" end");
                }
            }
        } else {
            if (null != divisorendOtherStr) {
                newExp.append("(case when (").append(divisorend).append(") = 0 then 0 else ").append(divisor)
                        .append("@").append(divisorend).append(" end)").append(divisorendOtherStr);
            } else {
                if ("0".equalsIgnoreCase(divisorend)) {
                    newExp.append("0");
                } else if (isNum) {
                    newExp.append(divisor).append("@").append(divisorend);
                } else {
                    newExp.append("case when ").append(divisorend).append(" = 0 then 0 else ").append(divisor).append("@")
                            .append(divisorend).append(" end");
                }
            }
        }
        if (newExp.indexOf(SqlUtils.STR_DIV) > 0) {
            return convertHandle(newExp.toString());
        }
        return newExp.toString();
    }

    private static int captureDividend(String divisorend) {
        int num = 0;
        for (int i = 0; i < divisorend.length(); i++) {
            if (i == divisorend.length() - 1) {
                return i;
            }
            char ch = divisorend.charAt(i);
            String str = String.valueOf(ch);
            if (Constants.BRACKET_LEFT.equals(str)) {
                num++;
            }
            if (num == 0 && Constants.BRACKET_RIGHT.contains(str)) {
                return i;
            }
            if (num > 0 && Constants.BRACKET_RIGHT.equals(str)) {
                num--;
            }
            if (num == 0 && Constants.LIST_DIVIDEND_SYMBOL.contains(str)) {
                return i;
            }
        }
        return 0;
    }

    private static int captureDivisor(String divisor) {
        int num = 0;
        for (int i = divisor.length() - 1; i >= 0; i--) {
            if (i == 0) {
                return i;
            }
            char ch = divisor.charAt(i);
            String str = String.valueOf(ch);
            if (Constants.BRACKET_RIGHT.equals(str)) {
                num++;
            }
            if (num == 0 && Constants.BRACKET_LEFT.contains(str)) {
                return i;
            }
            if (num > 0 && Constants.BRACKET_LEFT.equals(str)) {
                num--;
            }
            if (num == 0 && Constants.LIST_DIVISORS_SYMBOL.contains(str)) {
                return i;
            }
        }
        return 0;
    }
}
