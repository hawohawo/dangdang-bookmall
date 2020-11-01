package com.dangdang.bookmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dangdang.bookmall.order.entity.vo.OrderInfoAndBookInfoVo;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.bookmall.order.entity.OrderinfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:50:45
 */
public interface OrderinfoService extends IService<OrderinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<OrderinfoEntity> findOrders(Map<String,Object> orderinfoEntity);

    List<OrderInfoAndBookInfoVo> selectUserOrderList(Map<String, Object> params);
}

