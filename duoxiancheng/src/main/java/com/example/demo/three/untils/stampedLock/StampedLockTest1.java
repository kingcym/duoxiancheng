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
 * Lock是一个接口，核心方法是lock()，unlock()，tryLock()，实现类有ReentrantLock, ReentrantReadWriteLock.ReadLock, ReentrantReadWriteLock.WriteLock；

 ReentrantReadWriteLock, ReentrantLock 和synchronized锁都有相同的内存语义。

 与synchronized不同的是，Lock完全用Java写成，在java这个层面是无关JVM实现的。Lock提供更灵活的锁机制，很多synchronized 没有提供的许多特性，比如锁投票，定时锁等候和中断锁等候，但因为lock是通过代码实现的，要保证锁定一定会被释放，就必须将unLock()放到finally{}中
 */
/**
 * 它是java8在java.util.concurrent.locks新增的一个API。

 ReentrantReadWriteLock 在沒有任何读写锁时，才可以取得写入锁，这可用于实现了悲观读取（Pessimistic Reading），即如果执行中进行读取时，经常可能有另一执行要写入的需求，为了保持同步，ReentrantReadWriteLock 的读取锁定就可派上用场。

 然而，如果读取执行情况很多，写入很少的情况下，使用 ReentrantReadWriteLock 可能会使写入线程遭遇饥饿（Starvation）问题，也就是写入线程吃吃无法竞争到锁定而一直处于等待状态。

 StampedLock控制锁有三种模式（写，读，乐观读），一个StampedLock状态是由版本和模式两个部分组成，锁获取方法返回一个数字作为票据stamp，它用相应的锁状态表示并控制访问，数字0表示没有写锁被授权访问。在读锁上分为悲观锁和乐观锁。

 所谓的乐观读模式，也就是若读的操作很多，写的操作很少的情况下，你可以乐观地认为，写入与读取同时发生几率很少，因此不悲观地使用完全的读取锁定，程序可以查看读取资料之后，是否遭到写入执行的变更，再采取后续的措施（重新读取变更信息，或者抛出异常） ，这一个小小改进，可大幅度提高程序的吞吐量！！
 */

/**
 * 悲观读
 * 这种形式跟ReentrantReadWriteLock基本没有区别
 */
public class StampedLockTest1 {
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
        long stamp = -1;
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
