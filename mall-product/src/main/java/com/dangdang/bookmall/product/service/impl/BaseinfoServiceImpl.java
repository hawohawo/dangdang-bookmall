package com.dangdang.bookmall.product.service.impl;


import com.dangdang.bookmall.product.dto.BaseinfosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    private BaseinfoDao baseinfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BaseinfoEntity> page = this.page(
                new Query<BaseinfoEntity>().getPage(params),
                new QueryWrapper<BaseinfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<BaseinfosEntity> getBooksType(Map<String, Object> params) {
        return baseinfoDao.getBooksType();
    }

    @Override
    public List<BaseinfoEntity> getBooksByType(int typeId) {
        /*LambdaQueryWrapper<BaseinfoEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(BaseinfoEntity::getTypeId,typeId)
                .select(BaseinfoEntity::getTypeId);*/
        QueryWrapper<BaseinfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", typeId);
        List<BaseinfoEntity> list = this.list(queryWrapper);
        return list;
    }

}