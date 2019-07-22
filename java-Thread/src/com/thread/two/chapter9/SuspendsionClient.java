package com.thread.two.chapter9;

/**
 * @author scaf_xs
 * @ClassName: SuspendsionClient
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/11 14:16
 */

public class SuspendsionClient {

    public static void main(String[] args) throws InterruptedException {
        RequestQueue queue=new RequestQueue();
        new ClientThread(queue,"Alex").start();
        ServiceThread st=new ServiceThread(queue);
        st.start();
        Thread.sleep(10000);
        st.close();
    }
}
