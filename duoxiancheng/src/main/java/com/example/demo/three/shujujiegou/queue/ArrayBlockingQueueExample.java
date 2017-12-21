package com.example.demo.three.shujujiegou.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/18 22:24
 */

public class ArrayBlockingQueueExample {

    private ArrayBlockingQueue<String> queue;



    /**
     * 1. FIFO first-in-first-out
     * 2. Once created, the capacity cannot be changed.
     * 3. Attempts to {@code put} an element into a full queue
     * will result in the operation blocking;
     * 4. attempts to {@code take} an
     * element from an empty queue will similarly block.
     * 5.有边界
     *
     * @return
     */
    @Before
    public void Before() {
        queue = new ArrayBlockingQueue(5);
    }

    /**
     * 添加一个元素
     *
     * @throws IllegalStateException if this queue is full
     * @throws NullPointerException  if the specified element is null
     */
    @Test
    public void add() {
        assertThat(queue.add("1"), equalTo(true));
        assertThat(queue.add("2"), equalTo(true));
        assertThat(queue.add("3"), equalTo(true));
        assertThat(queue.add("4"), equalTo(true));
        assertThat(queue.add("5"), equalTo(true));
        assertThat(queue.size(), equalTo(5));
    }

    /**
     * 添加一个元素
     * 如果容量已满，返回false
     *
     * @throws NullPointerException if the specified element is null
     */
    @Test
    public void offer() {
        assertThat(queue.offer("1"), equalTo(true));
        assertThat(queue.offer("2"), equalTo(true));
        assertThat(queue.offer("3"), equalTo(true));
        assertThat(queue.offer("4"), equalTo(true));
        assertThat(queue.offer("5"), equalTo(true));
        assertThat(queue.offer("6"), equalTo(false));
    }

    /**
     * 添加一个元素
     * 如果容量已满，会一直阻塞，直到容器有空间插入数据
     *
     * @throws InterruptedException
     * @throws NullPointerException if the specified element is null
     */
    @Test
    public void put() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 2, TimeUnit.SECONDS);

        queue.put("1");

        System.out.println("==");

        queue.put("2");
        System.out.println("==");

        queue.put("3");
        System.out.println("==");

        queue.put("4");
        System.out.println("==");

        queue.put("5");
        System.out.println("==");

        queue.put("6");
        System.out.println("==");

        executorService.shutdown();
    }


    /**
     * 取元素
     * 取一个元素，相应删除对应元素，若没有元素，则返回null
     */
    @Test
    public void poll() {
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        queue.offer("4");
        queue.offer("5");

        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
        System.out.println(queue.poll() + "--" + queue.size());
    }
/**
 * todo
 *  remove
 *  element
 */

    /**
     * 取元素
     * 永远只取第一个数据，不会删除数据
     */
    @Test
    public void peek() {
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        queue.offer("4");
        queue.offer("5");

        System.out.println(queue.peek() + "--" + queue.size());
        System.out.println(queue.peek() + "--" + queue.size());
        System.out.println(queue.peek() + "--" + queue.size());
        System.out.println(queue.peek() + "--" + queue.size());
    }




    /**
     * 取元素
     * 取一个元素，相应删除对应元素，若没有元素，则会一直等待，直到存入元素
     */
    @Test
    public void take() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(queue.offer("1"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 2, TimeUnit.SECONDS);


        queue.offer("1");
        queue.offer("2");

        System.out.println(queue.element() + "--" + queue.size());
        System.out.println(queue.take() + "--" + queue.size());
        System.out.println(queue.take() + "--" + queue.size());

    }

    /**
     * 排干，并把所有元素放到一个Collection里面
     */
    @Test
    public void drainTo() {
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        queue.offer("4");
        queue.offer("5");
        ArrayList<String> list = new ArrayList<>();
        queue.drainTo(list);
        System.out.println("queue的大小" + queue.size());
        for (String s : list) {
            System.out.println(s);
        }
    }

}
