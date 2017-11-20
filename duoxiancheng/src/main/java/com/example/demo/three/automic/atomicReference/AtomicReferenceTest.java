package com.example.demo.three.automic.atomicReference;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by King on 2017/10/2.
 */
public class AtomicReferenceTest {

    public static void main(String[] args) {
        Simple cai = new Simple("cai", 25);
        AtomicReference<Simple> s = new AtomicReference<>(cai);
        System.out.println(s.get().getAge());
        final boolean b = s.compareAndSet(cai, new Simple("cai", 26));
        System.out.println(b);
        System.out.println(s.get().getAge());
        s.set(new Simple("cai", 27));
        System.out.println(s.get().getAge());
    }

    static class Simple {
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
