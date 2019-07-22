package com.thread.one.chapter2;

/**
 * @author scaf_xs
 * @ClassName: TicketWindow
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/31 9:23
 */

public class TicketWindow implements Runnable {

    private final static int MAX = 500;

    private static int index = 1;

    private final Object MARK = new Object();

    /**
     * 1、不管是使用同步方法还是同步代码块，涉及到共享的数据修改时一定要放到同步代码块或同步方法中
     * 2、例中的index,如果放到同步代码块或同步方法之外，会出现数据不安全（读出大于MAX的数据）
     * 3、同步的目的就是保护多线程的共享数据，在执行同步代码块或同步方法时，程序是单线程运行
     * 4、同步方法或同步代码块尽可能范围小的给共享数据加锁
     * 5、synchronized 同步方法默认是this对象锁，自定义的Object对象锁和this不是同一个锁
     */

    /**
     * 使用同步方法
     */
    @Override
    public void run() {
        while (true) {
            if (ticket())
                break;
        }
    }

    private synchronized boolean ticket() {
        if (index > MAX)
            return true;
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + "的号码是：" + (index++));
        return false;
    }


    /**
     * 使用同步代码块
     */
    /*@Override
    public void run() {
        while (true) {
            synchronized (MARK){
                if (index > MAX)
                    break;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 的号码是：" + (index++));
            }
        }
    }*/


}
