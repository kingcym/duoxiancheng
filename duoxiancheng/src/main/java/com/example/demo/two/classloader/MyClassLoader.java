package com.example.demo.two.classloader;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Created by King
 * on 2017/9/26.
 */
public class MyClassLoader {
    public static MyClassLoader instance = new MyClassLoader();
    public static final int x = 0;
    public static int y;

    private MyClassLoader() {
        y++;
    }

    public MyClassLoader getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(System.currentTimeMillis());

    }
}
