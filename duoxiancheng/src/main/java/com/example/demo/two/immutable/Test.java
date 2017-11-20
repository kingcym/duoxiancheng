package com.example.demo.two.immutable;

/**
 * Created by King on 2017/9/20.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Integer s = 211;
        Integer l = 211;

        System.err.println(s==l);
    }
}


final class ImmutableObj{
    private final String name;
    ImmutableObj(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "ImmutableObj{" +
                "name='" + name + '\'' +
                '}';
    }
}

 class SycObj {
    private  String name;


     public synchronized void setName(String name) {
         this.name = name;
     }

     @Override
     public String toString() {
         return "SycObj{" +
                 "name='" + name + '\'' +
                 '}';
     }
}
