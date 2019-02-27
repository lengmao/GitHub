package com.springboot.demo.synchronize;

/**
 * @author scaf_xs
 * @ClassName: SynchronizedClassStatic4
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/1/12 11:15
 */

public class SynchronizedClassStatic4 implements Runnable {
    static SynchronizedClassStatic4 instance1 = new SynchronizedClassStatic4();
    static SynchronizedClassStatic4 instance2 = new SynchronizedClassStatic4();

    @Override
    public void run() {
        method();
    }

    public static synchronized void method() {
        System.out.println("类锁的static形式，当前线程：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行完毕！");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("finished");
    }
}
