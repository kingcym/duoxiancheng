package com.example.demo.two.readwritelock;

/**
 * Created by King on 2017/9/19.
 */
public class ShareDate {
    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    public ShareDate(int size) {
        this.buffer = new char[size];
        for(int i=0;i<buffer.length;i++){
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return doRead();
        } finally {
            lock.readUnlock();
        }
    }

    public char[] write(char c) throws InterruptedException {
        try {
            lock.writerLock();
            return doWrite(c);
        } finally {
            lock.writerUnlock();
        }
    }

    private char[] doWrite(char c) {
        for(int i=0;i<buffer.length;i++){
            buffer[i] = c;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return buffer;
    }

    private char[] doRead() {
        char[] newbuf = new char[buffer.length];
        for(int i=0;i<buffer.length;i++){
            newbuf[i] = buffer[i];
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return newbuf;
    }


}
