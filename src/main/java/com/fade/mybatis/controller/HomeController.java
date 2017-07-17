/**
 * 
 */
package com.fade.mybatis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fade.mybatis.model.TableInfo;
import com.fade.mybatis.service.out.DataSourceService;
import com.fade.mybatis.vo.Result;

/**
* Description: 统一对外入口<br/>
*
* @author qingquanzhong
* @version 1.0
* @date: 2016-12-20 12:01:14
* @since JDK 1.8
*/
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private DataSourceService dataSourceService;

	/**
	 * 1.启动应用
	 * 2.可以选择以前保存的数据库配置信息/或者使用新建(新建后可保存)
	 * 3.测试数据库连接是否是通过(可选)
	 * 4.获取数据库的表(勾选需要操作的表默认是所有?)
	 * 5.填写包路径信息
	 * 6.确认自动生成,生成相关代码 流程结束
	 */
	
	/**
	 * 获取保存的数据库配置信息
	 */
	 public Object getDbConfigs() {
		 
		 return null;
	 }
	 
	 /**
	  * 保存或更新数据库配置
	  */
	 public Object saveDbConfig() {
		 return null;
	 }
	 
	 /**
	  * 切换数据库配置
	  * 这里需要重新设置DataSourceConfig对象
	  */
	 public Object switchDb() {
		 return null;
	 }
	 
	/**
	 * 获取制定数据库下的所有表 
	 */
	public Object getDatabaseTables() {
		Result<Object> result = new Result<>();
		List<TableInfo> tables = dataSourceService.getTables();
		return result.setSuccessValue(tables);
	}
	 
	/**
	 * 根据配置生成想要模板
	 */
	public Object exeAutoGenerate() {
		Result<String> result = new Result<>();
		dataSourceService.generateCode();
		return result.setSuccessValue(null);
	}
	
	
}
