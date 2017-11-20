package com.example.demo.shujujiegou.ch2简单排序;

import java.util.Arrays;

/**
 * https://baike.baidu.com/item/%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F/4602306?fr=aladdin
 * Created by King on 2017/11/13.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{45, 44, 34, 23, 100, 88, 77, 98, 50};
        System.out.println("初始" + Arrays.toString(arr));
        bubbleSort2(arr);
        System.out.println("最终" + Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     * -------------------------------------------------
     * 初始[45, 44, 34, 23, 100, 88, 77, 98, 50]
     * 1次 [44, 34, 23, 45, 88, 77, 98, 50, 100]
     * 2次 [34, 23, 44, 45, 77, 88, 50, 98, 100]
     * 3次 [23, 34, 44, 45, 77, 50, 88, 98, 100]
     * 4次 [23, 34, 44, 45, 50, 77, 88, 98, 100]
     * 5次 [23, 34, 44, 45, 50, 77, 88, 98, 100]
     * 6次 [23, 34, 44, 45, 50, 77, 88, 98, 100]
     * 7次 [23, 34, 44, 45, 50, 77, 88, 98, 100]
     * 8次 [23, 34, 44, 45, 50, 77, 88, 98, 100]
     * 最终[23, 34, 44, 45, 50, 77, 88, 98, 100]
     * --------------------------------------------------
     * 分析：实际上第四次，这时就已经排好序了,有什么办法判断提前排行序呢？
     *
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        int num = 0;//记录次数
        int temp;
        int size = arr.length - 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            System.out.println((++num) + "次 " + Arrays.toString(arr));
        }
    }

    /**
     * 改进
     *
     * ===============================================
     * 初始[45, 44, 34, 23, 100, 88, 77, 98, 50]
     * 1次 [44, 34, 23, 45, 88, 77, 98, 50, 100]
     * 2次 [34, 23, 44, 45, 77, 88, 50, 98, 100]
     * 3次 [23, 34, 44, 45, 77, 50, 88, 98, 100]
     * 4次 [23, 34, 44, 45, 50, 77, 88, 98, 100]
     * 最终[23, 34, 44, 45, 50, 77, 88, 98, 100]
     * ===============================================
     *
     * @param arr
     */
    private static void bubbleSort2(int[] arr) {
        int num = 0;//记录次数
        int temp;
        int size = arr.length - 1;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int j = 0; j < size; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            size--;
            if (flag)
                System.out.println((++num) + "次 " + Arrays.toString(arr));
        }
    }

}
