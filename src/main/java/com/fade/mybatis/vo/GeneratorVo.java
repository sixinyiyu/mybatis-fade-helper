/**
 * 
 */
package com.fade.mybatis.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author qingquanzhong	
 *
 */
public class GeneratorVo implements Serializable{

	private static final long serialVersionUID = 1L;

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
    private String nameStrategy;
    
    /**指定前缀/后缀*/
    private String prefix;
    
    private List<String> tableNames;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLogicServiceItfPackage() {
		return logicServiceItfPackage;
	}

	public void setLogicServiceItfPackage(String logicServiceItfPackage) {
		this.logicServiceItfPackage = logicServiceItfPackage;
	}

	public String getLogicServicePackage() {
		return logicServicePackage;
	}

	public void setLogicServicePackage(String logicServicePackage) {
		this.logicServicePackage = logicServicePackage;
	}

	public String getProjectFolder() {
		return projectFolder;
	}

	public void setProjectFolder(String projectFolder) {
		this.projectFolder = projectFolder;
	}

	public String getDomainPackage() {
		return domainPackage;
	}

	public void setDomainPackage(String domainPackage) {
		this.domainPackage = domainPackage;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
	}

	public Boolean getGenerateComment() {
		return generateComment;
	}

	public void setGenerateComment(Boolean generateComment) {
		this.generateComment = generateComment;
	}

	public Boolean getUseBuilderModel() {
		return useBuilderModel;
	}

	public void setUseBuilderModel(Boolean useBuilderModel) {
		this.useBuilderModel = useBuilderModel;
	}

	public String getNameStrategy() {
		return nameStrategy;
	}

	public void setNameStrategy(String nameStrategy) {
		this.nameStrategy = nameStrategy;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<String> getTableNames() {
		return tableNames;
	}

	public void setTableNames(List<String> tableNames) {
		this.tableNames = tableNames;
	}
    
}
