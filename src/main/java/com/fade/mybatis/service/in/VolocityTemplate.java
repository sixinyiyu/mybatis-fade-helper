package com.fade.mybatis.service.in;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VolocityTemplate {

	public static void main(String[] args) {
		 // 设置Velocity引擎
		 VelocityEngine ve = new VelocityEngine();
		 // 引擎配置
		 ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		 ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		 // 初始化引擎
		 ve.init();
		  
		 // 设置模板
		 Template t = ve.getTemplate("hellovelocity.vm");
		 
		 // 创建模板上下文
		 VelocityContext ctx = new VelocityContext();
		  
		 // 设置模板中的变量
		 ctx.put("name", "velocity");
		 ctx.put("date", (new Date()).toString());
		  
		 List<String> temp = new ArrayList<>();
		 temp.add("1");
		 temp.add("2");
		 ctx.put("list", temp);
		  
		 StringWriter sw = new StringWriter();
		 // 生成模板
		 t.merge(ctx, sw);
		  
		 System.out.println(sw.toString());
		}
}
