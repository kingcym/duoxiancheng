package com.example.demo.three.untils.executorService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/24 17:14
 */
public class InvokeAnyTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<String>> list = new ArrayList<>();
        list.add(new MycallableA());
        list.add(new MycallableB());

        try {
            List<Future<String>> futures = executorService.invokeAll(list,2,TimeUnit.SECONDS);
            for (Future<String> future : futures) {
               System.out.println(future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("===Main异常==");
        }

        executorService.shutdown();
    }


    static class MycallableA implements Callable<String> {
        @Override
        public String call() throws Exception {
            for (int j = 0; j < ThreadLocalRandom.current().nextInt(9); j++) {
                Math.random();
                Math.random();
                Math.random();
                Math.random();
            }

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return "a";
        }
    }

    static class MycallableB implements Callable<String> {
        @Override
        public String call() throws Exception {
            for (int j = 0; j < ThreadLocalRandom.current().nextInt(999999990); j++) {
                Math.random();
                Math.random();
                Math.random();
                Math.random();
            }

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
 //               e.printStackTrace();
            }

            return "b";
        }
    }
}
