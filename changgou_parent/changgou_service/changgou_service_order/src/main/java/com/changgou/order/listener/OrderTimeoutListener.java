package com.changgou.order.listener;

import com.changgou.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderTimeoutListener {

    @Autowired
    private OrderService orderService;

    /**
     * 更新支付状态
     * @param orderId
     */
    @RabbitListener(queues = "queue.ordertimeout")
    public void closeOrder(String orderId){
        System.out.println("接收到关闭订单消息："+orderId);
        try {
            orderService.closeOrder( orderId );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
