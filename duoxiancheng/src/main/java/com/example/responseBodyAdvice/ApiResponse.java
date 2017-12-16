package com.example.responseBodyAdvice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * API返回信息
 * @author xiaoyin
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class ApiResponse<T> {
	private T body;
	private Integer status = 200;
	private String msg = "success";
	public ApiResponse(T object) throws Exception {
		super();
		this.body = object;
	}

	public ApiResponse() {
		super();
	}
}
