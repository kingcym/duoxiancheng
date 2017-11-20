package com.example.demo.two.Designpattern;

/**
 * Created by King on 2017/9/12.
 */
/**
 * 懒汉式
 * 解决多线程不安全
 * 可能存在空指针异常
 * （原因：如果线程1 new Single3()了。这时instance ！=null了
 * 这时构造方法没有执行完，第二个线程就进来了，instance ！=null
 * 返回 instance,如果需要构造方法一些属性时，可能存在空指针异常。
 * ）
 */
public class Single3 {

    private static Single3 instance;
    public int sss=1;

    private Single3(){
        try {
            System.out.println(Thread.currentThread().getName()+"进入构造方法");
            Thread.sleep(10000);
            this.sss=10;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  //将构造方法私有
    public  static Single3 getInstance(){
        if (null == instance){
            System.out.println(Thread.currentThread().getName()+"进入"+instance);
            synchronized (Single3.class){
                if (null == instance){
                    instance = new Single3();
                    System.out.println(Thread.currentThread().getName()+"将要离开锁");
                }
            }
        }
        System.out.println(Thread.currentThread().getName()+"返回instance");
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
                Single3 instance = Single3.getInstance();
                System.out.println(instance);
                System.out.println(instance.sss);
            },"t1").start();

         Thread.sleep(5000);
            new Thread(() -> {
                Single3 instance = Single3.getInstance();
                System.out.println(instance);
                System.out.println(instance.sss);
            },"t2").start();

    }


}
