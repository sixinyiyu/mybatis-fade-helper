/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */



package com.fade.mybatis.enums;

import java.util.HashMap;
import java.util.Map;

import com.fade.mybatis.utils.CollectionUtil;

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
	
	public static String converNaming(String source, NamingStrategyType type, String prefix) {
		if (null == type || original.equals(type)) return source;
		if (remove_prefix.equals(type)) {
			source = removePrefix(source, prefix);
		} else if (plus_prefix.equals(type)) {
			source = plusPrefix(source, prefix);
		} else if (underline_to_camel.equals(type)) {
			source = underlineToCamel(source);
		} else if (remove_prefix_to_camel.equals(type)) {
			source = removePrefixAnd2Camel(source, prefix);
		} else if (plus_prefix_to_camel.equals(type)) {
			source = plusPrefixAnd2Camel(source, prefix);
		}
		return source;
	}

	/**增加前缀*/
    public static String plusPrefix(String target, String prefix) {
        if (StringUtils.isBlank(target) || StringUtils.isBlank(prefix)) {
            return target;
        }
        return prefix + target;
    }

    /**加前缀再转驼峰*/
    public static String plusPrefixAnd2Camel(String target, String prefix) {
        return underlineToCamel(plusPrefix(target, prefix));
    }

    /**去掉固定前缀*/
    public static String removePrefix(String target, String prefix) {
        if (StringUtils.isBlank(target) || StringUtils.isBlank(prefix)) {
            return target;
        }
        return target.trim().replaceFirst(prefix.trim(), "");
    }

    /**去掉前缀再转驼峰*/
    public static String removePrefixAnd2Camel(String target, String prefix) {
        return underlineToCamel(removePrefix(target, prefix));
    }
    
    /**首字母大写*/
    public static String toCapital(String target) {
    	if (StringUtils.isBlank(target)) return target;
    	return target.substring(0, 1).toUpperCase() + target.substring(1);
    }
    
    /**首字母小写*/
    public static String toLower(String target) {
    	if (StringUtils.isBlank(target)) return target;
    	return target.substring(0, 1).toLowerCase() + target.substring(1);
    }

    /**下划线转驼峰*/
    public static String underlineToCamel(String param) {
        if (StringUtils.isBlank(param)) {
            return "";
        }
        int  len = param.length();
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
    
    private static final Map<String, NamingStrategyType> map;

    static {
        map = new HashMap<>(CollectionUtil.capacitySize(values().length));
        for (NamingStrategyType type : values()) {
            map.put(type.name(), type);
        }
    }

    /**根据key获取枚举,未匹配到则返回空*/
    public static NamingStrategyType get(String value) {
        if (StringUtils.isBlank(value))  return null;
        return map.get(value);
    }
    
}

