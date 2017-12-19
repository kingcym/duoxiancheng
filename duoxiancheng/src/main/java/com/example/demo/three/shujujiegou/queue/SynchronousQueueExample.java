package com.example.demo.three.shujujiegou.queue;

import org.junit.Before;

import java.util.concurrent.SynchronousQueue;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/19 14:55
 */
public class SynchronousQueueExample {
    private SynchronousQueue<Integer> queue;

    @Before
    public void Before() {
        queue = new SynchronousQueue();
    }

}
