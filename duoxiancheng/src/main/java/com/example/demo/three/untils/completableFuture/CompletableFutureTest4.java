package com.example.demo.three.untils.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/30 22:11
 */
public class CompletableFutureTest4 {
    public static void main(String[] args) {
      //  whencomplete();
        whencompleteAsync();   //BiConsumer<T, U> 接收上个阶段的返回值T和异常U,return Void;返回上个阶段返回值CompletableFuture<T>
//        thenapply();
        thenapplyAsync();      //Function<T, R>  接收上个阶段的返回值T,return R ; 返回CompletableFuture<R>
//        handle();
        handleAsync();         //BiFunction<T, U, R> 接收上个阶段的返回值T和异常U,return R;返回CompletableFuture<R>
//        thenAccept();
        thenAcceptAsync();     //Consumer<T> 接收上个阶段的返回值T,return Void;返回CompletableFuture<Void>
//        thenRun();
       thenRunAsync();        // Runnable   不接受上个阶段返回值和异常,return Void;返回CompletableFuture<Void>




    }

    private static void thenRunAsync() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync---------开始---");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync---------结束---");
            return "hello";
        });
        CompletableFuture<Void> voidCompletableFuture = stringCompletableFuture.thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---thenRunAsync---start---");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---thenRunAsync----end---");
        });
        try {
            System.out.println("============================");
            System.out.println(stringCompletableFuture.get());
            System.out.println(voidCompletableFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void thenAcceptAsync() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync---------开始---");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync---------结束---");
            return "hello";
        });
        CompletableFuture<Void> voidCompletableFuture = stringCompletableFuture.thenAcceptAsync(s -> {
            System.out.println(Thread.currentThread().getName() + "---thenAcceptAsync---start---" + s);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---thenAcceptAsync----end---" + s);
        });
        try {
            System.out.println("============================");
            System.out.println(stringCompletableFuture.get());
            System.out.println(voidCompletableFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void handleAsync() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync---------开始---");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync---------结束---");
            return "hello";
        });
        CompletableFuture<Integer> stringCompletableFuture1 = stringCompletableFuture.handleAsync((s, v) -> {
            System.out.println(Thread.currentThread().getName() + "---handle---start---" + s);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---handle----end---" + s);
            return s == null ? 0 : s.length();
        });
        try {
            System.out.println("============================");
            System.out.println(stringCompletableFuture1.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ForkJoinPool.commonPool-worker-1---------supplyAsync---------开始---
     * ============================
     * ForkJoinPool.commonPool-worker-1---------supplyAsync---------结束---
     * ForkJoinPool.commonPool-worker-1---handle---start---hello
     * ForkJoinPool.commonPool-worker-1---handle----end---hello
     * 5
     */
    private static void handle() {
        CompletableFuture<Integer> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync---------开始---");

            System.out.println(Thread.currentThread().getName() + "---------supplyAsync---------结束---");
            return "hello";
        }).handle((s, v) -> {
            System.out.println(Thread.currentThread().getName() + "---handle---start---" + s);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---handle----end---" + s);
            return s == null ? 0 : s.length();
        });
        try {
            System.out.println("============================");
            System.out.println(stringCompletableFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ForkJoinPool.commonPool-worker-1---------supplyAsync---------开始---
     * hello
     * ForkJoinPool.commonPool-worker-1---thenApplyAsync---start---hello
     * ============================
     * ForkJoinPool.commonPool-worker-1---thenApplyAsync----end---hello
     * 5
     */
    private static void thenapplyAsync() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync---------开始---");
            return "hello";
        });
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CompletableFuture<Integer> stringCompletableFuture1 = stringCompletableFuture.thenApplyAsync(v -> {
            System.out.println(Thread.currentThread().getName() + "---thenApplyAsync---start---" + v);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---thenApplyAsync----end---" + v);
            return v.length();
        });
        try {
            System.out.println(stringCompletableFuture.get());
            System.out.println("============================");
            System.out.println(stringCompletableFuture1.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ForkJoinPool.commonPool-worker-1---------supplyAsync---------开始---
     * main---thenApply---start---hello
     * main---thenApply----end---hello
     * hello
     * ============================
     * 5
     */
    private static void thenapply() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync---------开始---");

            return "hello";
        });
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CompletableFuture<Integer> stringCompletableFuture1 = stringCompletableFuture.thenApply(v -> {
            System.out.println(Thread.currentThread().getName() + "---thenApply---start---" + v);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---thenApply----end---" + v);
            return v.length();
        });
        try {
            //       System.out.println(stringCompletableFuture.get());
            System.out.println("============================");
            System.out.println(stringCompletableFuture1.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void whencomplete() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {

            System.out.println(Thread.currentThread().getName() + "---------supplyAsync---------开始---");
            return "hello";
        });
        CompletableFuture<String> stringCompletableFuture1 = stringCompletableFuture.whenComplete((V, T) -> {
            System.out.println(Thread.currentThread().getName() + "---whenComplete---start---" + V);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---whenComplete----end---" + V);
        });
        try {
            System.out.println(stringCompletableFuture.get());
            System.out.println("============================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void whencompleteAsync() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---------supplyAsync---------开始---");
            return "hello";
        });
        CompletableFuture<String> stringCompletableFuture1 = stringCompletableFuture.whenCompleteAsync((V, T) -> {
            System.out.println(Thread.currentThread().getName() + "---whenCompleteAsync---start---" + V);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---whenCompleteAsync----end---" + V);
        });
        try {
            System.out.println(stringCompletableFuture.get());
            System.out.println("============================");
            stringCompletableFuture1.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
