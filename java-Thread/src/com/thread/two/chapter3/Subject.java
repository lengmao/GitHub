package com.thread.two.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: Subject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/6 11:57
 */

public class Subject {

    private List<Observer> observers=new ArrayList<>();

    private int state;

    public Subject() {

    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        if(state==this.state)
            return;
        this.state=state;
        notifyAllObserve();
    }

    public void touch(Observer observer) {
        observers.add(observer);
    }

    private void notifyAllObserve() {
        observers.stream().forEach(Observer::update);
    }
}
