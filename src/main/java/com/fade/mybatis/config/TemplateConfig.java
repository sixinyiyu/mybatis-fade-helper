/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.config;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import com.fade.mybatis.enums.TemplateType;
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
    
    private Template serviceItf;

    public Template getEntity() {
        return entity;
    }

    public TemplateConfig setEntity(Template entity) {
        this.entity = entity;
        return this;
    }

    public Template getMapper() {
        return mapper;
    }

    public TemplateConfig setMapper(Template mapper) {
        this.mapper = mapper;
        return this;
    }

    public Template getDao() {
        return dao;
    }

    public TemplateConfig setDao(Template dao) {
        this.dao = dao;
        return this;
    }

    public Template getService() {
        return service;
    }

    public TemplateConfig setService(Template service) {
        this.service = service;
        return this;
    }

	public Template getServiceItf() {
		return serviceItf;
	}

	public TemplateConfig setServiceItf(Template serviceItf) {
		this.serviceItf = serviceItf;
		return this;
	}
    
	public static TemplateConfig getDefault() {
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setEntity(new Template().setType(TemplateType.entity_java).setPath("entity.vm"))
		  .setMapper(new Template().setType(TemplateType.mapper_xml).setPath("mapper.xml.vm"))
		  .setDao(new Template().setType(TemplateType.mapper_java).setPath("mapper.java.vm"))
		  .setService(new Template().setType(TemplateType.service_java).setPath("logicService.java.vm"))
		  .setServiceItf(new Template().setType(TemplateType.service_java).setPath("logicServiceItf.java.vm"));
		return templateConfig;
	}
}
