package com.example.demo.three.untils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/14 21:01
 */
public class PhaserTest3ewqe {

    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser(5);
        for (int i = 1; i <= 3; i++) {
            new Task(phaser).start();
        }
        TimeUnit.SECONDS.sleep(7);
        System.out.println("++++++++++++"+phaser.getRegisteredParties());
    }

    static class Task extends Thread {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndDeregister();
            System.out.println("##############"+phaser.getRegisteredParties());
            phaser.arriveAndAwaitAdvance();
            System.out.println("====================");
        }
    }




}
