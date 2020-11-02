package com.dangdang.bookmall.promotion.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dangdang.bookmall.promotion.entity.AdEntity;
import com.dangdang.bookmall.promotion.entity.vo.HotBookVo;
import com.dangdang.bookmall.promotion.feign.ProductFeignService;
import com.dangdang.common.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.promotion.dao.HotDao;
import com.dangdang.bookmall.promotion.entity.HotEntity;
import com.dangdang.bookmall.promotion.service.HotService;


@Service("hotService")
public class HotServiceImpl extends ServiceImpl<HotDao, HotEntity> implements HotService {

    @Autowired
    private ProductFeignService productFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = params.get("key").toString();
        System.out.println(key);
        QueryWrapper<HotEntity> hotEntityQueryWrapper = new QueryWrapper<>();
        hotEntityQueryWrapper.like("book_id",key);

        IPage<HotEntity> page = this.page(
                new Query<HotEntity>().getPage(params),
                hotEntityQueryWrapper
        );

        PageUtils pageUtils = new PageUtils(page);
        //流式编程 封装VO
        //1 . 得到基本的HotEntity类
        List<HotEntity> records = page.getRecords();
        //2 . 获取到每个item(hotEntity)的map进行处理
        List<HotBookVo> hotBookVoList = records.stream().map((hotEntity) -> {
            //3 . new 一个 要进行封装的VO类
            HotBookVo hotBookVo = new HotBookVo();
            hotBookVo.setHotEntity(hotEntity);
//            可以采用beanUtils进行封装
//            BeanUtils.copyProperties(hotEntity,hotBookVo);
            //4 . 继续封装剩余VO，这里需要用到远程调用product-service 服务
            R r = productFeignService.feignBookInfoById(Long.valueOf(hotEntity.getBookId()));
            hotBookVo.setBookName((String)r.get("name"));
            hotBookVo.setAuthor((String)r.get("author"));
            hotBookVo.setPicture((String)r.get("picture"));
            String priceSj=  String.valueOf(r.get("priceSj"));
            Integer publishId= (Integer) r.get("publishId");
            Integer bookdetailId= (Integer) r.get("bookdetailId") ;
            BigDecimal bd=new BigDecimal(priceSj);
            hotBookVo.setPriceSj(bd);
            hotBookVo.setPublishId(publishId);
            hotBookVo.setBookdetailId(bookdetailId);


            return hotBookVo;
        }).collect(Collectors.toList());

        pageUtils.setList(hotBookVoList);

        return pageUtils;
    }


}