package com.example.demo.two.updown;


/**
 * Created by King on 2017/9/21.
 */
public class ExecutionTask implements Runnable{
    private QueryFromDBAction queryAction = new QueryFromDBAction();

    @Override
    public void run() {
        final Context context = new Context();
        queryAction.execute(context);

    }
}
