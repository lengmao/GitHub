package com.thread.two.chapter12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author scaf_xs
 * @ClassName: BalkingData
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/11 16:51
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

    public synchronized void change(String newContent){
        this.content=newContent;
        this.changed=true;
    }

    public synchronized void save(){
        if(!changed){
            return;
        }

        doSave();
        this.changed=false;
    }

    private void doSave() {
        System.out.println(Thread.currentThread().getName()+" calls to save, content is "+content);
        try {
            Writer writer=new FileWriter(fileName,true);
            writer.write(content);
            writer.write("\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
