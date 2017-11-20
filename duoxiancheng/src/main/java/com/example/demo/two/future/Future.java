package com.example.demo.two.future;

/**
 * Created by King on 2017/9/20.
 */
public interface Future<T> {
    T get() throws InterruptedException;

}
