/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */



package com.fade.mybatis.enums;

/**
 * Created by qingquanzhong on 2016/12/19.
 */

import com.fade.mybatis.utils.ConstansValue;
import com.fade.mybatis.utils.StringUtils;

/**
 * Description: 命名策略<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 19:00:00
 * @since JDK 1.8
 */
public enum NamingStrategyType {

    /** 原样 */
    original,

    /** 去掉前缀 */
    remove_prefix,

    /** 加固定前缀 */
    plus_prefix,

    /** 下划线转驼峰 */
    underline_to_camel,

    /** 去掉前缀转驼峰 */
    remove_prefix_to_camel,

    /** 加前缀转驼峰 */
    plus_prefix_to_camel;

    public static String plusPrefix(String target, String prefix) {
        if (StringUtils.isBlank(target) || StringUtils.isBlank(prefix)) {
            return target;
        }

        return prefix + target;
    }

    public static String plusPrefixAnd2Camel(String target, String prefix) {
        return underlineToCamel(plusPrefix(target, prefix));
    }

    public static String removePrefix(String target, String prefix) {
        if (StringUtils.isBlank(target) || StringUtils.isBlank(prefix)) {
            return target;
        }

        return target.trim().replaceFirst(prefix.trim(), "");
    }

    public static String removePrefixAnd2Camel(String target, String prefix) {
        return underlineToCamel(removePrefix(target, prefix));
    }

    public static String underlineToCamel(String param) {
        if (StringUtils.isBlank(param)) {
            return "";
        }

        int           len = param.length();
        StringBuilder sb  = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);

            if (ConstansValue.UNDERLINE == c) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}

