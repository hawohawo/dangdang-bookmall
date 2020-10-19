package com.dangdang.bookmall.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.bookmall.promotion.entity.SeckillEntity;

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
}

