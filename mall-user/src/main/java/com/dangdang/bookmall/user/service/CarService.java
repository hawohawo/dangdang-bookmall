package com.dangdang.bookmall.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.bookmall.user.entity.CarEntity;

import java.util.Map;

/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-17 20:59:59
 */
public interface CarService extends IService<CarEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

