package com.dangdang.bookmall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.order.dao.ReturninfoDao;
import com.dangdang.bookmall.order.entity.ReturninfoEntity;
import com.dangdang.bookmall.order.service.ReturninfoService;


@Service("returninfoService")
public class ReturninfoServiceImpl extends ServiceImpl<ReturninfoDao, ReturninfoEntity> implements ReturninfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReturninfoEntity> page = this.page(
                new Query<ReturninfoEntity>().getPage(params),
                new QueryWrapper<ReturninfoEntity>()
        );

        return new PageUtils(page);
    }

}