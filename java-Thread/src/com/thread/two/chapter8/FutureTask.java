package com.thread.two.chapter8;

public interface FutureTask<T> {

    T call() throws InterruptedException;
}
