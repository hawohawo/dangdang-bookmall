package com.dangdang.bookmall.promotion.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.dangdang.bookmall.promotion.dao.SeckillSessionBookDao;
import com.dangdang.bookmall.promotion.dao.SeckillSessionDao;
import com.dangdang.bookmall.promotion.entity.AdEntity;
import com.dangdang.bookmall.promotion.entity.SeckillSessionBookEntity;
import com.dangdang.bookmall.promotion.entity.SeckillSessionEntity;
import com.dangdang.bookmall.promotion.entity.dto.BookBaseInfoDto;
import com.dangdang.bookmall.promotion.entity.dto.SeckillAndSeckillBooksDto;
import com.dangdang.bookmall.promotion.entity.dto.SeckillSessionBookAndBookDto;
import com.dangdang.bookmall.promotion.entity.vo.HotBookVo;
import com.dangdang.bookmall.promotion.entity.vo.SeckillMiniAppVo;
import com.dangdang.bookmall.promotion.entity.vo.SeckillSessionAndBookInfoVo;
import com.dangdang.bookmall.promotion.entity.vo.SeckillSessionAndBookNumVo;
import com.dangdang.bookmall.promotion.feign.ProductFeignService;
import com.dangdang.common.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.promotion.dao.SeckillDao;
import com.dangdang.bookmall.promotion.entity.SeckillEntity;
import com.dangdang.bookmall.promotion.service.SeckillService;


@Service("seckillService")
public class SeckillServiceImpl extends ServiceImpl<SeckillDao, SeckillEntity> implements SeckillService {

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SeckillSessionDao seckillSessionDao;

    @Autowired
    private SeckillSessionBookDao seckillSessionBookDao;

//    远程调用服务
    @Autowired
    private ProductFeignService productFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = params.get("key").toString();
        QueryWrapper<SeckillEntity> seckillEntityQueryWrapper = new QueryWrapper<>();
        seckillEntityQueryWrapper.like("title",key)
                .or().like("start_date",key)
                .or().like("end_date",key);

        IPage<SeckillEntity> page = this.page(
                new Query<SeckillEntity>().getPage(params),
                seckillEntityQueryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<SeckillSessionAndBookNumVo> getSeckillSessionBooksNum(Integer seckillId) {
        List<SeckillSessionEntity> seckillSessionEntities = seckillDao.getSeckillSession();
        List<SeckillSessionAndBookNumVo> seckillSessionAndBookNumVos = seckillSessionEntities.stream().map((item) -> {
            SeckillSessionAndBookNumVo seckillSessionAndBookNumVo = new SeckillSessionAndBookNumVo();
            seckillSessionAndBookNumVo.setId(item.getId());
            seckillSessionAndBookNumVo.setStartTime(item.getStartTime());
            seckillSessionAndBookNumVo.setEndTime(item.getEndTime());
//          获取该秒杀活动下的秒杀时段下的秒杀商品数量
            //1. 新开dao
            Integer bookNumber = seckillDao.getBookNumber(seckillId, item.getId());
            seckillSessionAndBookNumVo.setBookNum(bookNumber);
            return seckillSessionAndBookNumVo;
        }).collect(Collectors.toList());


        return seckillSessionAndBookNumVos;
    }

    @Override
    public List<SeckillSessionAndBookInfoVo> getSeckillSessionBooksInfo(Integer seckillId, Integer seckillSessionId) {
        // 当对应的秒杀活动已经下架或者对应的秒杀时间段已经下架，若发起get-url请求，则返回空值
        if(seckillSessionDao.isStatus(seckillSessionId)==0 || seckillDao.isStatus(seckillId)==0)
            return null;
        //判断秒杀活动上线，封装List<SeckillSessionAndBookInfoVo>
        List<SeckillSessionBookEntity> seckillSessionBookEntities = seckillSessionBookDao.getBySeeionAndSeckill(seckillId, seckillSessionId);
        List<SeckillSessionAndBookInfoVo> seckillSessionAndBookInfoVos = seckillSessionBookEntities.stream().map((item) -> {
            SeckillSessionAndBookInfoVo seckillSessionAndBookInfoVo = new SeckillSessionAndBookInfoVo();
            BeanUtils.copyProperties(item, seckillSessionAndBookInfoVo);
            //远程调用product-service服务 获取图书基本的信息
                    R r = productFeignService.feignBookInfoById(Long.valueOf(item.getBookId()));
            seckillSessionAndBookInfoVo.setBookName((String) r.get("name"));
            seckillSessionAndBookInfoVo.setPriceSj((Double) r.get("priceSj"));

            return seckillSessionAndBookInfoVo;
        }).collect(Collectors.toList());
        return seckillSessionAndBookInfoVos;
    }

    @Override
    public List<SeckillMiniAppVo> seckillDisplay() {
        QueryWrapper<SeckillSessionEntity> seckillSessionEntityQueryWrapper = new QueryWrapper<>();
        seckillSessionEntityQueryWrapper.eq("status",1);
        //1 获取系统当前的时间的HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("HH:mm:ss");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        String format = sdf.format(date);
        Date dateNow = DateUtil.parse(format);
        List<SeckillSessionEntity> seckillSessionEntities = seckillSessionDao.selectList(seckillSessionEntityQueryWrapper);
        //System.out.println(sdf.format(seckillSessionEntities.get(0).getStartTime()));

        List<SeckillMiniAppVo> collect = seckillSessionEntities.stream().map(item -> {
            //2 获取秒杀时间段的HH:mm:ss
            SeckillMiniAppVo seckillMiniAppVo = new SeckillMiniAppVo();
            seckillMiniAppVo.setId(item.getId());
            //格式转化
            String startTime = sdf.format(item.getStartTime());
            String endTime = sdf.format(item.getEndTime());
            Date dateStartTime = DateUtil.parse(startTime);
            Date dateEndTime = DateUtil.parse(endTime);
            seckillMiniAppVo.setStartTime(item.getStartTime());
            seckillMiniAppVo.setEndTime(item.getEndTime());

            //          判断当前时间是否处于该范围内
            if (!dateNow.before(dateStartTime) && !dateNow.after(dateEndTime)) {
                seckillMiniAppVo.setIsNow("抢购中");
                long surplusTime = DateUtil.between(dateNow, dateEndTime, DateUnit.SECOND);
                seckillMiniAppVo.setSurplusTime(surplusTime);
            } else {
                seckillMiniAppVo.setIsNow("即将开始");
            }

            //封装每个秒杀时段下的秒杀活动以及秒杀的商品
            // 取已经上线的秒杀活动
            QueryWrapper<SeckillEntity> seckillEntityQueryWrapper = new QueryWrapper<>();
            seckillEntityQueryWrapper.eq("status",1);
            List<SeckillEntity> seckillEntities = seckillDao.selectList(seckillEntityQueryWrapper);
            List<SeckillAndSeckillBooksDto> SeckillAndSeckillBooksDtoStream = seckillEntities.stream().map(seckillEntity -> {
                SeckillAndSeckillBooksDto seckillAndSeckillBooksDto = new SeckillAndSeckillBooksDto();
                seckillAndSeckillBooksDto.setSeckillEntity(seckillEntity);

                //封装SeckillSessionBookAndBookDto(秒杀活动下对应上架的书籍，前提是属于当前秒杀时间段)
                List<SeckillSessionBookEntity> seckillSessionBookEntities = seckillSessionBookDao.getBySeeionAndSeckill(seckillEntity.getId(), item.getId());
                List<SeckillSessionBookAndBookDto> SeckillSessionBookAndBookDtoStream = seckillSessionBookEntities.stream().map(seckillSessionBookEntity -> {
                    SeckillSessionBookAndBookDto seckillSessionBookAndBookDto = new SeckillSessionBookAndBookDto();
                    seckillSessionBookAndBookDto.setSeckillSessionBookEntity(seckillSessionBookEntity);
//                   远程调用服务，请求获取书籍基本信息
                    BookBaseInfoDto bookBaseInfoDto = new BookBaseInfoDto();
                    R r = productFeignService.feignBookInfoById(Long.valueOf(seckillSessionBookEntity.getBookId()));
                    bookBaseInfoDto.setBookName((String) r.get("name"));
                    bookBaseInfoDto.setAuthor((String) r.get("author"));
                    bookBaseInfoDto.setPicture((String) r.get("picture"));
                    String priceSj = String.valueOf(r.get("priceSj"));
                    Integer publishId = (Integer) r.get("publishId");
                    Integer bookdetailId = (Integer) r.get("bookdetailId");
                    BigDecimal bd = new BigDecimal(priceSj);
                    bookBaseInfoDto.setPriceSj(bd);
                    bookBaseInfoDto.setPublishId(publishId);
                    bookBaseInfoDto.setBookdetailId(bookdetailId);
                    seckillSessionBookAndBookDto.setBookBaseInfoDto(bookBaseInfoDto);

                    return seckillSessionBookAndBookDto;
                }).collect(Collectors.toList());

                seckillAndSeckillBooksDto.setSeckillSessionBookAndBookDtos(SeckillSessionBookAndBookDtoStream);

                return seckillAndSeckillBooksDto;
            }).collect(Collectors.toList());

            seckillMiniAppVo.setSeckillAndSeckillBooksDto(SeckillAndSeckillBooksDtoStream);

            return seckillMiniAppVo;
        }).collect(Collectors.toList());

        return collect;

    }


}