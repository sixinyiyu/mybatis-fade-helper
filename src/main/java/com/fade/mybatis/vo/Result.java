package com.fade.mybatis.vo;

import java.io.Serializable;

import com.fade.mybatis.utils.StringUtils;

/**
* Description: 统一对外模型<br/>
*
* @author qingquanzhong
* @version 1.0
* @date: 2016-12-20 13:01:14
* @since JDK 1.8
*/
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 9130894805115698656L;
	
	/**响应码(0:成功，其他失败)*/
	private Integer code;
	
	/**结果*/
	private T data;
	
	/**提示信息*/
	private String message;

	public Integer getCode() {
		return code;
	}

	public Result<T> setCode(Integer code) {
		this.code = code;
		return this;
	}

	public Object getData() {
		return data;
	}

	public Result<T> setData(T data) {
		this.data = data;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Result<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public Result<T> setSuccessValue(T data) {
		this.message = "成功";
		this.code = 0;
		this.data = data;
		return this;
	}
	
	public Result<T> setFaildMessage(String message) {
		this.code = -1;
		this.message = StringUtils.isBlank(message) ? "操作失败" : message;
		return this;
	}
	
	public boolean success() {
		return null == this.code ? false : 0 == this.code.intValue();
	}
}
