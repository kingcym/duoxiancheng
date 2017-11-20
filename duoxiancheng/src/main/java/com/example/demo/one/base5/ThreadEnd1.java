package com.example.demo.one.base5;

/**
 * Created by King
 * on 2017/9/8.
 */

/**
 * 通过标识关闭线程
 */
public class ThreadEnd1 {
    volatile static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run() {
                while (flag) {
                    System.out.println("=========");
                }
            }
        };
        t.start();
        Thread.sleep(5);
        shutdown();//关闭
    }

    private static void shutdown() {
        flag = false;
    }
}
