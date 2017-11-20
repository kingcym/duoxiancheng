package com.example.demo.two.future;

import java.util.function.Consumer;

/**
 * Created by King on 2017/9/20.
 */
public class FutureService {

//    public <T> Future<T> submit(final FutureTask<T> task){
//        AsycFuture<T> asy = new AsycFuture<>();
//        new Thread(()->{
//            T result = task.call();
//            asy.done(result);
//        }).start();
//        return asy;
//    }


    public <T> void submit(final FutureTask<T> task, final Consumer<T> consumer){
        new Thread(()->{
            T result = task.call();
            consumer.accept(result);
        }).start();
    }

}
