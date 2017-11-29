package com.example.demo.shujujiegou.ch哈希表;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/11/25 15:17
 */
public class Test {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");


    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        if (set.contains(1)){
            set.remove(1);
        }
        System.out.println(set);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 3;
        n |= n >>> 9;
        n |= n >>> 27;
        n |= n >>> 81;
        return (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
