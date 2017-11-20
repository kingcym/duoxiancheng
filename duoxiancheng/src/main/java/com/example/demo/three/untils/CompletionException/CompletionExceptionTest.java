package com.example.demo.three.untils.CompletionException;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/27 14:12
 */
public class CompletionExceptionTest {

    public static void main(String[] args) {
        final Date date = new Date();

        scheduleTest2();
    }

    private static void scheduleTest1() {
        ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(2);
        ScheduledFuture<?> future = scheduledExecutorService.schedule(() -> {
            System.out.println("------------");
        }, 2, TimeUnit.SECONDS);

        System.out.println("=====");
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        scheduledExecutorService.shutdown();
    }

    private static void scheduleTest2() {
        ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(2);
        scheduledExecutorService.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("------------");
        }, 0,4, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();
    }
}
