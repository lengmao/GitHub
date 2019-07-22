package com.thread.two.chapter7;

/**
 * @author scaf_xs
 * @ClassName: StringTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 15:28
 */

public class StringTest {

    public static void main(String[] args) {
        String s1="hello";
        String s2=s1.replace("l","k");

        System.out.println(s1.getClass()+"======"+s1.hashCode());
        System.out.println(s2.getClass()+"======"+s2.hashCode());
    }
}
