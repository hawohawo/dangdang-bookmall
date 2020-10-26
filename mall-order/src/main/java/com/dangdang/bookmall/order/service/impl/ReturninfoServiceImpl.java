package com.dangdang.bookmall.order.service.impl;

import com.dangdang.bookmall.order.entity.OrderinfoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.order.dao.ReturninfoDao;
import com.dangdang.bookmall.order.entity.ReturninfoEntity;
import com.dangdang.bookmall.order.service.ReturninfoService;

import javax.annotation.Resource;


@Service("returninfoService")
public class ReturninfoServiceImpl extends ServiceImpl<ReturninfoDao, ReturninfoEntity> implements ReturninfoService {

    @Resource
    ReturninfoDao returninfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReturninfoEntity> page = this.page(
                new Query<ReturninfoEntity>().getPage(params),
                new QueryWrapper<ReturninfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ReturninfoEntity> findReturn(Map<String, Object> params) {
        return returninfoDao.findReturn(params);
    }

}