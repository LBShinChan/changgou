package com.changgou.order.listener;

import com.changgou.order.config.RabbitMQConfig;
import com.changgou.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderTaskListener {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_TACK)
    public void autoTack(String message){
        System.out.println("收到自动确认收货消息");
        orderService.autoTack(); //自动确认收货
    }
}
