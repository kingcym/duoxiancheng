package com.example.demo.two.threadlocal;

/**
 * Created by King on 2017/9/21.
 */
public class ThreadLocalSimple {
    private static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            local.set("t1");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"" +
                        " "+local.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            local.set("t2");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+": "+local.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("===============================================");
        System.out.println(Thread.currentThread().getName()+": "+local.get());
    }


}
