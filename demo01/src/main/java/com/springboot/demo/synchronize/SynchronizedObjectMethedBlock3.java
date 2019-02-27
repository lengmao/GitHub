package com.springboot.demo.synchronize;

/**
 * @author scaf_xs
 * @ClassName: SynchronizedObjectMethedBlock3
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2018/12/27 16:46
 */

public class SynchronizedObjectMethedBlock3 implements Runnable {
    static SynchronizedObjectMethedBlock3 instance = new SynchronizedObjectMethedBlock3();

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("finished");
    }

    @Override
    public void run() {
        method();
    }

    public synchronized void method() {
        System.out.println("对象锁方法修饰，线程名：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行完毕");
    }


}
