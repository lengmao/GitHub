package com.thread.two.chapter4;

import java.util.Arrays;

/**
 * @author scaf_xs
 * @ClassName: LifeCycleClient
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/6 16:41
 */

public class LifeCycleClient {
    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1","2"));
    }
}
