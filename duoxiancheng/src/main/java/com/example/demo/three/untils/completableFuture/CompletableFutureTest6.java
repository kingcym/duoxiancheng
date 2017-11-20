package com.example.demo.three.untils.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/11/1 16:53
 */
public class CompletableFutureTest6 {
    public static void main(String[] args) {
        //getNow();
        //complete();
        completeExceptionally();

    }
    private static void completeExceptionally() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync----begin-------"+ System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync-----end------"+ System.currentTimeMillis());
            return "hello";
        });
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //若完成，返回false,get()时获取返回值; 若未完成，返回true,get()时抛出指定异常
            boolean complete = stringCompletableFuture.completeExceptionally(new RuntimeException("超时了"));
            System.out.println(complete);
            System.out.println(Thread.currentThread().getName() + "  get值：" + stringCompletableFuture.get() + " 时间： " + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("================completeExceptionally==================");
        }
    }

    private static void complete() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync----begin-------"+ System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync-----end------"+ System.currentTimeMillis());
            return "hello";
        });
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //若完成，返回false,get()时获取返回值; 若未完成，返回true,get()时获取指定值
        boolean complete = stringCompletableFuture.complete("WWWWW");
        System.out.println(complete);
        try {
            System.out.println(Thread.currentThread().getName() + "  get值：" + stringCompletableFuture.get() + " 时间： " + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getNow() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync----begin-------"+ System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync-----end------"+ System.currentTimeMillis());
            return "hello";
        });
        //执行完了 获取返回的值，如果没有执行完毕，返回指定值
        String now = stringCompletableFuture.getNow("i am getNow value");
        System.out.println(Thread.currentThread().getName() + "  getNow值：" + now + " 时间： " + System.currentTimeMillis());
        try {
            System.out.println(Thread.currentThread().getName() + "  get值：" + stringCompletableFuture.get() + " 时间： " + System.currentTimeMillis());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
