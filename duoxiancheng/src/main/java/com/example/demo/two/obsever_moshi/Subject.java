package com.example.demo.two.obsever_moshi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by King
 * on 2017/9/17.
 */
public class Subject {
    private final List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        if (this.state==state) return;
        this.state = state;
        notifyAllObserver();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    private void notifyAllObserver(){
        observers.stream().forEach(Observer::update);
    }

}
