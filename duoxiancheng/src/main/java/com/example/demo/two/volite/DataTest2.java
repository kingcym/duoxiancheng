package com.example.demo.two.volite;

import java.time.Instant;
import java.util.Date;

/**
 * Created by King on 2017/9/22.
 */
public class DataTest2 {
    public static void main(String[] args) {
        //获取当前时间的instant
        Instant now_instant= Instant.now();// 获取北京时间;
        System.out.println(now_instant.toEpochMilli());
        System.out.println(System.currentTimeMillis());
        Date date = new Date(now_instant.toEpochMilli());
        System.out.println(date);

    }
}
