package com.thread.one.chapter5;

import java.util.stream.Stream;

/**
 * @author scaf_xs
 * @ClassName: ProduceConsumerV3
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/31 15:49
 */

public class ProduceConsumerV3 {
    private int i = 0;

    private final Object LOCK = new Object();

    private volatile boolean isProduce = false;

    /**
     * 1、生产者线程A如果已经进行了生产，那么调用wait()方法，将锁让出，自己处于等待状态
     * 2、消费者线程B获取锁后，调用notify()方法通知锁对象的等待序列，使得生产者线程A从等待队列进入阻塞队列
     * 3、生产者线程A进入阻塞队列后，直至消费者线程B释放索后，生产者线程A竞争得到索从wait()方法后执行
     */

    public void produce() {
        synchronized (LOCK) {
            if (isProduce) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                LOCK.notify();
                System.out.println("P=>" + i);
                isProduce = true;
            }
        }
    }

    public void consumer() {
        synchronized (LOCK) {
            if (isProduce) {
                LOCK.notify();
                System.out.println("C=>" + i);
                isProduce = false;
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        ProduceConsumerV3 pc3 = new ProduceConsumerV3();

        /**
         * 以下方式执行多线程
         * 1、出现程序假死的状态
         * 2、原因：notify()方法只唤醒this object monitor下的线程
         * 3、创建多个生产者和消费者线程，最终使得每个线程都处于wait状态
         *
         */
        Stream.of("P1", "P2").forEach(n ->
                new Thread() {
                    @Override
                    public void run() {
                        while (true)
                            pc3.produce();
                    }
                }.start()
        );

        Stream.of("C1", "C2").forEach(n ->
                new Thread() {
                    @Override
                    public void run() {
                        while (true)
                            pc3.consumer();
                    }
                }.start()
        );
    }

}
