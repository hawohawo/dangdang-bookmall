package com.dangdang.bookmall.promotion.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.promotion.dao.SeckillSessionBookDao;
import com.dangdang.bookmall.promotion.entity.SeckillSessionBookEntity;
import com.dangdang.bookmall.promotion.service.SeckillSessionBookService;


@Service("seckillSessionBookService")
public class SeckillSessionBookServiceImpl extends ServiceImpl<SeckillSessionBookDao, SeckillSessionBookEntity> implements SeckillSessionBookService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillSessionBookEntity> page = this.page(
                new Query<SeckillSessionBookEntity>().getPage(params),
                new QueryWrapper<SeckillSessionBookEntity>()
        );

        return new PageUtils(page);
    }

}