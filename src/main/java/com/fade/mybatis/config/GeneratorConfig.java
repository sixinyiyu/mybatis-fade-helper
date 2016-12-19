/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.config;/**
 * Created by Administrator on 2016/12/18.
 */

/**
 * Description: {一句话描述类是干什么的}<br/>
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

}
