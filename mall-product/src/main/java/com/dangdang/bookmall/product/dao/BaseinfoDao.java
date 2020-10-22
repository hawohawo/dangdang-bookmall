package com.dangdang.bookmall.product.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dangdang.bookmall.product.dto.BaseinfosEntity;
import com.dangdang.bookmall.product.entity.BaseinfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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
    List<BaseinfosEntity> getBooksType();


}
