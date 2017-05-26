package cc.cleverfan.web.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具
 */
public class StringUtil {

    /**
     * 字符串分隔符
     */
    public static final String separator = String.valueOf((char) 29);

    /**
     * 判断字符串是否为空
     *
     * @param str 待判断字符串
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否非空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 分割固定格式字符串
     *
     * @param str
     * @param separator
     * @return
     */
    public static String[] splitString(String str, String separator) {
        return StringUtils.splitByWholeSeparator(str, separator);
    }
}
