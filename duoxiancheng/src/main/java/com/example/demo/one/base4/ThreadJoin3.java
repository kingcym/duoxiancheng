package com.example.demo.one.base4;

/**
 * Created by King on 2017/9/7.
 */
public class ThreadJoin3 {
    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunable("M1",10000));
        Thread t2 = new Thread(new CaptureRunable("M2",30000));
        Thread t3 = new Thread(new CaptureRunable("M3",15000));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        long s = System.currentTimeMillis();
        System.out.println("三个线程执行完耗时："+(s-l));
    }
}

class CaptureRunable implements Runnable{
    private String name;
    private long time;

    public CaptureRunable(String name, long time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time);
            System.out.println(name+" 完成"+"----当前时间："+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


