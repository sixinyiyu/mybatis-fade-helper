/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.model;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import java.io.Serializable;

/**
 * Description: {一句话描述类是干什么的}<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 12:10:31
 * @since JDK 1.8
 */
public class TableInfo implements Serializable{

    private static  final  long serialVersionUID = 1L;

    private String name;

    private String comment;

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

    @Override
    public String toString() {
        return "TableInfo{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
