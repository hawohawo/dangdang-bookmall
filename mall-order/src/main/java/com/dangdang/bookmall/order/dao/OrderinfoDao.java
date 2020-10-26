package com.dangdang.bookmall.order.dao;

import com.dangdang.bookmall.order.entity.OrderinfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:50:45
 */
@Mapper
public interface OrderinfoDao extends BaseMapper<OrderinfoEntity> {
    @Select("select * from omt_orderinfo where code like '%${code}%' and user_phone like '%${userPhone}%' and status like '%${status}%' and time_xd like '%${timeXd}%'")
    List<OrderinfoEntity> findOrders(Map<String,Object> orderinfoEntity);
}
