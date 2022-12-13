package com.changgou.user.dao;

import com.changgou.user.pojo.PointLog;
import tk.mybatis.mapper.common.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PointLogMapper extends Mapper<PointLog> {

    @Select("select * from tb_point_log where order_id=#{orderId}")
    PointLog findLogInfoByOrderId(@Param("orderId") String orderId);
}
