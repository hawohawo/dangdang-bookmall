package com.dangdang.bookmall.order.service.impl;

import com.dangdang.bookmall.order.entity.OrderinfoEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.order.dao.ReasonDao;
import com.dangdang.bookmall.order.entity.ReasonEntity;
import com.dangdang.bookmall.order.service.ReasonService;


@Service("reasonService")
public class ReasonServiceImpl extends ServiceImpl<ReasonDao, ReasonEntity> implements ReasonService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = params.get("key").toString();
        QueryWrapper<ReasonEntity> reasonEntityQueryWrapper = new QueryWrapper<>();
        reasonEntityQueryWrapper.like("id",key).or().like("type",key);

        IPage<ReasonEntity> page = this.page(
                new Query<ReasonEntity>().getPage(params),
                reasonEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}