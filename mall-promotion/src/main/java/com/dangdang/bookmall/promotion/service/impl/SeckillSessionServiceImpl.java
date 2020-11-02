package com.dangdang.bookmall.promotion.service.impl;

import com.dangdang.bookmall.promotion.entity.SeckillEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.promotion.dao.SeckillSessionDao;
import com.dangdang.bookmall.promotion.entity.SeckillSessionEntity;
import com.dangdang.bookmall.promotion.service.SeckillSessionService;


@Service("seckillSessionService")
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionDao, SeckillSessionEntity> implements SeckillSessionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = params.get("key").toString();
        QueryWrapper<SeckillSessionEntity> seckillSessionEntityQueryWrapper = new QueryWrapper<>();
        seckillSessionEntityQueryWrapper.like("id",key)
                .or().like("start_time",key)
                .or().like("end_time",key);

        IPage<SeckillSessionEntity> page = this.page(
                new Query<SeckillSessionEntity>().getPage(params),
                seckillSessionEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}