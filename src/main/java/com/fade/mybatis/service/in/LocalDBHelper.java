/**
 * 
 */
package com.fade.mybatis.service.in;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;
import org.mapdb.serializer.SerializerCompressionWrapper;

import com.alibaba.fastjson.JSON;
import com.fade.mybatis.vo.DBConfigVo;
import com.google.common.base.Strings;

/**
 * @author qingquanzhong
 * 本地数据库工具
 */
public class LocalDBHelper {
	
	private static final String FILE_NAME = "DBConfig.db";
	
	private static final String MAP_NAME = "DBConnection";
	
	private static DB db = makeDB(FILE_NAME);
	
	public static List<DBConfigVo> list() {
		HTreeMap<String, String> map = db.hashMap(MAP_NAME,  Serializer.STRING, Serializer.STRING).createOrOpen();
		List<DBConfigVo> vos = map.getValues().stream().map(one -> JSON.parseObject(one, DBConfigVo.class)).collect(Collectors.toList());
		return vos;
	}
	
	public static void remove(String id) {
		if (Strings.isNullOrEmpty(id)) return ;
		HTreeMap<String, String> map = db.hashMap(MAP_NAME,  Serializer.STRING, Serializer.STRING).createOrOpen();
		map.remove(id);
		db.commit();
	}
	
	public static void update(DBConfigVo config){
		if (null == config || Strings.isNullOrEmpty(config.getId())) return ;
		HTreeMap<String, String> map = db.hashMap(MAP_NAME,  Serializer.STRING, Serializer.STRING).createOrOpen();
		map.replace(config.getId(), JSON.toJSONString(config));
		db.commit();
	}
	
	
	public static void save(DBConfigVo config) {
		if (null == config) throw new IllegalArgumentException("保存配置不能为空");
		ConcurrentMap<String, String> map = db.hashMap(MAP_NAME, Serializer.STRING, Serializer.STRING).valueSerializer(new SerializerCompressionWrapper<>(Serializer.STRING)).createOrOpen();
		if (Strings.isNullOrEmpty(config.getId())) config.setId(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() + "");
		map.put(config.getId(), JSON.toJSONString(config));
		db.commit();
	}
	
	public static DBConfigVo get(String configName) {
		if (Strings.isNullOrEmpty(configName)) return null;
		HTreeMap<String, String> map = db.hashMap(MAP_NAME,  Serializer.STRING, Serializer.STRING).createOrOpen();
		String content = map.get(configName);
		if (Strings.isNullOrEmpty(content)) return null;
		return JSON.parseObject(content, DBConfigVo.class);
	}
	
	private static DB makeDB(String file) {
		return Strings.isNullOrEmpty(file) ? DBMaker.memoryDB().checksumHeaderBypass().closeOnJvmShutdown().make() : DBMaker.fileDB(file).closeOnJvmShutdown().checksumHeaderBypass().make();
	}
	
	protected static void closeDB(DB db) {
		if (null != db) db.close();
	}
}
