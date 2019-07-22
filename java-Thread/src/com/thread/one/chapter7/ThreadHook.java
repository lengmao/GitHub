package com.thread.one.chapter7;

/**
 * @author scaf_xs
 * @ClassName: ThreadHook
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/4 10:39
 */

public class ThreadHook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("the application will exit.");
            notifyAndRelease();
        }));
        int i = 0;
        while (true) {
            try {
                Thread.sleep(1_000);
                System.out.println("i am working...");
            } catch (InterruptedException e) {
                //ignore
            }
            i++;
            if(i>10)
                throw new RuntimeException("error");
        }
    }

    public static void notifyAndRelease() {
        System.out.println("notify the admin...");
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {

        }
        System.out.println("release source.....");
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {

        }
        System.out.println("the source is release and down.");
    }


}
