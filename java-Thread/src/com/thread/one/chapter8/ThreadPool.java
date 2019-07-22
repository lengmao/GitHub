package com.thread.one.chapter8;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author scaf_xs
 * @ClassName: ThreadPool
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/4 15:31
 */

public class ThreadPool extends Thread {

    private int size;

    private final int queueSize;

    private static final int DEFAULT_TASK_QUEUE_SIZE = 1000;

    private static volatile int seq = 0;

    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private static final ThreadGroup GROUP = new ThreadGroup("POOL_GROUP");

    private static final LinkedList<Runnable> RUNNABLE = new LinkedList<>();

    private static final List<WorkTask> THREAD_QUEUE = new LinkedList<>();

    private final DiscardPolicy discardPolicy;

    public static final DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException(" discard this task ");
    };

    private volatile boolean destroy = false;

    private int min;

    private int active;

    private int max;


    public ThreadPool() {
        this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public ThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        for (int i = 0; i < this.min; i++) {
            createWorkTask();
        }
        this.size = min;
        this.start();
    }

    @Override
    public void run() {
        while (!destroy) {
            System.out.printf("Pool#Min:%d, Active:%d,  Max:%d,Current:%d,QueueSize:%d\n",
                    this.min, this.active, this.max, this.size, RUNNABLE.size());
            try {
                Thread.sleep(2_000);
                if (queueSize > active && size < active) {
                    for (int i = size; i < active; i++) {
                        createWorkTask();
                    }
                    System.out.println("The Pool Incremented to active.");
                    size = active;
                } else if (queueSize > max && size < max) {
                    for (int i = size; i < max; i++) {
                        createWorkTask();
                    }
                    System.out.println("The Pool Incremented to max.");
                    size = max;
                }
                synchronized (THREAD_QUEUE) {
                    if (RUNNABLE.isEmpty() && size > active) {
                        System.out.println("====================Reduce===================");
                        int release = size - active;
                        for (Iterator<WorkTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (release <= 0) {
                                break;
                            }
                            WorkTask task = it.next();
                            task.close();
                            task.interrupt();
                            it.remove();
                            release--;
                        }
                        size = active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void submit(Runnable runnable) {
        if (isDestroy())
            throw new IllegalStateException("the thread pool is already destroy and not allow submit !");
        synchronized (RUNNABLE) {
            if (RUNNABLE.size() > queueSize)
                discardPolicy.discard();
            RUNNABLE.addLast(runnable);
            RUNNABLE.notifyAll();
        }
    }

    public void shutdown() throws InterruptedException {
        while (!RUNNABLE.isEmpty()) {
            Thread.sleep(50);
        }
        synchronized (THREAD_QUEUE) {
            int initVal = THREAD_QUEUE.size();
            while (initVal > 0) {
                for (WorkTask workTask : THREAD_QUEUE) {
                    if (workTask.status == Status.BLOCKED) {
                        workTask.interrupt();
                        workTask.close();
                        initVal--;
                    } else {
                        Thread.sleep(10);
                    }
                }
            }
        }
        this.destroy = true;
        System.out.println("the thread pool disposed !");
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public int getMin() {
        return min;
    }

    public int getActive() {
        return active;
    }

    public int getMax() {
        return max;
    }

    public boolean isDestroy() {
        return this.destroy;
    }

    private void createWorkTask() {
        WorkTask task = new WorkTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    private enum Status {
        FREE, RUNNING, BLOCKED, DEAD
    }

    private static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    private static class WorkTask extends Thread {

        private volatile Status status = Status.FREE;

        public WorkTask(ThreadGroup group, String name) {
            super(group, name);
        }

        private Status getStatus() {
            return this.status;
        }

        @Override
        public void run() {
            OUTER:
            while (this.status != Status.DEAD) {
                Runnable runnable;
                synchronized (RUNNABLE) {
                    while (RUNNABLE.isEmpty()) {
                        try {
                            status = Status.BLOCKED;
                            RUNNABLE.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Closed.");
                            break OUTER;
                        }
                    }
                    runnable = RUNNABLE.removeFirst();
                }
                if (null != runnable) {
                    status = Status.RUNNING;
                    runnable.run();
                    status = Status.FREE;
                }
            }

        }

        private void close() {
            this.status = Status.DEAD;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        for (int i = 0; i < 40; i++) {
            pool.submit(() -> {
                Optional.of(Thread.currentThread().getName() + " is running").ifPresent(System.out::println);
                try {
                    Thread.sleep(4_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Optional.of(Thread.currentThread().getName() + " is finished").ifPresent(System.out::println);
            });
        }
        Thread.sleep(10_000);
        pool.shutdown();

//        Thread.sleep(20_000);
//        pool.shutdown();
//        pool.submit(() -> {
//            System.out.println("another task ! ");
//        });
    }
}
