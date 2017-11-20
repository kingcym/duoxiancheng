package com.example.demo.three.untils.stampedLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/**
 * @Author: CYM
 * @Description: StampedLock
 * @Data: 2017/10/12 14:14
 */

/**
 * 乐观读
 */
public class StampedLockTest2 {
    private static final StampedLock stampedLock = new StampedLock();
    private static final List<Long> DATA = new ArrayList<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Runnable readRunnable = ()->{
            for (;;){
                read();
            }
        };
        Runnable writeRunnable = ()->{
            for (;;){
                write();
            }
        };
        executor.submit(readRunnable);
        executor.submit(readRunnable);
        executor.submit(readRunnable);
        executor.submit(readRunnable);
        executor.submit(readRunnable);
        executor.submit(readRunnable);
        executor.submit(readRunnable);
        executor.submit(readRunnable);
        executor.submit(readRunnable);
        executor.submit(writeRunnable);
    }

    private static void read() {
        /**tryOptimisticRead是一个乐观的读，使用这种锁的读不阻塞写
         * 每次读的时候得到一个当前的stamp值（类似时间戳的作用）*/
        long stamp = stampedLock.tryOptimisticRead();
        Optional.of(
                DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""))
        ).ifPresent(System.out::println);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**如果读取的时候发生了写，则stampedLock的stamp属性值会变化，此时需要重读，
         * 再重读的时候需要加读锁（并且重读时使用的应当是悲观的读锁，即阻塞写的读锁）
         * 当然重读的时候还可以使用tryOptimisticRead，此时需要结合循环了，即类似CAS方式
         * 读锁又重新返回一个stampe值*/
        if (!stampedLock.validate(stamp)){//重读
            try {
                stamp = stampedLock.readLock();
                Optional.of(
                        DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""))
                ).ifPresent(System.out::println);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }

    }

    private static void write() {
        long stamp = -1;
        try {
            stamp = stampedLock.writeLock();
            DATA.add(System.currentTimeMillis());
            System.out.println("=======写=======");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }
}
