package com.example.demo.three.automic.atomicArray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by King on 2017/10/4.
 */
public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        final AtomicIntegerArray array = new AtomicIntegerArray(10);
        System.out.println(array.length());
        System.out.println(array.get(9));
        array.set(1,11);

    }
}
