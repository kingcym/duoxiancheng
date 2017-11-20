package com.example.demo.two.readwritelock;
/**
 * 读写分离
 * read read 并行化
 * read write 不允许
 * write write 不允许
 */
public class ReadWriteLock {
    //当前多少线程读
    private int readingReaders = 0;
    //当前多少线程等待
    private int waitReaders = 0;
    //当前多少线程写
    private int writingWriter = 0;
    //当前多少线程等写
    private int waitWriter = 0;

    public synchronized void readLock() throws InterruptedException {
        this.waitReaders++;
        System.out.println(waitReaders);
        try {
            while (writingWriter>0){
                this.wait();
            }
            this.readingReaders++;
        } finally {
            this.waitReaders--;
        }
    }

    public synchronized void readUnlock() {
        this.readingReaders--;
        this.notifyAll();
    }

    public synchronized void writerLock() throws InterruptedException {
        this.waitWriter++;
        try {
            while (writingWriter>0||readingReaders>0){
                this.wait();
            }
            this.writingWriter++;
        } finally {
            this.waitWriter--;
        }
    }

    public synchronized void writerUnlock() {
        this.writingWriter--;
        this.notifyAll();
    }

}
