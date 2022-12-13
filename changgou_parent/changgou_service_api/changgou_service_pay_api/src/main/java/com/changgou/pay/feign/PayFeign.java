package com.changgou.pay.feign;

import com.changgou.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pay")
public interface PayFeign {

    @GetMapping("/wxpay/nativePay")
    public Result nativePay(@RequestParam("orderId")String orderId, @RequestParam("money") Integer money);

    /**
     * 查询微信订单
     * @param orderId
     * @return
     */
    @GetMapping("/wxpay/query/{orderId}")
    public Result queryOrder(@PathVariable("orderId") String orderId);


    /**
     * 关闭微信订单
     * @param orderId
     * @return
     */
    @PutMapping("/wxpay/close/{orderId}")
    public Result closeOrder(@PathVariable("orderId") String orderId);
}
