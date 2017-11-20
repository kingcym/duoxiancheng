package com.example.demo.three.untils.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/30 14:23
 */

/**
 * runAsync(Runnable runnable)
 * runAsync(Runnable runnable, Executor executor)
 * supplyAsync(Supplier<U> supplier)
 * supplyAsync(Supplier<U> supplier, Executor executor)
 */
public class CompletableFutureTest3 {
    public static void main(String[] args) throws InterruptedException {
        //            supplyasyhc();
        //runAsync();
        // supplyasyhcANDrunafterboth();
        //  TimeUnit.SECONDS.sleep(20);

        completedFuture();
        //anyof();
        allof();
    }

    private static void allof() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(CompletableFuture.runAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println(Thread.currentThread().getName() + ": 1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).whenComplete((T, V) -> System.out.println(Thread.currentThread().getName() + "完成后执行 1")),
                CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println(Thread.currentThread().getName() + ": 2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "ssssssssssssss";
                }).whenComplete((T, V) -> System.out.println(Thread.currentThread().getName() + "完成后执行 2"))
        );
        voidCompletableFuture.join();
    }

    private static void anyof() {
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(CompletableFuture.runAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println(Thread.currentThread().getName() + ": 1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).whenComplete((T, V) -> System.out.println(Thread.currentThread().getName() + "完成后执行 1")),
                CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println(Thread.currentThread().getName() + ": 2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "ssssssssssssss";
                }).whenComplete((T, V) -> System.out.println(Thread.currentThread().getName() + "完成后执行 2"))
        );
        try {
            final Object o = objectCompletableFuture.get();
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void completedFuture() {
        CompletableFuture<Void> sss = CompletableFuture.completedFuture("sss").thenAcceptAsync(s -> {
            System.out.println(s);
        });
        sss.join();
    }

    private static void runAsync() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + ": runAsync");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete((T, V) -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "完成后执行，这个。。。。。");
        });
        voidCompletableFuture.join();
    }


    /**
     * supplyAsync(Supplier<U> supplier):返回一个新的,在ForkJoinPool.commonPool()中运行的任务异步完成,带返回值的CompletableFuture (CompletableFuture<U>)。
     * thenAcceptAsync(Consumer<? super T> action):返回一个新的CompletableFuture<Void>，将上一阶段的结果作为参数传入。
     * <p>
     * 打印结果：
     * ForkJoinPool.commonPool-worker-1: supplyAsync====java.lang.Object@49c2adca
     * ForkJoinPool.commonPool-worker-1:Object=====start
     * ForkJoinPool.commonPool-worker-1:Object=====end  java.lang.Object@49c2adca
     */
    private static void supplyasyhc() {
        CompletableFuture.supplyAsync(() -> {
            Object o = null;
            try {
                TimeUnit.SECONDS.sleep(5);
                o = new Object();
                System.out.println(Thread.currentThread().getName() + ":supplyAsync====" + o);
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return o;
        }).thenAcceptAsync(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":Object=====start");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName() + ":Object=====end  " + o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }






































    /*===========================================================================*/

    /**
     * ForkJoinPool.commonPool-worker-1:即将返回Objectjava.lang.Object@2641babe
     * ForkJoinPool.commonPool-worker-2即将返回hello
     * ForkJoinPool.commonPool-worker-2:String=====start
     * ForkJoinPool.commonPool-worker-1:Object=====start
     * ForkJoinPool.commonPool-worker-2:String=====end  hello
     * ForkJoinPool.commonPool-worker-1:Object=====end  java.lang.Object@2641babe
     * ======================================================
     */
    private static void supplyasyhcANDrunafterboth() {
        CompletableFuture.supplyAsync(() -> {
            Object o = null;
            try {
                o = new Object();
                System.out.println(Thread.currentThread().getName() + ":即将返回Object" + o);
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return o;
        }).thenAcceptAsync(o -> {
            try {
                System.out.println(Thread.currentThread().getName() + ":Object=====start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + ":Object=====end  " + o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "即将返回hello");
            return "hello";
        }).thenAcceptAsync(new Consumer<String>() {
            @Override
            public void accept(String o) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":String=====start");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + ":String=====end  " + o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }), () -> System.out.println("======================================================"));

    }


}
