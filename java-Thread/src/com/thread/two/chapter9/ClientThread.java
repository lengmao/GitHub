package com.thread.two.chapter9;

import java.util.Random;

/**
 * @author scaf_xs
 * @ClassName: ClientThread
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 20:57
 */

public class ClientThread extends Thread {

    private final RequestQueue queue;
    private final String value;
    private final Random random;

    public ClientThread(RequestQueue queue, String value) {
        this.random = new Random(System.currentTimeMillis());
        this.queue = queue;
        this.value = value;
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Client->" + value);
            queue.putRequest(new Request(value));
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
