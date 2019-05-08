package com.lm.pag.rabbitmq.producer;

import com.lm.pag.entity.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author scaf_xs
 * @ClassName: OrderSender
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/4/26 14:33
 */
@Component
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息方法，设定发送时的交换机，路由，消息体，消息的唯一ID
     * @param order
     */
    public void sender(Order order) throws Exception {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange",
                "order.one",
                order,
                correlationData);
    }

}
