/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.enums;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import com.fade.mybatis.utils.CollectionUtil;
import com.fade.mybatis.utils.StringUtils;

import java.io.File;
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
            return "Mapper xml配置模版";
        }
        
        @Override
        public String getValue() {

            return "mapper_xml";
        }

		@Override
		public String getPathTmeplate() {
			return new StringBuilder(20).append("%s").append(File.separator).append("%s").append(File.separator).append("%sMapper.xml").toString();
		}
    },mapper_java {
        @Override
        public String getDesc() {
            return "Mapper java 接口模版";
        }

        @Override
        public String getValue() {

            return "mapper_java";
        }

		@Override
		public String getPathTmeplate() {
			return new StringBuilder(12).append("%s").append(File.separator).append("%s").append(File.separator).append("%sMapper.java").toString();
		}
    },entity_java {
        @Override
        public String getDesc() {
            return "Entity 模版";
        }

        @Override
        public String getValue() {

            return "entity_java";
        }

		@Override
		public String getPathTmeplate() {
			return new StringBuilder(12).append("%s").append(File.separator).append("%s").append(File.separator).append("%sEntity.java").toString();
		}
    },service_java {
        @Override
        public String getDesc() {
            return "Service 模版";
        }

        @Override
        public String getValue() {

            return "service_java";
        }

		@Override
		public String getPathTmeplate() {
			return new StringBuilder(12).append("%s").append(File.separator).append("%s").append(File.separator).append("%sLogicService.java").toString();
		}
    },
    service_itf_java {
        @Override
        public String getDesc() {
            return "Service 接口 模版";
        }

        @Override
        public String getValue() {

            return "service_itf_java";
        }

		@Override
		public String getPathTmeplate() {
			return new StringBuilder(12).append("%s").append(File.separator).append("%s").append(File.separator).append("I%sLogicService.java").toString();
		}
    },
    controller_java {
        @Override
        public String getDesc() {
            return "Controller 模版";
        }

        @Override
        public String getValue() {

            return "controller_java";
        }

		@Override
		public String getPathTmeplate() {
			return new StringBuilder(12).append("%s").append(File.separator).append("%s").append(File.separator).append("%sController.java").toString();
		}
    };

    public abstract  String getValue();

    public  abstract  String getDesc();
    
    public abstract String getPathTmeplate();
    
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
