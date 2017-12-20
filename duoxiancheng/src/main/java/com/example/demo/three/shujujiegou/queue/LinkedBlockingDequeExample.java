package com.example.demo.three.shujujiegou.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/19 14:22
 */
public class LinkedBlockingDequeExample {


    /**
     * LinkedBlockingDeque  底层链表结构是双向的
     * 其它的与LinkedBlockingQueue类似
     */
    private LinkedBlockingDeque<Integer> queue;

    @Before
    public void Before() {
        queue = new LinkedBlockingDeque();
    }

    @Test
    public void poll() {
        queue.addFirst(1);
        queue.addFirst(11);
        queue.addFirst(12);
        queue.addFirst(4);
        queue.addFirst(2);
        for (Integer integer : queue) {
            System.out.println(integer);
        }

    }


}
