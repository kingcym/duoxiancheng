package com.example.demo.shujujiegou.ch6递归的应用;

/**
 * Created by hasee
 * on 2017/11/21.
 */
public class Rescursion {
    public static void main(String[] args) {
       // m(9);
        long l = System.currentTimeMillis();
        int f = f(45);
        long s = System.currentTimeMillis();
        System.out.println(f + "   :    "  + (s-l));
    }


    /**
     * 递归九九乘法表
     * @param i
     */
    public static void m(int i) {
        if (i == 1) {
            System.out.println("1*1=1 ");
        } else {
            m(i - 1);
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + j * i + " ");
            }
            System.out.println();

        }
    }

    /**
     * 递归方法（当i比较大时，效率太低）
     * 计算： 1，1，2，3，5........
     * @param i
     */
    public static int f(int i) {
        if (i <= 2){
            return 1;
        } else {
            int f = f(i - 1);
            int f1 = f(i - 2);
            return f + f1;
        }
    }
}
