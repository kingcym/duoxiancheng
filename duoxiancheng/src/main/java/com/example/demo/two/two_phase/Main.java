package com.example.demo.two.two_phase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * Created by King on 2017/9/21.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        final ConuterIncrement conuterIncrement = new ConuterIncrement();
        conuterIncrement.start();
        Thread.sleep(5000);
        conuterIncrement.close();





    }
}
