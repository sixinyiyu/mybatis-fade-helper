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
import com.fade.mybatis.model.TableField;
import com.fade.mybatis.model.TableInfo;
import com.fade.mybatis.service.in.DbServiceHelper;
import com.fade.mybatis.utils.StringUtils;

import java.util.List;

/**
 * Description: {一句话描述类是干什么的}<br/>
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
}
