package com.lm.pag.entity;

import java.io.Serializable;

/**
 * @author scaf_xs
 * @ClassName: Order
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/4/26 14:24
 */

public class Order implements Serializable{

    //订单ID
    private String id;

    //消息信息（名称，这里我起的名称有点歧义）
    private String messageBody;

    //发送订单消息ID
    private String messageId;

    public Order(String id, String messageBody, String messageId) {
        this.id = id;
        this.messageBody = messageBody;
        this.messageId = messageId;
    }

    public Order(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
