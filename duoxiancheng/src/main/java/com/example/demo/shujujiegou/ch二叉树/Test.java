package com.example.demo.shujujiegou.ch二叉树;

import java.util.Comparator;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/11/25 15:17
 */
public class Test {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == null && o2 != null) {
                    return -1;
                }
                if (o2 == null && o1 != null) {
                    return 1;
                }
                if (o2 == null && o1 == null) {
                    return 0;
                }
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(10);
        tree.insert(0);
        tree.insert(25);
        tree.insert(5);

        tree.lastForEatch();
        System.out.println();

        System.out.println(tree.delete(20));

        tree.lastForEatch();

    }
}
