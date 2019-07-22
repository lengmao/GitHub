package com.thread.two.chapter2;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author scaf_xs
 * @ClassName: WaitSet
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/5 16:53
 */

public class WaitSet {

    private static Object LOCK = new Object();

    /**
     * 1、所有的Object都会有一个wait set,用来存放调用了该对象wait()方法之后进入BLOCK状态的线程；
     * 2、线程被notify()之后，不一定立即执行
     * 3、线程从wait set 唤醒的顺序不一定是FIFO
     * 4、线程被唤醒后必须重新获取锁
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        IntStream.rangeClosed(1, 10).forEach(i ->
                new Thread(() -> {
                    synchronized (LOCK) {
                        try {
                            Optional.of(Thread.currentThread().getName() + " is come to wait set").ifPresent(System.out::println);
                            LOCK.wait();
                            Optional.of(Thread.currentThread().getName() + " is leave to wait set").ifPresent(System.out::println);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, String.valueOf(i)).start());

        Thread.sleep(5_000);

        IntStream.rangeClosed(1, 10).forEach(i -> {
            synchronized (LOCK) {
                LOCK.notify();
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
