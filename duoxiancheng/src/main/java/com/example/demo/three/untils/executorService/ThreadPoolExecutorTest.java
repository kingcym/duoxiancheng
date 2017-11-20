package com.example.demo.three.untils.executorService;

import java.util.concurrent.*;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/20 11:01
 */
/**
 * ThreadPoolExecutor有四大构造方法
 * 重点看 七大构造参数的构造方法
 * int corePoolSize,(核心线程池大小,要保持在池中的线程数)
 *
 * int maximumPoolSize,(线程池中允许的最大线程数)
 *
 * long keepAliveTime,(线程池中超过corePoolSize数目的空闲线程最大存活时间)
 *
 * TimeUnit unit,(时间单位)
 *
 * BlockingQueue<Runnable> workQueue,(阻塞任务队列)
 *
 * ThreadFactory threadFactory,(新建线程工厂)
 *
 * RejectedExecutionHandler handler (当提交任务数超过maxmumPoolSize+workQueue之和时，任务会交给RejectedExecutionHandler来处理)
 * @throws IllegalArgumentException if one of the following holds:<br>
 * {@code corePoolSize < 0}<br>
 * {@code keepAliveTime < 0}<br>
 * {@code maximumPoolSize <= 0}<br>
 * {@code maximumPoolSize < corePoolSize}
 * @throws NullPointerException if {@code workQueue}
 * or {@code threadFactory} or {@code handler} is null
 */
public class ThreadPoolExecutorTest {
    private static final CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) buildPool();
//        countDownLatch.await();

        executorService.shutdown(); //任务执行完毕，关闭线程池。
        /**
         * 执行该方法，线程池的状态立刻变成STOP状态，并
         * 试图停止所有正在执行的线程，不再处理还在池队列中等待的任务，当然，它会返回那些未执行的任务。
         * 它试图终止线程的方法是通过调用Thread.interrupt()方法来实现的，
         * 如果线程中有sleep 、wait、Condition、定时锁等应用, interrupt()方法是会中断当前的线程（慎用）
         */

        executorService.awaitTermination(1,TimeUnit.HOURS);//等待结束,最多等待1个小时
        System.out.println("结束");
    }

    private static ExecutorService buildPool() {
        /**
         * 举例说明：何时启动最大线程数
         * corePoolSize为1，maximumPoolSize为2，workQueue.size()为1
         * I: 当启动2个线程时，corePoolSize启动一个，workQueue里面放一个，maximumPoolSize不启动
         * II: 当启动三个线程时，corePoolSize启动一个，workQueue里面放一个，这时阻塞队列已经满了
         *     这时maximumPoolSize启动了。
         */
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1),
                r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.AbortPolicy());

        System.out.println("线程池已创建");
        executorService.execute(() -> sleepSeconds(5));
        executorService.execute(() -> sleepSeconds(5));
        executorService.execute(() -> sleepSeconds(5));
        return executorService;
    }

    private static void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
            System.out.println("***" + Thread.currentThread().getName() + "***");
//            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
