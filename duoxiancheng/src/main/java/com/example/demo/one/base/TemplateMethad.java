package com.example.demo.one.base;

/**
 * Created by King on 2017/9/4.
 */
/**
 *模板方法
 * */
public abstract class TemplateMethad {

    public final void print(String message){
        System.out.println("++++++++++++++++++++++++++++++++++++++");
        printMethod(message);
        System.out.println("++++++++++++++++++++++++++++++++++++++");
    }

    protected abstract void printMethod(String message);


    public static void main(String[] args) {
        TemplateMethad c=new TemplateMethad(){
            @Override
            protected void printMethod(String message) {
                System.out.println(message);
            }
        };
        c.print("haha ha ha haha");
    }
}
