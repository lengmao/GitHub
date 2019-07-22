package com.thread.two.chapter1;

/**
 * @author scaf_xs
 * @ClassName: SingletonObject1
 * @Description: 单例模式设计1
 * @date 2019/6/5 15:36
 */

public class SingletonObject1 {

    /**
     * can't lazy load
     */
    private static final SingletonObject1 instance=new SingletonObject1();

    private SingletonObject1(){
        //empty
    }

    public static SingletonObject1 getInstance() {
        return instance;
    }
}
