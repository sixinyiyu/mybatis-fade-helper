/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.config;/**
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
public class GeneratorConfig {

    /**表名*/
    private String tableName;

    /**实体类名*/
    private String domainName;

    /**工程目录*/
    private String projectFolder;

    /**实体目录*/
    private String domainPackage;

    /**mapper目录*/
    private String mapperPackage;

    /**生成注释*/
    private Boolean generateComment = Boolean.TRUE;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
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
    
}
