package com.thread.one.chapter1;

/**
 * @author scaf_xs
 * @ClassName: ThreadService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/30 17:24
 */

public class ThreadService {

    private Thread executeThread;

    private static Boolean flag = false;

    public void execute(Runnable task) {

        executeThread = new Thread() {
            @Override
            public void run() {
                Thread inner = new Thread(task);
                inner.setDaemon(true);
                inner.start();
                try {
                    inner.join();
                    flag = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executeThread.start();
    }

    public void shutDown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!flag) {
            if (System.currentTimeMillis() - currentTime >= mills) {
                System.out.println("请求超时，将断开链接！！");
                executeThread.interrupt();
                break;
            }
            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("执行线程被打断！");
                break;
            }
        }
        flag = false;
    }
}
