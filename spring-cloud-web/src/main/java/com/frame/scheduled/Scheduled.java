package com.frame.scheduled;/**
 * Created by scaf_xs on 2017/12/16.
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * @author scaf_xs
 * @ClassName: Scheduled
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/12/16 14:54
 */

public class Scheduled {

    public static void main(String[] args){
//        Runnable runnable=new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println("执行任务！！");
//                Predicate<Integer> atLeast5 = x -> x > 5;
//                BinaryOperator<Long> addLongs = (x, y) -> x + y;
//                Runnable helloWorld=()-> System.out.println("Hello Lambda Expressions");
//            }
//        };
        Runnable runnable=() ->{
            System.out.println("执行任务！！");
        };

        ScheduledExecutorService service=  Executors.newSingleThreadScheduledExecutor();
                service.scheduleAtFixedRate(runnable,5,2, TimeUnit.SECONDS);
    }
}
