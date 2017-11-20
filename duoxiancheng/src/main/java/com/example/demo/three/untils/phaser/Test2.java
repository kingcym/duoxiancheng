package com.example.demo.three.untils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/14 21:01
 */
public class Test2 {

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        final Phaser phaser4 = new Phaser(3){
            /**
             * @param phase  到达第几个屏障
             * @param registeredParties  注册Parties数量
             * @return  true 第一次phaser.arriveAndAwaitAdvance()后，调用此方法，之后Phaser呈无效/销毁状态
             *          false 多次调用phaser.arriveAndAwaitAdvance()，Phaser继续工作
             */
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.err.println("进入onAdvance：" + phase+registeredParties);
                return true;
            }
        };
        for (int i = 1; i <= 3; i++) {
            new Task(phaser4).start();
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
                System.out.println(Thread.currentThread().getName()+"---1---");
                TimeUnit.SECONDS.sleep(2);
                phaser.arriveAndAwaitAdvance();
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"===2===");

                System.out.println("是否销毁：" + phaser.isTerminated());

                if (Thread.currentThread().getName().equals("Thread-0")){
                    TimeUnit.SECONDS.sleep(10);
                }
                phaser.arriveAndAwaitAdvance();
                TimeUnit.SECONDS.sleep(1);
                System.out.println("*********");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
