package com.thread.two.chapter10;

import java.util.Random;

/**
 * @author scaf_xs
 * @ClassName: ThreadLocalSimpleTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/11 15:01
 */

public class ThreadLocalSimpleTest {

    private static final ThreadLocal threadLocal=new ThreadLocal();

    private static Random random=new Random();

    public static void main(String[] args) throws InterruptedException {

        Thread t1=new Thread(()->{
           threadLocal.set("Thread-T1");
            try {
                System.out.println(Thread.currentThread().getName()+"  "+threadLocal.get());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2=new Thread(()->{
            threadLocal.set("Thread-T2");
            try {
                System.out.println(Thread.currentThread().getName()+"  "+threadLocal.get());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(Thread.currentThread().getName()+"  "+threadLocal.get());

    }
}
