package com.thread.one.chapter7;

import java.util.Collection;
import java.util.concurrent.TimeoutException;

public interface Lock {

    class TimeOutException extends TimeoutException {
        public TimeOutException(String message) {
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(Long mills) throws InterruptedException,TimeoutException;

    void unLock();

    Collection<Thread> getCollectionThread();

    int getCollectionSize();

}
