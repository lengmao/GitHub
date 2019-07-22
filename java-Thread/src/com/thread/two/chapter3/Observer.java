package com.thread.two.chapter3;

/**
 * @author scaf_xs
 * @ClassName: Observer
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/6 11:57
 */

public abstract class Observer {

     Subject subject;

    public Observer(Subject subject){
        this.subject=subject;
        this.subject.touch(this);
    }

    public abstract void update();

}
