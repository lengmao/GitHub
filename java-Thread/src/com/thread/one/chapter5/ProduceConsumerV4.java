package com.thread.one.chapter5;

import java.util.stream.Stream;

/**
 * @author scaf_xs
 * @ClassName: ProduceConsumerV4
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/31 17:18
 */

public class ProduceConsumerV4 {

    private int i = 0;

    private final Object LOCK = new Object();

    private volatile boolean isProduce = false;

    public void produce() {
        synchronized (LOCK) {
            while (isProduce) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            LOCK.notifyAll();
            System.out.println("P=>" + i);
            isProduce = true;
        }
    }

    public void consumer() {
        synchronized (LOCK) {
            while (!isProduce) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C=>" + i);
            LOCK.notifyAll();
            isProduce = false;
        }
    }


    public static void main(String[] args) {

        ProduceConsumerV4 pv4 = new ProduceConsumerV4();

        Stream.of("P1", "P2", "P3", "P4").forEach(n ->
                new Thread() {
                    @Override
                    public void run() {
                        while (true)
                            pv4.produce();
                    }
                }.start()
        );

        Stream.of("C1", "C2", "C3").forEach(n ->
                new Thread() {
                    @Override
                    public void run() {
                        while (true)
                            pv4.consumer();
                    }
                }.start()
        );
    }
}
