package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
public class BasicOneApplication {


	// 根目录映射 Get访问方式 直接返回一个字符串
	@RequestMapping("/tets")
	Map<String, String> hello(Integer type,String name) {
		// 返回map会变成JSON key value方式
		Map<String,String> map=new HashMap<String,String>();
		map.put("content1", "~说的sssssss啊啊都是");
		return map;
	}





	public static void main(String[] args) {
		SpringApplication.run(BasicOneApplication.class, args);
	}
}
