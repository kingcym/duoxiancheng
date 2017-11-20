package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties(prefix="webservice",locations={"classpath:webservice.yml"})
public class WebServiceConfig {
	
	@Value("webServiceUrl")
	private String webServiceUrl;
	
	@Value("userinfoByIDMethod")
	private String userinfoByIDMethod;
	
}