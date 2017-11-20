package com.example.demo.two.balking;

import java.io.IOException;
import java.util.Random;

/**
 * Created by King on 2017/9/21.
 */
public class ConsumerThread extends Thread {
    private final BalkingData balkingData;
    private final Random random = new Random(System.currentTimeMillis());

    public ConsumerThread(BalkingData balkingData) {
        super("consumer");
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
        try {
            balkingData.save();
            for (int i = 0; i < 20; i++) {
                balkingData.change("NO."+i);
                Thread.sleep(random.nextInt(1000));
                balkingData.save();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
