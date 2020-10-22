package com.dangdang.bookmall.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dangdang.bookmall.product.dto.BaseinfosEntity;
import com.dangdang.bookmall.product.dto.BaseInfoAddNameEntity;
import com.dangdang.bookmall.product.dto.SelectBookByParam;
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
    public IPage<BaseInfoAddNameEntity> getBooksType(Page<BaseInfoAddNameEntity> page) {
        return baseinfoDao.getBooksType(page);
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

    @Override
    public List<BaseinfoEntity> getBooksByParams(SelectBookByParam sbbp) {
        QueryWrapper<BaseinfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", sbbp.getName())
                    .eq("author",sbbp.getAuthor())
                    .eq("type_id",sbbp.getTypeId())
                    .eq("insale",sbbp.getInsale())
                    .eq("publish_id",sbbp.getPublishId());
        List<BaseinfoEntity> list = this.list(queryWrapper);
        return list;
    }

}