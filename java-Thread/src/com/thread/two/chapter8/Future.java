package com.thread.two.chapter8;

public interface Future<T> {

    T get() throws InterruptedException;

}
