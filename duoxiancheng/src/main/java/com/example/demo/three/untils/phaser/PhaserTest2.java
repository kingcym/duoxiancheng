package com.example.demo.three.untils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/14 21:01
 */
public class PhaserTest2 {

    /**
     * 所有线程执行完A,再执行B,所有线程执行完B后,再执行C.........
     * 类似 ： CyclicBarrier
     * @param args
     */
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(3);
        for (int i = 1; i <= 3; i++) {
            new Task(phaser).start();
        }

    }

    static class Task extends Thread {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                System.out.println( Thread.currentThread().getName()+"-----A-----start");
                TimeUnit.SECONDS.sleep(2);
                System.out.println( Thread.currentThread().getName()+"-----A-----end");
                phaser.arriveAndAwaitAdvance();

                System.out.println( Thread.currentThread().getName()+"-----B-----start");
                TimeUnit.SECONDS.sleep(2);
                System.out.println( Thread.currentThread().getName()+"-----B-----end");
                phaser.arriveAndAwaitAdvance();

                System.out.println( Thread.currentThread().getName()+"-----C-----start");
                TimeUnit.SECONDS.sleep(2);
                System.out.println( Thread.currentThread().getName()+"-----C-----end");
                phaser.arriveAndAwaitAdvance();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
