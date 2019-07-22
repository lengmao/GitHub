package com.thread.two.chapter6;

/**
 * @author scaf_xs
 * @ClassName: ReadWork
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 14:09
 */

public class ReadWork extends Thread {
    private final SharedData sharedData;

    public ReadWork(SharedData sharedData){
        this.sharedData=sharedData;
    }

    @Override
    public void run() {
        try{
            while (true){
                char[] c=sharedData.read();
                System.out.println(Thread.currentThread().getName()+" reads "+ String.valueOf(c));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
