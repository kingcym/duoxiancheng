package com.example.demo.two.readwritelock;

/**
 * Created by King on 2017/9/19.
 */
public class ReadWork extends Thread {
    private final ShareDate data;


    public ReadWork(ShareDate data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true){
                char[] read = data.read();
                System.out.println(Thread.currentThread().getName() + " read: "+String.valueOf(read));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
