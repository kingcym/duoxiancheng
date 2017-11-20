package com.example.demo.three.untils.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/30 10:04
 */
public class Test {
    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(() -> "hello").
                thenApply(s -> s + " world").join();
        System.out.println(result);
    }
}
