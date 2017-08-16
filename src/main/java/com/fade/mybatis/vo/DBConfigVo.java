/**
 * 
 */
package com.fade.mybatis.vo;

import java.io.Serializable;

import com.fade.mybatis.enums.DbType;
import com.fade.mybatis.enums.EncodeType;
import com.google.common.base.Strings;

/**
 * @author qingquanzhong
 *
 */
public class DBConfigVo implements Serializable {

	private static final long serialVersionUID = -3442349954458451410L;

	private String id;

	/** 连接名 */
	private String connectionName;
	
	private String dbType;
	
	/** 主机名或IP */
	private String host;

	/** 端口号 */
	private Integer port;

	/** 数据库名 */
	private String databaseName;

	/** 用户名 */
	private String userName;

	/** 密码 */
	private String password;

	/** 编码 */
	private String encode;
	
	private String fullURL;
	
	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}


	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getConnectionName() {
		return connectionName;
	}

	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}
	
	public String getFullURL() {
		if (DbType.mysql.getValue().equals(this.dbType)) {
			fullURL = String.format(DbType.mysql.getUrl(), this.host, this.port, this.databaseName, Strings.isNullOrEmpty(this.encode) ? this.encode :EncodeType.UTF8.getValue());
        }else if (DbType.oracle.getValue().equals(this.dbType)){
        	fullURL =  String.format(DbType.oracle.getUrl(), this.host, this.port, this.databaseName);
        }
		return fullURL;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DBConfigVo [id=").append(id).append(", connectionName=").append(connectionName)
				.append(", host=").append(host).append(", port=").append(port).append(", databaseName=").append(databaseName)
				.append(", userName=").append(userName).append(", password=").append(password).append(", encode=")
				.append(encode).append("]");
		return builder.toString();
	}

}
