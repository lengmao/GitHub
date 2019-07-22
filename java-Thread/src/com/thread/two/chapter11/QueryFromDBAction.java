package com.thread.two.chapter11;

import com.sun.javafx.scene.control.skin.ContextMenuContent;

/**
 * @author scaf_xs
 * @ClassName: QueryFromDBAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/11 15:40
 */

public class QueryFromDBAction {

    public void execute() {
        try {
            Context context = ActionContext.getActionContext().getContext();
            context.setName("Alex" + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
