package com.example.demo.shujujiegou.ch2简单排序;

import java.util.Arrays;

/**
 * https://baike.baidu.com/item/%E7%9B%B4%E6%8E%A5%E9%80%89%E6%8B%A9%E6%8E%92%E5%BA%8F/2090477?fr=aladdin
 * Created by King on 2017/11/13.
 */
public class StraightSelectionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{45, 44, 34, 23, 100, 88, 77, 98, 50};
        System.out.println("初始" + Arrays.toString(arr));
        straightSelectionSort(arr);
        System.out.println("最终" + Arrays.toString(arr));
    }

    /**
     * 直接选择排序
     *
     * @param arr
     */
    private static void straightSelectionSort(int[] arr) {
        int num = 0;//记录次数
        int size = arr.length - 1;
        for (int i = 0; i < size; i++) {
            //最小数的索引，该索引每次都根据外层循环的计数器来觉得初始值。
            int minIndex = i;
            for (int j = i + 1; j < size + 1; j++) {
                //根据最小数的索引，判断当前这个数是否小于最小数。
                //如果小于，则把当前数的索引作为最小数的索引。
                //否则不处理。
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            //如果当前索引i等于最小索引minIndex,则不用交换
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
            System.out.println((++num) + "次 " + Arrays.toString(arr));
        }

    }
}
