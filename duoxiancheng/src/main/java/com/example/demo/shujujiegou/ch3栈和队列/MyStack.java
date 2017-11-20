package com.example.demo.shujujiegou.ch3栈和队列;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by King
 * on 2017/11/14.
 */
public class MyStack {
    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        s.push("1");
        s.push("2");
        s.push("3");
        System.out.println(s);
        //返回最后进的值peek
        System.out.println("------peek---- : " + s.peek());
        System.out.println(s);
        //返回最后进的值,并且删除 pop
        System.out.println("------pop---- : " + s.pop());
        System.out.println(s);
        //返回堆栈顶部的距离,最靠近的是1
        System.out.println("------seach---- : " + s.search("2"));

    }

    public static void it(Stack<String> s){
        System.out.print("stack:");
        Iterator<String> it = s.iterator();
        while(it.hasNext()){
            System.out.print(it.next()+"  ");
        }
        System.out.print("\n");
    }
}
