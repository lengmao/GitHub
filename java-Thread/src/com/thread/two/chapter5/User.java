package com.thread.two.chapter5;

/**
 * @author scaf_xs
 * @ClassName: User
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/6 17:37
 */

public class User extends Thread {

    private String name;
    private String address;
    private Gate gate;

    public User(String name, String address, Gate gate) {
        this.name = name;
        this.address = address;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(name + " Begin into...");
        while (true) {
            gate.pass(name, address);
        }
    }

}
