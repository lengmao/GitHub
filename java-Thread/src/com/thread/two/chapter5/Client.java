package com.thread.two.chapter5;

/**
 * @author scaf_xs
 * @ClassName: Client
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/6 17:41
 */

public class Client {
    public static void main(String[] args) {
        Gate gate = new Gate();
        User u1 = new User("BeiJing", "BeiLao", gate);
        User u2 = new User("GanSu", "GanLao", gate);
        User u3 = new User("GuangZhou", "GuangLao", gate);
        u1.start();
        u2.start();
        u3.start();
    }
}
