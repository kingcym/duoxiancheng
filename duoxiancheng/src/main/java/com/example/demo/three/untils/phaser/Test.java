package com.example.demo.three.untils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/14 21:01
 */
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser(1);

        //getPhase()获取到达第几个屏障 Returns the current phase number.
        System.out.println("getPhase: " + phaser.getPhase()); //0
        phaser.arriveAndAwaitAdvance();
        System.out.println("getPhase: " + phaser.getPhase()); //1
        phaser.arriveAndAwaitAdvance();
        System.out.println("getPhase: " + phaser.getPhase()); //2

        /**-------------------------------------------------------------------------------------------------*/

        //getRegisteredParties获取注册partis的数量   Returns the number of parties registered at this phaser
        System.out.println("getRegisteredParties: " + phaser.getRegisteredParties());//1
        //register()动态添加一个partis
        phaser.register();
        System.out.println("getRegisteredParties: " + phaser.getRegisteredParties());//2
        //bulkRegister()批量增加partis
        phaser.bulkRegister(2);
        System.out.println("getRegisteredParties: " + phaser.getRegisteredParties());//4

        /**-------------------------------------------------------------------------------------------------*/
        final Phaser phaser3 = new Phaser(4);
        phaser3.arriveAndDeregister();//parties减少一个
        System.out.println("getRegisteredParties1: " + phaser3.getRegisteredParties());//3

        /**-------------------------------------------------------------------------------------------------*/

        final Phaser phaser2 = new Phaser(5);
        for (int i = 1; i <= 3; i++) {
            new Task(phaser2).start();
        }
        TimeUnit.SECONDS.sleep(2);
        //getArrivedParties()已被使用partis
        //getUnarrivedParties()未被使用partis
        System.out.println("getArrivedParties：" + phaser2.getArrivedParties());  //3
        System.out.println("getUnarrivedParties：" + phaser2.getUnarrivedParties());//2
    }

    static class Task extends Thread {
        private final Phaser phaser;
        Task(Phaser phaser) {
            this.phaser = phaser;
        }
        @Override
        public void run() {
            phaser.arriveAndAwaitAdvance();
        }
    }
}
