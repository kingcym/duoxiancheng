package com.example.demo.one.base12;

/**
 * Created by King on 2017/9/10.
 */
public class ThreadGroupApi {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg1 = new ThreadGroup("tg1");
        Thread t1 = new Thread(tg1,() -> {
            while (true) {
                try {
                    Thread.sleep(20_000);
//                    System.out.println(Thread.currentThread().getThreadGroup().getName());
//                    System.out.println(Thread.currentThread().getThreadGroup().getParent());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");
        t1.start();
        ThreadGroup tg2 = new ThreadGroup(tg1, "tg2");
        Thread t2 = new Thread(tg2,() -> {
            while (true) {
                try {
                    Thread.sleep(10_000);
//                    System.out.println(Thread.currentThread().getThreadGroup().getName());
//                    System.out.println(Thread.currentThread().getThreadGroup().getParent());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2");
        t2.start();
        System.out.println(tg1.activeCount());
        System.out.println(tg1.activeGroupCount());
        System.out.println(tg2.activeCount());
    }



}
