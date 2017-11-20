package com.example.demo.two.readwritelock;

import java.util.Random;

/**
 * Created by King on 2017/9/19.
 */
public class WriterWork extends Thread {

    private static final Random random = new Random(System.currentTimeMillis());

    private final ShareDate data;

    private final String filler;

    private int index = 0;

    public WriterWork(ShareDate data, String filler) {
        this.data = data;
        this.filler = filler;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextchar();
                data.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private char nextchar() {
        char c = filler.charAt(index);
        index++;
        if (index>=filler.length()){
            index = 0;
        }
        return c;
    }
}
