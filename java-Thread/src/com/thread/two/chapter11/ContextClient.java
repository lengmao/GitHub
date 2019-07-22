package com.thread.two.chapter11;

import java.util.stream.IntStream;

/**
 * @author scaf_xs
 * @ClassName: ContextClient
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/11 15:48
 */

public class ContextClient {

    public static void main(String[] args) {

        IntStream.rangeClosed(1, 5).forEach(i ->
                new Thread(new ExecutionTask()).start());
    }
}
