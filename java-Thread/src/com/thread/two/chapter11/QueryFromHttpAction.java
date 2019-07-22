package com.thread.two.chapter11;

/**
 * @author scaf_xs
 * @ClassName: QueryFromHttpAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/11 15:43
 */

public class QueryFromHttpAction {

    public void execute() {
        Context context=ActionContext.getActionContext().getContext();
        String name = context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
    }

    private String getCardId(String name) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "6767" + Thread.currentThread().getId();
    }
}
