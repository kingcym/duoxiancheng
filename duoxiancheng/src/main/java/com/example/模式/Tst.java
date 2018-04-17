package com.example.模式;

import java.util.HashMap;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2018/1/30 11:11
 */
public class Tst {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap();
        long l = System.currentTimeMillis();
        for (int i = 0 ;i<999999;i++){
            map.put(i, "ss");

        }
        System.out.println(System.currentTimeMillis() - l);

    }
}
