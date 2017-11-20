package com.example.demo.three.untils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/14 21:01
 */
public class PhaserTest1 {

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(6);
        for (int i = 1; i <= 5; i++) {
            new Task(phaser).start();
        }
        phaser.arriveAndAwaitAdvance();

        System.out.println("===========");
    }

    static class Task extends Thread {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;

        }

        @Override
        public void run() {
            System.out.println("working: +" + Thread.currentThread().getName()+"--");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
        }
    }
}
