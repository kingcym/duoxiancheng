package com.example.demo.two.immutable;

/**
 * Created by King on 2017/9/20.
 */
public class Persion {
    private final String name;

    private final String address;

    public Persion(String name, String address) {
        this.name = name;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
