/**
 * 
 */
package com.fade.mybatis.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fade.mybatis.config.DataSourceConfig;
import com.fade.mybatis.config.GeneratorConfig;
import com.fade.mybatis.config.builder.DataSourceConfigBuilder;
import com.fade.mybatis.enums.DbType;
import com.fade.mybatis.enums.EncodeType;
import com.fade.mybatis.enums.NamingStrategyType;
import com.fade.mybatis.model.TableInfo;
import com.fade.mybatis.service.in.DbServiceHelper;
import com.fade.mybatis.service.in.LocalDBHelper;
import com.fade.mybatis.service.out.DataSourceService;
import com.fade.mybatis.utils.CollectionUtil;
import com.fade.mybatis.vo.DBConfigVo;
import com.fade.mybatis.vo.GeneratorVo;
import com.fade.mybatis.vo.ResultVo;
import com.google.common.base.Strings;

/**
* Description: 统一对外入口<br/>
*
* @author qingquanzhong
* @version 1.0
* @date: 2016-12-20 12:01:14
* @since JDK 1.8
*/
@RestController
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private DataSourceService dataSourceService;

	/**
	 * 获取保存的数据库配置信息
	 */
	@GetMapping("/dbConfig")
	 public Object getDbConfigs() {
		ResultVo<Object> result = new ResultVo<>();
		List<DBConfigVo> vos = LocalDBHelper.list();
		return result.setSuccessValue(vos);
	 }
	 
	 /**
	  * 保存或更新数据库配置
	  */
	 @PostMapping("/dbConfig")
	 public Object saveDbConfig(@RequestBody DBConfigVo config) {
		 ResultVo<Object> result = new ResultVo<>();
		 if (Strings.isNullOrEmpty(config.getId())) {
			 LocalDBHelper.save(config);
		 } else {
			 LocalDBHelper.update(config);
		 }
		 return result.setSuccessValue("保存成功");
	 }
	 
	 @DeleteMapping("/dbConfig/{id}")
	 public Object removeDbConfig(@PathVariable String id) {
		 ResultVo<Object> result = new ResultVo<>();
		 LocalDBHelper.remove(id);
		 return result.setSuccessValue("删除成功");
	 }
	 
	 
	 @GetMapping("/getDbInfo")
	 public Object getDbName() {
		 logger.info(".................获取当前连接数据库信息");
		 ResultVo<Object> result = new ResultVo<>();
		 try{
			 Objects.requireNonNull(dataSourceService.getDbServiceHelper(), "未初始化数据库连接");
			 DbServiceHelper dbHelper =  dataSourceService.getDbServiceHelper();
			 result.setSuccessValue(dbHelper.getDbInfo());
		 } catch (Exception e) {
			logger.error("服务异常" + e.getMessage(), e);
			result.setFaildMessage(e.getMessage());
		}
		 return result;
	 }
	 
	 /***
	  * 测试连接是否有效
	  */
	 @PostMapping("/checkConnection")
	 public Object testDbConnection(@RequestBody DBConfigVo configVo) {
		 ResultVo<Object> result = new ResultVo<>();
		 try {
			 Objects.requireNonNull(configVo, "缺少必要参数");
			 if (Strings.isNullOrEmpty(configVo.getConnectionName())) throw new IllegalArgumentException("请指定连接名");
			 if (Strings.isNullOrEmpty(configVo.getHost())) throw new IllegalArgumentException("请指定连接名或IP");
			 if (Objects.isNull(configVo.getPort()) || configVo.getPort().intValue() <=0 ) throw new IllegalArgumentException("数据库端口不合法");
			 if (Strings.isNullOrEmpty(configVo.getUserName())) throw new IllegalArgumentException("请指定用户名");
			 if (Strings.isNullOrEmpty(configVo.getDatabaseName())) throw new IllegalArgumentException("请指定数据库");
			 DbType dbType =  DbType.get(configVo.getDbType());
			 if (null == dbType) throw new IllegalArgumentException("请指定数据库类型");
			 EncodeType encode = EncodeType.get(configVo.getEncode());
			 DataSourceConfig config = DataSourceConfigBuilder.newInstance().setDbType(dbType)
	                 .setDatabaseName(configVo.getDatabaseName())
	                 .setEncodeType(encode != null ? encode : EncodeType.UTF8)
	                 .setHost(configVo.getHost())
	                 .setPort(configVo.getPort())
	                 .setUserName(configVo.getUserName())
	                 .setPassword(configVo.getPassword()).builder();
			 DbServiceHelper dbServiceHelper= new DbServiceHelper(config);
			 dbServiceHelper.checkConection();
			 result.setSuccessValue("测试连接成功");
		} catch (Exception e) {
			result.setFaildMessage("连接失败" + e.getMessage());
		}
		return result;
	 }
	 
	 /**
	  * 切换数据库配置
	  * 这里需要重新设置DataSourceConfig对象
	  */
	 @PostMapping("switchDb")
	 public Object switchDb(@RequestBody DBConfigVo configVo) {
		 ResultVo<Object> result = new ResultVo<>();
		 try {
			 logger.info("...............切换数据库.........");
			 Objects.requireNonNull(configVo, "缺少必要参数");
			 if (Strings.isNullOrEmpty(configVo.getConnectionName())) throw new IllegalArgumentException("请指定连接名");
			 if (Strings.isNullOrEmpty(configVo.getHost())) throw new IllegalArgumentException("请指定连接名或IP");
			 if (Objects.isNull(configVo.getPort()) || configVo.getPort().intValue() <=0 ) throw new IllegalArgumentException("数据库端口不合法");
			 if (Strings.isNullOrEmpty(configVo.getUserName())) throw new IllegalArgumentException("请指定用户名");
			 if (Strings.isNullOrEmpty(configVo.getDatabaseName())) throw new IllegalArgumentException("请指定数据库");
			 DbType dbType =  DbType.get(configVo.getDbType());
			 if (null == dbType) throw new IllegalArgumentException("请指定数据库类型");
			 EncodeType encode = EncodeType.get(configVo.getEncode());
			 DataSourceConfig config = DataSourceConfigBuilder.newInstance().setDbType(dbType)
			                                      .setDatabaseName(configVo.getDatabaseName())
			                                      .setEncodeType(encode != null ? encode : EncodeType.UTF8)
			                                      .setHost(configVo.getHost())
			                                      .setPort(configVo.getPort())
			                                      .setUserName(configVo.getUserName())
			                                      .setPassword(configVo.getPassword()).builder();
			 DbServiceHelper dbServiceHelper= new DbServiceHelper(config);
			 dataSourceService.setDbServiceHelper(dbServiceHelper);
			 result.setSuccessValue("切换数据库成功");
		} catch (Exception e) {
			logger.error("服务异常" + e.getMessage(), e);
			result.setFaildMessage(e.getMessage());
		}
		return result;
	 }
	 
	/**
	 * 获取指定数据库下的所有表 
	 */
	@GetMapping(value = "list/tables")
	public Object getDatabaseTables() {
		ResultVo<Object>  result = new ResultVo<>();
		try {
			Objects.requireNonNull(dataSourceService.getDbServiceHelper(), "未初始化数据库连接");
			List<TableInfo> tables = dataSourceService.getTables(null);
			result.setSuccessValue(tables);
		} catch (Exception e) {
			logger.error("服务异常" + e.getMessage(), e);
			result.setFaildMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取表的列信息
	 */
	@GetMapping("/{tableName}/column")
	public Object getTableColumn(@PathVariable String tableName) {
		ResultVo<Object>  result = new ResultVo<>();
		Objects.requireNonNull(dataSourceService.getDbServiceHelper(), "未初始化数据库连接");
		return result.setSuccessValue(dataSourceService.getTableCloumns(tableName, NamingStrategyType.underline_to_camel, null));
	}
	 
	/**
	 * 根据配置生成想要模板
	 */
	@PostMapping(value = "/generateCode")
	public Object exeAutoGenerate(@RequestBody GeneratorVo generatorVo) {
		ResultVo<String> result = new ResultVo<>();
		Objects.requireNonNull(generatorVo, "缺少必要参数");
		if (Strings.isNullOrEmpty(generatorVo.getProjectFolder())) throw new IllegalArgumentException("请指定项目路径");
		if (Strings.isNullOrEmpty(generatorVo.getDomainPackage())) throw new IllegalArgumentException("请指定实体类包名");
		if (Strings.isNullOrEmpty(generatorVo.getMapperPackage())) throw new IllegalArgumentException("请指定Dao包名");
		if (Strings.isNullOrEmpty(generatorVo.getLogicServiceItfPackage())) throw new IllegalArgumentException("请指定逻辑接口包名");
		if (Strings.isNullOrEmpty(generatorVo.getLogicServicePackage())) throw new IllegalArgumentException("请指定逻辑服务包名");
		GeneratorConfig generatorConfig = new GeneratorConfig();
		String author = Strings.isNullOrEmpty(generatorVo.getAuthor()) ? "qingquanzhong@126.com" : generatorVo.getAuthor();
		NamingStrategyType nameStrategy = NamingStrategyType.get(generatorVo.getNameStrategy());
		if (null == nameStrategy) nameStrategy = NamingStrategyType.underline_to_camel;
    	generatorConfig.setAuthor(author).setProjectFolder(generatorVo.getProjectFolder())
    				   .setDomainPackage(generatorVo.getDomainPackage())
    				   .setMapperPackage(generatorVo.getMapperPackage())
    				   .setLogicServiceItfPackage(generatorVo.getLogicServiceItfPackage())
    				   .setLogicServicePackage(generatorVo.getLogicServicePackage())
    				   .setNameStrategy(nameStrategy).setPrefix(NamingStrategyType.toCapital(generatorVo.getPrefix()))
    				   .setUseBuilderModel(null == generatorVo.getUseBuilderModel() ? true : generatorVo.getUseBuilderModel());
    	List<TableInfo> tables = dataSourceService.getTables(null);
    	List<String> tableNams = generatorVo.getTableNames();
    	if (CollectionUtil.isNotEmpty(tableNams)) {
    		tables = tables.stream().filter(one -> tableNams.contains(one.getName())).collect(Collectors.toList());
    	}
		dataSourceService.generateCode(generatorConfig, tables);
		return result.setSuccessValue("成功生成代码,保存目录:" + generatorVo.getProjectFolder());
	}
	
	
}
