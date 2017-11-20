package com.example.demo.two.obsever_moshi;

/**
 * Created by King on 2017/9/17.
 */
public abstract class Observer {
    protected Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public abstract void update();
}
