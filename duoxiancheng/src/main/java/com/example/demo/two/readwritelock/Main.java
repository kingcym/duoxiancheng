package com.example.demo.two.readwritelock;

/**
 * Created by King on 2017/9/19.
 */
public class Main {
    public static void main(String[] args) {
        ShareDate shareDate = new ShareDate(10);
        ReadWork readWork1 = new ReadWork(shareDate);
        ReadWork readWork2 = new ReadWork(shareDate);
        readWork1.start();
        readWork2.start();
    }
}
