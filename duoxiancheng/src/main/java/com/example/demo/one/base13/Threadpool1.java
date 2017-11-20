package com.example.demo.one.base13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by King on 2017/9/11.
 */

/**
 * 线程池
 * 初级版，只要简单的size和任务队列
 */
public class Threadpool1 {
    private final int size;
    private final static  int DEFAULT_SIZE = 10;
    //任务队列
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    private static volatile int seq = 0;
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";
    private final static ThreadGroup group = new ThreadGroup("Pool_Group");

    /**
     * 定义线程状态
     */
    private enum TaskState {
        FRIE,RUNNING,BLICKED,DEAD
    }

    public Threadpool1() {
       this(DEFAULT_SIZE);
    }
    public Threadpool1(int size) {
        this.size = size;
        init();
    }

    /**
     * 初始化,
     * 初始化的时候加载了size个线程，处于等待中
     */
    private void init() {
        for (int i=0;i<size;i++){
            createWorkerTask();
        }
    }

    /**
     * 提供外部调用方法
     */
    public void submit(Runnable runnable){
        synchronized (TASK_QUEUE){
            TASK_QUEUE.add(runnable);
            TASK_QUEUE.notifyAll();
        }
    }



    private void createWorkerTask(){
        WorkerTask workerTask = new WorkerTask(group,THREAD_PREFIX+(seq++));
        workerTask.start();
        THREAD_QUEUE.add(workerTask);
    }



    private static class WorkerTask extends Thread {
        private volatile TaskState taskState = TaskState.FRIE;

        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            int j =1;
            OUTER:
            while (this.taskState != TaskState.DEAD){
                Runnable runnable;
                synchronized (TASK_QUEUE){
                    while (TASK_QUEUE.isEmpty()){
                        try {
                            this.taskState = TaskState.BLICKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }
                if (runnable != null){
                    this.taskState = TaskState.RUNNING;
                    runnable.run();
                    this.taskState = TaskState.FRIE;
                }
            }
        }

        //获取线程状态
        public TaskState getTaskState(){
            return this.taskState;
        }

        //线程状态关闭
        public void close(){
            this.taskState = TaskState.DEAD;
        }
    }



    public static void main(String[] args) {
        Threadpool1 threadpool = new Threadpool1();
        for (int i = 0; i < 40; i++) {
            threadpool.submit(() -> {
                System.out.println("The runnable  be serviced by " + Thread.currentThread() + " start.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable be serviced by " + Thread.currentThread() + " finished.");
            });
        }







    }

}
