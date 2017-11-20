package com.example.demo.three.untils.completableFuture;

import java.util.concurrent.*;
import java.util.function.BiConsumer;


/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/29 12:46
 */

/**
 * ForkJoinPool.commonPool()请参考
 * https://segmentfault.com/a/1190000008470012
 */
public class CompletableFutureTest1 {
    public static void main(String[] args) {
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


}
