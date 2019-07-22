package com.thread.two.chapter2;

/**
 * @author scaf_xs
 * @ClassName: VolatileTest2
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/5 22:15
 */

public class VolatileTest2 {

    private static int INIT_VALUE = 0;

    private static final int MAX_VALUE = 50;

    public static void main(String[] args) {
        new Thread("T1"){
            @Override
            public void run() {
                while (INIT_VALUE<MAX_VALUE){
                    System.out.println("T1==>>"+(++INIT_VALUE));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread("T2"){
            @Override
            public void run() {
                while (INIT_VALUE<MAX_VALUE){
                    System.out.println("T2==>>"+(++INIT_VALUE));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
