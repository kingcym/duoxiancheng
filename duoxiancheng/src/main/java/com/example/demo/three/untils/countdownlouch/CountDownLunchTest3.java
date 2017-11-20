package com.example.demo.three.untils.countdownlouch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: King
 * @Description:
 * @Data: 2017/10/9 9:44
 */
public class CountDownLunchTest3 {
    static class Event {
        int id = 0;

        public Event(int id) {
            this.id = id;
        }
    }

    static class TaskBatch {
        private CountDownLatch latch;

        public TaskBatch(int size) {
            this.latch = new CountDownLatch(size);
        }

//        public void startWatch() {
//
//        }

        public  void done(Table table)  {

                latch.countDown();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (latch.getCount() == 0) {
                    System.out.println("table"+Thread.currentThread().getName()+"名：" + table.tableName + "finsh,【" + table + "】");
            }
        }


    }

    static class Table {
        String tableName;
        long sourceRecordCount = 10;
        long targetCount;
        String sourceColumnSchema = "ASDFGH";
        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetCount=" + targetCount +
                    ", sourceColumnSchema='" + sourceColumnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    private static List<Table> capture(Event event) {
        ArrayList<Table> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Table("table-" + event.id + "-" + i, i * 1000));
        }
        return list;
    }

    public static void main(String[] args) {
        Event[] events = {new Event(1), new Event(2)};
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (Event event : events) {
            List<Table> tables = capture(event);
            for (Table table : tables) {
                TaskBatch taskBatch = new TaskBatch(2);
                TruestSourceRecordCount truestSourceRecordCount = new TruestSourceRecordCount(table, taskBatch);
                TruestSourceColums truestSourceColums = new TruestSourceColums(table, taskBatch);
                executorService.submit(truestSourceRecordCount);
                executorService.submit(truestSourceColums);
            }
        }

    }

    static class TruestSourceRecordCount implements Runnable {
        private final Table table;
        private final TaskBatch taskBatch;
        TruestSourceRecordCount(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
//                System.out.println("TruestSourceRecordCount:"+Thread.currentThread().getName());

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetCount = table.sourceRecordCount;
            taskBatch.done(table);
        }
    }


    static class TruestSourceColums implements Runnable {
        private final Table table;
        private final TaskBatch taskBatch;

        TruestSourceColums(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
 //               System.out.println("TruestSourceColums:"+Thread.currentThread().getName());

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            table.targetColumnSchema = table.sourceColumnSchema;
            taskBatch.done(table);
        }
    }
}
