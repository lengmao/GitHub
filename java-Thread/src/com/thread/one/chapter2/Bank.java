package com.thread.one.chapter2;

/**
 * @author scaf_xs
 * @ClassName: Bank
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/31 9:21
 */

public class Bank {

    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow();
        Thread window1 = new Thread(ticketWindow, "一号窗口");
        Thread window2 = new Thread(ticketWindow, "二号窗口");
        Thread window3 = new Thread(ticketWindow, "三号窗口");

        window1.start();
        window2.start();
        window3.start();
    }
}
