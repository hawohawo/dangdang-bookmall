package com.dangdang.bookmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.bookmall.product.entity.BaseinfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-17 19:56:33
 */
public interface BaseinfoService extends IService<BaseinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

