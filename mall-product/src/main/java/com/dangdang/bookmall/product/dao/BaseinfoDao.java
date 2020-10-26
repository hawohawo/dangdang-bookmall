package com.dangdang.bookmall.product.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dangdang.bookmall.product.vo.BaseInfoAddNameEntity;
import com.dangdang.bookmall.product.vo.SelectBookByInsale;
import com.dangdang.bookmall.product.vo.SelectBookByParam;
import com.dangdang.bookmall.product.entity.BaseinfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

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
    @Select("select name from bmt_type where id = #{id} ")
    String getBooksType(int id);

    /**
     * 联表查图书基本信息和分类名
     * @param typeId 图书类别号
     * @return
     */
    @Select("select * from bmt_baseinfo where type_id=#{typeId}")
    IPage<BaseinfoEntity> getBooksByType (Page<BaseinfoEntity> page,int typeId);


    /**
     * 查询图书（多条件）
     * @param
     * @return
     */
    @Select("select * from bmt_baseinfo where name like CONCAT('%', #{name}, '%') " +
            "and author like CONCAT('%', #{author},'%') " +
            "and type_id like CONCAT('%', #{typeId},'%')"+
            "and insale like CONCAT('%', #{insale},'%')"+
            "and publish_id like CONCAT('%', #{publishId},'%')")
    IPage<BaseinfoEntity> getBookByParam (Page<BaseinfoEntity> page,SelectBookByParam sbbp);

    /**
     * 查询图书（条件：是否上架）
     * @param
     * @return
     */
    @Select("select id,name,price_sj from bmt_baseinfo where insale=1")
    IPage<SelectBookByInsale> getBookByInsale (Page<SelectBookByInsale> page);

    /**
     * 查询积分
     * @param id
     * @return
     */
    @Select("select score from bmt_baseinfo where id=#{id}")
    BigDecimal getScoreByIds (int id);

    @Select("select name from bmt_baseinfo where id=#{id}")
    String getBookNameById(Long id);

    @Select("select * from bmt_baseinfo where id=#{id}")
    BaseinfoEntity feignBookInfoById(Long id);
}
