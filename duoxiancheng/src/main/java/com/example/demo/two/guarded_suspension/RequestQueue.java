package com.example.demo.two.guarded_suspension;

import java.util.LinkedList;

/**
 * Created by King on 2017/9/21.
 */
public class RequestQueue {
    private final LinkedList<Request> queue = new LinkedList<>();


    public Request getRequest() {
        synchronized (queue) {
            while (queue.size() <= 0) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                   return null;
                }
            }
            return queue.removeFirst();
        }
    }

    public void putRequest(Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }


}
