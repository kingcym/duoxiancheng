package com.example.demo.three.untils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/9 22:50
 */

/**
 * Semaphore自定义一个显示锁
 * new Semaphore(int i)表示允许有i个线程同时拥有这把锁
 */
public class SemaphoreTest2 {

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 4; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        semaphore.acquire(2);//一次拿两个许可证
                        TimeUnit.MILLISECONDS.sleep(2);
                        System.out.println(Thread.currentThread().getName()+"get acquire");
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release(2);//一次释放两个许可证
                        System.out.println(Thread.currentThread().getName()+"relase acquire");
                    }
                }
            }.start();
        }
        TimeUnit.MILLISECONDS.sleep(2);
        while (true){
            System.err.println("可用许可证-> "+semaphore.availablePermits());
            System.err.println("block的个数-> "+semaphore.getQueueLength());
            System.err.println("========================");
            TimeUnit.SECONDS.sleep(1);
        }
    }


}
