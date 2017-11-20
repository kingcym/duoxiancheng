package com.example.demo.security;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/27 17:48
 */
//@Component
public class ScheduledTest {
    @Scheduled(fixedDelay = 2000)
    public void fixedDelayJob() {
        System.out.println("======1======" +"线程明" +Thread.currentThread().getName());
    }

    @Scheduled(fixedDelay = 3000)
    public void fixedDelayJob2() {
        System.out.println("======2======" +"线程明" +Thread.currentThread().getName());
    }


    @Scheduled(fixedDelay = 4000)
    public void fixedDelayJob3() {
        System.out.println("======3======" +"线程明" +Thread.currentThread().getName());
    }
}
