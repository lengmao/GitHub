package com.thread.two.chapter1;

/**
 * @author scaf_xs
 * @ClassName: SingletonObject2
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/5 15:49
 */

public class SingletonObject2 {

    private static SingletonObject2 instance;

    private SingletonObject2() {
        //empty
    }

    public synchronized static SingletonObject2 getInstance() {
        if (instance == null)
            instance = new SingletonObject2();
        return instance;
    }
}
