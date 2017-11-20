package com.example.demo.shujujiegou.ch1数组;

/**
 * Created by King on 2017/11/13.
 * 二分法查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{12, 15, 34, 45, 56, 67, 77, 89, 90};
        System.out.println(binarySearch(arr, 12));
        System.out.println(binarySearch(arr, 45));
        System.out.println(binarySearch(arr, 67));
        System.out.println(binarySearch(arr, 89));
        System.out.println(binarySearch(arr, 99));
    }

    private static int binarySearch(int[] arr, int value) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (value < arr[middle]) {
                end = middle - 1;
            } else if (value > arr[middle]) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
