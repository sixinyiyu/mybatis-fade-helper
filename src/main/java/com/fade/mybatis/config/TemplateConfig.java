/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.config;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import com.fade.mybatis.model.Template;

/**
 * Description: 基于Velocity模版配置<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 18:01:23
 * @since JDK 1.8
 */
public class TemplateConfig {

    /**实体*/
    private Template entity;

    /**xml*/
    private Template mapper;

    /**dao接口*/
    private Template dao;

    /**基础service*/
    private Template service;

    public Template getEntity() {
        return entity;
    }

    public void setEntity(Template entity) {
        this.entity = entity;
    }

    public Template getMapper() {
        return mapper;
    }

    public void setMapper(Template mapper) {
        this.mapper = mapper;
    }

    public Template getDao() {
        return dao;
    }

    public void setDao(Template dao) {
        this.dao = dao;
    }

    public Template getService() {
        return service;
    }

    public void setService(Template service) {
        this.service = service;
    }
}
