/**
 * <p>Company: fade </p>
 * <p>Copyright: Copyright (c) 2016</p>
 */
package com.fade.mybatis.service.in;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fade.mybatis.config.DataSourceConfig;
import com.fade.mybatis.enums.DbType;
import com.fade.mybatis.enums.NamingStrategyType;
import com.fade.mybatis.model.TableField;
import com.fade.mybatis.model.TableInfo;
import com.fade.mybatis.utils.CloseableUtils;
import com.fade.mybatis.utils.StringUtils;

/**
 * Description: 数据库工具<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 12:17
 * @since JDK 1.8
 */
public  class DbServiceHelper {
	
	private static final Logger log = LoggerFactory.getLogger(DbServiceHelper.class);

    private static final int LOGIN_TIME_OUT = 2;

    private DataSourceConfig config;

    private Connection connection;

    public DbServiceHelper(DataSourceConfig config){
        this.config = config;
    }
    
    /**获取当前连接数据库信息*/
    public Map<String, String> getDbInfo() {
    	Map<String, String> map = new HashMap<>();
    	map.put("url", config.getUrl());
    	map.put("userName", config.getUserName());
    	map.put("password", config.getPassword());
    	map.put("dbType", config.getDbType().getDesc());
    	map.put("databaseName", config.getDbName());
    	return map;
    }
    
    /**
     * 获取数据库表结构信息
     * @return 表信息
     */
    public  List<TableInfo> getTables(String tableName) {
        checkConection();
        List<TableInfo> tables = new ArrayList<TableInfo>();
        ResultSet resultSet = null;
        try{
    		log.info("try to get talbes from {} (databases).", connection.getMetaData().getDatabaseProductName());
            DatabaseMetaData metaData = connection.getMetaData();
//            resultSet = metaData.getTables(null, DbType.oracle.equals(config.getDbType()) ? config.getUserName() : config.getDbName(), Strings.isNullOrEmpty(tableName) ? "%" : tableName, new String[]{"TABLE"});
            resultSet = metaData.getTables(config.getDbName() ,null , "%", new String[]{"TABLE"});
            TableInfo table;
            while (resultSet.next()) {
                table = new TableInfo(resultSet.getString("TABLE_NAME"), resultSet.getString("REMARKS"));
                tables.add(table);
            }
        }catch (Exception e) {
        		log.error("throw exception when try to get tables from database");
            throw new RuntimeException(e);
        }finally {
            CloseableUtils.closeQuietly(resultSet);
        }
        tables.forEach(System.out::println);
        return tables;
    }

    /**
     * 获取表字段结构信息
     * @param tableName
     * @return List<TableField>
     */
    public  List<TableField> getTableFields( String tableName, NamingStrategyType namingStrategy, String prefix) {
        if (StringUtils.isBlank(tableName)) throw new RuntimeException("TableName is Null or Empty.");
        checkConection();
        ResultSet resultSet = null, keyRs = null;
        String primaryKeyColumnName = null;
        List<TableField> fields = new ArrayList<TableField>();
        try{
            DatabaseMetaData metaData = connection.getMetaData();
            log.info("try to get {} (table's) cloumn", tableName);
            resultSet = metaData.getColumns(config.getDbName(), null , tableName, "%");
            keyRs = metaData.getPrimaryKeys(config.getDbName(), null, tableName);
            while(keyRs.next()){  
                primaryKeyColumnName = keyRs.getString("COLUMN_NAME");  
            } 
            TableField field;
            if (null == namingStrategy) namingStrategy = NamingStrategyType.underline_to_camel; 
            while (resultSet.next()) {
                field = new TableField();
                /**字段名*/
                field.setName(resultSet.getString("COLUMN_NAME"));
                /**属性名 驼峰法处理*/
                field.setPropertyName(NamingStrategyType.converNaming(field.getName(), namingStrategy, prefix));
                /**主键处理*/
                if(field.getName().equals(primaryKeyColumnName)) field.setPrimary(Boolean.TRUE);
                /**描述*/
                field.setComment(resultSet.getString("REMARKS"));
                /**类型*/
                field.setJdbcType(resultSet.getString("TYPE_NAME"));
                /**是否允许空*/
                /** 0:'YES'; 1:'NO'; 2:''*/
                field.setNullAble(resultSet.getInt("NULLABLE")!= 1);
                /** 默认值*/
                field.setDefaultValue(resultSet.getString("COLUMN_DEF"));
                field.setTypeName(resultSet.getString("TYPE_NAME").toLowerCase());
                field.setPropertyType(processPropertyType(field.getTypeName()));
                fields.add(field);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            CloseableUtils.closeQuietly(resultSet);
        }
        return  fields;
    }

    //******************************************************************************************************************

    @PreDestroy
    private void destoryConnection() {
        CloseableUtils.closeQuietly(this.connection);
    }

    /**
     * 获取数据库连接
     * @author qingquanzhong
     */
    @PostConstruct
    private void getConnection() {
        if (null == config) throw new IllegalArgumentException("DataSourceConfig is Null");
        getConnection0();
    }

    private void getConnection0() {
        try {
            DriverManager.setLoginTimeout(LOGIN_TIME_OUT);
            Class.forName(config.getDriverClass());
            Properties properties = new Properties();
            properties.put("user", config.getUserName());
            properties.put("password", config.getPassword());
            properties.setProperty("remarks", "true"); //设置可以获取remarks信息 
            if (DbType.mysql.equals(config.getDbType())) {
            	properties.put("useInformationSchema", "true");//表注释 myslq
            }else if (DbType.oracle.equals(config.getDbType())) {
            	properties.setProperty("remarksReporting","true");  // oracle
            }
            log.info("try get connection :{}", config.getUrl());
            this.connection = DriverManager.getConnection(config.getUrl(), properties);
        }catch (SQLException | ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        } 
    }

    /**检查Connection 若连接失效重置连接*/
    public void checkConection() {
        try {
            if (null == connection || connection.isClosed()) {
                getConnection0();
            }
        }catch (SQLException e) {
        	    log.error("Connection is not effective");
            throw new RuntimeException("Connection is not effective.");
        }
    }

    private String processPropertyType(String targetType) {
        DbType type = this.config.getDbType();
        if (DbType.mysql.equals(type))
            return processMysqlPropertyType(targetType);
        else if (DbType.oracle.equals(type))
            return processOraclePropertyType(targetType);
        return null;
    }

    /**转换属性类型*/
    private static String processMysqlPropertyType(String targetType) {
        if (targetType.contains("char") || targetType.contains("varchar") || targetType.contains("text") || targetType.contains("json"))
            return "String";
        else if (targetType.equals("bigint"))
            return "Long";
        else if (targetType.contains("int"))
            return "Integer";
        /**时间戳是否考虑用Date*/
        else if (targetType.equals("timestamp"))
            return "Date";//"Timestamp";
        else if (targetType.equals("date") || targetType.equals("year"))
            return "Date";
        else if (targetType.equals("float") || targetType.equals("real"))
            return "Float";
        else if (targetType.equals("double"))
            return "Double";
        else if (targetType.equals("bit"))
            return "Boolean";
        else if (targetType.equals("blob") || targetType.contains("binary"))
            return "byte[]";
        else if (targetType.equals("decimal"))
            return "BigDecimal";
        return "String";
    }

    private String processOraclePropertyType(String targetType) {
        if (targetType.contains("char")) {
            return "String";
        } else if (targetType.contains("date") || targetType.contains("timestamp")) {
            return "Date";
        } else if (targetType.contains("number")) {
            if (targetType.matches("number\\(+\\d{1}+\\)")) {
                return "Integer";
            } else if (targetType.matches("number\\(+\\d{2}+\\)")) {
                return "Long";
            }
            return "Double";
        } else if (targetType.contains("float")) {
            return "Float";
        } else if (targetType.contains("blob")) {
            return "Object";
        } else if (targetType.contains("raw")) {
            return "byte[]";
        }
        return "String";
    }
}
