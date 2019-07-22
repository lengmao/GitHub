package com.thread.two.chapter11;

/**
 * @author scaf_xs
 * @ClassName: ActionContext
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/11 16:19
 */

public class ActionContext {

    private static final ThreadLocal<Context> threadLocal=new ThreadLocal<Context>(){
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    private static class ContextHolder{
        private static final ActionContext actionContext=new ActionContext();
    }

    public static ActionContext getActionContext(){
        return ContextHolder.actionContext;
    }

    public Context getContext(){
        return threadLocal.get();
    }

}
