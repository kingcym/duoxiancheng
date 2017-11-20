package com.example.demo.two.guarded_suspension;

import java.util.Random;

/**
 * Created by King on 2017/9/21.
 */
public class ServiceThread extends Thread {
    private final RequestQueue queue;
    private final Random random;
    private volatile boolean flag = false;

    public ServiceThread(RequestQueue queue) {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!flag) {
            Request request = queue.getRequest();
            if (null == request) {
                System.out.println("request等于null");
                continue;
            }
            System.out.println("获取： " + request.getValue());
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void close() {
        this.flag = true;
        this.interrupt();
    }
}
