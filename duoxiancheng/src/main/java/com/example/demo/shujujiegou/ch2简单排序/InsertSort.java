package com.example.demo.shujujiegou.ch2简单排序;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://baike.baidu.com/item/%E6%8F%92%E5%85%A5%E6%8E%92%E5%BA%8F/7214992?fr=aladdin
 * Created by King on 2017/11/13.
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{44, 45, 34, 23, 100, 88, 77, 98, 50};
        System.out.println("初始" + Arrays.toString(arr));
        insertSort(arr);
        System.out.println("最终" + Arrays.toString(arr));
    }

    /**
     * 直接插入排序
     *
     * @param arr
     */
    private static void insertSort(int[] arr) {
        int num = 0;//记录次数
        int temp;
        int size = arr.length;
        for (int i = 1; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                } else {
                    break;
                }
            }
            System.out.println((++num) + "次 " + Arrays.toString(arr));
        }
    }


}
