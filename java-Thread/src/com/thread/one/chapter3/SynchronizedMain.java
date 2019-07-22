package com.thread.one.chapter3;

/**
 * @author scaf_xs
 * @ClassName: SynchronizedMain
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/31 12:00
 */

public class SynchronizedMain {

     static SynchronizedThis synchronizedThis = new SynchronizedThis();

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                synchronizedThis.m1();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                synchronizedThis.m2();
            }
        }.start();
    }

}
