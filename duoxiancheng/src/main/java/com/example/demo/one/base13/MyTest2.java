package com.example.demo.one.base13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by King on 2017/9/12.
 */
public class MyTest2 {
    public static void main(String[] args) {
        String[] array = {"a","b","c","c","d","e","e","e","a"};
        Arrays.sort(array);
        List<String> list = new ArrayList<>();
        list.add(array[0]);
        for(int i=1;i<array.length;i++){
            if(!array[i].equals(list.get(list.size()-1))){
                list.add(array[i]);
            }
        }
        String[] arrayResult = list.toArray(new String[list.size()]);
        System.out.println(Arrays.toString(arrayResult));
    }

}
