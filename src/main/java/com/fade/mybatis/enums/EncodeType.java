/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.enums;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import com.fade.mybatis.utils.CollectionUtil;
import com.fade.mybatis.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 编码<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 13:49:01
 * @since JDK 1.8
 */
public enum EncodeType {

    UTF8{
        @Override
        public String getValue() {

            return "UTF-8";
        }

        @Override
        public String getDesc() {

            return "UTF-8";
        }
    },
    GBK{
        @Override
        public String getValue() {

            return "gbk";
        }

        @Override
        public String getDesc() {

            return "GBK 中文";
        }
    },
    GB2312{
        @Override
        public String getValue() {

            return "gb2312";
        }

        @Override
        public String getDesc() {

            return "GBK2312";
        }
    };

    public abstract  String getValue();

    public abstract  String getDesc();

    private static final Map<String, EncodeType> map;

    static {
        map = new HashMap<>(CollectionUtil.capacitySize(values().length));
        for (EncodeType type : values()) {
            map.put(type.getValue(), type);
        }
    }

    /**根据key获取枚举,未匹配到则返回空*/
    public static EncodeType get(String value) {
        if (StringUtils.isBlank(value))  return null;
        return map.get(value);
    }
}
