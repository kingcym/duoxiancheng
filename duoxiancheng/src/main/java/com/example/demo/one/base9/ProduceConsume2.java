package com.example.demo.one.base9;

/**
 * Created by King on 2017/9/8.
 */

/**
 * 单个消费者与生产者
 */
public class ProduceConsume2 {
    private int i=0;
    private final Object lock = new Object();
    private boolean isProduced = false;


    public void produce() {
        synchronized (lock) {
            if (isProduced) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("p---> " + i);
                isProduced = true;
                lock.notify();
            }
        }
    }


    public void consume()  {
        synchronized (lock) {
            if (isProduced) {
                System.out.println("C---> " + i);
                isProduced = false;
                lock.notify();
            } else {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    public static void main(String[] args) {
        ProduceConsume2 produceConsume2 = new ProduceConsume2();
        new Thread(){
            @Override
            public void run() {
                while (true) produceConsume2.produce();
            }
        }.start();


        new Thread(){
            @Override
            public void run() {
                while (true) produceConsume2.consume();
            }
        }.start();

    }
}
