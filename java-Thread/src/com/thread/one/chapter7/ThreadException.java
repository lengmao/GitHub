package com.thread.one.chapter7;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author scaf_xs
 * @ClassName: ThreadException
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/4 11:11
 */

public class ThreadException {
    private static final int A = 10;
    private static final int B = 0;

    public static void main(String[] args) {
        /**
         * 1、复写的run()方法中的异常只能捕获，你能抛出。
         * 2、使用setUncaughtExceptionHandler(Thread,e)方法可以获取到运行过程中的异常；
         */
        Thread t=new Thread(() -> {
            try {
                Thread.sleep(1_000);
                int res = A / B;
                System.out.println(res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        );
        t.setUncaughtExceptionHandler((Thread,e)->{
            System.out.println(e);
            System.out.println(Thread);
        });
        t.start();

//        Test1 test1 = new Test1();
//        test1.t1();
    }

    static class Test1 {
        Test2 test2 = new Test2();

        public void t1() {
            test2.t2();
        }
    }

    static class Test2 {
        /**
         * 此方法中的Thread.currentThread().getStackTrace()追踪到方法的调用顺序
         */
        public void t2() {
            Arrays.asList(Thread.currentThread().getStackTrace()).stream()
                    .filter(e -> !e.isNativeMethod())
                    .forEach(e -> Optional.of(e.getClassName() + ":" + e.getMethodName()).ifPresent(System.out::println));
        }
    }
}
