/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.model;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import com.fade.mybatis.enums.TemplateType;

import java.io.Serializable;

/**
 * Description: 模版模型<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 18:02:31
 * @since JDK 1.8
 */
public class Template implements Serializable{

	private static final long serialVersionUID = 6879021516337954675L;

	/**模版标识*/
    private Integer id;

    /**模版名称*/
    private String name;

    /**模版描述*/
    private String desc;

    /**模版所在路径*/
    private String path;

    /**类型*/
    private TemplateType type;

    public TemplateType getType() {
        return type;
    }

    public Template setType(TemplateType type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public Template setName(String name) {
        this.name = name;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public Template setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Template setPath(String path) {
        this.path = path;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Template{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", path='").append(path).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
