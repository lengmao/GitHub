package com.thread.one.chapter4;

/**
 * @author scaf_xs
 * @ClassName: Test
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/31 14:18
 */

public class Test {

    public static void main(String[] args) {
        OtherService otherService=new OtherService();
        DeadLock deadLock=new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread(){
            @Override
            public void run() {
                while (true){
                    deadLock.m1();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while (true){
                    otherService.s2();
                }
            }
        }.start();
    }
}
