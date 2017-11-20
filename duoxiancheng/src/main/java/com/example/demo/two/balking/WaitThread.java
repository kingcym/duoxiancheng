package com.example.demo.two.balking;

import java.io.IOException;

/**
 * Created by King on 2017/9/21.
 */
public class WaitThread extends Thread {

    private final BalkingData balkingData;

    public WaitThread(BalkingData balkingData) {
        super("WaitThread");
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {

            try {
                balkingData.save();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
