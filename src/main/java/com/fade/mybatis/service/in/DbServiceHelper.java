/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.service.in;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import com.fade.mybatis.config.DataSourceConfig;
import com.fade.mybatis.enums.DbType;
import com.fade.mybatis.model.TableField;
import com.fade.mybatis.model.TableInfo;
import com.fade.mybatis.utils.CloseableUtils;
import com.fade.mybatis.utils.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Description: 数据库工具<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 12:17
 * @since JDK 1.8
 */
public  class DbServiceHelper {

    private static final int LOGIN_TIME_OUT = 2;

    private DataSourceConfig config;

    private Connection connection;

    public DbServiceHelper(DataSourceConfig config){
        this.config = config;
    }


    /**
     * 获取数据库表结构信息
     * @return 表信息
     */
    public  List<TableInfo> getTables() {
        checkConection();
        List<TableInfo> tables = new ArrayList<TableInfo>();
        ResultSet resultSet = null;
        try{
            DatabaseMetaData metaData = connection.getMetaData();
            resultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            TableInfo table;
            while (resultSet.next()) {
                table = new TableInfo(resultSet.getString("TABLE_NAME"), resultSet.getString("REMARKS"));
                tables.add(table);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            CloseableUtils.closeQuietly(resultSet);
        }
        return tables;
    }

    /**
     * 获取表字段结构信息
     * @param tableName
     * @return List<TableField>
     */
    public  List<TableField> getTableFields( String tableName) {
        if (StringUtils.isBlank(tableName)) throw new RuntimeException("TableName is Null or Empty.");
        checkConection();
        ResultSet resultSet = null;
        List<TableField> fields = new ArrayList<TableField>();
        try{
            DatabaseMetaData metaData = connection.getMetaData();
            resultSet = metaData.getColumns(null, null, tableName, "%");
            TableField field;
            while (resultSet.next()) {
                field = new TableField();
                field.setName(resultSet.getString("COLUMN_NAME"));
                field.setComment(resultSet.getString("REMARKS"));
                field.setJdbcType(resultSet.getString("TYPE_NAME"));
                /** 0:'YES'; 1:'NO'; 2:''*/
                field.setNullAble(resultSet.getInt("NULLABLE")!= 1);
                /** 默认值*/
                field.setDefaultValue(resultSet.getString("COLUMN_DEF"));
                field.setPropertyType(processPropertyType(resultSet.getString("TYPE_NAME").toLowerCase()));
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
        try {
            Class.forName(config.getDriverClass());
            getConnection0();
        }catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void getConnection0() {
        try {
            DriverManager.setLoginTimeout(LOGIN_TIME_OUT);
            Properties properties = new Properties();
            properties.put("user", config.getUserName());
            properties.put("password", config.getPassword());
            properties.put("remarks", "true");
            this.connection = DriverManager.getConnection(config.getUrl(), properties);
        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**检查Connection 若连接失效重置连接*/
    private void checkConection() {
        try {
            if (null == connection || connection.isClosed()) {
                getConnection0();
            }
        }catch (SQLException e) {
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
        else if (targetType.equals("timestamp"))
            return "Timestamp";
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
