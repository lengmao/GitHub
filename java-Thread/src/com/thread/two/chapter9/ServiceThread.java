package com.thread.two.chapter9;

import java.util.Random;

/**
 * @author scaf_xs
 * @ClassName: ServiceThread
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/11 14:03
 */

public class ServiceThread extends Thread {

    private final RequestQueue queue;
    private Random random;
    private volatile boolean flag = false;

    public ServiceThread(RequestQueue queue) {
        this.random = new Random(System.currentTimeMillis());
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!flag) {
            Request request = queue.getRequest();
            if(request==null){
                System.out.println("Request is empty ");
                continue;
            }
            System.out.println("Service-> " + request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                return;
            }
        }

    }

    public void close() {
        this.flag = true;
        this.interrupt();
    }
}
