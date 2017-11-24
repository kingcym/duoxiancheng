package com.example.demo.shujujiegou.ch二叉树;

/**
 * @Author: Kingcym
 * @Description:  二叉树节点
 * @Date: 2017/11/24 10:06
 */
public class BTNode<E> {
    //数据项  （可根据需求，一个节点有多个数据项）
    private E data;
    private BTNode<E> leftChild;
    private BTNode<E> rightChild;

    /**
     * 构造方法
     * @param data
     */
    public BTNode(E data) {
        this(data,null,null);
    }
    public BTNode(E data, BTNode<E> leftChild, BTNode<E> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public BTNode<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BTNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    public BTNode<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BTNode<E> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "BTNode{" +
                "data=" + data +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }


}
