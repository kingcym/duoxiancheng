package com.example.demo.two.balking;

/**
 * Created by King on 2017/9/21.
 */
public class Main {
    public static void main(String[] args) {
        BalkingData balkingData = new BalkingData("E:\\IdeaProjects1\\duoXianCheng\\basicOne\\balking.txt", "=======begin======");
        new ConsumerThread(balkingData).start();
        new WaitThread(balkingData).start();
    }
}
