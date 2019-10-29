package com.xinke.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: RegexHelper
 * @Description: java正则表达式工具类
 * @author sunjl
 */
public abstract class RegexUtil {

    /**
     * @Title: isNumeric
     * @Description: 是否是纯数字字符串
     * @param str 输入字符串
     * @return 是否是纯数字字符串
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * @Title: isMatch
     * @Description: 检查字符串是否匹配正则表达式
     * @param value 需要判断的字符串
     * @param regex 判断使用的正则表达式
     * @return boolean 返回类型
     */
    public static boolean isMatch(final String value, final String regex) {
        final Pattern p = Pattern.compile(regex);
        final Matcher m = p.matcher(value);
        return m.find();
    }
}
