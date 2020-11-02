package com.dangdang.bookmall.promotion.service.impl;

import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AdDao adDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = params.get("key").toString();
        QueryWrapper<AdEntity> adEntityQueryWrapper = new QueryWrapper<>();
        adEntityQueryWrapper.like("url",key).or().like("id",key);

        IPage<AdEntity> page = this.page(
                new Query<AdEntity>().getPage(params),
                adEntityQueryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageMiniapp(Map<String, Object> params) {
        QueryWrapper<AdEntity> adEntityQueryWrapper = new QueryWrapper<>();

        adEntityQueryWrapper.eq("status",1);
        IPage<AdEntity> page = this.page(
                new Query<AdEntity>().getPage(params),
                adEntityQueryWrapper
        );
        return new PageUtils(page);
    }


}