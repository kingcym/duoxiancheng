package com.example.demo.shujujiegou.ch哈希表;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

/**
 * @Description: 链地址法 （hashMap用的就是链地址法）
 * 当hashcode冲突发生，在哈希表每个单元中设置链表
 */
public class liandizhiHashTable<K, V> {
    private MyLinkList<K,V>[] arr;

    /**
     * 默认的构造方法
     */
    public liandizhiHashTable() {
        this(100);
    }

    /**
     * 指定数组初始化大小
     */
    public liandizhiHashTable(int maxSize) {
        arr = new MyLinkList[maxSize];
    }

    /**
     * 插入数据
     */
    public void addFirst(K key, V value) {
        //关键字所自定的哈希数
        int hashVal = hashCode((String)key);
        if (arr[hashVal] == null) {
            arr[hashVal] = new MyLinkList<>();
        }
        arr[hashVal].addFirst(key,value);
    }

    public void addLast(K key, V value) {
        //关键字所自定的哈希数
        int hashVal = hashCode((String)key);
        if (arr[hashVal] == null) {
            arr[hashVal] = new MyLinkList<>();
        }
        arr[hashVal].addLast(key,value);
    }

    /**
     * 查找数据
     */
    public V get(String key) {
        int hashVal = hashCode(key);
        MyLinkList<K,V> values = arr[hashVal];
        for (Node<K,V> x = values.first; x != null; x = x.next) {
            if (x.key.equals(key))
                return x.item;
        }
        return null;
    }

    public int hashCode(String key) {
        BigInteger hashVal = new BigInteger("0");
        BigInteger pow27 = new BigInteger("1");
        for (int i = key.length() - 1; i >= 0; i--) {
            int letter = key.charAt(i) - 96;
            BigInteger letterB = new BigInteger(String.valueOf(letter));
            hashVal = hashVal.add(letterB.multiply(pow27));
            pow27 = pow27.multiply(new BigInteger(String.valueOf(27)));
        }
        return hashVal.mod(new BigInteger(String.valueOf(arr.length))).intValue();
    }

    static class Node<K, V> {
        //数据域
        public K key;
        //数据域
        public V item;
        //后继
        Node<K, V> next;
        //前驱
        Node<K, V> prev;
        Node(Node<K, V> prev,K key, V element,Node<K, V> next) {
            this.key =key;
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
    

    /**
     * LinkList源码解析
     * 参考：https://www.cnblogs.com/leesf456/p/5308843.html
     * Created by King on 2017/11/21.
     */
    static class MyLinkList<K,V> {
        //实际元素个数
        transient int size = 0;
        //第一个节点
        transient Node<K,V> first;
        //最后一个节点
        transient Node<K,V> last;

        public MyLinkList() {
        }



        /**
         * 在头部插入数据
         * @param e
         */
        public void addFirst(K key,V e) {
            linkFirst(key,e);
        }

        public void addLast(K key,V e) {
            linkLast(key,e);
        }

        /**
         * Links e as first element.
         */
        private void linkFirst(K key,V e) {
            final Node<K,V> f = first;
            final Node<K,V> newNode = new Node<>(null,key, e, f);
            first = newNode;
            if (f == null)
                last = newNode;
            else
                f.prev = newNode;
            size++;
        }

        private void linkLast(K key,V e) {
            final Node<K,V> f = last;
            final Node<K,V> newNode = new Node<>(f,key, e, null);
            last = newNode;
            if (f == null)
                first = newNode;
            else
                f.next = newNode;
            size++;
        }
    }

}
