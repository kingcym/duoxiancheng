package com.example.demo.one.base;

/**
 *线程启动和生命周期
 *Java应用程序的main方法是一个线程，是被JVM调用，线程名为main。
 *实现一个线程必须创建Thred实例，复写run()方法，调用start()方法。
 *在JVM启动后，实际有多个线程，但至少有一个非守护线程。
 *当调用一个线程start()方法时，至少有两个线程，（以main方法为例）一个是调用你的线程（mian线程）
 *一个是执行run()方法法人线程（被创建的线程）。
 *线程生命周期分为new,runable,runing,block,termate
 *
 /**
 * 调用start方法，执行了run方法
 * 实际用的是模板方法
 */
public class TryConcurrency {

    public static void main(String[] args) {
       new Thread("线程1"){
            @Override
            public void run() {
                readFromDataBase();
            }
        }.start();

        new Thread("线程2"){
            @Override
            public void run() {
                writeDataToFile();
            }
        }.start();



//
//        writeDataToFile();

    }

    private static void readFromDataBase() {
        //read data from database and handle it.
        try {
            println("Begin read data from db.1");
            Thread.sleep(1000 * 30L);
            println("Read data done and start handle it.2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("The data handle finish and successfully.3");
    }

    private static void writeDataToFile() {
        try {
            println("Begin write data to file.4");
            Thread.sleep(2000 * 20L);
            println("Write data done and start handle it.5");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("The data handle finish and successfully.6");
    }

    private static void println(String message) {
        System.out.println(message);
    }
}
