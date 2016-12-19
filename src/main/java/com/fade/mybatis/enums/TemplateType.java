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
 * Description: 模版类型<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 18:06:35
 * @since JDK 1.8
 */
public enum TemplateType {

    mapper_xml {
        @Override
        public String getDesc() {
            return "mapper_xml";
        }

        @Override
        public String getValue() {

            return "Mapper xml配置模版";
        }
    },mapper_java {
        @Override
        public String getDesc() {
            return "mapper_java";
        }

        @Override
        public String getValue() {

            return "Mapper java 接口模版";
        }
    },entity_java {
        @Override
        public String getDesc() {
            return "entity_java";
        }

        @Override
        public String getValue() {

            return "Entity 模版";
        }
    },service_java {
        @Override
        public String getDesc() {
            return "service_java";
        }

        @Override
        public String getValue() {

            return "Service 模版";
        }
    };

    public abstract  String getValue();

    public  abstract  String getDesc();

    private static final Map<String, TemplateType> map;

    static {
        map = new HashMap<>(CollectionUtil.capacitySize(values().length));
        for (TemplateType type : values()) {
            map.put(type.getValue(), type);
        }
    }

    /**根据key获取枚举,未匹配到则返回空*/
    public static TemplateType get(String value) {
        if (StringUtils.isBlank(value))  return null;
        return map.get(value);
    }
}
