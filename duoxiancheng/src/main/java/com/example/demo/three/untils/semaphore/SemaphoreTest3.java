package com.example.demo.three.untils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/9 22:50
 */
public class SemaphoreTest3 {

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(1);
        ThreadLocal<Thread> local = new ThreadLocal<>();
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println("进入t1");
                    while (true) {
                        long i = 0;
                        i++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("t1异常");
                } finally {
                    semaphore.release();
                    System.out.println("释放t2");
                }
            }
        };

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    local.set(Thread.currentThread());
                    System.out.println("进入t2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("t2异常");
                } finally {
                    System.out.println("t2即将释放：" + local.get());
                    if (Thread.currentThread() == local.get()) { //保证自己获得了信号量，才能释放信号量
                        semaphore.release();
                        System.out.println("t2释放：" + local.get());
                    }

                }
            }
        };

        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println("进入t3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("t3异常");
                } finally {
                    semaphore.release();
                }
            }
        };

        t1.start();
        TimeUnit.MILLISECONDS.sleep(50);
        t2.start();
        TimeUnit.MILLISECONDS.sleep(50);
        t3.start();
        TimeUnit.MILLISECONDS.sleep(50);
        /**
         * 等待获取 信号量 的线程才会被打断
         * 已经获取到 信号量 未结束的线程 不会被打断
         */
        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
        /**
         * semaphore.acquire()换成semaphore.acquireUninterruptibly();
         * 功能同acquire()，但是不会被中断。如果在阻塞期间线程中断，阻塞依然会继续下去。
         */
    }


}
