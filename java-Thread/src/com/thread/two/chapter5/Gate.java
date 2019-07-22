package com.thread.two.chapter5;

/**
 * @author scaf_xs
 * @ClassName: Gate
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/6 17:31
 */

public class Gate {
    private int count;
    private String name;
    private String address;

    public synchronized void pass(String name, String address) {
        this.count++;
        this.name = name;
        this.address = address;
        check();
    }

    private void check() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("****************BLOCKED**************"+toString());
        }
        System.out.println(toString());
    }

    @Override
    public synchronized String toString() {
        return "count:"+count+",name:"+name+",address:"+address;
    }
}
