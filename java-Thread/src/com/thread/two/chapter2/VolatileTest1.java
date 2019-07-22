package com.thread.two.chapter2;

/**
 * @author scaf_xs
 * @ClassName: VolatileTest1
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/5 17:51
 */

public class VolatileTest1 {

    private volatile static int INIT_VALUE = 0;

    private static final int MAX_VALUE = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_VALUE) {
                if (localValue != INIT_VALUE) {
                    System.out.printf("the value update to %d\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "READER").start();

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_VALUE) {
                System.out.printf("update the value to %d\n", ++localValue);
                INIT_VALUE=localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATE").start();
    }
}
