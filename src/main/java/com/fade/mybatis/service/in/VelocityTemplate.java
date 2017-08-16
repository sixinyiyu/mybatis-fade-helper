package com.fade.mybatis.service.in;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VelocityTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(VelocityTemplate.class);

	private VelocityEngine vEngine = null;

	private VelocityEngine getVelocityEngine() {
		if (null != vEngine)
			return vEngine;
		logger.info("初始化Velocity引擎.........");
		// 设置Velocity引擎
		vEngine = new VelocityEngine();
		// 引擎配置
		vEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		//解决中文乱码问题
		vEngine.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
		vEngine.setProperty(RuntimeConstants.OUTPUT_ENCODING, "UTF-8");
		vEngine.setProperty(RuntimeConstants.ENCODING_DEFAULT, "UTF-8");
		vEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		// 初始化引擎
		vEngine.init();
		return vEngine;
	}

	/**
	 * 根据模板生成文件到指定目录
	 * @param vContext       Velocity上下文
	 * @param vmTemplatePath 模版路径
	 * @param outputPath     目标文件目录
	 */
	public void mergeVm2File(VelocityContext vContext, String vmTemplatePath, String outputPath) {
		VelocityEngine engine = getVelocityEngine();
		// 设置模板
		Template t = engine.getTemplate(vmTemplatePath);
		try {
			/**创建父目录*/
			File outputFile = new File(outputPath);
    		if (!outputFile.getParentFile().exists()) outputFile.getParentFile().mkdirs();
			FileOutputStream fos = new FileOutputStream(outputPath);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
			t.merge(vContext, writer);
			writer.close();
			logger.info("成功生成 {}文件", outputFile.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
