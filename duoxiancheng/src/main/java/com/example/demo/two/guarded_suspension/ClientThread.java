package com.example.demo.two.guarded_suspension;

import java.util.Random;

/**
 * Created by King on 2017/9/21.
 */
public class ClientThread extends Thread {
    private final RequestQueue queue;
    private final String senValue;
    private final Random random;

    public ClientThread(RequestQueue queue, String senValue) {
        this.queue = queue;
        this.senValue = senValue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("clien-> " + senValue);
            queue.putRequest(new Request(senValue));
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
