package com.example.demo.one.base4;

/**
 * Created by King on 2017/9/7.
 */
/**
 *1. interrupt方法是用于中断线程的，调用该方法的线程的状态将被置为"中断"状态。
 *     注意：线程中断仅仅是设置线程的中断状态位，不会停止线程。需要用户自己去监视线程的状态为并做处理。
 *     支持线程中断的方法（也就是线程中断后会抛出InterruptedException的方法，比如这里的sleep，以及Object.wait等方法）
 *     就是在监视线程的中断状态，一旦线程的中断状态被置为“中断状态”，就会抛出中断异常。
 *     抛出InterruptedException的时候清除掉中断状态;
 *     注：如果线程的sleep的时间过去了，再interrupt，这时并不会抛出中断异常;
 *2. interrupted和isInterrupted：这两个方法一个是static的，一个不是，但实际上都是在调用同一个方法，
 *      只是interrupted方法传入的参数为true，而inInterrupted传入的参数为false（看源码）。
 *      静态方法interrupted将会清除中断状态（传入的参数ClearInterrupted为true），
 *      而实例方法isInterrupted则不会（传入的参数ClearInterrupted为false）。
 */
public class ThreadInterrput {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
 //                       Thread.sleep(11);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
        Thread.sleep(10);
        System.out.println("中断之前："+ t.isInterrupted());
        t.interrupt();
        System.out.println("中断之后："+ t.isInterrupted());
    }
}
