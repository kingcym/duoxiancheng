package com.example.demo.three.untils.completableFuture;


import java.util.concurrent.*;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/29 22:32
 */
public class CompletableFutureTest2 {
    public static void main(String[] args) {
        try {
//            ExecutorService executorService = Executors.newFixedThreadPool(10);
            System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");

            for (int i = 0; i < 10; i++) {
                CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> get());
                final CompletableFuture<Void> voidCompletableFuture = integerCompletableFuture.thenAccept((T) -> display());
                voidCompletableFuture.whenComplete((v,t)->{
                    System.out.println("==DONE===");
                });
            }

            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int display() {
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.err.println(Thread.currentThread().getName() + "display 将要休眠：" + value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println(Thread.currentThread().getName() + "display 将要done：" + value);
        return value;
    }

    private static int get() {
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + "get 将要休眠：" + value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "get 将要done：" + value);
        return value;
    }
}
