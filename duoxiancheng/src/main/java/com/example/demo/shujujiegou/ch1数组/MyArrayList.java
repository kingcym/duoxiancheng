package com.example.demo.shujujiegou.ch1数组;

import java.util.Arrays;

/**
 * @Author: CYM
 * @Description: 自定义arraylist
 * @Data: 2017/11/12 22:33
 */
public class MyArrayList<E> {
    //长度
    private int size;
    //数组
    private Object[] arr;
    //添加元素时，默认数据容量
    private static final int DEFAULT_CAPACITY = 10;
    //创建一个空数组
    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public MyArrayList() {
        this(0);
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.arr = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.arr = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("非法数值: " +
                    initialCapacity);
        }
    }


    /**
     * 添加元素
     *
     * @param e 元素
     * @return boolean
     */
    public boolean add(E e) {
        //确保内部容量(通过判断，如果够则不进行操作；容量不够就扩容来确保内部容量)
        ensureCapacityInternal(size + 1);  // ①Increments modCount!!
        arr[size++] = e;
        return true;
    }

    /**
     * 指定位置添加元素
     *
     * @param e 元素
     * @return boolean
     */
    public void add(E e, int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("越界,最大为：" + size);
        ensureCapacityInternal(size + 1);
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = e;
        size++;
    }

    /**
     * 指定位置修改值
     *
     * @param index
     * @param element
     * @return 被修改之前的值
     */
    public E set(int index, E element) {
        if (index >= size)
            throw new IndexOutOfBoundsException("越界,最大为：" + size);
        E oldValue = (E) arr[index];
        arr[index] = element;
        return oldValue;
    }

    /**
     * 删除指定index
     */
    public E remove(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("越界,最大为：" + size);
        E oldValue = (E) arr[index];
        int numMoved = size - index - 1;
        if (numMoved > 0)

        /**
         * arraycopy(Object src,  int  srcPos,
         Object dest, int destPos,
         int length);
         *    从指定源数组中复制一个数组，复制从指定的位置开始，到目标数组的指定位置结束。
         *    从 src 引用的源数组到 dest 引用的目标数组，
         *    数组组件的一个子序列被复制下来。
         *    被复制的组件的编号等于 length 参数。
         *    源数组中位置在 srcPos 到 srcPos+length-1 之间的组件被分别复制到目标数组中的 destPos 到 destPos+length-1 位置。

         String[] s1 = {"中国", "山西", "太原", "TYUT", "zyy", "加拿大", "不知道哪个州", "不知道哪个市", "不知道哪个学校", "yxf"};
         String[] s2 = new String[10];
         System.arraycopy(s1, 1, s2, 2, 3);
         System.out.println(Arrays.asList(s1));
         System.out.println(Arrays.asList(s2));
         */ {
            final long l = System.currentTimeMillis();
            System.arraycopy(arr, index + 1, arr, index, numMoved); //几乎不耗时（测试移动个数999999）
//            for (int i = index; i < size; i++) {
//                arr[i] = arr[i + 1];
//            }
            final long s = System.currentTimeMillis();
            System.err.println(l + "=======" + s + "======" + (s - l));
        }

        arr[--size] = null; // clear to let GC do its work
        return oldValue;
    }


    /**
     * 显示元素
     */
    public void display() {
        System.out.print("[");

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }


    /**
     * 查找元素
     *
     * @param index
     * @return
     */

    public E get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("越界,最大为：" + size);
        return (E) arr[index];
    }

    /**
     * 查找元素 的 index
     *
     * @return
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (arr[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(arr[i]))
                    return i;
        }
        return -1;
    }

    public int size() {
        return this.size;
    }


    /**
     * 扩容
     *
     * @param minCapacity
     */
    private void ensureCapacityInternal(int minCapacity) {
        //如果实际存储数组 是空数组，则最小需要容量就是默认容量
        if (arr == EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        //如果数组（elementData)的长度小于最小需要的容量（minCapacity）就扩容
        if (minCapacity - arr.length > 0)
            grow(minCapacity);
    }

    /*
    *增加容量，以确保它至少能容纳
    *由最小容量参数指定的元素数。
    * @param mincapacity所需的最小容量
    */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = arr.length;
        //>>位运算，右移动一位。 整体相当于newCapacity =oldCapacity + 0.5 * oldCapacity
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        //jdk1.7这里增加了对元素个数的最大个数判断,jdk1.7以前是没有最大值判断的，Integer.MAX_VALUE - 8（不清楚为什么用这个值做比较）
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
        }
        // 最重要的复制元素方法（最终调用 native本地 arraycopy方法）
        arr = Arrays.copyOf(arr, newCapacity);
    }
}
