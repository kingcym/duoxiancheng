package com.example.demo.two.Designpattern;

/**
 * Created by King on 2017/9/12.
 */
/**
 * 懒汉式
 * 在上诉基础上加一个volatile(多个线程间可见性)
 */
public class Single5 {

    private  static volatile Single5 instance;

    private Single5(){}  //将构造方法私有
    public  static Single5 getInstance(){
        if (null == instance){
            synchronized (Single5.class){
                if (null == instance){

                    instance = new Single5();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Single5 instance = Single5.getInstance();
        Single5 instance1 = Single5.getInstance();
        System.out.println(instance1);
        System.out.println(instance==instance1);
    }





}
