package com.example.demo.shujujiegou.ch5双端链表和双向链表;


/**
 * 双向链表
 * 每个结点除了对下一个结点的引用，还保留对上一个结点的引用
 * 可以实现对尾部删除。
 * Created by King on 2017/11/15.
 */
public class MyDoubleLinkedList<E> {

    //头节点
    public Node<E> first;

    //尾节点
    public Node<E> last;

    public MyDoubleLinkedList() {
        this.first = null;
        this.last = null;
    }

    /**
     * 插入一个结点，在头结点插入
     */
    public void insert(E value) {
        Node<E> node = new Node(value);
        if (isEmpty()) {
            last = node;
        } else {
            first.previous = node;
        }
        node.next = first;
        first = node;

    }

    /**
     * 插入一个结点，在尾结点插入
     */
    public void insertlast(E value) {
        Node<E> node = new Node(value);
        if (isEmpty()) {
            first = node;
        } else {
            last.next = node;
            node.previous = last;
        }
        last = node;
    }

    /**
     * 删除结点，在头结点删除
     */
    public E delete() {
        if (isEmpty()) {
            return null;
        }
        Node<E> node = first;
        if (first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }
        first = node.next;
        return node.data;
    }

    /**
     * 删除结点，在尾结点删除
     */
    public E deletelast() {
        if (isEmpty()) {
            return null;
        }
        Node<E> node = last;
        if (last.previous == null) {
            first = null;
        } else {
            last.previous.next = null;
        }
        last = node.previous;
        return node.data;
    }

    /**
     * 删除结点，根据值删除
     */
    public Boolean delete(E value) {
        Node<E> current = first;
        while (current != null) {
            if (current.data.equals(value)) {
                break;
            }
            current = current.next;
        }
        if (current == first) {
            first = first.next;
        } else if (null == current) {
            return false;
        } else {
            current.previous.next = current.next;
            current.next.previous = current.previous;
        }
        return true;
    }


    /**
     * 查询指定值的上一个值
     */
    public E previous(E value) {
        Node<E> current = first;
        while (current != null) {
            if (current.data.equals(value)) {
                break;
            }
            current = current.next;
        }
        Node<E> previous = current.previous;
        return previous == null ? null : previous.data;
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty() {
        return (first == null);
    }


    public void display() {
        Node<E> current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyDoubleLinkedList<Integer> list = new MyDoubleLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.display();
        System.out.println(list.delete());
        list.display();
        System.out.println(list.previous(2));
    }
}
