package com.dangdang.bookmall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.product.dao.PublishDao;
import com.dangdang.bookmall.product.entity.PublishEntity;
import com.dangdang.bookmall.product.service.PublishService;
import org.springframework.transaction.annotation.Transactional;


@Service("publishService")
public class PublishServiceImpl extends ServiceImpl<PublishDao, PublishEntity> implements PublishService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PublishEntity> page = this.page(
                new Query<PublishEntity>().getPage(params),
                new QueryWrapper<PublishEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public int insert(PublishEntity publishEntity) {
        return baseMapper.insert(publishEntity);
    }

}