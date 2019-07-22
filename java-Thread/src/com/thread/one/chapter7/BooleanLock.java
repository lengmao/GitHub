package com.thread.one.chapter7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

/**
 * @author scaf_xs
 * @ClassName: BooleanLock
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/3 15:46
 */

public class BooleanLock implements Lock {

    /**
     * 1、initValue为true，说明已经获取到锁
     * 2、initValue为false，说明锁是空闲着的，线程可以去获取这个锁
     */
    private boolean initValue;

    private Collection<Thread> threadCollection = new ArrayList<>();

    private Thread current;


    public BooleanLock() {
        this.initValue = false;
    }

    /**
     * 所有线程同时进来，抢到所的执行自己的任务，抢不到锁的执行wait()方法
     */
    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            //将当前线程加入到集合中
            threadCollection.add(Thread.currentThread());
            //当前线程取得所，执行wait(),释放锁
            this.wait();
        }

        //如果当前线程没有取得锁，从collect中删除，initValue值设置为true
        threadCollection.remove(Thread.currentThread());
        this.initValue = true;

        /**
         * 1、此处设置当前线程，便于释放锁是做判断
         * 2、如果不做当前线程的设置，那么unlock()方法会被任何线程调用
         */
        this.current = Thread.currentThread();
    }

    @Override
    public synchronized void lock(Long mills) throws InterruptedException, TimeoutException {
        if (mills <= 0)
            lock();
        Long hasRemain = mills;
        Long endTime = System.currentTimeMillis() + mills;
        while (initValue) {
            if (hasRemain <= 0)
                throw new TimeOutException(Thread.currentThread().getName() + " time out");
            this.wait(mills);
            hasRemain=endTime-System.currentTimeMillis();
        }
        this.initValue=true;
        this.current=Thread.currentThread();
    }

    @Override
    public synchronized void unLock() {
        if (Thread.currentThread() == current) {
            this.initValue = false;
            Optional.of(Thread.currentThread() + "release the monitor").ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getCollectionThread() {
        return Collections.unmodifiableCollection(threadCollection);
    }

    @Override
    public int getCollectionSize() {
        return threadCollection.size();
    }
}
