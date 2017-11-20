package com.example.demo.three.untils.phaser;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/14 21:01
 */
public class Test5 {
    /**
     * Phaser销毁
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser(3);
        new Thread(phaser::arriveAndAwaitAdvance).start();

        TimeUnit.SECONDS.sleep(3);
        System.out.println(phaser.isTerminated());
        phaser.forceTermination();
        System.out.println(phaser.isTerminated());
        System.out.println(Runtime.getRuntime().availableProcessors());
    }


}
