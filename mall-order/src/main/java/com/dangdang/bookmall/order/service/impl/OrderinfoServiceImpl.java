package com.dangdang.bookmall.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.order.dao.OrderinfoDao;
import com.dangdang.bookmall.order.entity.OrderinfoEntity;
import com.dangdang.bookmall.order.service.OrderinfoService;

import javax.annotation.Resource;


@Service("orderinfoService")
public class OrderinfoServiceImpl extends ServiceImpl<OrderinfoDao, OrderinfoEntity> implements OrderinfoService {

    @Resource
    OrderinfoDao orderinfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<OrderinfoEntity> orderinfoEntityQueryWrapper = new QueryWrapper<>();
        orderinfoEntityQueryWrapper.orderByAsc("time_xd","status");
        if(params.get("userId")!=null){
            orderinfoEntityQueryWrapper.eq("user_id",(String)params.get("userId"));
        }


        IPage<OrderinfoEntity> page = this.page(
                new Query<OrderinfoEntity>().getPage(params),
                orderinfoEntityQueryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public List<OrderinfoEntity> findOrders(Map<String,Object> orderinfoEntity) {
        return orderinfoDao.findOrders(orderinfoEntity);
    }


}