package com.dangdang.bookmall.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.order.dao.BookinfoDao;
import com.dangdang.bookmall.order.entity.BookinfoEntity;
import com.dangdang.bookmall.order.service.BookinfoService;


@Service("bookinfoService")
public class BookinfoServiceImpl extends ServiceImpl<BookinfoDao, BookinfoEntity> implements BookinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BookinfoEntity> page = this.page(
                new Query<BookinfoEntity>().getPage(params),
                new QueryWrapper<BookinfoEntity>()
        );

        return new PageUtils(page);
    }

}