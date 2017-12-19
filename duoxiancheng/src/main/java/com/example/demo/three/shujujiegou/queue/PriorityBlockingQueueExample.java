package com.example.demo.three.shujujiegou.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/19 10:13
 */
public class PriorityBlockingQueueExample {
    /**
     * 1. 无边界,和ArrayList类似,实现自动扩容
     * 2. new一个对象时,传入Comparator，按照顺序取出，若没有传入Comparator,里面的元素必须实现Comparatable接口
     * 3. 其它类似ArrayBlockingQueue
     */


    private PriorityBlockingQueue<Integer> queue;

    @Before
    public void Before() {
        queue = new PriorityBlockingQueue(2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }


    /**
     * 取元素
     * 取一个元素，相应删除对应元素，若没有元素，则返回null
     */
    @Test
    public void poll() {
        queue.offer(1);
        queue.offer(99);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
    }

}
