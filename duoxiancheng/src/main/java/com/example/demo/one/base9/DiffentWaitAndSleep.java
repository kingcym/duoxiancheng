package com.example.demo.one.base9;

/**
 * Created by King on 2017/9/9.
 */
public class DiffentWaitAndSleep {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        m2();
    }


    public static void m2(){
        synchronized (lock) {
            try {
                lock.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
