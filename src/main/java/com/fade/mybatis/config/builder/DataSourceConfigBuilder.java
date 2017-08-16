/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.config.builder;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import com.fade.mybatis.config.DataSourceConfig;
import com.fade.mybatis.enums.DbType;
import com.fade.mybatis.enums.EncodeType;
import com.fade.mybatis.utils.StringUtils;

/**
 * Description: 数据库配置构造器<br/>
 * (host,port,databaseName,dbType,userName,password)
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 13:12:44
 * @since JDK 1.8
 */
public class DataSourceConfigBuilder {

    /**主机名或ip*/
    private String host;

    /**端口号*/
    private Integer port;

    /**数据库名*/
    private String databaseName;

    /**数据库类型*/
    private DbType dbType;

    /**数据库用户名*/
    private String userName;

    /**数据库密码*/
    private String password;

    /**驱动名*/
    private String driverClass;

    /**编码*/
    private EncodeType encodeType = EncodeType.UTF8;

    public static DataSourceConfigBuilder newInstance() {
        return new DataSourceConfigBuilder();
    }

    public DataSourceConfig builder(){
        DataSourceConfig config =  new DataSourceConfig();
        init();
        config.setDbType(this.dbType);
        config.setDriverClass(this.driverClass);
        config.setUrl(this.processUrl());
        config.setUserName(this.userName);
        config.setPassword(this.password);
        config.setDbName(this.databaseName);
        config.argsCheck();
        return config;
    }

    private void init() {
        if (DbType.mysql.equals(this.dbType)) {
            if (null == this.port) this.port = 3306;
        }else if (DbType.oracle.equals(this.dbType)){
            if (null == this.port) this.port = 1521;
        }
        if (StringUtils.isBlank(this.driverClass) && null != this.dbType)
            this.driverClass =  this.dbType.getDriverClassString();
        if (null == this.dbType && StringUtils.isNotBlank(this.driverClass)) {
            if (this.driverClass.contains("mysql"))
                this.dbType = DbType.mysql;
            else if (this.driverClass.contains("oracle"))
                this.dbType = DbType.oracle;
        }
    }

    private String processUrl() {
        if (DbType.mysql.equals(this.dbType)) {
            return String.format(this.dbType.getUrl(), this.host, this.port, this.databaseName, (null != this.encodeType ? this.encodeType :EncodeType.UTF8).getValue());
        }else if (DbType.oracle.equals(this.dbType)){
            return  String.format(this.dbType.getUrl(), this.host, this.port, this.databaseName);
        }
        return null;
    }

    public DataSourceConfigBuilder setDbType(DbType dbType) {
        this.dbType = dbType;
        return this;
    }

    public DataSourceConfigBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public DataSourceConfigBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    /**可不设置会根据DbType自动匹配*/
    public DataSourceConfigBuilder setDriverClass(String driverClass) {
        this.driverClass = driverClass;
        return this;
    }

    public DataSourceConfigBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public DataSourceConfigBuilder setPort(Integer port) {
        this.port = port;
        return this;
    }

    public DataSourceConfigBuilder setEncodeType(EncodeType encodeType) {
        this.encodeType = encodeType;
        return this;
    }

    public DataSourceConfigBuilder setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }
}
