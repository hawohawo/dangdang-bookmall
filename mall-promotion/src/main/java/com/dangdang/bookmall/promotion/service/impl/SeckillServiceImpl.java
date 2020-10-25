package com.dangdang.bookmall.promotion.service.impl;

import com.dangdang.bookmall.promotion.dao.SeckillSessionBookDao;
import com.dangdang.bookmall.promotion.dao.SeckillSessionDao;
import com.dangdang.bookmall.promotion.entity.SeckillSessionBookEntity;
import com.dangdang.bookmall.promotion.entity.SeckillSessionEntity;
import com.dangdang.bookmall.promotion.entity.vo.SeckillSessionAndBookInfoVo;
import com.dangdang.bookmall.promotion.entity.vo.SeckillSessionAndBookNumVo;
import com.dangdang.bookmall.promotion.feign.ProductFeignService;
import com.dangdang.common.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.promotion.dao.SeckillDao;
import com.dangdang.bookmall.promotion.entity.SeckillEntity;
import com.dangdang.bookmall.promotion.service.SeckillService;


@Service("seckillService")
public class SeckillServiceImpl extends ServiceImpl<SeckillDao, SeckillEntity> implements SeckillService {

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SeckillSessionDao seckillSessionDao;

    @Autowired
    private SeckillSessionBookDao seckillSessionBookDao;

//    远程调用服务
    @Autowired
    private ProductFeignService productFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillEntity> page = this.page(
                new Query<SeckillEntity>().getPage(params),
                new QueryWrapper<SeckillEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SeckillSessionAndBookNumVo> getSeckillSessionBooksNum(Integer seckillId) {
        List<SeckillSessionEntity> seckillSessionEntities = seckillDao.getSeckillSession();
        List<SeckillSessionAndBookNumVo> seckillSessionAndBookNumVos = seckillSessionEntities.stream().map((item) -> {
            SeckillSessionAndBookNumVo seckillSessionAndBookNumVo = new SeckillSessionAndBookNumVo();
            seckillSessionAndBookNumVo.setId(item.getId());
            seckillSessionAndBookNumVo.setStartTime(item.getStartTime());
            seckillSessionAndBookNumVo.setEndTime(item.getEndTime());
//          获取该秒杀活动下的秒杀时段下的秒杀商品数量
            //1. 新开dao
            Integer bookNumber = seckillDao.getBookNumber(seckillId, item.getId());
            seckillSessionAndBookNumVo.setBookNum(bookNumber);
            return seckillSessionAndBookNumVo;
        }).collect(Collectors.toList());


        return seckillSessionAndBookNumVos;
    }

    @Override
    public List<SeckillSessionAndBookInfoVo> getSeckillSessionBooksInfo(Integer seckillId, Integer seckillSessionId) {
        // 当对应的秒杀活动已经下架或者对应的秒杀时间段已经下架，若发起get-url请求，则返回空值
        if(seckillSessionDao.isStatus(seckillSessionId)==0 || seckillDao.isStatus(seckillId)==0)
            return null;
        //判断秒杀活动上线，封装List<SeckillSessionAndBookInfoVo>
        List<SeckillSessionBookEntity> seckillSessionBookEntities = seckillSessionBookDao.getBySeeionAndSeckill(seckillId, seckillSessionId);
        List<SeckillSessionAndBookInfoVo> seckillSessionAndBookInfoVos = seckillSessionBookEntities.stream().map((item) -> {
            SeckillSessionAndBookInfoVo seckillSessionAndBookInfoVo = new SeckillSessionAndBookInfoVo();
            BeanUtils.copyProperties(item, seckillSessionAndBookInfoVo);
            //远程调用product-service服务 获取图书基本的信息
            R r = productFeignService.feignBookInfoById(Long.valueOf(item.getBookId()));
            seckillSessionAndBookInfoVo.setBookName((String) r.get("name"));
            seckillSessionAndBookInfoVo.setPriceSj((Double) r.get("priceSj"));

            return seckillSessionAndBookInfoVo;
        }).collect(Collectors.toList());
        return seckillSessionAndBookInfoVos;
    }

}