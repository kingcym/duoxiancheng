package com.example.demo.three.untils.executorService;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @Author: CYM
 * @Description: Executors.newCachedThreadPool();
 * @Data: 2017/10/18 16:14
 */
public class ExecutorsExample1 {


    public static void main(String[] args) throws InterruptedException {
        //       cachedThreadpool(); //Executors.newCachedThreadPool();
             fixedSizePool(); //Executors.newFixedThreadPool(10);
        //singleThreadExecutor(); //Executors.newSingleThreadExecutor();

    }

    /**
     * public static ExecutorService newCachedThreadPool() {
     * return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     * 60L, TimeUnit.SECONDS,
     * new SynchronousQueue<Runnable>());
     * }
     * <p>
     * 自动销毁。
     */
    private static void cachedThreadpool() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
        IntStream.range(0, 100).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "[" + i + "]");
            });
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }

    /**
     * public static ExecutorService newFixedThreadPool(int nThreads) {
     * return new ThreadPoolExecutor(nThreads, nThreads,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>());
     * }
     */

    private static void fixedSizePool() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        IntStream.range(0, 2).boxed().forEach(i -> {
            try {
                executorService.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "[" + i + "]");

                });
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("异常");
            }
        });
        executorService.shutdown();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------------");
    }

    /**
     * public static ExecutorService newSingleThreadExecutor() {
     *      return new Executors.FinalizableDelegatedExecutorService
     *                          (new ThreadPoolExecutor(1, 1,
     *                              0L, TimeUnit.MILLISECONDS,
     *                               new LinkedBlockingQueue<Runnable>()));
     * }
     */
    private static void singleThreadExecutor() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        IntStream.range(0, 30).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "[" + i + "]");
            });
        });
        executorService.shutdown();
    }




}
