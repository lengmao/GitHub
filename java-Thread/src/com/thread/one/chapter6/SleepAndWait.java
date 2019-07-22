package com.thread.one.chapter6;

import java.util.stream.Stream;

/**
 * @author scaf_xs
 * @ClassName: SleepAndWait
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/3 9:50
 */

public class SleepAndWait {

    /**
     * 1、sleep()是Thread的方法，wait()是Object的方法
     * 2、sleep()方法对象不会释放锁，wait()方法会释放对象所并加入到对象锁的等待队列中
     * 3、使用sleep()方法，不需要使用synchronized关键字定义一个同步块，但是wait()方法需要使用synchronized同步
     * 4、使用sleep()方法不需要被唤醒，wait()方法需要使用notify()或notifyAll()唤醒(除了使用wait(mills)加参数的方法)
     */

    private final static Object LOCK = new Object();

    public static void main(String[] args) {
        Stream.of("T1", "T2").forEach(name ->
                new Thread(name) {
                    @Override
                    public void run() {
                        //m1(name);
                        m2(name);
                    }
                }.start()
        );
    }

    /**
     * 1、m1()方法如果不使用synchronized同步块，多个线程会同时运行，
     * 2、如果加上synchronized同步块，会依次运行，因为sleep()方法不会释放对象锁
     *
     * @param name
     */
    public static void m1(String name) {
        synchronized (LOCK) {
            try {
                System.out.println("Thread enter.  and Thread name is :" + name);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 1、此处使用wait()方法，如果不使用synchronized关键字，程序运行时会报IllegalMonitorStateException
     * 2、使用synchronized方法，也会多线程同时运行，因为wait()方法会释放掉对象所，并加入到对象锁的等待队列中
     * 3、如果不唤醒wait()方法，程序会处于假死状态
     */
    public static void m2(String name) {
        synchronized (LOCK) {
            try {
                System.out.println("Thread enter.  and Thread name is :" + name);
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
