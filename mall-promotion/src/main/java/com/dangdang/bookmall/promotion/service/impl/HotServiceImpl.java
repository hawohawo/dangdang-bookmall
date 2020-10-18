package com.dangdang.bookmall.promotion.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.promotion.dao.HotDao;
import com.dangdang.bookmall.promotion.entity.HotEntity;
import com.dangdang.bookmall.promotion.service.HotService;


@Service("hotService")
public class HotServiceImpl extends ServiceImpl<HotDao, HotEntity> implements HotService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HotEntity> page = this.page(
                new Query<HotEntity>().getPage(params),
                new QueryWrapper<HotEntity>()
        );

        return new PageUtils(page);
    }

}