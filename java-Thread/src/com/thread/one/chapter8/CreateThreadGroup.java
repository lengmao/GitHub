package com.thread.one.chapter8;

/**
 * @author scaf_xs
 * @ClassName: CreateThreadGroup
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/4 14:04
 */

public class CreateThreadGroup {
    public static void main(String[] args) {

        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "T1") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        };
        t1.start();

        ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");
        Thread t2 = new Thread(tg2, "T2") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.start();
        System.out.println(tg1.activeCount());
        System.out.println(tg1.activeGroupCount());
    }
}
