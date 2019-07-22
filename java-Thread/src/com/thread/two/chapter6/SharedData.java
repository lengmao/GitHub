package com.thread.two.chapter6;

/**
 * @author scaf_xs
 * @ClassName: SharedData
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 11:29
 */

public class SharedData {

    private final char[] buffer;

    private ReadWriteLock lock = new ReadWriteLock();

    public SharedData(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < size; i++) {
            this.buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            this.lock.readLock();
            return doRead();
        } finally {
            this.lock.readUnLock();
        }
    }

    public void wirte(char c) throws InterruptedException {
        try {
            this.lock.writeLock();
            this.doWrite(c);
        } finally {
            this.lock.writeUnLock();
        }
    }

    private char[] doRead() {
        char[] newBuffer = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++)
            newBuffer[i] = buffer[i];
        slowly(50);
        return newBuffer;
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly(10);
        }
    }

    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
