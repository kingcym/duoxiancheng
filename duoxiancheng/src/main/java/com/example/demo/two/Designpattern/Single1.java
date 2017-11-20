package com.example.demo.two.Designpattern;

/**
 * Created by King on 2017/9/12.
 */

/**
 * 饿汉式
 * 多线程安全
 */
public class Single1 {
    private static final Single1 instance = new Single1();

    private Single1() {}

    public static Single1 getInstance(){
        return instance;
    }


    public static void main(String[] args) {
        Single1 instance = Single1.getInstance();
        Single1 instance1 = Single1.getInstance();
        System.out.println(instance1);
        System.out.println(instance==instance1);
    }
}
