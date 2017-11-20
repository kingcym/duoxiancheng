package com.example.demo.three.untils.executorService;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/25 22:51
 */
public class FutureTest1 {
    public static void main(String[] args) {
        futureTest1();
        //  futureTest3();
    }

    private static void futureTest1() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        Future<String> future = null;
        try {
            future = executorService.submit(() -> {
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println("=++++++=");
                }
                return "sss";
            });
        } catch (Exception e) {
            System.err.println("submit Exception" + e);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(future.cancel(true));
        System.out.println(future.isCancelled());

        executorService.shutdown();
    }

    private static void futureTest2() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println(System.currentTimeMillis());
        Future<String> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "sss";
        });
        System.out.println(future.isDone());
        executorService.shutdown();
    }

    private static void futureTest3() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> future = executorService.submit(() -> {
            try {
                System.out.println("======");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "sss";
        });

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(System.currentTimeMillis());
            System.out.println(future.get());
            System.out.println(System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("exception");
        }
        executorService.shutdown();
    }
}
