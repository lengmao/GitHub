package com.thread.one.chapter5;

/**
 * @author scaf_xs
 * @ClassName: ProduceConsumerV2
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/31 15:24
 */

public class ProduceConsumerV2 {

    private int i = 0;

    private final Object LOCK = new Object();

    private volatile boolean isProduce = false;

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

        ProduceConsumerV2 pc2 = new ProduceConsumerV2();

        new Thread("P") {
            @Override
            public void run() {
                while (true)
                    pc2.produce();
            }
        }.start();

        new Thread("C") {
            @Override
            public void run() {
                while (true)
                    pc2.consumer();
            }
        }.start();
    }
}
