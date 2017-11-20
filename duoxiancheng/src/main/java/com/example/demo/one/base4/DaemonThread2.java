package com.example.demo.one.base4;

/**
 * Created by King on 2017/9/7.
 */

/**
 * 守护线程
 */
public class DaemonThread2 {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            Thread inner = new Thread(()->{
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            inner.setDaemon(true);
            inner.start();

            try {
                inner.join();
                System.out.println("t结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        t.setDaemon(true);
        t.start();
        t.interrupt();
    }
}
