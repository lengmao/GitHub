package com.lm.pag.rabbitmq.consumer;

import com.lm.pag.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author scaf_xs
 * @ClassName: OrderReceiver
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/4/26 15:43
 */

@Component
public class OrderReceiver {


    /**
     * @RabbitListener注解解释
     * bindings:监听绑定的消息队列
     * @QueueBinding 绑定的消息队列，其中value值代表消息队列的信息，exchange值代表交换机信息
     * 如果在此处设置交换机和消息队列的值，那么rabbitmq会按照此处设置的值进行创建消息队列和交换机
     * @param order
     * @param headers
     * @param channel
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue", durable = "true"),
            exchange = @Exchange(name = "order-exchange", type = "topic")
    ))
    @RabbitHandler
    public void receiver(@Payload Order order,
                         @Headers Map<String, Object> headers,
                         Channel channel) throws Exception {
        //消费者开始消费消息
        System.out.println("================>>>>>>>消费者开始消费消息<<<<<<<================");
        System.out.println("订单ID：" + order.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        //ACK 配置文件配置手动签收消息，这里要回应rabbitmq服务器
        channel.basicAck(deliveryTag, false);
    }
}
