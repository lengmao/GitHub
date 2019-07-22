package com.thread.two.chapter3;

/**
 * @author scaf_xs
 * @ClassName: OctalObserver
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/6 14:10
 */

public class OctalObserver extends Observer {


    public  OctalObserver(Subject subject){
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String"+Integer.toOctalString(subject.getState()));
    }
}
