package com.dangdang.bookmall.product.dao;

import com.dangdang.bookmall.product.dto.BaseInfoAddNameEntity;
import com.dangdang.bookmall.product.entity.BaseinfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:51:28
 */
@Mapper
public interface BaseinfoDao extends BaseMapper<BaseinfoEntity> {

    /**
     * 联表查图书基本信息和分类名
     * @param
     * @return
     */
    @Select("select a.* , b.`name` as name1 from bmt_baseinfo a,bmt_type b where a.type_id=b.id")
    List<BaseInfoAddNameEntity> getBooksType();

}
