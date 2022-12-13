package com.changgou.task;

import com.changgou.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderTask {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 订单自动收货
     */
    @Scheduled(cron = "0 0 0 * * ?") // 秒 分 小时 日 月 星期几 年
    public void autoTake(){
        System.out.println(new Date(  ) );
        rabbitTemplate.convertAndSend( "", RabbitMQConfig.ORDER_TACK,"-" );
    }
}
