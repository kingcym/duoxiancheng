package com.example.demo.shujujiegou.ch哈希表;

import com.example.demo.shujujiegou.ch二叉树.BinaryTree;

import java.util.Comparator;
import java.util.HashMap;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/11/25 15:17
 */
public class Test {
    private final char value[];
    public Test(Test original) {
        this.value = original.value;
    }

    public static void main(String[] args) {
        String a="张三";
        char[] chars = a.toCharArray();
        int h = 0;
        for (int i = 0; i < chars.length; i++) {
            h = 31 * h + chars[i];
        }
        System.out.println(h);
    }
}
