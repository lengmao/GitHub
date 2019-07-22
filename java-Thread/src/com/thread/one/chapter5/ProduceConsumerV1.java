package com.thread.one.chapter5;


/**
 * @author scaf_xs
 * @ClassName: ProduceConsumerV1
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/31 15:07
 */

public class ProduceConsumerV1 {

    private int i = 0;

    private final Object LOCK = new Object();

    public void produce() {
        synchronized (LOCK) {
            System.out.println("P=> " + (i++));
        }
    }

    public void consumer() {
        synchronized (LOCK) {
            System.out.println("C=> " + i);
        }
    }

    public static void main(String[] args) {
        ProduceConsumerV1 pc = new ProduceConsumerV1();

        new Thread("P") {
            @Override
            public void run() {
               while (true)
                   pc.produce();
            }
        }.start();


        new Thread("C") {
            @Override
            public void run() {
                while (true)
                    pc.consumer();
            }
        }.start();
    }
}

