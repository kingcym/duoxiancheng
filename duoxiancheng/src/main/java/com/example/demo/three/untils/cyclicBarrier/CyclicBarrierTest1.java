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
 * new CyclicBarrier(3)
 * 三个线程到达 cyclicBarrier.await()后，同时进行
 * 下面程序
 */
public class CyclicBarrierTest1 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        new Thread(){
            @Override
            public void run() {
                System.out.println("==========1=================");
                try {
                    cyclicBarrier.await();
                    System.out.println("------------1---------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                System.out.println("==========2=================");
                try {
                    TimeUnit.SECONDS.sleep(5);
                    cyclicBarrier.await();
                    System.out.println("------------2---------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        cyclicBarrier.await();
        System.out.println("****************************************");
    }

}
