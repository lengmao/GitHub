package com.thread.one.chapter4;

/**
 * @author scaf_xs
 * @ClassName: DeadLock
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/31 14:09
 */

public class DeadLock {

    private OtherService otherService;

    private final Object lock = new Object();

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    public void m1() {
        synchronized (lock) {
            System.out.println("m1==============");
            otherService.s1();
        }
    }

    public void m2() {
        synchronized (lock) {
            System.out.println("m2==============");
        }
    }
}
