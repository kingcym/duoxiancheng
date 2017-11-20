package com.example.demo.two.men;

/**
 * Created by King on 2017/9/18.
 */
public class User extends Thread {
    private final String myName;
    private final String myAddress;
    private final Gate gate;

    public User(String myName, String myAddress, Gate gate) {
        this.myName = myName;
        this.myAddress = myAddress;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(myName + "begin");
        while (true) {
            this.gate.pass(myName,myAddress);
        }
    }
}
