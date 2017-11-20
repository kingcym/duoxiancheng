package com.example.demo.shujujiegou.ch4链表;

/**
 * 链表 (单结点)
 * Created by King on 2017/11/15.
 */
public class MyFirstList<E> {

    //头节点
    public Node<E> first;

    public MyFirstList() {
        this.first = null;
    }

    /**
     * 插入一个结点，在头结点插入
     */
    public void insert(E value){
        Node<E> node = new Node(value);
        node.next = first;
        first = node;

    }

    /**
     * 删除结点，在头结点删除
     */
    public E delete(){
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


    public void display(){
        Node<E> current = first;
        while (current != null){
            current.display();
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyFirstList<Integer> list = new MyFirstList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.display();
        System.out.println(list.delete(8));
        list.display();
    }
}
