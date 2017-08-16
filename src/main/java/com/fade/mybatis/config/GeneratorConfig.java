/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.config;

import java.io.File;
import java.io.Serializable;

import com.fade.mybatis.enums.NamingStrategyType;
import com.fade.mybatis.utils.StringUtils;
import com.google.common.base.Strings;

/**
 * Created by Administrator on 2016/12/18.
 */

/**
 * Description:项目自动生成总体配置<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 06:10:23
 * @since JDK 1.8
 */
public class GeneratorConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**作者*/
	private String author;

	/** 接口目录 */
    private String logicServiceItfPackage;

    /** 服务目录*/
    private String logicServicePackage;

    /**工程目录*/
    private String projectFolder;

    /**实体目录*/
    private String domainPackage;

    /**mapper目录*/
    private String mapperPackage;

    /**生成注释*/
    private Boolean generateComment = Boolean.TRUE;
    
    /**建造者模式*/
    private Boolean useBuilderModel = Boolean.TRUE;
    
    /**命名策略 默认驼峰法*/
    private NamingStrategyType nameStrategy;
    
    /**指定前缀/后缀*/
    private String prefix;
    
    /**模板路径配置*/
    private TemplateConfig templateConfig;
    
	public NamingStrategyType getNameStrategy() {
		return nameStrategy == null ? NamingStrategyType.underline_to_camel : nameStrategy;
	}

	public GeneratorConfig setNameStrategy(NamingStrategyType nameStrategy) {
		this.nameStrategy = nameStrategy;
		return this;
	}

	public String getPrefix() {
		return prefix;
	}

	public GeneratorConfig setPrefix(String prefix) {
		this.prefix = prefix;
		return this;
	}

	public String getLogicServiceItfPackage() {
		return logicServiceItfPackage;
	}

	public GeneratorConfig setLogicServiceItfPackage(String logicServiceItfPackage) {
		this.logicServiceItfPackage = logicServiceItfPackage;
		return this;
	}

	public String getLogicServicePackage() {
		return logicServicePackage;
	}

	public GeneratorConfig setLogicServicePackage(String logicServicePackage) {
		this.logicServicePackage = logicServicePackage;
		return this;
	}

	public String getProjectFolder() {
		return projectFolder;
	}

	public GeneratorConfig setProjectFolder(String projectFolder) {
		this.projectFolder = projectFolder;
		return this;
	}

	public String getDomainPackage() {
		return domainPackage;
	}

	public GeneratorConfig setDomainPackage(String domainPackage) {
		this.domainPackage = domainPackage;
		return this;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}
	
	/**xml配置路径*/
	public String getXmlMapperPackage() {
		return StringUtils.concat(mapperPackage, "xml", File.separator);
	}
	
	public GeneratorConfig setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
		return this;
	}

	public Boolean getGenerateComment() {
		return generateComment;
	}

	public GeneratorConfig setGenerateComment(Boolean generateComment) {
		this.generateComment = generateComment;
		return this;
	}

	public Boolean getUseBuilderModel() {
		return useBuilderModel == null ? Boolean.TRUE : useBuilderModel;
	}

	public GeneratorConfig setUseBuilderModel(Boolean useBuilderModel) {
		this.useBuilderModel = useBuilderModel;
		return this;
	}

	public String getAuthor() {
		return Strings.isNullOrEmpty(author) ? "qingquanzhong@126.com" : author;
	}

	public GeneratorConfig setAuthor(String author) {
		this.author = author;
		return this;
	}

	public TemplateConfig getTemplateConfig() {
		return templateConfig;
	}

	public GeneratorConfig setTemplateConfig(TemplateConfig templateConfig) {
		this.templateConfig = templateConfig;
		return this;
	}
    
}
