package com.thread.two.chapter4;

/**
 * @author scaf_xs
 * @ClassName: ObservableRunnable
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/6 15:21
 */

public abstract class ObservableRunnable implements Runnable {

    protected final LifeCycleListener listener;

    public ObservableRunnable(LifeCycleListener listener) {
        this.listener = listener;
    }

    protected void notifyChange(final RunnableEvent event) {
        listener.onEvent(event);
    }


    public enum RunnableState {
        RUNNING, ERROR, DONE
    }

    public class RunnableEvent {

        private final RunnableState state;
        private final Thread thread;
        private final Throwable throwable;

        public RunnableEvent(RunnableState state, Thread thread, Throwable throwable) {
            this.state = state;
            this.thread = thread;
            this.throwable = throwable;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getThrowable() {
            return throwable;
        }
    }
}
