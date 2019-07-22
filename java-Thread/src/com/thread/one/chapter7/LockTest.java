package com.thread.one.chapter7;

import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

/**
 * @author scaf_xs
 * @ClassName: LockTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/3 16:08
 */

public class LockTest {

    static final BooleanLock booleanLock = new BooleanLock();

    public static void main(String[] args) throws InterruptedException {
        Stream.of("T1", "T2", "T3", "T4").forEach(name ->
                new Thread(() -> {
                    try {

                        booleanLock.lock(10L);
                        Optional.of("Thread " + name + " get the lock").ifPresent(System.out::println);
                        work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        booleanLock.unLock();
                    }
                }, name).start()
        );
    }

    /**
     * 业务执行逻辑和线程分配锁的代码分离
     */
    public static void work() {
        Optional.of(Thread.currentThread().getName() + " is working...").ifPresent(System.out::println);
        try {
            //TODO (执行自己的业务)
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
