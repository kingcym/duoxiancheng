package com.example.demo.three.automic.automicboolean;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by King on 2017/9/29.
 */
public class MyAutomicBoolean {
    public static void main(String[] args) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        System.out.println(atomicBoolean);
        final AtomicBoolean atomicBoolean2 = new AtomicBoolean(true);
        System.out.println(atomicBoolean2);

         AtomicBoolean atomicBoolean3 = new AtomicBoolean(true);
         boolean andSet1 = atomicBoolean3.getAndSet(false);
         boolean andSet = andSet1;
        System.out.println(andSet);
        System.out.println(atomicBoolean3);
    }
}
