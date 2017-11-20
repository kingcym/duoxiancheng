package com.example.demo.three.untils.condition;
/**
 * @Author: CYM
 * @Description:
 * @Data: 2017/10/11 16:23
 */
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;
/**
 * Condition是一个wait notify替代
 */
public class ConditionTest1 {
    private final static Lock lock = new ReentrantLock();
    private final static Condition pCondition = lock.newCondition();
    private final static Condition cCondition = lock.newCondition();
    private final static LinkedList<Long> timeList = new LinkedList<>();
    private final static int MAX = 100;

    public static void main(String[] args) {
        IntStream.range(0, 6).boxed().forEach(ConditionTest1::beginProduce);
        IntStream.range(0, 10).boxed().forEach(ConditionTest1::beginConsume);
    }

    private static void beginProduce(int i) {
        new Thread(() -> {
            for (; ; ) {
                produce();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p-" + i).start();
    }

    private static void beginConsume(int i) {
        new Thread(() -> {
            for (; ; ) {
                consume();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "c" + i).start();
    }

    private static void produce() {
        try {
            lock.lock();
            while (timeList.size() >= MAX) {
                pCondition.await();
            }
            long l = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "-p-->>" + l);
            timeList.addLast(l);
            cCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void consume() {
        try {
            lock.lock();
            while (timeList.isEmpty()) {
                cCondition.await();
            }
            Long aLong = timeList.removeFirst();
            System.out.println(Thread.currentThread().getName() + "--c->>" + aLong);
            pCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
