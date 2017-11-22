package com.example.demo.shujujiegou.ch6递归的应用;

/**
 * Created by hasee on 2017/11/21.
 */
public class Rescursion {
    public static void main(String[] args) {
        test(5);

    }

    private static void test(int i) {
        System.out.println(i);
        if (i == 0){
            return;
        }
        test(i-1);
        System.out.println("==="+i);
    }
}
