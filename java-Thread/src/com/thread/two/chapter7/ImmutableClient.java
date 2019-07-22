package com.thread.two.chapter7;

/**
 * @author scaf_xs
 * @ClassName: ImmutableClient
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 16:24
 */

public class ImmutableClient {

    public static void main(String[] args) throws InterruptedException {

        long satrtTime = System.currentTimeMillis();
        //33199
        // ImmutableObj immutableObj = new ImmutableObj("Alex");

        //32513
        SyncObj syncObj = new SyncObj();
        syncObj.setName("Alex");
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (long l = 0L; l < 1000000; l++) {
                    System.out.println(Thread.currentThread().getName() + syncObj.toString());
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (long l = 0L; l < 1000000; l++) {
                    System.out.println(Thread.currentThread().getName() + syncObj.toString());
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long endTime = System.currentTimeMillis();
        System.out.println("use time :" + (endTime - satrtTime));
    }
}

final class ImmutableObj {

    final private String name;

    ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}

class SyncObj {
    private String name;

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "[" + name + "]";
    }
}
