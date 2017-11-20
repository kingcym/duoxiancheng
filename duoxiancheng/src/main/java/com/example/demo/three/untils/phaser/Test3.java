package com.example.demo.three.untils.phaser;

import java.util.concurrent.Phaser;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/14 21:01
 */
public class Test3 {

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser(2);
        new Thread(phaser::arrive).start(); //Arrives at this phaser,without waiting for others to arrive.
        System.out.println("*********");
        new Thread(phaser::arriveAndAwaitAdvance).start();
        System.out.println("*********");
    }

    static class Task extends Thread {
        private final Phaser phaser;
        Task(Phaser phaser) {
            this.phaser = phaser;
        }
        @Override
        public void run() {
            try {
 //               phaser.arriveAndAwaitAdvance();
                phaser.arrive(); //Arrives at this phaser, without waiting for others to arrive.
                System.out.println("*********");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
