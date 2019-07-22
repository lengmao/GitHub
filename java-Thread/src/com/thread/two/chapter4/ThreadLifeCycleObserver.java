package com.thread.two.chapter4;

import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: ThreadLifeCycleObserver
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/6 15:38
 */

public class ThreadLifeCycleObserver implements LifeCycleListener {

    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids) {
        if (ids == null)
            return;
        ids.stream().forEach(id ->
                new Thread(new ObservableRunnable(this) {
                    @Override
                    public void run() {
                        try {
                            notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                            System.out.println("query for the id :" + id);
                            //TODO 这里执行自己需要的代码逻辑
                            Thread.sleep(1000);
                            notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                        } catch (Exception e) {
                            notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                        }
                    }
                }, id).start()
        );
    }

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
        synchronized (LOCK) {
            System.out.println("The Runnable [" + Thread.currentThread().getName() + "] state is [" + event.getState() + "]");
            if (event.getThrowable() != null) {
                System.out.println("The Runnable [" + Thread.currentThread() + "] process is field");
                event.getThrowable().printStackTrace();
            }
        }
    }
}
