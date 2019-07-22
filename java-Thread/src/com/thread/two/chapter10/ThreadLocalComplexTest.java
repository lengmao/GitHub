package com.thread.two.chapter10;

import java.util.HashMap;
import java.util.Map;

/**
 * @author scaf_xs
 * @ClassName: ThreadLocalComplexTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/11 15:06
 */

/**
 * 自己实现ThreadLocal，原理和ThreadLocal一样
 * @param <T>
 */
public class ThreadLocalComplexTest<T> {

    ThreadLocalComplexTest<T> threadLocal=new ThreadLocalComplexTest<>();

        private final Map<Thread,T> map=new HashMap<>();

    public void set(T t){
        synchronized (this){
            Thread thread=Thread.currentThread();
            map.put(thread,t);
        }
    }

    public T get(){
        synchronized (this){
            Thread key=Thread.currentThread();
            T value=map.get(key);
            if(value==null){
                return initValue();
            }
            return value;
        }
    }

    private T initValue() {
        return null;
    }


}
