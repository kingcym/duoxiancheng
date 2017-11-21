package com.example.demo.shujujiegou.ch5双端链表和双向链表;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * LinkList源码解析
 * 参考：https://www.cnblogs.com/leesf456/p/5308843.html
 * Created by King on 2017/11/21.
 */
public class MyLinkList<E> {
    //实际元素个数
    transient int size = 0;
    //第一个节点
    transient Node<E> first;
    //最后一个节点
    transient Node<E> last;

    public MyLinkList() {
    }
    public MyLinkList(Collection<? extends E> c) {
        // 调用无参构造函数
        this();
        //todo
        // 添加集合中所有的元素
 //       addAll(c);
    }

    /**
     * @return 第一个元素
     */
    public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }
    /**
     * @return 最后一个元素
     */
    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    /**
     * 在头部插入数据
     * @param e
     */
    public void addFirst(E e) {
        linkFirst(e);
    }

    /**
     * 在尾部插入数据
     * @param e
     */
    public void addLast(E e) {
        linkLast(e);
    }

    /**
     * 在尾部插入数据
     * @param e
     * @return ture,false
     */
    public boolean add(E e) {
        linkLast(e);
        return true;
    }



    /**
     * 删除第一个元素
     * @return  返回被删除的元素
     */
    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    /**
     * 删除最后一个元素
     * @return  返回被删除的元素
     */
    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    /**
     * 删除第一个出现的指定元素
     * @param o
     * @return
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 查询是否包含冒个元素
     * @param o
     * @return 返回ture,false
     */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * @return  返回大小
     */
    public int size() {
        return size;
    }

    /**
     * 清空
     */
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }
    /**
     * 删除指定Node<E>
     * @param x
     * @return
     */
    E  unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    /**
     * 查找是否有指定元素
     * @param o
     * @return
     */
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    /**
     * Unlinks non-null first node f.
     */
    private E unlinkFirst(Node<E> f) {
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }


    /**
     * Unlinks non-null last node l.
     */
    private E unlinkLast(Node<E> l) {
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    /**
     * Links e as first element.
     */
    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    /**
     * Links e as last element.
     */
    private void linkLast(E e) {
        final Node<E> f = last;
        final Node<E> newNode = new Node<>(f, e, null);
        last = newNode;
        if (f == null)
            first = newNode;
        else
            f.next = newNode;
        size++;
    }


    /**
     * 内部类Node就是实际的结点，用于存放实际元素的地方。
     * @param <E>
     */
    private static class Node<E> {
        //数据域
        E item;
        //后继
        Node<E> next;
        //前驱
        Node<E> prev;
        Node(Node<E> prev, E element,Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }







    public static void main(String[] args) {
        List liat = new LinkedList();
    }



}
