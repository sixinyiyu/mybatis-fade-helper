/**
 * 
 */
package com.fade.mybatis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fade.mybatis.vo.ResultVo;

/**
 * @author qingquanzhong 全局异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Object handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		logger.error("不支持当前请求方法", e);
		return new ResultVo<>().setFaildMessage("不支持当前请求方法");
	}

	/**
	 * 415 - Unsupported Media Type
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Object handleHttpMediaTypeNotSupportedException(Exception e) {
		logger.error("不支持当前媒体类型", e);
		return new ResultVo<>().setFaildMessage("content_type_not_supported");
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Object handleException(Exception e) {
		logger.error("通用异常", e);
		return new ResultVo<>().setFaildMessage("内部服务器：" + e.getMessage());
	}
}
