package com.dangdang.bookmall.promotion.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dangdang.bookmall.promotion.entity.vo.HotBookVo;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.bookmall.promotion.entity.HotEntity;

import java.util.Map;

/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:54:13
 */
public interface HotService extends IService<HotEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

