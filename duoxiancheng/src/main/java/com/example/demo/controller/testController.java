package com.example.demo.controller;

import com.example.demo.two.balking.Main;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/11/8 10:24
 */
@RestController
@RequestMapping("/aaa")
public class testController {
    // 根目录映射 Get访问方式 直接返回一个字符串
    @RequestMapping("/tets")
    Map<String, String> hello(Integer type, String name) {
        // 返回map会变成JSON key value方式
        Map<String, String> map = new HashMap<String, String>();
        map.put("content", "hello freewolf~");
        return map;
    }


    public static void main(String[] args) {
        int a[] = new int[10];
        System.out.println(a.length);

        int newa[] = new int[a.length * 2];
//        for (int i = 0; i < a.length; i++) {
//            newa[i] = a[i];
//        }
        a = newa;

        System.out.println(a.length);
    }
}
