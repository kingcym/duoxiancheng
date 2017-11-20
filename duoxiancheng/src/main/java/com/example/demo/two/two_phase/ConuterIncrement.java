package com.example.demo.two.two_phase;

import java.util.Random;

/**
 * Created by King on 2017/9/21.
 */
public class ConuterIncrement extends Thread {
    private volatile boolean terminated = false;
    private int counter = 0;
    private final Random random = new Random(System.currentTimeMillis());


    @Override
    public void run() {
        try {
            while (!terminated) {
                System.out.println(Thread.currentThread().getName() + "  " + counter++);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.clean();
        }
    }

    private void clean() {
        System.out.println("执行clean工作。。。。。。。。。。。。。。。。。。。。。。。。"+ counter);
    }

    public void close() {
        this.terminated = true;
        this.interrupt();
    }

}
