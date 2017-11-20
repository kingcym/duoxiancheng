package com.example.demo.two.balking;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by King on 2017/9/21.
 */
public class BalkingData {
    private final String fileName;
    private String content;
    private boolean changed;

    public BalkingData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    public synchronized void change(String newContent) {
        this.content = newContent;
        this.changed = true;
    }

    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }
        doSave();
        this.changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName()+"  :  "+content);
        FileWriter fileWriter = new FileWriter(fileName, true);
        fileWriter.write(content);
        fileWriter.write("\n");
        fileWriter.flush();
    }


}
