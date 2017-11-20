package com.example.demo.one.baseSinze7;

/**
 * Created by King on 2017/9/8.
 */

/**
 * 分析this锁的存在
 *
 * todo class锁的存在
 */
public class Synize {
    public static void main(String[] args) {
        ThisLock thisLock = new ThisLock();
        new Thread("t1"){
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();

        new Thread("t2"){
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();

    }

}

class ThisLock {
    public synchronized void m1()  {
        try {
            System.out.println(this);
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void m2()  {
        try {
            System.out.println(this);
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
