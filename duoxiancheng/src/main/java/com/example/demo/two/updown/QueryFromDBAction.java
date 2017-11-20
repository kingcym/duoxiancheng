package com.example.demo.two.updown;

/**
 * Created by King on 2017/9/21.
 */
public class QueryFromDBAction {

    public void execute(Context context) {
        try {
            Thread.sleep(1000);
            String name = "caiyaming";
            context.setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
