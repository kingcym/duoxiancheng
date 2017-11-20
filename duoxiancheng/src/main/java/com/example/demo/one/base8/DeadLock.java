package com.example.demo.one.base8;


public class DeadLock {
    private final Object dlock = new Object();
    private OtherService otherService;

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    public void m1(){
        synchronized (dlock){
            System.out.println("m1");
            otherService.s1();
        }
    }


    public void m2(){
        synchronized (dlock){
            System.out.println("m2");

        }
    }
}
