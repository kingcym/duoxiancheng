package com.example.demo.three.untils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/9 22:50
 * <p>
 * Semaphore有两个构造方法 Semaphore(int)、Semaphore(int,boolean)，参数中的int表示该信号量拥有的许可数量，boolean表示获取许可的时候是否是公平的，如果是公平的那么，当有多个线程要获取许可时，会按照线程来的先后顺序分配许可，否则，线程获得许可的顺序是不定的。这里在jdk中讲到 “一般而言，非公平时候的吞吐量要高于公平锁”，这是为什么呢？附上链接中的一段话：
 * <p>
 * 非公平锁性能高于公平锁性能的原因：在恢复一个被挂起的线程与该线程真正运行之间存在着严重的延迟。假设线程A持有一个锁，并且线程B请求这个锁。由于锁被A持有，因此B将被挂起。当A释放锁时，B将被唤醒，因此B会再次尝试获取这个锁。与此同时，如果线程C也请求这个锁，那么C很可能会在B被完全唤醒之前获得、使用以及释放这个锁。这样就是一种双赢的局面：B获得锁的时刻并没有推迟，C更早的获得了锁，并且吞吐量也提高了。当持有锁的时间相对较长或者请求锁的平均时间间隔较长，应该使用公平锁。在这些情况下，插队带来的吞吐量提升（当锁处于可用状态时，线程却还处于被唤醒的过程中）可能不会出现。

但是需要注意，信号量更像是一个计数的装置，而不是一个资源的管理中心。
acquire将计数减1，直到减到0，就会发生阻塞。release将计数加1。release的行为是没有限制的，你可以通过release持续的增加计数，不管之前是否有调用过acquire。所以，初始的许可证个数并不代表上限，通过release可以让许可证的个数超过这个初始值。


 */


/**
 * Semaphore自定义一个显示锁
 * new Semaphore(int i)表示允许有i个线程同时拥有这把锁
 */
public class SemaphoreTest1 {

    public static void main(String[] args) {
        final SemaphoreLock lock = new SemaphoreLock();
        for (int i = 0; i < 4; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "running");
                        lock.lock();
                        TimeUnit.MILLISECONDS.sleep(2);
                        System.out.println(Thread.currentThread().getName() + "get lock");
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                        System.out.println(Thread.currentThread().getName() + "relase lock");
                    }
                }
            }.start();
        }

    }


    static class SemaphoreLock {
        private final Semaphore semaphore = new Semaphore(1);

        public void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public void unlock() {
            semaphore.release();
        }
    }
}
