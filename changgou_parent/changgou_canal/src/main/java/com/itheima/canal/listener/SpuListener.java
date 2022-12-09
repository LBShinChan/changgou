package com.itheima.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.itheima.canal.config.RabbitMQConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZJ
 */
@CanalEventListener
public class SpuListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * spu 表更新
     * @param eventType
     * @param rowData
     */
    @ListenPoint(schema = "changgou_goods", table = {"tb_spu"},eventType = CanalEntry.EventType.UPDATE )
    public void goodsUp(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.err.println("tb_spu表数据发生变化");

        //修改前数据
        Map<String, String> oldMap=new HashMap<>();
        for(CanalEntry.Column column: rowData.getBeforeColumnsList()) {
            oldMap.put(column.getName(),column.getValue());
        }

        //修改后数据
        Map<String, String> newMap=new HashMap<>();
        for(CanalEntry.Column column: rowData.getAfterColumnsList()) {
            newMap.put(column.getName(),column.getValue());
        }

        //is_marketable  由0改为1表示上架
        if("0".equals(oldMap.get("is_marketable")) && "1".equals(newMap.get("is_marketable")) ){
            rabbitTemplate.convertAndSend(RabbitMQConfig.GOODS_UP_EXCHANGE,"",newMap.get("id")); //发送到mq商品上架交换器上
        }

        // 获取最新下架的商品 1-》0
        //is_marketable  由0改为1表示上架
        if("1".equals(oldMap.get("is_marketable")) && "0".equals(newMap.get("is_marketable")) ){
            rabbitTemplate.convertAndSend(RabbitMQConfig.GOODS_DOWN_EXCHANGE,"",newMap.get("id")); //发送到mq商品上架交换器上
        }

        // 获取最新被审核通过的商品
        if("0".equals(oldMap.get("status")) && "1".equals(newMap.get("status")) ){
            rabbitTemplate.convertAndSend(RabbitMQConfig.GOODS_UP_EXCHANGE,"",newMap.get("id")); //发送到mq商品上架交换器上
        }
    }



}
