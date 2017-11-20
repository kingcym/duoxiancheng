package com.example.demo.one.base5;

/**
 * Created by King on 2017/9/8.
 */

/**
 * 通过判断线程中断标识
 */
public class ThreadEnd2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(1);
//                        System.out.println("=========");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                        break;
//                    }
//                }
                while (!isInterrupted()){
                }
            }
        };
        t.start();
        Thread.sleep(100);
        t.interrupt();
    }
}
