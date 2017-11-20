package com.example.demo.one.base3;

/**
 * Created by King on 2017/9/6.
 * 构造Thread对象也许不知道的事
 */

import java.util.Arrays;

/**
 *1. 构造线程对象Thread,默认有一个线程名，以Thread-开头，从0开始计数
 *2. 如果在构造Thread的时候没有传递Runnable或者没有复写Thread的run方法，
 *  该Thread将不会调用任何东西.
 *3.  如果构造线程对象时未传入ThreadGroup，Thread会默认获取父类的ThreadGroup作为
 *  该线程的ThreadGroup。
 */
public class ThreadSomeThing {

    public static void main(String[] args) {

        Thread thread = new Thread(()-> {
            try {
                Thread.sleep(100000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"e");
        thread.start();

    }
}
