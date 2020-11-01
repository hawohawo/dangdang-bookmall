package com.dangdang.bookmall.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dangdang.bookmall.promotion.entity.vo.SeckillMiniAppVo;
import com.dangdang.bookmall.promotion.entity.vo.SeckillSessionAndBookInfoVo;
import com.dangdang.bookmall.promotion.entity.vo.SeckillSessionAndBookNumVo;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.bookmall.promotion.entity.SeckillEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:54:13
 */
public interface SeckillService extends IService<SeckillEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SeckillSessionAndBookNumVo> getSeckillSessionBooksNum(Integer id);

    List<SeckillSessionAndBookInfoVo> getSeckillSessionBooksInfo(Integer seckillId, Integer seckillSessionId);

    List<SeckillMiniAppVo> seckillDisplay();
}

