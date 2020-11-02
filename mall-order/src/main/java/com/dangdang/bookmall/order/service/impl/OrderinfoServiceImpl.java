package com.dangdang.bookmall.order.service.impl;

import com.dangdang.bookmall.order.dao.BookinfoDao;
import com.dangdang.bookmall.order.entity.BookinfoEntity;
import com.dangdang.bookmall.order.entity.vo.OrderInfoAndBookInfoVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Resource
    BookinfoDao bookinfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = params.get("key").toString();
        QueryWrapper<OrderinfoEntity> orderinfoEntityQueryWrapper = new QueryWrapper<>();
        orderinfoEntityQueryWrapper.like("code",key).or().like("payment_type",key).or().like("time_xd",key);

        orderinfoEntityQueryWrapper.orderByAsc("time_xd","status");
        if(params.get("userId")!=null){
            orderinfoEntityQueryWrapper.eq("user_id",(String)params.get("userId"));
        }
        if(params.get("status")!=null){
            orderinfoEntityQueryWrapper.eq("status",(String)params.get("status"));
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

    @Override
    public List<OrderInfoAndBookInfoVo> selectUserOrderList(Map<String, Object> params) {
        //过滤条件
        QueryWrapper<OrderinfoEntity> orderinfoEntityQueryWrapper = new QueryWrapper<>();
        //根据订单状态
        if(params.get("userId")!=null){
            orderinfoEntityQueryWrapper.eq("user_id",(String)params.get("userId"));
        }
        if(params.get("status")!=null){
            orderinfoEntityQueryWrapper.eq("status",(String)params.get("status"));
        }

        List<OrderinfoEntity> orderinfoEntities = orderinfoDao.selectList(orderinfoEntityQueryWrapper);
        List<OrderInfoAndBookInfoVo> orderInfoAndBookInfoVos = orderinfoEntities.stream().map(orderinfoEntity -> {
            OrderInfoAndBookInfoVo orderInfoAndBookInfoVo = new OrderInfoAndBookInfoVo();
            orderInfoAndBookInfoVo.setOrderinfoEntity(orderinfoEntity);
            QueryWrapper<BookinfoEntity> bookinfoEntityQueryWrapper = new QueryWrapper<>();
            bookinfoEntityQueryWrapper.eq("order_id", orderinfoEntity.getId());
            List<BookinfoEntity> bookinfoEntities = bookinfoDao.selectList(bookinfoEntityQueryWrapper);
            orderInfoAndBookInfoVo.setBookinfoEntitys(bookinfoEntities);
            return orderInfoAndBookInfoVo;
        }).collect(Collectors.toList());
        return orderInfoAndBookInfoVos;
    }


}