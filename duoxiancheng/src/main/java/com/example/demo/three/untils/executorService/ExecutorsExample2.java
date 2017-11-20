package com.example.demo.three.untils.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/18 18:00
 */
public class ExecutorsExample2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newWorkStealingPool();

    }
}
