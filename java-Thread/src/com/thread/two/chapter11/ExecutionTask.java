package com.thread.two.chapter11;

/**
 * @author scaf_xs
 * @ClassName: ExecutionTask
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/11 15:38
 */

public class ExecutionTask implements Runnable {

    private QueryFromDBAction queryFromDBAction=new QueryFromDBAction();
    private QueryFromHttpAction httpAction=new QueryFromHttpAction();


    @Override
    public void run() {
        Context context=ActionContext.getActionContext().getContext();
        queryFromDBAction.execute();
        System.out.println("the name query successful !");
        httpAction.execute();
        System.out.println("the card id query successful !");
        System.out.println("The name is "+context.getName()+" and  the cardID is "+context.getCardId());
    }
}
