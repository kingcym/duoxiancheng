package com.example.demo.three.untils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/14 21:01
 */
public class Test4 {

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser(4);
        for (int i = 0; i <= 3; i++) {
            new Task(phaser).start();
        }
        final int phase = phaser.getPhase();
        /**awaitAdvance(phase):如果当前传入参数phase和getPhase()方法
         * 返回的值一样。则在屏障处等待，直到两者值不一样。不可被打中断
         *
         * 分析：传入参数phase=0,phaser.awaitAdvance(0)会一直等待，当第一次所有线程
         * 执行到phaser.arriveAndAwaitAdvance()，这时phaser.getPhase()为1，phaser.awaitAdvance(0)
         * 放行。
         *
         * 输出结果： *****1****1
                     *****1****1
                     *****1****1
                     *****1****1
                     *********0
                     *****2****2
                     *****2****2
                     *****2****2
                     *****2****2
         */
        phaser.awaitAdvance(phase);
        System.out.println("*********" + phase);

        /**----------------------------------------*/
        /**
         * awaitAdvance(int phase) 并不参与parties计数
         *
         * 结果：从输出结果可以看到：phaser1.getArrivedParties()为0
         */
        final Phaser phaser1 = new Phaser(4);
        new Thread(()->phaser1.awaitAdvance(0)).start();
        TimeUnit.SECONDS.sleep(20);
        System.err.println(phaser1.getArrivedParties());

        /**
         * awaitAdvanceInterruptibly(int phase)可被中断
         *
         * awaitAdvanceInterruptibly(int phase,long timeout, TimeUnit unit) 最多等待指定时间，可中断
         */
    }

    static class Task extends Thread {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                phaser.arriveAndAwaitAdvance();
                System.out.println("*****1****"+phaser.getPhase());

                TimeUnit.SECONDS.sleep(1);
                phaser.arriveAndAwaitAdvance();
                System.out.println("*****2****"+phaser.getPhase());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
