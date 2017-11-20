package com.example.demo.one.base4;

/**
 * Created by King on 2017/9/7.
 */

/**
 * join 阻塞进程直到线程执行完毕
 * join()：如下面的例子，当t和t1全都执行完，才执行下面的for循环
 *      t和t1相互不受影响。
 * join(long millis)：经过millis毫秒后，就算t,t1没有执行完，也执行下面代码。
 * join(long millis, int nanos)：同上。
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            for (int i = 0;i<1000;i++){

                System.out.println(Thread.currentThread().getName()+"->"+i);
            }
        });

        Thread t1 = new Thread(()->{
            for (int i = 0;i<1000;i++){
                System.out.println(Thread.currentThread().getName()+"->"+i);
            }
        });
        t.start();
        t1.start();
        t.join();
        t1.join();
        for (int i = 0;i<1000;i++){
            System.out.println(Thread.currentThread().getName()+"->"+i);
        }
    }
}
