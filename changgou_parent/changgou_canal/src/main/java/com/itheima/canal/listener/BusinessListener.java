package com.itheima.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.itheima.canal.config.RabbitMQConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ZJ
 */
@CanalEventListener
public class BusinessListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     *
     * @param eventType 当前操作数据库的类型
     * @param rowData 当前操作数据库的数据
     */
    @ListenPoint(schema = "changgou_business", table = {"tb_ad"})
    public void adUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.err.println("广告数据发生变化");

        //修改前数据
//        for(CanalEntry.Column column: rowData.getBeforeColumnsList()) {
//            System.out.println("改变前的数据："+column.getName()+"::"+column.getValue());
//            if(column.getName().equals("position")){
//                System.out.println("发送消息到mq  ad_update_queue:"+column.getValue());
//                rabbitTemplate.convertAndSend("",RabbitMQConfig.AD_UPDATE_QUEUE,column.getValue());  //发送消息到mq
//                break;
//            }
//        }

        //修改后数据
        for(CanalEntry.Column column: rowData.getAfterColumnsList()) {
//            System.out.println("改变后的数据："+column.getName()+"::"+column.getValue());
            if(column.getName().equals("position")){
                System.out.println("发送消息到mq  ad_update_queue:"+column.getValue());
                rabbitTemplate.convertAndSend("", RabbitMQConfig.AD_UPDATE_QUEUE,column.getValue());  //发送消息到mq
                break;
            }
        }
    }
}
