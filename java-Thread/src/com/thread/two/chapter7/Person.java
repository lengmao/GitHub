package com.thread.two.chapter7;

/**
 * @author scaf_xs
 * @ClassName: Person
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 15:20
 */

public final class Person {

    private final String name;
    private final String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
