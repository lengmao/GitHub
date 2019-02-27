package com.springboot.demo.synchronize;

/**
 * @author scaf_xs
 * @ClassName: SynchronizedObjectCodeBlock2
 * @Description: 对象锁示例1，代码块形式
 * @date 2018/12/27 16:15
 */

public class SynchronizedObjectCodeBlock2 implements Runnable {
    static SynchronizedObjectCodeBlock2 instance = new SynchronizedObjectCodeBlock2();
    Object lock1 = new Object();
    Object lock2 = new Object();

    @Override
    public void run() {

        synchronized (lock1) {
            System.out.println("对象锁lock1，线程名：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lock1部分运行完毕");
        }
        synchronized (lock2) {
            System.out.println("对象锁lock2，线程名：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lock2部分运行完毕");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("finished");
    }
}
