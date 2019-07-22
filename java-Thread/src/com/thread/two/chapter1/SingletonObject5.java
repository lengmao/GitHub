package com.thread.two.chapter1;

import java.util.stream.IntStream;

/**
 * @author scaf_xs
 * @ClassName: SingletonObject5
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/5 16:20
 */

public class SingletonObject5 {

    private SingletonObject5() {

    }

    private enum Single {
        INSTANCE;
        private final SingletonObject5 instance;

        Single() {
            instance = new SingletonObject5();
        }

        private SingletonObject5 getInstance() {
            return instance;
        }
    }

    public static SingletonObject5 getInstance() {
        return Single.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 40).forEach(i ->
                new Thread(() -> {
                    System.out.println(SingletonObject5.getInstance());
                }, String.valueOf(i)).start()
        );
    }
}
