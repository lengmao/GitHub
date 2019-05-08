package com.lm.pag;

import com.lm.pag.entity.Order;
import com.lm.pag.rabbitmq.producer.OrderSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    OrderSender orderSender;

    @Test
    public void sender() throws Exception {

        String id = "201904260001";
        String messageBody = "测试订单";
        String messageId = "$" + UUID.randomUUID().toString();

        Order order = new Order(id, messageBody, messageId);

        orderSender.sender(order);

    }
}
