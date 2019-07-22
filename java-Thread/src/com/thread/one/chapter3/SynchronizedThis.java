package com.thread.one.chapter3;

/**
 * @author scaf_xs
 * @ClassName: SynchronizedThis
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/31 11:58
 */

public class SynchronizedThis {

    public synchronized void m1(){
        try {
            Thread.sleep(3_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    public synchronized void m2(){
        try {
            Thread.sleep(3_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
