package com.example.demo.one.base2;

/**
 * Created by King on 2017/9/5.
 */
public class TicketWindows extends Thread{
    private final String name;
    private  static final int MAX = 6;
    private  int index = 1;

    public TicketWindows(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while(index <= MAX){
            System.out.println("柜台"+name+"出票：" + index++);
        }

    }


    public static void main(String[] args) {
        TicketWindows ticketWindows1 = new TicketWindows("一");
        ticketWindows1.start();

        TicketWindows ticketWindows2 = new TicketWindows("二");
        ticketWindows2.start();

        TicketWindows ticketWindows3 = new TicketWindows("三");
        ticketWindows3.start();
    }
}
