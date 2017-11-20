package com.example.demo.two.future;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.function.Consumer;

/**
 * Created by King on 2017/9/20.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();
        futureService.submit(new FutureTask<String>() {
            @Override
            public String call() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "FINISH";
            }
        },System.out::print);

        System.out.println("可以做其他的事情了。。。。。");

    }
}
