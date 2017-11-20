package com.example.demo.three.untils.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description: CyclicBarrier介绍
 * @Data: 2017/10/9 13:43
 */

/**
 * 输出结果：
     * 进入Thread
     * =======================
     * *********************************
     * await()完毕
     * 等待完毕
 *
 * 1.先执行System.out.println("进入Thread")，执行到cyclicBarrier.await()时;
 * 2.回调，执行System.out.println("======================="),执行完回调中的方法
 * 3.继续执行cyclicBarrier.await()下面的程序
 */
public class CyclicBarrierTest2 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("=======================");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("********************************");
            }
        });
        new Thread(){
            @Override
            public void run() {
                System.out.println("进入Thread");
                try {
                    TimeUnit.SECONDS.sleep(5);
                    cyclicBarrier.await();
                    System.out.println("await()完毕");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("等待完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        TimeUnit.SECONDS.sleep(5);
        System.err.println(cyclicBarrier.getNumberWaiting());
        System.err.println(cyclicBarrier.getParties());
        System.err.println(cyclicBarrier.isBroken());
    }
}
