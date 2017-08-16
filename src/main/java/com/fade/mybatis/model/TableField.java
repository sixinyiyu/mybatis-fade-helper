/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.model;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import java.io.Serializable;

import com.fade.mybatis.enums.NamingStrategyType;

/**
 * Description: {一句话描述类是干什么的}<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 12:11:32
 * @since JDK 1.8
 */
public class TableField implements Serializable {

	private static final long serialVersionUID = 4572636218095513192L;

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
    
    /**列类型 */
    private String typeName;
    
    /**是否主键*/
    private Boolean primary = Boolean.FALSE;

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

    /**{@linkplain #propertyType}*/
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
    
    public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCapitalPropertyName() {
		return NamingStrategyType.toCapital(propertyName);
	}

	/**为set/get准备*/
    public String getCapitalSetPropertyName(){
    	return NamingStrategyType.plusPrefix(NamingStrategyType.toCapital(propertyName), "set");
    }
    
    public String getCapitalGetPropertyName(){
    	return NamingStrategyType.plusPrefix(NamingStrategyType.toCapital(propertyName), "get");
    }
    
    /**对Boolean is开头的属性处理*/
    public String getBooleanPropertyName() {
    	return NamingStrategyType.toLower(NamingStrategyType.removePrefix(propertyName, "is"));
    }
    
    public String getBooleanSetPropertyName() {
    	return NamingStrategyType.plusPrefix(NamingStrategyType.toCapital(NamingStrategyType.removePrefix(propertyName, "is")), "set");
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TableField [name=").append(name).append(", comment=").append(comment).append(", jdbcType=")
				.append(jdbcType).append(", defaultValue=").append(defaultValue).append(", nullAble=").append(nullAble)
				.append(", propertyName=").append(propertyName).append(", propertyType=").append(propertyType)
				.append(", primary=").append(primary).append("]");
		return builder.toString();
	}
    
}
