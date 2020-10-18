package com.dangdang.bookmall.promotion.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillEntity> page = this.page(
                new Query<SeckillEntity>().getPage(params),
                new QueryWrapper<SeckillEntity>()
        );

        return new PageUtils(page);
    }

}