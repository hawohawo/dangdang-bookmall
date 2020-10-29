package com.dangdang.bookmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.bookmall.product.entity.BookdetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:51:28
 */
public interface BookdetailService extends IService<BookdetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

    int insert(BookdetailEntity bookdetailEntity);

    int updateOwn(BookdetailEntity bookdetailEntity);
}

