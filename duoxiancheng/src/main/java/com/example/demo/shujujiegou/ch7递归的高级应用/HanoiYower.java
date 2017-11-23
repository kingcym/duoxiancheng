package com.example.demo.shujujiegou.ch7递归的高级应用;

/**
 * Created by hasee on 2017/11/22.
 */
public class HanoiYower {
    public static void main(String[] args) {
        doTower(5,'A','B','C');
    }

    private static void doTower(int topN, char a, char b, char c) {
        if (topN == 1){
            System.out.println("盘子1，从" + a + "到" + c);
        } else{
            doTower(topN-1,a,c,b);
            System.out.println("盘子"+topN+"，从" + a + "到" + c);
            doTower(topN-1,b,a,c);
        }


    }


}
