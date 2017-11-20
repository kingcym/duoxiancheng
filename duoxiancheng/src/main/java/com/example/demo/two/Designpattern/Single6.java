package com.example.demo.two.Designpattern;

/**
 * Created by King on 2017/9/13.
 */
public class Single6 {
    private Single6(){}  //将构造方法私有

    private static class instanceHolder{
        private static final Single6 instance = new Single6();
    }

    public  static Single6 getInstance(){
        return instanceHolder.instance;
    }


    public static void main(String[] args) {
        Single6 instance = Single6.getInstance();
        Single6 instance1 = Single6.getInstance();
        System.out.println(instance1);
        System.out.println(instance==instance1);
    }
}
