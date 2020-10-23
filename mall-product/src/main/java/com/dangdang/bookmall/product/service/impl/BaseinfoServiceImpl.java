package com.dangdang.bookmall.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dangdang.bookmall.product.dto.BaseInfoAddNameEntity;
import com.dangdang.bookmall.product.dto.SelectBookByInsale;
import com.dangdang.bookmall.product.dto.SelectBookByParam;
import com.dangdang.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
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
    public IPage<BaseinfoEntity> getBooksByType(Page<BaseinfoEntity> page,int typeId) {
        /*LambdaQueryWrapper<BaseinfoEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(BaseinfoEntity::getTypeId,typeId)
                .select(BaseinfoEntity::getTypeId);*/

        /*QueryWrapper<BaseinfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", typeId);
        List<BaseinfoEntity> list = this.list(queryWrapper);*/
        return baseinfoDao.getBooksByType(page,typeId);
    }

    @Override
    public IPage<BaseinfoEntity> getBooksByParams(Page<BaseinfoEntity> page,SelectBookByParam sbbp) {
        QueryWrapper<BaseinfoEntity> queryWrapper = new QueryWrapper<>();

        Map<String, Object> map = new HashMap<>();
        map.put("name",sbbp.getName());
        map.put("author",sbbp.getAuthor());
        map.put("type_id",sbbp.getTypeId());
        map.put("insale",sbbp.getInsale());
        map.put("publish_id",sbbp.getPublishId());
        queryWrapper.allEq(map,false);
        /*queryWrapper.allEq({name:sbbp.getName()})
                    .like("author",sbbp.getAuthor())
                    .like("type_id",sbbp.getTypeId())
                    .like("insale",sbbp.getInsale())
                    .like("publish_id",sbbp.getPublishId());*/
        //<BaseinfoEntity> list = this.list(queryWrapper);
        IPage<BaseinfoEntity> list = this.page(page,queryWrapper);
        return list;
    }

    @Override
    public IPage<SelectBookByInsale> getBooksByInsale(Page<SelectBookByInsale> page) {
        return baseinfoDao.getBookByInsale(page);
    }

    @Override
    public BigDecimal getScoreById(int id) {
        return baseinfoDao.getScoreByIds(id);
    }

}