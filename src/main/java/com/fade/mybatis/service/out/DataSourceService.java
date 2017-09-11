/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.service.out;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fade.mybatis.config.GeneratorConfig;
import com.fade.mybatis.config.TemplateConfig;
import com.fade.mybatis.enums.NamingStrategyType;
import com.fade.mybatis.enums.TemplateType;
import com.fade.mybatis.model.TableField;
import com.fade.mybatis.model.TableInfo;
import com.fade.mybatis.service.in.DbServiceHelper;
import com.fade.mybatis.service.in.VelocityTemplate;
import com.fade.mybatis.utils.CollectionUtil;
import com.fade.mybatis.utils.StringUtils;
import com.google.common.collect.Maps;

/**
 * Description: 暴露对外的服务<br/>
 * <pre>dbServiceHelper 需要手动的初始化后赋值</pre>
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 12:01:14
 * @since JDK 1.8
 */
@Service(value = "dataSourceService")
public class DataSourceService {
	
	private static final Logger logger = LoggerFactory.getLogger(DataSourceService.class);

    private DbServiceHelper dbServiceHelper;
    
    public DbServiceHelper getDbServiceHelper() {
		return dbServiceHelper;
	}

	public void setDbServiceHelper(DbServiceHelper dbServiceHelper) {
		this.dbServiceHelper = dbServiceHelper;
	}

	@Autowired
    private VelocityTemplate  velocityTemplate;

/*    public static void main(String[] args) {
        DataSourceService service = new DataSourceService();
        DataSourceConfig dataSourceConfig = DataSourceConfigBuilder.newInstance().setDbType(DbType.mysql).setHost("127.0.0.1").setPort(3306).setUserName("root").setPassword("fade90,").setDatabaseName("apps").builder();
        service.dbServiceHelper = new DbServiceHelper(dataSourceConfig);
        service.velocityTemplate = new VelocityTemplate();
        List<TableInfo> tables = service.getTables("");
        if (CollectionUtil.isNotEmpty(tables)) {
        	tables.forEach(one -> System.out.println(one.getName()));
        	TableInfo table = tables.get(0);//tables.stream().filter(one -> one.getName().equals("tf_f_user")).findAny().orElse(tables.get(tables.size() - 1));
        	List<TableField> fields = service.getTableCloumns(table.getName(), NamingStrategyType.underline_to_camel, null);
        	table.setFields(fields);
        	GeneratorConfig generatorConfig = new GeneratorConfig();
        	generatorConfig.setAuthor("卿全忠").setProjectFolder("F://data").setDomainPackage("src/com/fade/entity").setMapperPackage("src/com/fade/dao").setLogicServiceItfPackage("src/com/fade/logicService/itf").setLogicServicePackage("src/com/fade/logicService");
        	
        	service.generateCode(generatorConfig, Lists.newArrayList(table));
        }
    }
*/     
    /*更新模板，没有就新增，有就覆盖*/
    public void updateTemplate() {
    	
    }

    /**
     * 获取数据库下所有表
     * @return List<String> 表名
     */
    public List<TableInfo> getTables(String tableName) {
        return dbServiceHelper.getTables(tableName);
    }

    /**
     * 获取指定表结构
     * @param tableName
     * @return List<TableField>
     */
    public List<TableField> getTableCloumns(String tableName , NamingStrategyType namingStrategy, String prefix) {
        if (StringUtils.isBlank(tableName)) throw new IllegalArgumentException("TableField Name can not be Null or Empty.");
        List<TableField> tableFields = dbServiceHelper.getTableFields(tableName, namingStrategy , prefix);
        return tableFields;
    }
    
    
    
    /**
     * 生成代码
     * <pre>
     * 1.参数初略验证
     * 2.初始上下文信息
     * 3.执行生成
     * </pre>
     */
    public void generateCode(GeneratorConfig generatorConfig , List<TableInfo> tables ) {
	    // 参数校验
    	if (CollectionUtil.isEmpty(tables)) throw new IllegalArgumentException("请选择要操作的表");
    	Objects.requireNonNull(generatorConfig, "通用配置信息丢失");
    	
    	//模板配置
    	final TemplateConfig templateConfig = Objects.isNull(generatorConfig.getTemplateConfig()) ? TemplateConfig.getDefault() : generatorConfig.getTemplateConfig();
    	Map<String, VelocityContext> contextMap = initContext(generatorConfig, tables);
    	
    	contextMap.forEach((name, context) -> {
    		/**依次生成对应的Entity、xml、Mapper、LogicService*/
    		velocityTemplate.mergeVm2File(context, templateConfig.getEntity().getPath(), (String) context.get("entityOutput") );
    		
    		velocityTemplate.mergeVm2File(context, templateConfig.getMapper().getPath(), (String) context.get("xmlOutput") );
    		
    		velocityTemplate.mergeVm2File(context, templateConfig.getDao().getPath(), (String) context.get("mapperOutput") );
    		
    		velocityTemplate.mergeVm2File(context, templateConfig.getServiceItf().getPath(), (String) context.get("serviceItfOutput"));
    		
    		velocityTemplate.mergeVm2File(context, templateConfig.getService().getPath(), (String) context.get("serviceOutput") );
    	});
    }
    
    
    private Map<String, VelocityContext>  initContext(GeneratorConfig generatorConfig , List<TableInfo> tables) {
    	logger.info("解析并填充上下文数据.....");
    	Map<String, VelocityContext> contextMap = Maps.newHashMap();
    	
    	/**作者*/
    	final String author =  generatorConfig.getAuthor();
    	/**时间*/
    	final String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    	/**是否使用建造者 默认使用*/
    	final Boolean builderModel = generatorConfig.getUseBuilderModel();
    	final String baseDir = generatorConfig.getProjectFolder();
    	/**路径信息*/
    	Map<String, String> packageInfo = Maps.newHashMap();
    	packageInfo.put("Entity", generatorConfig.getDomainPackage().replace("/", ".").replace("\\", "."));
    	packageInfo.put("Mapper", generatorConfig.getMapperPackage().replace("/", ".").replace("\\", "."));
    	packageInfo.put("XmlMapper", generatorConfig.getXmlMapperPackage().replace("/", ".").replace("\\", "."));
    	packageInfo.put("LogicServiceItf", generatorConfig.getLogicServiceItfPackage().replace("/", ".").replace("\\", "."));
    	packageInfo.put("LogicService", generatorConfig.getLogicServicePackage().replace("/", ".").replace("\\", "."));
    	
    	tables.forEach(table -> {
    		/**获得列*/
    		table.setFields(dbServiceHelper.getTableFields(table.getName(), generatorConfig.getNameStrategy(), generatorConfig.getPrefix()));
    		table.setEntityName(NamingStrategyType.toCapital(NamingStrategyType.converNaming(table.getCapitalName(), generatorConfig.getNameStrategy(), generatorConfig.getPrefix())));
    		VelocityContext ctx = new VelocityContext();
    		String entityName = table.getEntityName();
    		ctx.put("author",author);
        	ctx.put("date", date);
        	ctx.put("simpleBuilderModel", builderModel);
        	ctx.put("package", packageInfo);
        	ctx.put("entityName", table.getEntityName() + "Entity");
        	
        	/**输出文件路径信息*/
    		ctx.put("entityOutput", String.format(TemplateType.entity_java.getPathTmeplate(), baseDir , generatorConfig.getDomainPackage().replace(".", "/"), entityName));
    		ctx.put("xmlOutput", String.format(TemplateType.mapper_xml.getPathTmeplate(), baseDir, generatorConfig.getXmlMapperPackage().replace(".", "/"), entityName));
    		ctx.put("mapperOutput", String.format(TemplateType.mapper_java.getPathTmeplate(), baseDir, generatorConfig.getMapperPackage().replace(".", "/"), entityName));
    		ctx.put("serviceItfOutput", String.format(TemplateType.service_itf_java.getPathTmeplate(), baseDir, generatorConfig.getLogicServiceItfPackage().replace(".", "/"), entityName));
    		ctx.put("serviceOutput", String.format(TemplateType.service_java.getPathTmeplate(), baseDir, generatorConfig.getLogicServicePackage().replace(".", "/"), entityName));
    		
    		/**表结构信息*/
        	ctx.put("table", table);
        	contextMap.put(table.getName(), ctx);
    	});
    	
    	return contextMap;
    }
}
