package com.thread.two.chapter11;

/**
 * @author scaf_xs
 * @ClassName: Context
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/6/11 15:41
 */

public class Context {

    private String name;
    private String cardId;

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setCardId(String cardId) {
        this.cardId=cardId;
    }

    public String getCardId() {
        return this.cardId;
    }
}
