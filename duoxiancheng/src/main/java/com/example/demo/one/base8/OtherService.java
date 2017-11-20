package com.example.demo.one.base8;


public class OtherService {

    private final Object olock = new Object();

    private DeadLock deadLock;


    public void s1(){
        synchronized (olock){
            System.out.println("s1");
        }
    }

    public void s2(){
        synchronized (olock){
            System.out.println("s2");
            deadLock.m2();
        }
    }



    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }
}
