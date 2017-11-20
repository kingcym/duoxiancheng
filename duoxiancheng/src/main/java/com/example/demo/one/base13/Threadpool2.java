package com.example.demo.one.base13;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by King on 2017/9/11.
 */

/**
 * 线程池
 * 增加拒绝策略（抛出）及停止方法
 */
public class Threadpool2 {
    private final int size;
    private final int queueSize;
    private final DiscardPolicy discardPolicy;

    private final static  int DEFAULT_SIZE = 10;

    //设置任务队列最大值
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    //任务队列
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    private static volatile int seq = 0;
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";
    private final static ThreadGroup group = new ThreadGroup("Pool_Group");

    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("队列超过最大值.");
    };

    /**
     * 定义线程状态
     */
    private enum TaskState {
        FRIE,RUNNING,BLICKED,DEAD
    }

    public Threadpool2() {
       this(DEFAULT_SIZE,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARD_POLICY);
    }
    public Threadpool2(int size,int queueSize,DiscardPolicy discardPolicy) {
        this.size = size;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
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
     * 定义异常
     */

    public static class DiscardException extends RuntimeException{
        public DiscardException(String message) {
            super(message);
        }
    }

    /**
     * 定义拒绝策略
     */
    public interface DiscardPolicy{
        void discard() throws DiscardException;
    }

    /**
     * 停止方法
     */
    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }
        int size = THREAD_QUEUE.size();
        while (size > 0) {
            for (WorkerTask task:THREAD_QUEUE){
                if (task.getTaskState()==TaskState.BLICKED) {
                    task.interrupt();
                    task.close();
                    size--;
                } else {
                    Thread.sleep(10);
                }
            }
        }
        System.out.println("线程全部停止");
    }

    /**
     * 提供外部调用方法
     */
    public void submit(Runnable runnable) {
        synchronized (TASK_QUEUE){
            if (TASK_QUEUE.size()>queueSize) {
                discardPolicy.discard();
            }
            TASK_QUEUE.add(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    private void createWorkerTask() {
        WorkerTask workerTask = new WorkerTask(group, THREAD_PREFIX + (seq++));
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
            OUTER:
            while (this.taskState != TaskState.DEAD){
                Runnable runnable;
                synchronized (TASK_QUEUE){
                    while (TASK_QUEUE.isEmpty()){
                        try {
                            this.taskState = TaskState.BLICKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            System.out.println("线程被打断");
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


    /**
     * 40个任务，10条线程执行
     * @param args
     */
    public static void main(String[] args) {
        try {
            Threadpool2 threadpool = new Threadpool2();
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
            threadpool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
