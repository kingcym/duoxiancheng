package com.example.demo.two.guarded_suspension;


/**
 * Created by King on 2017/9/21.
 */
public class Main {
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue,"cai").start();
        new ServiceThread(requestQueue).start();
    }
}
