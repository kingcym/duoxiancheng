package com.example.demo.three.shujujiegou.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/20 14:06
 */
public class LinkedTransferQueueExample {
    /**
     * 1.无边界
     */
    private LinkedTransferQueue<Integer> queue;

    @Before
    public void Before() {
        queue = new LinkedTransferQueue();
    }

    /**
     *  不阻塞
     *  @return  true   有 消费者 等待消费
     *  @return  false  无 消费者 等待消费
     */
    @Test
    public void tryTransfer() {
        boolean b = queue.tryTransfer(1);
        System.out.println(b);
    }

    /**
     *  transfer,阻塞,直到有消费者消费,才能插入
     *  注意：只能消费，不能使用peek等（peek只是取出，并未消费），不然一直等待
     *
     *  take：一直阻塞
     *  poll: 不阻塞，拿不到值，返回null
     *
     *  还有个重载：tryTransfer(E e, long timeout, TimeUnit unit)
     */
    @Test
    public void poll() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(queue.take());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 2, TimeUnit.SECONDS);
        queue.transfer(1);
      //  System.out.println("----" + queue.size());
    }


    /**
     * add offer put 这三个方法都是调用xfer(e, true, ASYNC, 0)
     * 只能return不一样，都是直接插值，无阻塞
     * @throws InterruptedException
     */
    @Test
    public void addofferput() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(queue.take());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 2, TimeUnit.SECONDS);
        queue.add(1);
        System.out.println("--=====--" + queue.size());
        queue.offer(2);
        System.out.println("--=====--" + queue.size());
        queue.put(3);
        System.out.println("--=====--" + queue.size());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("--=====--" + queue.size());
    }


}
