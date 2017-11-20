package com.example.demo.three.untils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/14 21:01
 */
public class PhaserTest3 {

    /**
     * 所有线程执行完A,再执行B,所有线程执行完B后,再执行C.........
     * 类似 ： CyclicBarrier
     * @param args
     */
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(4);
        for (int i = 1; i <= 3; i++) {
            new Task(phaser).start();
        }
        new Task1(phaser).start();
    }

    static class Task extends Thread {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                task(phaser, Thread.currentThread().getName() + "-----A-----start", Thread.currentThread().getName() + "-----A-----end");

                task(phaser, Thread.currentThread().getName()+"-----B-----start", Thread.currentThread().getName()+"-----B-----end");

                task(phaser, Thread.currentThread().getName()+"-----C-----start", Thread.currentThread().getName()+"-----C-----end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Task1 extends Thread {
        private final Phaser phaser;

        Task1(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {

                task(phaser, Thread.currentThread().getName() + "-----A-----start", Thread.currentThread().getName() + "-----A-----end");

                task(phaser, Thread.currentThread().getName()+"-----B-----start", Thread.currentThread().getName()+"-----B-----end");

                phaser.arriveAndDeregister();//减少一个
                System.out.println("=======================================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void task(Phaser phaser, String x, String x2) throws InterruptedException {
        System.out.println(x);
        TimeUnit.SECONDS.sleep(2);
        System.out.println(x2);
        phaser.arriveAndAwaitAdvance();

    }
}
