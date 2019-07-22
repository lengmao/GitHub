package com.thread.two.chapter8;

/**
 * @author scaf_xs
 * @ClassName: SyncInvoker
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/10 16:45
 */

/**
 * Future          ->代表的是未来的一个凭据
 * FutureTask      ->将你的调用逻辑进行隔离
 * FutureService   ->桥接Future和FutureTask
 */
public class SyncInvoker {

    public static void main(String[] args) throws InterruptedException {
        /*String s=get();
        System.out.println(s);*/

        FutureService futureService = new FutureService();

        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISHED";
        });

        System.out.println("===============");
        System.out.println("do other things");
        Thread.sleep(100);
        System.out.println("===============");
        System.out.println(future.get());
    }

    public static String get() {
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "FINISHED";
    }
}
