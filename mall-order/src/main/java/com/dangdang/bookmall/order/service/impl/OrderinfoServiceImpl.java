package com.dangdang.bookmall.order.service.impl;

import cn.hutool.core.lang.UUID;
import com.dangdang.bookmall.order.dao.BookinfoDao;
import com.dangdang.bookmall.order.entity.BookinfoEntity;
import com.dangdang.bookmall.order.entity.dto.FKXD;
import com.dangdang.bookmall.order.entity.vo.OrderInfoAndBookInfoVo;
import com.dangdang.bookmall.order.feign.ProductFeignService;
import com.dangdang.bookmall.order.feign.UserFeignService;
import com.dangdang.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
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

    @Autowired
    ProductFeignService productFeignService;
    @Autowired
    UserFeignService userFeignService;

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
        System.out.println(params.get("status"));
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

    @Override
    public Long placeOrder(List<FKXD> ts, String score) {
        // 获取图书的ids // 获取图书的nums
        Integer[] ids = new Integer[ts.size()];
        Integer[] nums = new Integer[ts.size()];
        for (int i = 0; i < ts.size(); i++) {
            ids[i] = ts.get(i).getGoods_id();
            nums[i] = ts.get(i).getNum();
        }
//        1.下单成功 锁定库存
          if(!productFeignService.lockStock(ids,nums)){
              return null;
          }
//        2.新增订单基本信息，此时订单为待付款状态
          OrderinfoEntity orderinfoEntity = new OrderinfoEntity();
        BigDecimal moneyTotal  = new BigDecimal(0.00);
        BigDecimal freight  = new BigDecimal(0.00);
        for(FKXD fkxd : ts){
            freight = BigDecimal.valueOf(Double.valueOf(fkxd.getGoods_priceYf().toString())).max(freight);
            moneyTotal = BigDecimal.valueOf(Double.valueOf(fkxd.getGoods_price().toString()));
        }
//          orderinfoEntity.setId();

          orderinfoEntity.setCode(UUID.fastUUID().toString());
          orderinfoEntity.setTimeXd(new Date());
          orderinfoEntity.setMoneyTotal(moneyTotal);
          orderinfoEntity.setStatus(0); //默认为待付款状态
          orderinfoEntity.setLogisticsNum("N/a");
//          获取收货人信息
        R r = userFeignService.feignDefault();
          orderinfoEntity.setAddress(r.get("address").toString());
          orderinfoEntity.setUserPhone(r.get("userPhone").toString());
          orderinfoEntity.setUserName(r.get("name").toString());
          orderinfoEntity.setPostal(r.get("postal").toString());
          orderinfoEntity.setRemark("N/a");
//          orderinfoEntity.setPaymentType("微信支付");
//          orderinfoEntity.setPaymentTime();
//          orderinfoEntity.setDeliverTime();
//          orderinfoEntity.setReciveTime();
          orderinfoEntity.setFreight(freight);
          orderinfoEntity.setDiscountScore(BigDecimal.valueOf(Double.valueOf(score)));
//          orderinfoEntity.setDiscountCoupon(new BigDecimal(0.00));
//          orderinfoEntity.setAutorecive();
          orderinfoEntity.setUserId("1");
          orderinfoDao.insert(orderinfoEntity);
          Long orderId = orderinfoEntity.getId();
//        3.新增订单书籍信息
        for(FKXD fkxd:ts){
            BookinfoEntity bookinfoEntity = new BookinfoEntity();
            bookinfoEntity.setBookId(Long.valueOf(fkxd.getGoods_id().toString()));
            bookinfoEntity.setBookName(fkxd.getGoods_name());
            bookinfoEntity.setBookNum(fkxd.getNum());
            bookinfoEntity.setBookPic(fkxd.getPics());
            bookinfoEntity.setBookPrice(Double.valueOf(fkxd.getGoods_price()));
            bookinfoEntity.setOrderId(orderId.toString());
            bookinfoEntity.setStatus(0); //1为退货的商品
            bookinfoDao.insert(bookinfoEntity);
        }

//        4.设置订单过期时间 利用MQ实现延迟消息

//        5.库存解锁(订单过期没有支付被取消)

        return orderId;
    }

}