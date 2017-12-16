package com.example.responseBodyAdvice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/5 17:54
 */

//拦截controller的返回值
@ControllerAdvice(basePackages = "com.example.demo.controller")
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ApiResponse<Object> apiRes ;
        try {
            //抛出异常不返回state:200
            if (body instanceof ApiError) {
                return body;
            }
            apiRes = new ApiResponse<>(body);
            return apiRes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomerException(111,"系统错误，请联系管理员");
        }
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }


}
