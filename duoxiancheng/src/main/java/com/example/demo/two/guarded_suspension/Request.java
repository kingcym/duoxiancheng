package com.example.demo.two.guarded_suspension;

/**
 * Created by King on 2017/9/21.
 */
public class Request {
    private final String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
