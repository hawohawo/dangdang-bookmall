package com.dangdang.bookmall.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.order.dao.ReceiveDao;
import com.dangdang.bookmall.order.entity.ReceiveEntity;
import com.dangdang.bookmall.order.service.ReceiveService;


@Service("receiveService")
public class ReceiveServiceImpl extends ServiceImpl<ReceiveDao, ReceiveEntity> implements ReceiveService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReceiveEntity> page = this.page(
                new Query<ReceiveEntity>().getPage(params),
                new QueryWrapper<ReceiveEntity>()
        );

        return new PageUtils(page);
    }

}