package com.thread.two.chapter8;

/**
 * @author scaf_xs
 * @ClassName: AsyncFuture
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 16:53
 */

public class AsyncFuture<T> implements Future<T> {

    private volatile boolean isDone = false;

    private T result;

    public void done(T result){
        synchronized (this){
            this.result=result;
            this.isDone=true;
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (!isDone) {
                this.wait();
            }
        }
        return result;
    }
}
