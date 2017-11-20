package com.example.demo.two.obsever_moshi;

/**
 * Created by King on 2017/9/17.
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
       super(subject);
    }

    @Override
    public void update() {
        System.out.println("BinaryObserver: "+ Integer.toBinaryString(subject.getState()));
    }
}
