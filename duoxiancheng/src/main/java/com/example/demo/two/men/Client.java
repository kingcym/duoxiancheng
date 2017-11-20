package com.example.demo.two.men;

/**
 * Created by King on 2017/9/18.
 */
public class Client {
    public static void main(String[] args) {
        Gate gate = new Gate();
        User user1 = new User("zhuangsan", "ztjianjin",gate);
        User user2 = new User("lisi", "lshanghai",gate);
        User user3 = new User("wangwu", "whangzhou",gate);
        user1.start();
        user2.start();
        user3.start();
    }
}
