package com.thread.two.chapter3;

/**
 * @author scaf_xs
 * @ClassName: ObserveClient
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/6 14:26
 */

public class ObserveClient {

    public static void main(String[] args) {
        Subject subject=new Subject();
        new BinaryObserve(subject);
        new OctalObserver(subject);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        subject.setState(10);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        subject.setState(15);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        subject.setState(12);
    }
}
