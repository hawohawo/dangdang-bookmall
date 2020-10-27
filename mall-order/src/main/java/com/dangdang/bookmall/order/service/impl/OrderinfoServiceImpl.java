package com.dangdang.bookmall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.order.dao.OrderinfoDao;
import com.dangdang.bookmall.order.entity.OrderinfoEntity;
import com.dangdang.bookmall.order.service.OrderinfoService;


@Service("orderinfoService")
public class OrderinfoServiceImpl extends ServiceImpl<OrderinfoDao, OrderinfoEntity> implements OrderinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderinfoEntity> page = this.page(
                new Query<OrderinfoEntity>().getPage(params),
                new QueryWrapper<OrderinfoEntity>()
        );

        return new PageUtils(page);
    }







}