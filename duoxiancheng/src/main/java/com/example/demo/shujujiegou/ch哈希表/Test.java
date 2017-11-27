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
        KaifangdizhiHashTable ht = new KaifangdizhiHashTable();
        ht.insert(new Info("a","张三"));
        ht.insert(new Info("ct","李四"));

        System.out.println(ht.find("a").getName());
        System.out.println(ht.find("ct").getName());
    }
}
