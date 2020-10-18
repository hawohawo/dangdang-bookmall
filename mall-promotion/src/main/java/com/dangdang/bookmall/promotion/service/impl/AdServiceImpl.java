package com.dangdang.bookmall.promotion.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.promotion.dao.AdDao;
import com.dangdang.bookmall.promotion.entity.AdEntity;
import com.dangdang.bookmall.promotion.service.AdService;


@Service("adService")
public class AdServiceImpl extends ServiceImpl<AdDao, AdEntity> implements AdService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdEntity> page = this.page(
                new Query<AdEntity>().getPage(params),
                new QueryWrapper<AdEntity>()
        );

        return new PageUtils(page);
    }

}