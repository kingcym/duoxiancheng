package com.example.demo.security.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * API返回信息
 * @author xiaoyin
 *
 */
@JsonInclude(Include.NON_NULL)
public class ApiResponse<T> {
	private T body;
	private Integer status = 200;
	private String msg = "操作成功";
	public ApiResponse(T object) throws NoSuchMethodException, Exception {
		super();
//		this.pagination = getPagination(getPage(object));
		this.body = object;
	}


	public ApiResponse() {
		super();
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
