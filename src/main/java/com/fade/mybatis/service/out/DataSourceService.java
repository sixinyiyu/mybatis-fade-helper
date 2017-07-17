/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.service.out;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import com.fade.mybatis.config.DataSourceConfig;
import com.fade.mybatis.config.builder.DataSourceConfigBuilder;
import com.fade.mybatis.enums.DbType;
import com.fade.mybatis.enums.NamingStrategyType;
import com.fade.mybatis.model.TableField;
import com.fade.mybatis.model.TableInfo;
import com.fade.mybatis.service.in.DbServiceHelper;
import com.fade.mybatis.utils.StringUtils;

import java.util.List;

/**
 * Description: 暴露对外的服务<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 12:01:14
 * @since JDK 1.8
 */
public class DataSourceService {

    private DbServiceHelper dbServiceHelper;

    public static void main(String[] args) {
        DataSourceService service = new DataSourceService();
        DataSourceConfig dataSourceConfig = DataSourceConfigBuilder.newInstance().setDbType(DbType.mysql).setHost("127.0.0.1").setPort(3306).setUserName("root").setPassword("fade90,").setDatabaseName("apps").builder();
        service.dbServiceHelper = new DbServiceHelper(dataSourceConfig);
//  List<TableInfo> tables = service.getTables();
//        if (CollectionUtil.isNotEmpty(tables))
            service.getTableCloumns( "test");
    }
     
    
    /**获取某类模板*/
    public void getTemplate() {
    	
    }
    
    /*更新模板，没有就新增，有就覆盖*/
    public void updateTemplate() {
    	
    }
    

    /**
     * 获取数据库下所有表
     * @return List<String> 表名
     */
    public List<TableInfo> getTables() {
        return dbServiceHelper.getTables();
    }

    /**
     * 获取指定表结构
     * @param tableName
     * @return List<TableField>
     */
    public List<TableField> getTableCloumns(String tableName) {
        if (StringUtils.isBlank(tableName)) throw new IllegalArgumentException("TableField Name can not be Null or Empty.");
        List<TableField> tableFields = dbServiceHelper.getTableFields(tableName);
        tableFields.forEach(System.out::println);
        return null;
    }
    
    
    
    /**
     * 1.首先要知道数据库相关的配置(连接、用户名、密码、那个庫、生成那些表)
     * 2.得到文件生成规则
     * 3.设置文件保存路径
     * 4.生成文件信息
     * 
     * 附功能
     * 设置生成文件模板规则
     */
    
    public void generateCode() {
    		//利用vm模板生成
    		
    		//加载模板
    	
    		//根据选择的表
    	
    		//根据表单个生成xml/mapper/entity/BizService/bo
    	
    	
    	
    }
    
    
    
    
}
