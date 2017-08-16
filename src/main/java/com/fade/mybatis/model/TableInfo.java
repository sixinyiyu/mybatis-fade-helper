/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.model;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import java.io.Serializable;
import java.util.List;

import com.fade.mybatis.enums.NamingStrategyType;
import com.google.common.collect.Lists;


/**
 * Description: 表信息<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 12:10:31
 * @since JDK 1.8
 */
public class TableInfo implements Serializable{

    private static  final  long serialVersionUID = 1L;

    /**表名*/
    private String name;

    /**表注释*/
    private String comment;
    
    /**字段列表*/
    private List<TableField> fields = Lists.newArrayList();
    
    /**主键*/
    private TableField keyField;
    
    private String entityName;
    
    public TableInfo(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }
    
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

	public List<TableField> getFields() {
		return fields;
	}

	public void setFields(List<TableField> fields) {
		this.fields = fields;
	}
	
	public String getCapitalName() {
		return NamingStrategyType.toCapital(name);
	}
	
	public String getEntityName() {
		return entityName;
	}
	
	public void setEntityName(String entityName){
		this.entityName = entityName;
	}
	
	public String getLetterEntityName(){
		return NamingStrategyType.toLower(this.entityName);
	}
	
	public TableField getKeyField() {
		if (null != fields) keyField = fields.stream().filter(one -> one.getPrimary()).findFirst().orElse(new TableField());
		return keyField;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TableInfo [name=").append(name).append(", comment=").append(comment).append(", fields=")
				.append(fields).append(", keyField=").append(keyField).append("]");
		return builder.toString();
	}
    
}
