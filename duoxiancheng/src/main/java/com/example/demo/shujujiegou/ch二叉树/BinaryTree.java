package com.example.demo.shujujiegou.ch二叉树;

import java.util.Comparator;

/**
 * @Author: Kingcym
 * @Description: 二叉树
 * @Date: 2017/11/24 9:55
 */
//public class BinaryTree<E extends Comparable<? super E>> {
public class BinaryTree<E> {
    // 根节点
    private BTNode<E> root;
    private Comparator<E> comparator;

    public BinaryTree(E data, Comparator<E> comparator) {
        this.root.setData(data);
        this.comparator = comparator;
    }

    public BinaryTree(Comparator<E> comparator) {
        this(null, comparator);
    }

    /**
     * 获取根节点
     */
    public BTNode<E> getRoot() {
        return root;
    }

    /**
     * 插入数据
     * 比当前节点小的放左边，比当前节点大的放右边
     *
     * @param data 数据
     */
    public void insert(E data) {
        BTNode<E> newNode = new BTNode(data);
        if (root == null) {
            root = newNode;
        } else {
            BTNode<E> currentNote = root;
            BTNode<E> parent;
            int compareResult;
            while (true) {
                parent = currentNote;
                compareResult = comparator.compare(data, currentNote.getData());
                if (compareResult > 0) {
                    currentNote = currentNote.getRightChild();
                    if (currentNote == null) {
                        parent.setRightChild(newNode);
                        return;
                    }
                } else {
                    currentNote = currentNote.getLeftChild();
                    if (currentNote == null) {
                        parent.setLeftChild(newNode);
                        return;
                    }
                }
            }
        }
    }

    /**
     * 查找节点
     * 从根节点开始查找，如果查找的值比当前节点小，
     * 则继续查找左字树，否则查找右子树
     *
     * @param data
     */
    public boolean contain(E data) {
        if (root == null) {
            return false;
        } else {
            BTNode<E> currentNote = root;
            int compareResult;
            while (currentNote != null) {
                compareResult = comparator.compare(data, currentNote.getData());
                if (compareResult > 0) {
                    currentNote = currentNote.getRightChild();
                } else if (compareResult < 0) {
                    currentNote = currentNote.getLeftChild();
                } else {
                    return true;
                }
            }
            return false;
        }
    }


    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }

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
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(1);
        tree.insert(null);
        System.out.println(tree.contain(2));
        System.out.println(tree.contain(3));
        System.out.println(tree.contain(4));
        System.out.println(tree.contain(1));
        System.out.println(tree.contain(null));

    }
}
