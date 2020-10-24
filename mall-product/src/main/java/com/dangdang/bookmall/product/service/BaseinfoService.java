package com.dangdang.bookmall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dangdang.bookmall.product.dto.BaseInfoAddNameEntity;
import com.dangdang.bookmall.product.dto.SelectBookByInsale;
import com.dangdang.bookmall.product.dto.SelectBookByParam;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.bookmall.product.entity.BaseinfoEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:51:28
 */
public interface BaseinfoService extends IService<BaseinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    IPage<BaseInfoAddNameEntity> getBooksType(Page<BaseInfoAddNameEntity> page);

    IPage<BaseinfoEntity> getBooksByType(Page<BaseinfoEntity> page,int typeId);

    IPage<BaseinfoEntity> getBooksByParams(Page<BaseinfoEntity> page,SelectBookByParam sbbp);

    IPage<SelectBookByInsale> getBooksByInsale(Page<SelectBookByInsale> objectPage);

    BigDecimal getScoreById(int id);
}

