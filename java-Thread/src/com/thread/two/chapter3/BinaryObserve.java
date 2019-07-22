package com.thread.two.chapter3;

/**
 * @author scaf_xs
 * @ClassName: BinaryObserve
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/6 14:09
 */

public class BinaryObserve extends Observer {

    public BinaryObserve(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary String"+Integer.toBinaryString(subject.getState()));
    }
}
