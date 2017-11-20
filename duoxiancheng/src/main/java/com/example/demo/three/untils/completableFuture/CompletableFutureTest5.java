package com.example.demo.three.untils.completableFuture;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.function.Function;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/31 23:12
 */
public class CompletableFutureTest5 {
    public static void main(String[] args) {
        //thenAcceptBoth();
        thenAcceptBothAsync();    //同时执行两个线程，获取两个返回结果作为参数，进入另一个线程，无返回值

        //acceptEither();
        acceptEitherAsync();      //获取快的线程的结果(两个线程返回类型需一致)，作为参数，进入另一个线程，无返回值

        runAfterBoth();
        //runAfterBothAsync();      //两个线程执行完毕后,不关心结果,进入另一个线程，无返回值

        //runAfterEither();
        runAfterEitherAsync();      //有线程结束后,不关心结果,进入另一个线程，无返回值

        //thenCombine();
        thenCombineAsync();       //同时执行两个线程，获取两个返回结果作为参数，进入另一个线程,有返回值

        //thenCompose();
        thenComposeAsync();        //(Function<? super T, ? extends CompletionStage<U>> fn)

    }

    private static void thenComposeAsync() {
        CompletableFuture<Integer> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------开始---");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------结束---");
            return "hello1";
        }).thenComposeAsync(t -> {
            CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------开始---");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------结束---");
                return 100;
            });
            return integerCompletableFuture;
        });

        try {
            System.out.println("=----------------------------=");
            System.out.println("---------------------------" + voidCompletableFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void thenCombineAsync() {
        CompletableFuture<String> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------开始---");
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------结束---");
            return "hello1";
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------开始---");
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------结束---");
            return 100;
        }), (t, u) -> {
            System.out.println(Thread.currentThread().getName() + "........." + t);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "........." + u);
            return "=thenCombine=";
        });

        try {
            System.out.println("---------------------------" + voidCompletableFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void runAfterEitherAsync() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------开始---");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------结束---");
            return "hello1";
        }).runAfterEitherAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------开始---");
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------结束---");
            return "hello2".length();
        }), () -> {
            System.out.println(Thread.currentThread().getName() + ".........");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ".........");
        });

        System.out.println("---------------------------");
        voidCompletableFuture.join();
    }

    /**
     * ForkJoinPool.commonPool-worker-1---------supplyAsync1---------开始---
     * ForkJoinPool.commonPool-worker-1---------supplyAsync1---------结束---
     * ForkJoinPool.commonPool-worker-1---------supplyAsync2---------开始---
     * ForkJoinPool.commonPool-worker-1---------supplyAsync2---------结束---
     * ---------------------------
     */
    private static void runAfterBoth() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------开始---");
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------结束---");
            return "hello1";
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------开始---");
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------结束---");
            return "hello2".length();
        }), () -> {
            System.out.println(Thread.currentThread().getName() + ".........");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ".........");
        });

        System.out.println("---------------------------");
        voidCompletableFuture.join();
    }


    /**
     * ForkJoinPool.commonPool-worker-1---------supplyAsync1---------开始---
     * ForkJoinPool.commonPool-worker-1---------supplyAsync1---------结束---
     * ForkJoinPool.commonPool-worker-1---------supplyAsync2---------开始---
     * ForkJoinPool.commonPool-worker-1---------supplyAsync2---------结束---
     * ---------------------------
     * ForkJoinPool.commonPool-worker-1.........hello1
     */
    private static void acceptEitherAsync() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------开始---");
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------结束---");
            return "hello1";
        }).acceptEitherAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------开始---");
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------结束---");
            return "hello2";
        }), t -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "........." + t);
        });

        System.out.println("---------------------------");
        voidCompletableFuture.join();
    }

    /**
     * ForkJoinPool.commonPool-worker-1---------supplyAsync1---------开始---
     * ForkJoinPool.commonPool-worker-2---------supplyAsync2---------开始---
     * ForkJoinPool.commonPool-worker-1---------supplyAsync1---------结束---
     * ForkJoinPool.commonPool-worker-2---------supplyAsync2---------结束---
     * main.........hello1
     * ---------------------------
     */
    private static void acceptEither() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------开始---");
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------结束---");
            return "hello1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------开始---");
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------结束---");
            return "hello2";
        }), t -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "........." + t);
        });

        System.out.println("---------------------------");
        voidCompletableFuture.join();
    }

    /**
     * ForkJoinPool.commonPool-worker-1---------supplyAsync1---------结束---
     * ForkJoinPool.commonPool-worker-1---------supplyAsync2---------开始---
     * ForkJoinPool.commonPool-worker-1---------supplyAsync2---------结束---
     * main.........hello---5
     * ---------------------------
     */

    private static void thenAcceptBoth() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------开始---");
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------结束---");
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------开始---");
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------结束---");
            return "hello".length();
        }), (t, v) -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "........." + t + "---" + v);
        });

        System.out.println("---------------------------");
        voidCompletableFuture.join();
    }

    /**
     * ForkJoinPool.commonPool-worker-1---------supplyAsync1---------开始---
     * ForkJoinPool.commonPool-worker-1---------supplyAsync1---------结束---
     * ForkJoinPool.commonPool-worker-1---------supplyAsync2---------开始---
     * ForkJoinPool.commonPool-worker-1---------supplyAsync2---------结束---
     * ---------------------------
     * ForkJoinPool.commonPool-worker-2.........hello---5
     */

    private static void thenAcceptBothAsync() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------开始---");
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync1---------结束---");
            return "hello";
        }).thenAcceptBothAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------开始---");
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync2---------结束---");
            return "hello".length();
        }), (t, v) -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "........." + t + "---" + v);
        });

        System.out.println("---------------------------");
        voidCompletableFuture.join();
    }
}
