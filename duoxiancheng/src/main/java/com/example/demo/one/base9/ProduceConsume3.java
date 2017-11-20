package com.example.demo.one.base9;

/**
 * Created by King on 2017/9/8.
 */

/**
 * 多个个消费者与生产者
 * 打印结果（结果是随机，举例其中一种）：
 *           p---> p1:1
             C---> c1:1
             p---> p1:2
             C---> c2:2
 * 然后程序一直运行状态，但又不打印语句，处于一种假死状态
 * 分析可能原因：p1生产一个，激活一个;
 *              正好这时p1,p2分别抢到执行权，因为没被消费，所以p1,p2都wait;
 *              c1消费一个，激活了p1;
 *              这时正好c1,c2抢到执行权，因为没有生产，所以c1 c2 wait;
 *              p1又生产一个，恰好激活c2,然后p1处于wait状态;
 *              c2消费一个，激活了c1;
 *   这样一轮下来，p1,p2都处于等待状态，并且没有生产者，接着消费者也会处于等待状态
 *   这样形成了互相等待，假死现象。
 *
 * 根源： notify()唤醒具有不确定性。
 */
public class ProduceConsume3 {
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
                System.out.println("p---> "+ Thread.currentThread().getName() +":"+ i);
                isProduced = true;
                lock.notifyAll();
            }
        }
    }


    public void consume()  {
        synchronized (lock) {
            if (isProduced) {
                System.out.println("C---> " +Thread.currentThread().getName()+":"+ i);
                isProduced = false;
                lock.notifyAll();
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
        ProduceConsume3 produceConsume2 = new ProduceConsume3();
        new Thread("p1"){
            @Override
            public void run() {
                while (true) produceConsume2.produce();
            }
        }.start();

        new Thread("p2"){
            @Override
            public void run() {
                while (true) produceConsume2.produce();
            }
        }.start();

        new Thread("c1"){
            @Override
            public void run() {
                while (true) produceConsume2.consume();
            }
        }.start();

        new Thread("c2"){
            @Override
            public void run() {
                while (true) produceConsume2.consume();
            }
        }.start();

    }
}
