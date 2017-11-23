package com.example.demo.shujujiegou.ch8希尔排序;

import java.util.Arrays;

/**
 * @Author: Kingcym
 * @Description: 希尔排序希尔排序(Shell Sort)是插入排序的一种。
 * 也称缩小增量排序，是直接插入排序算法的一种更高效的改进版本。
 * 希尔排序是非稳定排序算法。
 * https://www.cnblogs.com/chengxiao/p/6104371.html
 * @Date: 2017/11/23 10:59
 */
public class ShellSor {
    public static void main(String[] args) {
        int[] arr = new int[]{88, 66, 34, 23, 100, 44, 55, 98, 50, 51};
        System.out.println("初始" + Arrays.toString(arr));
        shellSor(arr);
        System.out.println("最终" + Arrays.toString(arr));
    }

    private static void shellSor(int[] arr) {
        int r, temp;
        // 划组排序
        for (r = arr.length / 2; r >= 1; r = r / 2) {
            for (int i = r; i < arr.length; i++) {
                for (int j = i; j >= r; j -= r) {
                    if (arr[j] < arr[j-r]) {
                        temp = arr[j];
                        arr[j] = arr[j-r];
                        arr[j-r] = temp;
                    } else {
                        break;
                    }
                }
                System.out.println(i + "次 " + Arrays.toString(arr));
            }

        }
    }




    private static void shellSor1(int[] arr) {

        // i表示希尔排序中的第n/2+1个元素（或者n/4+1）
        // j表示希尔排序中从0到n/2的元素（n/4）
        // r表示希尔排序中n/2+1或者n/4+1的值
        int i, j, r, tmp;
        // 划组排序
        for (r = arr.length / 2; r >= 1; r = r / 2) {
            for (i = r; i < arr.length; i++) {
                tmp = arr[i];
                j = i - r;
                // 一轮排序
                while (j >= 0 && tmp < arr[j]) {
                    arr[j + r] = arr[j];
                    j -= r;
                }
                arr[j + r] = tmp;
                System.out.println(i + ":" + Arrays.toString(arr));
            }

        }
    }
}
