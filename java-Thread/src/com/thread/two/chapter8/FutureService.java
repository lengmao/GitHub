package com.thread.two.chapter8;

import java.util.function.Consumer;

/**
 * @author scaf_xs
 * @ClassName: FutureService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 16:59
 */

public class FutureService {

    public <T> Future<T> submit(final FutureTask<T> task) {
        AsyncFuture<T> asyncFuture = new AsyncFuture<>();
        new Thread(()->{
            try {
                T res=task.call();
                asyncFuture.done(res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return asyncFuture;
    }

    public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer) {
        AsyncFuture<T> asyncFuture = new AsyncFuture<>();
        new Thread(()->{
            try {
                T res=task.call();
                asyncFuture.done(res);
                consumer.accept(res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return asyncFuture;
    }
}
