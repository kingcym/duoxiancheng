package com.example.demo.two.men;

/**
 * Created by King on 2017/9/18.
 */
public class Gate {
    private int counter = 0;
    private String name = "Nobady";
    private String address = "Nowhere";

    public synchronized void pass(String name,String address) {
        this.counter++;
        this.name = name;
        this.address = address;
        verify();
    }

    private void verify() {
        if (this.name.charAt(0)!=this.address.charAt(0)) {
            System.out.println("******broken*******"+toString());
        }
    }

    public String toString() {
        return "No."+counter+":"+name+","+address;
    }
}
