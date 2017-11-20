package com.example.demo.three.untils.exchange;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/9 16:15
 */
/**
 * exchanger.exchange("i am from A",5,TimeUnit.SECONDS);
 * 如果5秒没有交换数据，报超时错误
 *
 * 只能两个线程之间交换
 */
public class ExchangerTest1 {
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"start.");
            try {
                final String value = exchanger.exchange("i am from A",5,TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName()+ value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"end.");
        },"==A==").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"start.");
            try {
                TimeUnit.SECONDS.sleep(10);
                final String value = exchanger.exchange("i am from B",5,TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName()+ value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"end.");
        },"==B==").start();
    }
}
