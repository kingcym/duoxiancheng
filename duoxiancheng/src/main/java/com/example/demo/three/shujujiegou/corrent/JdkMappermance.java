package com.example.demo.three.shujujiegou.corrent;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/21 21:08
 */
public class JdkMappermance {

    public static void main(String[] args) throws InterruptedException {
        // five();
        //one();
        Date d = new Date(1514735999000L);
       // 1514173529412
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
       System.out.println(sdf.format(d));
       System.out.println(System.currentTimeMillis());


    }

    private static void one() throws InterruptedException {
        pressureTest(new Hashtable<String, String>(), 1, false);
        pressureTest(new Hashtable<String, String>(), 1, true);
        System.out.println();
        System.out.println("--------------------------");
        System.out.println();

        pressureTest(Collections.synchronizedMap(new HashMap<String, String>()), 1, false);
        pressureTest(Collections.synchronizedMap(new HashMap<String, String>()), 1, true);
        System.out.println();
        System.out.println("--------------------------");
        System.out.println();


        pressureTest(new ConcurrentHashMap<String, String>(), 1, false);
        pressureTest(new ConcurrentHashMap<String, String>(), 1, true);
        System.out.println();
        System.out.println("--------------------------");
        System.out.println();

        pressureTest(new HashMap<String, String>(), 1, false);
        pressureTest(new HashMap<String, String>(), 1, true);
    }

    /**
     *这边加了一个循环，就是不断的尝试，
     * 因为在table的初始化和casTabAt用到了compareAndSwapInt、compareAndSwapObject
     * 因为如果其他线程正在修改tab，那么尝试就会失败，所以这边要加一个for循环，不断的尝试
     */
    private static void five() throws InterruptedException {
        pressureTest(new Hashtable<String, String>(), 5, false);
        pressureTest(new Hashtable<String, String>(), 5, true);
        System.out.println();
        System.out.println("--------------------------");
        System.out.println();

        pressureTest(Collections.synchronizedMap(new HashMap<String, String>()), 5, false);
        pressureTest(Collections.synchronizedMap(new HashMap<String, String>()), 5, true);
        System.out.println();
        System.out.println("--------------------------");
        System.out.println();


        pressureTest(new ConcurrentHashMap<String, String>(), 5, false);
        pressureTest(new ConcurrentHashMap<String, String>(), 5, true);
        System.out.println();
        System.out.println("--------------------------");
        System.out.println();
    }


    private static void pressureTest(final Map<String, String> map, int threshold, boolean retrieve) throws InterruptedException {
        System.out.println("开始map压力测试" + map.getClass() + "启动线程数" + threshold + "retrieve " + retrieve);
        long totalTime = 0L;
        final int MAX = 500000;
        for (int i = 0; i < 5; i++) {
            long startTime = System.nanoTime();
            ExecutorService executorService = Executors.newFixedThreadPool(threshold);
            for (int j = 0; j < threshold; j++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < MAX; i++) {
                           // int ceil = (int) Math.ceil(Math.random() * 6000000);
                            String uuid = UUID.randomUUID().toString().replace("-", "");
                            if (retrieve) {
                                map.get(uuid);
                            }
                            map.put(uuid, uuid);
                        }
                    }
                });
            }

            executorService.shutdown();
            executorService.awaitTermination(2, TimeUnit.HOURS);
            long endTime = System.nanoTime();
            long period = (endTime - startTime) / 1000000L;
            if (retrieve) {
                System.out.println(map.size() + "--读写耗时-- " + period + "ms");
            } else {
                System.out.println(map.size() + "写耗时 " + period + "ms");
            }
            totalTime += period;
        }

        System.out.println("map测试" + map.getClass() + "平均耗时" + (totalTime / 5) + "ms");
    }
}
