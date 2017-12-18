package com.example.demo.interceptor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 全局拦截器
 * @author King
 *
 */
public class CommonInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);
	/**
	 * 请求之前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) throws Exception {
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		logger.info("请求接口:{},请求参数:{}",request.getRequestURL(),gson.toJson(request.getParameterMap()));
		return true;
	}
	/**
	 * controller之后执行
	 */
	@Override
	public void postHandle(HttpServletRequest request,
						   HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}
	/**
	 * 执行完控制器后
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
