package com.example.demo.three.untils.forkjoin;


import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/12 23:08
 */
public class ForkJoinRecursiveTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long l1 = System.currentTimeMillis();
        System.out.println(IntStream.rangeClosed(0, 100000).sum());
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);

        long l3 = System.currentTimeMillis();
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> future = forkJoinPool.submit(new myRecursiveTask(0, 100000));
        Integer result = future.get();  //阻塞，直到获取值.
        long l4 = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(l4 - l3);
    }

    static class myRecursiveTask extends RecursiveTask<Integer> {
        private final long MID = 10000;
        private final int start;
        private final int end;

        myRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= MID) {
                return IntStream.rangeClosed(start, end).sum();
            } else {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int middle = Math.round((start + end) / 2);
                myRecursiveTask left = new myRecursiveTask(start, middle);
                myRecursiveTask right = new myRecursiveTask(middle + 1, end);
                left.fork();
                right.fork();
                return left.join() + right.join();
            }
        }
    }
}
