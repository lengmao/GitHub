package com.thread.two.chapter1;

/**
 * @author scaf_xs
 * @ClassName: SingletonObject3
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/5 15:52
 */

public class SingletonObject3 {

    /**
     * 1、volatile 保证对象的可见性(所有线程读到的是同一份数据)
     * 2、volatile 保证顺序
     */
    private static volatile SingletonObject3 instance;

    private SingletonObject3() {
        //empty
    }

    //double check and volatile
    public static SingletonObject3 getInstance() {
        if (null == instance) {
            synchronized (SingletonObject3.class) {
                if (null == instance)
                    instance = new SingletonObject3();
            }
        }
        return instance;
    }
}
