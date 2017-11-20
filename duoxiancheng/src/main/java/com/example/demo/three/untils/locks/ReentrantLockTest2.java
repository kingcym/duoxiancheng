package com.example.demo.three.untils.locks;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/11 14:31
 */


public class ReentrantLockTest2 {
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private static final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


    private static final List<Long> data = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread0 = new Thread(() -> write());
        thread0.start();
        Thread thread1 = new Thread(() -> write());
        thread1.start();

        TimeUnit.MILLISECONDS.sleep(10);

        Thread thread2 = new Thread(() -> read());
        thread2.start();
        Thread thread3 = new Thread(() -> read());
        thread3.start();

        TimeUnit.MILLISECONDS.sleep(10);
        Thread thread4 = new Thread(() -> write());
        thread4.start();
        /**
         *写时不能读，读时不能写
         *可同时读，不可同时写
         */
    }

    //写
    public static void write() {
        try {
            writeLock.lock();
            data.add(System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName()+"写入数据");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    //读
    public static void read() {
        try {
            readLock.lock();
            data.forEach(System.out::println);
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + "====================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }
}
