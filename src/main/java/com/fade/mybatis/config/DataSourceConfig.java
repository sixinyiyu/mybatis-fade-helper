/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.config;/**
 * Created by Administrator on 2016/12/18.
 */

import com.fade.mybatis.enums.DbType;
import com.fade.mybatis.utils.StringUtils;

/**
 * Description: 数据库配置信息<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18
 * @since JDK 1.8
 */
public class DataSourceConfig {

    /**数据库类型*/
    private DbType dbType;

    /**数据库url*/
    private String url;

    /**数据库用户名*/
    private String userName;

    /**数据库密码*/
    private String password;

    /**驱动名*/
    private String driverClass;

    /**参数校验*/
    public void argsCheck() {
        if(StringUtils.isBlank(this.driverClass) && null == this.dbType)
            throw new IllegalArgumentException("DbType or DriverClass can not be Null.");
        if (StringUtils.isNotBlank(this.driverClass)) {
            if (this.driverClass.contains("mysql"))
                this.dbType = DbType.mysql;
            else if(this.driverClass.contains("oracle"))
                this.dbType = DbType.oracle;
            else
                throw new IllegalArgumentException("just support mysql/oracle driver");
        }else {
            this.driverClass = this.dbType.getDriverClassString();
        }
        if (StringUtils.isBlank(this.url)) throw new IllegalArgumentException("url can not bu Null");
        if (StringUtils.isBlank(this.userName)) throw new IllegalArgumentException("userName cant not be Null");
    }


    public DbType getDbType() {
        return dbType;
    }

    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

}
