package com.example.demo.three.automic.automicinteger;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by King
 * on 2017/9/28.
 */
public class MyAutomicInteger {
    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());

    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger value = new AtomicInteger();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int tep = value.getAndIncrement();
                    set.add(tep);
                    System.out.println(Thread.currentThread().getName() + ": " + tep);
                    x++;
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int tep = value.getAndIncrement();
                    set.add(tep);
                    System.out.println(Thread.currentThread().getName() + ": " + tep);
                    x++;
                }
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(set.size());
    }
}
