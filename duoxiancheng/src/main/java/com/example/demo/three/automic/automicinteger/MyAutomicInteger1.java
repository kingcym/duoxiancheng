package com.example.demo.three.automic.automicinteger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by King on 2017/9/28.
 */
public class MyAutomicInteger1 {

    public static void main(String[] args) {
//        AtomicInteger a = new AtomicInteger();
//        System.out.println(a.get()); //获取值
//        a = new AtomicInteger(10);//赋值
//        System.out.println(a);
//        a.set(12);//赋值  没加锁  和普通赋值没有区别
//        System.out.println(a.get());
/*--------------------------------------------------*/
        AtomicInteger b = new AtomicInteger(5);
//        int andAdd = b.getAndAdd(10); //加上10，并且返回原先的值【原子性】
//        int andAdd = b.addAndGet(10); //先加上10 ，再返回值 18【原子性】
//        System.out.println(andAdd);  //8
//        System.out.println(b.get());  //18
        int andIncrement = b.getAndIncrement();//先返回原先的值,再加1 【原子性】
//        System.out.println(andIncrement);
//        System.out.println(b.get());
          boolean b1 = b.compareAndSet(5, 9);//如果原值是5，则赋新值9，返回true
        System.out.println(b1);
        System.out.println(b.get());
    }
}
