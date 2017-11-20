package com.example.demo.two.obsever_moshi;

/**
 * Created by King on 2017/9/17.
 */
public class main {
    public static void main(String[] args) {
        final Subject subject = new Subject();
        new BinaryObserver(subject);
        subject.setState(8);
        subject.setState(89);
    }
}
