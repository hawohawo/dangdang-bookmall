package com.dangdang.bookmall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.product.dao.BaseinfoDao;
import com.dangdang.bookmall.product.entity.BaseinfoEntity;
import com.dangdang.bookmall.product.service.BaseinfoService;


@Service("baseinfoService")
public class BaseinfoServiceImpl extends ServiceImpl<BaseinfoDao, BaseinfoEntity> implements BaseinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BaseinfoEntity> page = this.page(
                new Query<BaseinfoEntity>().getPage(params),
                new QueryWrapper<BaseinfoEntity>()
        );

        return new PageUtils(page);
    }

}