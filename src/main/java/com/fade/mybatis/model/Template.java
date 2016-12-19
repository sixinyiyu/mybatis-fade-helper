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

    /**模版标识*/
    private Integer id;

    /**模版名称*/
    private String name;

    /**模版描述*/
    private String desc;

    /**模版内容*/
    private String content;

    /**类型*/
    private TemplateType type;

    public static void main(String[] args) {
        String target = "121fwepovwe121";
        System.out.printf(target.trim().replaceFirst("1214",""));
    }

    public TemplateType getType() {
        return type;
    }

    public void setType(TemplateType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        sb.append(", content='").append(content).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
