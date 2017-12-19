package com.example.demo.three.shujujiegou.queue;

import org.junit.Before;

import java.util.Comparator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/19 14:22
 */
public class LinkedBlockingQueueExample {


    /**
     * 1.可指定边界，若未指定，默认Integer.MAX_VALUE
     * 2.FIFO
     */
    private LinkedBlockingQueue<Integer> queue;

    @Before
    public void Before() {
        queue = new LinkedBlockingQueue();
    }





}
