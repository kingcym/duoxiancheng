package com.example.demo.three.untils.executorService;

import com.sun.glass.ui.MenuItem;
import javafx.util.Callback;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/18 18:00
 */
public class ExecutorsExample3 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        testInvokeAny();
//        testInvokeAll();

    }

    private static void testInvokeAny() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        List<Callable<Integer>> collect = IntStream.range(0, 10).boxed().map(i -> (Callable<Integer>) () -> {

//                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                for (int j = 0; j < ThreadLocalRandom.current().nextInt(9999999); j++) {
                    Math.random();
                    Math.random();
                    Math.random();
                    Math.random();
                    Math.random();
                }
                if (Thread.currentThread().isInterrupted()){
                    throw new InterruptedException("被诊断了");
                }
                System.out.println(Thread.currentThread().getName() + " : " + i);

            return i;
        }).collect(Collectors.toList());


        Integer integer = null;
        try {
            integer = executorService.invokeAny(collect,100, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("===============================================");
        }

        System.out.println("====" + integer);
        executorService.shutdown();
    }

    private static void testInvokeAll() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        List<Callable<Integer>> collect = IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
            System.out.println(Thread.currentThread().getName() + " : " + i);
            return i;
        }).collect(Collectors.toList());

        List<Future<Integer>> futures = executorService.invokeAll(collect);
        System.out.println("===========");
        for (Future<Integer> future : futures) {
            System.out.println("======" + future.get());
        }
        executorService.shutdown();
    }
}
