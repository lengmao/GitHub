package com.thread.two.chapter6;

/**
 * @author scaf_xs
 * @ClassName: ReadWriteLock
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 11:03
 */

public class ReadWriteLock {

    private int readingReader = 0;
    private int waitingReader = 0;
    private int writingWriter = 0;
    private int waitingWriter = 0;
    private boolean preWrite = true;


    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preWrite) {
        this.preWrite = preWrite;
    }

    public synchronized void readLock() throws InterruptedException {
        this.waitingReader++;
        try {
            while (writingWriter > 0 || (preWrite && waitingWriter > 0)) {
                this.wait();
            }
            readingReader++;
        } finally {
            waitingReader--;
        }
    }

    public synchronized void readUnLock() {
        this.readingReader--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriter++;
        try {
            while (readingReader > 0 || writingWriter > 0) {
                this.wait();
            }
            this.writingWriter++;
        } finally {
            this.waitingWriter--;
        }
    }

    public synchronized void writeUnLock() {
        this.writingWriter--;
        this.notifyAll();
    }

}
