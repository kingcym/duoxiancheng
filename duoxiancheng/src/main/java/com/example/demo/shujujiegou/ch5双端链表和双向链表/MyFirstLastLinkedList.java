package com.example.demo.shujujiegou.ch5双端链表和双向链表;


/**
 * 双端链表
 * 在单结点列表中新加了一个last节点，可以实现尾部增加
 * Created by King on 2017/11/15.
 */
public class MyFirstLastLinkedList<E> {

    //头节点
    public Node<E> first;

    //尾节点
    public Node<E> last;

    public MyFirstLastLinkedList() {
        this.first = null;
        this.last = null;
    }

    /**
     * 插入一个结点，在头结点插入
     */
    public void insert(E value){
        Node<E> node = new Node(value);
        if (isEmpty()) {
            last = node;
        }
        node.next = first;
        first = node;

    }

    /**
     * 插入一个结点，在尾结点插入
     */
    public void insertlast(E value){
        Node<E> node = new Node(value);
        if (isEmpty()) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
    }

    /**
     * 删除结点，在头结点删除
     */
    public E delete(){
        if (isEmpty()){
            return null;
        }
        Node<E> node = first;
        first = node.next;
        return node.data;
    }

    /**
     * 删除结点，根据值删除
     */
    public Boolean delete(E value){
        Node<E> current = first;
        Node<E> previous = null;
        while (current != null){
            if (current.data.equals(value)){
                break;
            }
            previous = current;
            current = current.next;
        }
        if (current == first){
            first = first.next;
        } else if (null == current){
            return false;
        } else {
            previous.next = current.next;
        }

        return true;
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty() {
        return (first == null);
    }


    public void display(){
        Node<E> current = first;
        while (current != null){
            current.display();
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyFirstLastLinkedList<Integer> list = new MyFirstLastLinkedList();

        list.display();
        System.out.println(list.delete());

        list.display();
    }
}
