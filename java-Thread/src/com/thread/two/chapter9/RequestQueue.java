package com.thread.two.chapter9;

import java.util.LinkedList;

/**
 * @author scaf_xs
 * @ClassName: RequestQueue
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 20:43
 */

public class RequestQueue {

    private LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest() {
        synchronized (queue) {
            while (queue.size() <= 0) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return queue.removeFirst();
        }
    }

    public void putRequest(Request request) {
        synchronized (queue) {
            queue.addFirst(request);
            queue.notifyAll();
        }
    }
}
