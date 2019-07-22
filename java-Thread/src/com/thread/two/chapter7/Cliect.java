package com.thread.two.chapter7;

import java.util.stream.IntStream;

/**
 * @author scaf_xs
 * @ClassName: Cliect
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 15:22
 */

public class Cliect {

    public static void main(String[] args) {
        Person person=new Person("Alex","GanSu");

        IntStream.rangeClosed(1,5).forEach(i->
            new PersonThread(person).start()
        );
    }
}
