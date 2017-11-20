package com.example.demo.one.base13;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by King on 2017/9/11.
 */
public class Mytest1 {



    public static void main(String[] args) {
        String[] array = {"a","b","c","c","d","e","e","e","a"};
        Set<String> set = new HashSet<>();
        for (int i=0;i<array.length;i++) {
            set.add(array[i]);
        }
        String[] arrayResult =  set.toArray(new String[set.size()]);
        System.out.println(Arrays.toString(arrayResult));
    }
}
