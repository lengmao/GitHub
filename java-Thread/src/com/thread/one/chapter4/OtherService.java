package com.thread.one.chapter4;

/**
 * @author scaf_xs
 * @ClassName: OtherService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/31 14:10
 */

public class OtherService {

    private DeadLock deadLock;

    private final Object locked=new Object();

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    public void s1(){
        synchronized (locked){
            System.out.println("s1===========");
        }
    }

    public void s2(){
        synchronized (locked){
            System.out.println("s2===========");
            deadLock.m2();
        }
    }

}
