package com.example.demo.one.base9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by King on 2017/9/10.
 */
public class Abcd {
    // //通过JDK5中的锁来保证线程的访问的互斥
    private final static Lock lock = new ReentrantLock();

    private static int state = 0;// 用state来判断轮到谁执行

    private static final int RUN_NUMBER=100;//表示循环的次数
    public static void main(String[] args) {
        new Thread(()->{
            while (true){
                System.out.println("AAA"+state);
                if (state%2==0){
                    lock.lock();//获取锁定
                    System.out.println("A 获取锁");
                    state++;
                    lock.unlock();//释放锁定,不释放锁定，会被该线程一直保持
                    System.out.println("A 释放锁");
                }
                System.out.println("AAA----------"+state);

            }

        }).start();

        new Thread(()->{
            while (true){
                lock.lock();//获取锁定
                System.out.println("B 获取锁" + state);
                if (state%2==1){
                    state++;
                }
                lock.unlock();//释放锁定,不释放锁定，会被该线程一直保持
                System.out.println("B 释放锁" + state);
            }
        }).start();




    }
}
