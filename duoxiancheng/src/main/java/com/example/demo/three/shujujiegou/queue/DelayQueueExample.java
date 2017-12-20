package com.example.demo.three.shujujiegou.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/12/20 11:09
 */
public class DelayQueueExample {
    private DelayQueue<DelayElement> queue;

    @Before
    public void Before() {
        queue = new DelayQueue();
    }

    @Test
    public void test() throws InterruptedException {
        queue.add(DelayElement.of("hello",TimeUnit.SECONDS,1));
        System.out.println(queue.size());
        //peek无延迟
        System.out.println(queue.peek());
        //若时间未到，返回null,若时间已到，取值
        System.out.println(queue.poll());
        //take延迟，阻塞至取到值
        System.out.println(queue.take().getE());
    }


    @Test
    public void ssss() throws InterruptedException {
        queue.add(DelayElement.of("hello",TimeUnit.SECONDS,1));
        queue.add(DelayElement.of("hello",TimeUnit.SECONDS,4));
        queue.add(DelayElement.of("hello",TimeUnit.SECONDS,3));
        queue.add(DelayElement.of("hello",TimeUnit.SECONDS,2));
        for (DelayElement delayElement : queue) {
            System.out.println(delayElement);
        }
    }













    static class DelayElement<E> implements Delayed{
        private final E e;
        private final long dealy;

        DelayElement(E e, TimeUnit unit,long dealy) {
            this.e = e;
            this.dealy = TimeUnit.MILLISECONDS.convert(dealy,unit) + System.currentTimeMillis();
        }

        static <T>DelayElement<T> of(T e, TimeUnit unit,long dealy){
            return new DelayElement(e,unit, dealy);
        }

        static <T>DelayElement<T> of(T e,long dealy){
            return new DelayElement(e,TimeUnit.MILLISECONDS, dealy);
        }
        @Override
        public long getDelay(TimeUnit unit) {
            long l = dealy - System.currentTimeMillis();
            return unit.convert(l,TimeUnit.MICROSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            DelayElement that = (DelayElement) o;
            if (this.dealy > that.getDealy()){
                return 1;
            } else if (this.dealy < that.getDealy()){
                return -1;
            } else {
                return 0;
            }
        }

        public E getE() {
            return e;
        }

        public long getDealy() {
            return dealy;
        }


        @Override
        public String toString() {
            return "DelayElement{" +
                    "e=" + e +
                    ", dealy=" + dealy +
                    '}';
        }
    }




}
