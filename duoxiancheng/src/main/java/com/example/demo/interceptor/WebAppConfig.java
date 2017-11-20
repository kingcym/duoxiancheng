package com.example.demo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

	/**
	 * 注册拦截器
	 * @param registry
     */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		CommonInterceptor common = new CommonInterceptor();
		System.out.println("=====================注册拦截器=======================================");
		registry.addInterceptor(common);
	}

}
