package com.dangdang.bookmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.bookmall.order.entity.ReasonEntity;

import java.util.Map;

/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:50:46
 */
public interface ReasonService extends IService<ReasonEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

