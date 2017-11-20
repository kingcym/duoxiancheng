package com.example.demo.three.untils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/10 10:16
 */
public class SemaphoreTest4 {
    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(5);
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                int i = 1;
                try {
                    i = semaphore.drainPermits(); //获取所有许可数,并且返回所有许可数
                    System.out.println("进入t1--" + i);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("t1异常");
                } finally {
                    semaphore.release(2);
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        System.err.println("可用许可证-> "+semaphore.availablePermits());
    }

    /**
     * void acquire()
     请求一个许可证，如果没有就阻塞，直到有可用的许可证，或者线程被中断。

     void acquire(int permits)
     请求给定数量的许可证，如果没有就阻塞，直到有足够数量可用的许可证，或者线程被中断。

     void acquireUninterruptibly()
     功能同acquire()，但是不会被中断。如果在阻塞期间线程中断，阻塞依然会继续下去。

     void acquireUninterruptibly(int permits)
     你懂的。

     boolean tryAcquire()
     尝试获取一个许可证，如果有就返回true，否则返回false，不会发生阻塞。

     boolean tryAcquire(long timeout, TimeUnit unit)
     尝试获取一个许可证，如果有就返回true。如果没有就尝试等待给定的时间，在这个时间内线程阻塞，直到有可用的许可证或者超时或者线程中断。

     boolean tryAcquire(int permits)
     你懂的。

     boolean tryAcquire(int permits, long timeout, TimeUnit unit)
     你懂的。

     void release()
     释放一个许可证，将它交还给信号量。

     void release(int permits)
     释放指定个数的许可证，将它们交还给信号量。
     */
}
