package com.example.demo.one.base5;

/**
 * Created by King on 2017/9/8.
 */

/**
 * 利用 守护线程特性，将执行的线程设置为守护线程
 *
 */
public class ThreadEnd3 {
    private Thread excuteThread;
    private boolean finished = false;

    public void execute(Runnable task) {
        excuteThread = new Thread() {
            @Override
            public void run() {
                Thread inner = new Thread(task);
                inner.setDaemon(true); //excuteThread线程结束,inner也就随着结束
                inner.start();
                try {
                    inner.join();//inner执行完毕才继续执行下面代码
                    finished = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        excuteThread.start();
    }

    public void shutdown(long mills)  {
        long currentTimeMillis = System.currentTimeMillis();
        while (!finished) {
            if ((System.currentTimeMillis()-currentTimeMillis) >= mills) {
                System.out.println("任务超时需要结束");
                excuteThread.interrupt();
                break;
            }
        }
        finished = false;
    }

    public static void main(String[] args) {
        ThreadEnd3 threadService = new ThreadEnd3();
        threadService.execute(()-> {
                    System.out.println(11);
                });
        threadService.shutdown(1000);
    }
}
