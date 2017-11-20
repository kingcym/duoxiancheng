package com.example.demo.three.untils.executorService;

import java.util.concurrent.*;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/23 15:02
 */
public class RejectedExecutionHandler4nums {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 1; i <= 3; i++) {
                executorService.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"-------------");
                });

        }

        TimeUnit.SECONDS.sleep(1);

        executorService.execute(() -> {

            System.out.println(Thread.currentThread().getName()+"-------11------");
        });

        executorService.shutdown();

        executorService.execute(() -> {

            System.out.println(Thread.currentThread().getName()+"-------22------");
        });

    }
    static class mysss implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("***"+r+"****");
        }
    }
}
