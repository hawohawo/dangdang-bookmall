package com.dangdang.bookmall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dangdang.bookmall.product.entity.dto.StockDto;
import com.dangdang.bookmall.product.vo.SelectBookByInsale;
import com.dangdang.bookmall.product.vo.SelectBookByParam;
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

    PageUtils  getBooksType(Map<String, Object> params);

    IPage<BaseinfoEntity> getBooksByType(Page<BaseinfoEntity> page,int typeId);

    IPage<BaseinfoEntity> getBooksByParams(Page<BaseinfoEntity> page,SelectBookByParam sbbp);

    PageUtils getBooksByInsale(Map<String, Object> params);
    PageUtils getBooksByInsaleSeckill(Map<String, Object> params);

    BigDecimal getScoreById(int id);

    String getBookNameById(Long id);


    String getTotalOnShelves();
    //上架商品总数
    String getTotalOffShelves();
    //下架商品总数
    String getAllShelves();
    //全部商品数
    List<StockDto> getStockDto();
    //库存

    BaseinfoEntity feignBookInfoById(Long id);


    int insert(BaseinfoEntity baseinfoEntity);

    int updateOwn(BaseinfoEntity baseinfoEntity);


}

