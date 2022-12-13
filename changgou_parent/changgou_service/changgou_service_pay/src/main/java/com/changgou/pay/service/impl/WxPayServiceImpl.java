package com.changgou.pay.service.impl;

import com.changgou.pay.service.WxPayService;
import com.github.wxpay.sdk.WXPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class WxPayServiceImpl implements WxPayService {

    @Autowired
    private WXPay wxPay;

    @Value("${wxpay.notify_url}")
    private String notify_url;

    @Override
    public Map nativePay(String orderId, Integer money) {
        try {
            //1.封装请求参数
            Map<String,String> map=new HashMap();
            map.put("body","畅购商城");//商品描述
            map.put("out_trade_no",orderId);//订单号
            //map.put("total_fee",String.valueOf(money*100));//金额,以分为单位
            BigDecimal payMoney = new BigDecimal("0.01");
            BigDecimal fen = payMoney.multiply(new BigDecimal("100")); //1.00
            fen = fen.setScale(0,BigDecimal.ROUND_UP); // 1
            map.put("total_fee",String.valueOf(fen));

            map.put("spbill_create_ip","127.0.0.1");//终端IP
            map.put("notify_url",notify_url);//回调地址,先随便填一个
            map.put("trade_type","NATIVE");//交易类型
            Map<String, String> mapResult = wxPay.unifiedOrder( map ); //调用统一下单
            return mapResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map queryOrder(String orderId) {
        Map<String, String> map=new HashMap();
        map.put("out_trade_no", orderId);
        try {
            return  wxPay.orderQuery(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map closeOrder(String orderId) {
        Map<String, String> map=new HashMap<>();
        map.put( "out_trade_no",orderId );
        try {
            return  wxPay.closeOrder( map );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}