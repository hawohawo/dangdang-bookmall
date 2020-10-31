package com.dangdang.bookmall.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.bookmall.user.entity.VipEntity;

import java.util.Map;

/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 17:09:29
 */
public interface VipService extends IService<VipEntity> {

    PageUtils queryPage(Map<String, Object> params);

    VipEntity getByUserId(String userId);
}

