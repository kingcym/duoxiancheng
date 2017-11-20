package com.example.demo.three.untils.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/11 10:34
 */

/**
 * void lock().  //获取锁，一直等待，直到拿到锁，不可被打断
 * void lockInterruptibly() throws InterruptedException. //获取锁，可被打断
 * boolean tryLock(). //尝试拿锁,拿到返回ture,拿不到返回false
 * boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException. //在特定时间，一直尝试拿锁，可打断
 * void unlock(). //释放锁
 * int getQueueLength(). //此时有多少等待抢锁
 * boolean hasQueuedThreads(). //是否有线程等待抢锁
 * boolean hasQueuedThread(Thread thread).//是否有指定线程等待抢锁
 * int getHoldCount(). //当前线程是否抢到锁。返回0代表没有
 */
public class ReentrantLockTest1 {
    //显示锁
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

    }

    public static void needLock() {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
