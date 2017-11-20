package com.example.demo.one.base4;

/**
 * Created by King on 2017/9/7.
 */
public class DaemonThread {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "runing");
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "done");
            }
        };
        thread.setDaemon(true);
        thread.start();
        System.out.println(Thread.currentThread().getName() + "    ");

    }


}
