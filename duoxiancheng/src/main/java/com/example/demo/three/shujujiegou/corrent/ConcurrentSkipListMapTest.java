package com.example.demo.three.shujujiegou.corrent;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/24 18:28
 */

/**
 * ConcurrentSkipListMap底层数据结构是跳表，
 * 所以ConcurrentSkipListMap的key是有序的，因此要求传入comparator接口
 * 或者key实现了comparable
 */
public class ConcurrentSkipListMapTest {
    public static void main(String[] args) {
  //      ConcurrentSkipListMap<Integer,String> map = new ConcurrentSkipListMap(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });
//        map.put(1,"天天1");
//        map.put(4,"天天4");
//        map.put(3,"天天3");
//        map.put(2,"天天2");




        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("d", 2);
        map.put("c", 1);
        map.put("a", 3);
        map.put("b", 4);

        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

        // 对HashMap中的key 进行排序
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
//				System.out.println(o1.getKey()+"   ===  "+o2.getKey());
                return (o1.getKey()).toString().compareTo(o2.getKey().toString());
            }
        });
        for (Map.Entry<String, Integer> entry: map.entrySet()){
            System.out.println(entry.getValue());
        }

        
    }



}
