package com.example.demo.three.untils.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description: CyclicBarrier介绍
 * @Data: 2017/10/9 13:43
 * <p>
 * CyclicBarrier和CountDownLatch区别
 * <p>
 * 1.CountDownLatch不能reset,CyclicBarrier是可以循环使用
 * 2.CountDownLatch工程线程之间互不关心,CyclicBarrier工程线程必须等到一个共同点才去执行某一动作
 * @Author: CYM
 * @Description: CyclicBarrier介绍
 * @Data: 2017/10/9 13:43
 * <p>
 * CyclicBarrier和CountDownLatch区别
 * <p>
 * 1.CountDownLatch不能reset,CyclicBarrier是可以循环使用
 * 2.CountDownLatch工程线程之间互不关心,CyclicBarrier工程线程必须等到一个共同点才去执行某一动作
 * @Author: CYM
 * @Description: CyclicBarrier介绍
 * @Data: 2017/10/9 13:43
 */

/**
 * CyclicBarrier和CountDownLatch区别
 *
 * 1.CountDownLatch不能reset,CyclicBarrier是可以循环使用
 * 2.CountDownLatch工程线程之间互不关心,CyclicBarrier工程线程必须等到一个共同点才去执行某一动作
 *
 *
 */


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
public class CyclicBarrierTest3 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        for (int i = 0; i < 3; i++) {
            new Task(i + "").start();
        }
    }

    static class Task extends Thread {
        public Task(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "------------1---------------");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "------------2---------------");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "------------3---------------");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}

