package com.dangdang.bookmall.promotion.dao;

import com.dangdang.bookmall.promotion.entity.SeckillSessionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * 
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:54:13
 */
@Mapper
public interface SeckillSessionDao extends BaseMapper<SeckillSessionEntity> {

    @Select("select status from smt_seckill_session where id=#{seckillSessionId} ")
    Integer isStatus(Integer seckillSessionId);
}
