/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.utils;

/**
 * Created by Administrator on 2016/12/18.
 */

/**
 * Description: 字符串工具类<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 06:01:11
 * @since JDK 1.8
 */
public final  class StringUtils {

    private StringUtils(){

    }
    
    public static String concat (String base, String target, String with){
    	if (isBlank(base)) return with + target;
    	if (!base.endsWith(with)) base =base + with;
    	return base + target;
    }

    /**
     * 判断所给字符串是否空或空串.
     * <pre>
     * isBlank(null) = true
     * isBlank("") = true
     * isBlank("sss ") = false
     * </pre>
     * @author qingquanzhong
     * @param str 目标字符串
     * @return 是否为空
     */
    public static boolean isBlank(CharSequence  str) {
        int len;
        if (str == null || (len = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < len; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(CharSequence  str) {
        return !isBlank(str);
    }
}
