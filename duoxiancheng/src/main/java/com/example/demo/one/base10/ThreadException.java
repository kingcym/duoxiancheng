package com.example.demo.one.base10;

/**
 * Created by King on 2017/9/9.
 */

/**
 * 线程中发生异常，如果内部没有捕获，外部是无法捕获的
 * 因为线程中没有抛出异常
 *
 */
public class ThreadException {
    public static void main(String[] args) {
        try {
            Thread t= new Thread("TTTT"){
                @Override
                public void run() {
                    int i = 2/0;
                }
            };
            //捕获线程中的异常
            t.setUncaughtExceptionHandler((thread,e)->{
                e.printStackTrace();
                System.out.println("---------"+ thread);
            });
            t.start();
        } catch (Exception e) {
            System.out.println("发生也异常");
            e.printStackTrace();
        }
    }
}
