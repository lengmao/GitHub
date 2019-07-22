package com.thread.one.chapter6;

import java.util.*;

/**
 * @author scaf_xs
 * @ClassName: ThreadJoin
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/3 10:37
 */

public class ThreadJoin {

    private static final LinkedList<Controll> CONTROLS = new LinkedList<>();
    private static final int MAX_WORKER = 5;

    static List<String> list = new ArrayList<>();

    public static void addDataToList() {
        for (int i = 1; i <= 10; i++) {
            String t = "M" + i;
            list.add(t);
        }
    }

    public static void main(String[] args) {
        /*addDataToList();
        List<Thread> worker = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            createThread(list.get(j)).start();
            worker.add(createThread(list.get(j)));
        }*/
        List<Thread> worker = new ArrayList<>();

        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10")
                .stream()
                .map(ThreadJoin::createThread)
                .forEach(t -> {
                    t.start();
                    worker.add(t);
                });

        worker.stream().forEach(t -> {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Optional.of("All of capture is finish...").ifPresent(System.out::println);
    }

    public static Thread createThread(String name) {
        return new Thread(() -> {
            Optional.of("the Thread [" + Thread.currentThread().getName() + "] begin capture date...").ifPresent(System.out::println);
            synchronized (CONTROLS) {
                while (CONTROLS.size() > MAX_WORKER) {
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLS.addLast(new Controll());
            }
            Optional.of("the Thread [" + Thread.currentThread().getName() + "] is working...").ifPresent(System.out::println);
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (CONTROLS) {
                Optional.of("the Thread [" + Thread.currentThread().getName() + "] end...").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        }, name);
    }

    public static class Controll {
    }
}
