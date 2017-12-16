package com.example.responseBodyAdvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Kingcym
 * @Description:  处理所有controller下的自定义异常
 * @Date: 2017/12/5 17:50
 */
@ControllerAdvice
public class ExceptionHandler {

    /**
     * 异常处理核心类
     * @param e 自定义异常
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(CustomerException.class)
    @ResponseBody
    public ApiError<Object> errorHandler(CustomerException e){
        return new ApiError<Object>(e.getCode(),e.getMsg());
    }
}
