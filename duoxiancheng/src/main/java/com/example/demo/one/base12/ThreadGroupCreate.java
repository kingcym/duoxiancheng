package com.example.demo.one.base12;

/**
 * Created by King on 2017/9/9.
 */

/**
 * 线程组,可以批量管理线程（不用线程池情况下）
    构造方法：ThreadGroup(String name)//线程组取名
 *           ThreadGroup(ThreadGroup parent, String name)//线程组取名并且指定父类线程组
 */
public class ThreadGroupCreate {
    public static void main(String[] args) {
        ThreadGroup tg1 = new ThreadGroup("tg1");
        Thread t = new Thread(tg1,() -> {
            while (true) {
                try {
                    Thread.sleep(3_000);
                    System.out.println(Thread.currentThread().getThreadGroup().getName());
                    System.out.println(Thread.currentThread().getThreadGroup().getParent());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        ThreadGroup tg2 = new ThreadGroup(tg1, "tg2");
        System.out.println("===="+tg2.getName());
        System.out.println("---"+tg2.getParent());
    }


}
