package com.example.demo.two.Designpattern;

/**
 * Created by King on 2017/9/12.
 */

/**
 * 懒汉式
 * 多线程不安全
 * 执行下面mian方法多次，会出现两次
 * 打印地址不一样的情况
 */
public class Single2 {

    private static  Single2 instance;
    private Single2() {}

    public static Single2 getInstance(){
        if (null==instance){
            instance = new Single2();
        }
        return instance;
    }

    public static void main(String[] args) {
        new Thread(()->{
            Single2 instance = Single2.getInstance();
            System.out.println(instance);
        }).start();

        new Thread(()->{
            Single2 instance = Single2.getInstance();
            System.out.println(instance);
        }).start();
    }
}
