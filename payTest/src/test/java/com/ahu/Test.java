package com.ahu;

import com.github.wxpay.sdk.MyConfig;
import com.github.wxpay.sdk.WXPay;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        MyConfig config=new MyConfig();
        WXPay wxPay= null;
        try {
            wxPay = new WXPay( config );
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String,String> map=new HashMap();
        map.put("body","畅购");//商品描述
        map.put("out_trade_no","555552131");//订单号
        map.put("total_fee","1");//金额
        map.put("spbill_create_ip","127.0.0.1");//终端IP
        map.put("notify_url","http://www.baidu.com");//回调地址
        map.put("trade_type","NATIVE");//交易类型

        Map<String, String> result = null;
        try {
            result = wxPay.unifiedOrder( map );
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
