/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */



package com.fade.mybatis.enums;

/**
 * Created by qingquanzhong on 2016/12/18.
 */

import com.fade.mybatis.utils.CollectionUtil;
import com.fade.mybatis.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 数据库类型<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 12:00:00
 * @since JDK 1.8
 */
public enum DbType {
    mysql {
        @Override
        public String getValue() {
            return "mysql";
        }

        /**ip,port,database,characterEncoding 占位符处理*/
        @Override
        public String getUrl() {
            return "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=%s&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        }
        /**新版驱动*/
        @Override
        public String getDriverClassString() {
            return "com.mysql.cj.jdbc.Driver";
        }

        @Override
        public String getBackDriverClassString() {
            return "com.mysql.jdbc.Driver";
        }

        @Override
        public String getDesc() {
            return "Mysql DB";
        }
    },
    oracle {
        @Override
        public String getValue() {
            return "oracle";
        }

        /**ip,port,servicename*/
        @Override
        public String getUrl() {
            return "jdbc:oracle:thin:@%s:%s:%s";
        }

        @Override
        public String getDriverClassString() {
            return "oracle.jdbc.driver.OracleDriver";
        }

        @Override
        public String getBackDriverClassString() {
            return getDriverClassString();
        }

        @Override
        public String getDesc() {
            return "Oracle DB";
        }
    };

    public abstract String getDesc();

    public abstract String getValue();

    public abstract String getUrl();

    public abstract String getDriverClassString();

    public abstract String getBackDriverClassString();

    private static final Map<String, DbType> map;

    static {
        map = new HashMap<>(CollectionUtil.capacitySize(values().length));
        for (DbType type : values()) {
            map.put(type.getValue(), type);
        }
    }

    /**根据key获取枚举,未匹配到则返回空*/
    public static DbType get(String value) {
        if (StringUtils.isBlank(value))  return null;
        return map.get(value);
    }
}
