package com.thread.two.chapter7;

/**
 * @author scaf_xs
 * @ClassName: PersonThread
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 15:22
 */

public class PersonThread extends Thread {

    private final Person person;

    public PersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+" print "+ person.toString());
        }
    }
}
