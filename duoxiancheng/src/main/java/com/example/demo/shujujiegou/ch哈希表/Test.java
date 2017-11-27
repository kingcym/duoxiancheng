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
    public static void main(String[] args) {
        liandizhiHashTable<String,Info> ht = new liandizhiHashTable();
        ht.addLast("a",new Info("a","张三"));
        ht.addLast("ct",new Info("ct","李四"));
        ht.addLast("ct",new Info("ct","王五"));

        System.out.println(ht.get("a"));
        System.out.println(ht.get("ct"));

    }
}
