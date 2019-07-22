package com.thread.two.chapter6;

import java.util.Random;

/**
 * @author scaf_xs
 * @ClassName: WriteWork
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 12:00
 */

public class WriteWork extends Thread {

    private static final Random random = new Random(System.currentTimeMillis());

    private SharedData sharedData;

    private final String filter;

    private int index = 0;

    public WriteWork(SharedData sharedData, String filter) {
        this.sharedData = sharedData;
        this.filter = filter;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                sharedData.wirte(c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filter.charAt(index);
        index++;
        if (index >= filter.length()) {
            index = 0;
        }
        return c;
    }
}
