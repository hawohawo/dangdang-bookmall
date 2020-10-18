package com.dangdang.bookmall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.product.dao.BookdetailDao;
import com.dangdang.bookmall.product.entity.BookdetailEntity;
import com.dangdang.bookmall.product.service.BookdetailService;


@Service("bookdetailService")
public class BookdetailServiceImpl extends ServiceImpl<BookdetailDao, BookdetailEntity> implements BookdetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BookdetailEntity> page = this.page(
                new Query<BookdetailEntity>().getPage(params),
                new QueryWrapper<BookdetailEntity>()
        );

        return new PageUtils(page);
    }

}