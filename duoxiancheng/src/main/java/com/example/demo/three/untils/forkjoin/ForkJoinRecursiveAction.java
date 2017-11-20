package com.example.demo.three.untils.forkjoin;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Author: CYM
 * @Description: RecursiveAction
 * @Data: 2017/10/12 23:08
 */
public class ForkJoinRecursiveAction {
    private static final AtomicInteger INTEGER = new AtomicInteger(0);//原子性

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(IntStream.rangeClosed(0, 100000).sum());//求和

        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Void> submit = forkJoinPool.submit(new myRecursiveAction(0, 100000));//异步执行

        System.out.println(INTEGER.get());

    }

    static class myRecursiveAction extends RecursiveAction {
        private final long MID = 10000;
        private final int start;
        private final int end;

        myRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= MID) {
                INTEGER.getAndAdd(IntStream.rangeClosed(start, end).sum());
            } else {
                try {
                    System.out.println("-------------");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int middle = Math.round((start + end) / 2);
                myRecursiveAction left = new myRecursiveAction(start, middle);
                myRecursiveAction right = new myRecursiveAction(middle + 1, end);
                left.fork();
                right.fork();
            }
        }
    }
}
