package com.example.demo.three.automic.automicinteger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by King on 2017/9/29.
 */
public class CompareAndsetLock {
    private final AtomicInteger a = new AtomicInteger(0);
    private static Thread thread;
    public void tryLock() throws GetLockException {
        boolean b = a.compareAndSet(0, 1);
        if (!b){
            throw new GetLockException("无法获取锁");
        }
        thread = thread.currentThread();
    }

    public void unLock() {

        if (0==a.get()) return;
        if (thread == thread.currentThread())
        a.compareAndSet(1, 0);
    }


}
