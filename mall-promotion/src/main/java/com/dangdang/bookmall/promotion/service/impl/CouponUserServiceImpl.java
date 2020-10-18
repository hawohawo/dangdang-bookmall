package com.dangdang.bookmall.promotion.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.promotion.dao.CouponUserDao;
import com.dangdang.bookmall.promotion.entity.CouponUserEntity;
import com.dangdang.bookmall.promotion.service.CouponUserService;


@Service("couponUserService")
public class CouponUserServiceImpl extends ServiceImpl<CouponUserDao, CouponUserEntity> implements CouponUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponUserEntity> page = this.page(
                new Query<CouponUserEntity>().getPage(params),
                new QueryWrapper<CouponUserEntity>()
        );

        return new PageUtils(page);
    }

}