package com.example.demo.shujujiegou.ch4链表;

/**
 * Created by King on 2017/11/15.
 */
public class Node<E> {
    //数据域
    public E data;
    //指针域
    public Node<E> next;

    public Node(E data) {
        this.data = data;
    }

    public void display(){
        System.out.print(data+" ");
    }
}
