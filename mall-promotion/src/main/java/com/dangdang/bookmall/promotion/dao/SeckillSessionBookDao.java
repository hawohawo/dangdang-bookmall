package com.dangdang.bookmall.promotion.dao;

import com.dangdang.bookmall.promotion.entity.SeckillSessionBookEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:54:13
 */
@Mapper
public interface SeckillSessionBookDao extends BaseMapper<SeckillSessionBookEntity> {

    @Select("select * from smt_seckill_session_book where seckill_id=#{seckillId} and seckill_session_id=#{seckillSessionId}")
    List<SeckillSessionBookEntity> getBySeeionAndSeckill(Integer seckillId, Integer seckillSessionId);
}
