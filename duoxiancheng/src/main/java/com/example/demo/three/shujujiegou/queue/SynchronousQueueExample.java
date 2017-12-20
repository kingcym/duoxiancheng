package com.example.demo.three.shujujiegou.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/19 14:55
 */
public class SynchronousQueueExample {
    private SynchronousQueue<Integer> queue;

    /**
     * 没有容量存储数据，只有当一个线程等待取，才能插入数据
     */
    @Before
    public void Before() {
        queue = new SynchronousQueue();
    }

    /**
     * 一个线程等待take（），才能add(),否则抛出IllegalStateException("Queue full");
     * @throws InterruptedException
     */
    @Test
    public void addAndtake() throws InterruptedException {
        Executors.newCachedThreadPool().execute(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.MICROSECONDS.sleep(2);
        queue.add(11);
    }

    @Test
    public void putAndpoll() throws InterruptedException {
        Executors.newCachedThreadPool().execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(queue.poll());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        queue.put(11);
    }
}
