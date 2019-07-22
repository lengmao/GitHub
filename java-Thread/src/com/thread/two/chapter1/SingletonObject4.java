package com.thread.two.chapter1;

/**
 * @author scaf_xs
 * @ClassName: SingletonObject4
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/5 16:11
 */

public class SingletonObject4 {

    private SingletonObject4() {
        //empty
    }

    private static class InstanceHolder {
        private static final SingletonObject4 instance = new SingletonObject4();
    }

    public static SingletonObject4 getInstance() {
        return InstanceHolder.instance;
    }
}
