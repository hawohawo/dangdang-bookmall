package com.dangdang.bookmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.bookmall.product.entity.PublishEntity;

import java.util.Map;

/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-17 19:56:33
 */
public interface PublishService extends IService<PublishEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

