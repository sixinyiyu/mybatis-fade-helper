/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.model;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import java.io.Serializable;
import java.sql.JDBCType;

/**
 * Description: {一句话描述类是干什么的}<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 12:11:32
 * @since JDK 1.8
 */
public class TableField implements Serializable {

    /**列名*/
    private String name;

    /**注释*/
    private String comment;

    /**类型*/
    private String jdbcType;

    /**默认值*/
    private Object defaultValue;

    /**是否可Null*/
    private Boolean nullAble = Boolean.TRUE;

    /**对应属性名*/
    private String propertyName;

    /**属性类型*/
    private String propertyType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean getNullAble() {
        return nullAble;
    }

    public void setNullAble(Boolean nullAble) {
        this.nullAble = nullAble;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TableField{");
        sb.append("name='").append(name).append('\'');
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", jdbcType='").append(jdbcType).append('\'');
        sb.append(", defaultValue=").append(defaultValue);
        sb.append(", nullAble=").append(nullAble);
        sb.append(", propertyName='").append(propertyName).append('\'');
        sb.append(", propertyType='").append(propertyType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
