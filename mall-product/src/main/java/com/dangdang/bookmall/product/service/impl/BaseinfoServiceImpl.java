package com.dangdang.bookmall.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dangdang.bookmall.product.entity.dto.StockDto;
import com.dangdang.bookmall.product.vo.BaseInfoAddNameEntity;
import com.dangdang.bookmall.product.vo.SelectBookByInsale;
import com.dangdang.bookmall.product.vo.SelectBookByParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.product.dao.BaseinfoDao;
import com.dangdang.bookmall.product.entity.BaseinfoEntity;
import com.dangdang.bookmall.product.service.BaseinfoService;
import org.springframework.transaction.annotation.Transactional;


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
    public PageUtils getBooksType(Map<String, Object> params) {
        IPage<BaseinfoEntity> page = this.page(
                new Query<BaseinfoEntity>().getPage(params),
                new QueryWrapper<BaseinfoEntity>()
        );
        PageUtils pageUtils = new PageUtils(page);

        List<BaseinfoEntity> records = page.getRecords();
        List<BaseInfoAddNameEntity> baseInfoAddNameEntityList = records.stream().map(baseinfoEntity -> {
            BaseInfoAddNameEntity baseInfoAddNameEntity = new BaseInfoAddNameEntity();
            baseInfoAddNameEntity.setBaseinfoEntity(baseinfoEntity);
            String typeName = baseinfoDao.getBooksType(Integer.parseInt(String.valueOf(baseinfoEntity.getTypeId())));
            baseInfoAddNameEntity.setName1(typeName);
            return baseInfoAddNameEntity;
        }).collect(Collectors.toList());

        pageUtils.setList(baseInfoAddNameEntityList);
        return pageUtils;
    }

    @Override
    public IPage<BaseinfoEntity> getBooksByType(Page<BaseinfoEntity> page, int typeId) {
        /*LambdaQueryWrapper<BaseinfoEntity> wrapper = new LambdaQueryWrapper();
        wrapper.eq(BaseinfoEntity::getTypeId,typeId)
                .select(BaseinfoEntity::getTypeId);*/

        /*QueryWrapper<BaseinfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", typeId);
        List<BaseinfoEntity> list = this.list(queryWrapper);*/
        return baseinfoDao.getBooksByType(page, typeId);
    }

    @Override
    public IPage<BaseinfoEntity> getBooksByParams(Page<BaseinfoEntity> page, SelectBookByParam sbbp) {
        QueryWrapper<BaseinfoEntity> queryWrapper = new QueryWrapper<>();

        Map<String, Object> map = new HashMap<>();
        map.put("name", sbbp.getName());
        map.put("author", sbbp.getAuthor());
        map.put("type_id", sbbp.getTypeId());
        map.put("insale", sbbp.getInsale());
        map.put("publish_id", sbbp.getPublishId());
        queryWrapper.allEq(map, false);
        /*queryWrapper.allEq({name:sbbp.getName()})
                    .like("author",sbbp.getAuthor())
                    .like("type_id",sbbp.getTypeId())
                    .like("insale",sbbp.getInsale())
                    .like("publish_id",sbbp.getPublishId());*/
        //<BaseinfoEntity> list = this.list(queryWrapper);
        IPage<BaseinfoEntity> list = this.page(page, queryWrapper);
        return list;
    }

    @Override
    public PageUtils getBooksByInsale(Map<String, Object> params) {
        IPage<BaseinfoEntity> page = this.page(
                new Query<BaseinfoEntity>().getPage(params),
                new QueryWrapper<BaseinfoEntity>()
        );
        PageUtils pageUtils = new PageUtils(page);
        List<BaseinfoEntity> records = page.getRecords();
        List<SelectBookByInsale> collect = records.stream().map((baseinfoEntity) -> {
            SelectBookByInsale selectBookByInsale = new SelectBookByInsale();
            selectBookByInsale.setId(baseinfoEntity.getId());
            selectBookByInsale.setName(baseinfoEntity.getName());
            selectBookByInsale.setPriceSj(baseinfoEntity.getPriceSj());
            return selectBookByInsale;
        }).collect(Collectors.toList());
        pageUtils.setList(collect);
        return pageUtils;
//        SelectBookByInsale
//        return baseinfoDao.getBookByInsale(params);

    }

    @Override
    public Integer getScoreById(int id) {
        return baseinfoDao.getScoreByIds(id);
    }



    //    远程调用服务，根据图书id 获取到 图书的名称

    @Override
    public String getBookNameById(Long id) {
        return baseinfoDao.getBookNameById(id);
    }


    @Override
    public String getTotalOnShelves() {
        return baseinfoDao.getTotalOnShelves();
    }

    @Override
    public String getTotalOffShelves() {
        return baseinfoDao.getTotalOffShelves();
    }

    @Override
    public  String getAllShelves(){

        return  baseinfoDao.getAllShelves();
    }

    @Override
    public List<StockDto> getStockDto(){
        return  baseinfoDao.getStockDto();
    }


    //    远程调用服务，根据图书id 获取到 图书的基本信息
    @Override
    public BaseinfoEntity feignBookInfoById(Long id) {
        return baseinfoDao.feignBookInfoById(id);
    }

    @Override
    @Transactional
    public int insert(BaseinfoEntity baseinfoEntity) {
        return baseMapper.insert(baseinfoEntity);
    }

    @Override
    @Transactional
    public int updateOwn(BaseinfoEntity baseinfoEntity) {
        return baseMapper.updateById(baseinfoEntity);
    }

    @Override
    public PageUtils queryPageMiniapp(Map<String, Object> params) {
        QueryWrapper<BaseinfoEntity> baseinfoEntityQueryWrapper = new QueryWrapper<>();
        baseinfoEntityQueryWrapper.eq("insale",1);
        IPage<BaseinfoEntity> page = this.page(
                new Query<BaseinfoEntity>().getPage(params),
                baseinfoEntityQueryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils getBooksByInsaleSeckill(Map<String, Object> params) {
        IPage<BaseinfoEntity> page = this.page(
                new Query<BaseinfoEntity>().getPage(params),
                new QueryWrapper<BaseinfoEntity>()
        );
        PageUtils pageUtils = new PageUtils(page);
        List<BaseinfoEntity> records = page.getRecords();
        List<SelectBookByInsale> collect = records.stream().map((baseinfoEntity) -> {
            SelectBookByInsale selectBookByInsale = new SelectBookByInsale();
            selectBookByInsale.setId(baseinfoEntity.getId());
            selectBookByInsale.setName(baseinfoEntity.getName());
            selectBookByInsale.setPriceSj(baseinfoEntity.getPriceSj());
            return selectBookByInsale;
        }).collect(Collectors.toList());
        pageUtils.setList(collect);
        return pageUtils;
    }

}

