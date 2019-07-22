package com.thread.two.chapter6;

/**
 * @author scaf_xs
 * @ClassName: ReadWriteLockClient
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 14:14
 */

public class ReadWriteLockClient {

    public static void main(String[] args) {
        final SharedData sharedData=new SharedData(10);
        new ReadWork(sharedData).start();
        new ReadWork(sharedData).start();
        new ReadWork(sharedData).start();
        new ReadWork(sharedData).start();
        new ReadWork(sharedData).start();
        new ReadWork(sharedData).start();

        new WriteWork(sharedData,"qwertyuiop").start();
        new WriteWork(sharedData,"asdfghjkl").start();

    }
}
