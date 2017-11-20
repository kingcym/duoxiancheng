package com.example.demo.one.baseSinze7;

/**
 * Created by King on 2017/9/5.
 */
public class TicketWindowsRunable implements Runnable{
    private String name;
    private final static int MAX = 500;
    private  int index = 1;
    private final Object ss = new Object();
    @Override
    public void run() {


        while (true) {
            synchronized (ss){
            if (index <= MAX){
                System.out.println(Thread.currentThread().getName() + "号码是" + index++);
            } else {
                break;
            }

            }

        }
    }


    public static void main(String[] args) {
        final TicketWindowsRunable ticketWindowsRunable = new TicketWindowsRunable();
        Thread thread1 = new Thread(ticketWindowsRunable, "1");
        Thread thread2 = new Thread(ticketWindowsRunable, "2");
        Thread thread3 = new Thread(ticketWindowsRunable, "3");
        thread1.start();
        thread2.start();
        thread3.start();
    }


}
