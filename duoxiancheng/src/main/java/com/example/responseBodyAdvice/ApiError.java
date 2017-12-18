package com.example.responseBodyAdvice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import lombok.Data;

/**
 * API错误信息
 * @author xiaoyin
 *
 */
//@Data
@JsonInclude(Include.NON_NULL)
public class ApiError<T> {
	private Integer status = 200;
	private String msg;
	public ApiError(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
}
