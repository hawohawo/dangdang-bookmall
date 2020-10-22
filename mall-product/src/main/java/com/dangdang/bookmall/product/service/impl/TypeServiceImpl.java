package com.dangdang.bookmall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.product.dao.TypeDao;
import com.dangdang.bookmall.product.entity.TypeEntity;
import com.dangdang.bookmall.product.service.TypeService;


@Service("typeService")
public class TypeServiceImpl extends ServiceImpl<TypeDao, TypeEntity> implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TypeEntity> page = this.page(
                new Query<TypeEntity>().getPage(params),
                new QueryWrapper<TypeEntity>()
        );

        return new PageUtils(page);
    }

}