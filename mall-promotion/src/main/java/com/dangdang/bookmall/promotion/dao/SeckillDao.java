package com.dangdang.bookmall.promotion.dao;

import com.dangdang.bookmall.promotion.entity.SeckillEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dangdang.bookmall.promotion.entity.SeckillSessionEntity;
import com.dangdang.bookmall.promotion.entity.vo.SeckillSessionAndBookNumVo;
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
public interface SeckillDao extends BaseMapper<SeckillEntity> {

    @Select("select * from smt_seckill_session where status ='1'")
    List<SeckillSessionEntity> getSeckillSession();

    @Select("SELECT count(*) from smt_seckill_session_book where seckill_id=#{seckillId} and seckill_session_id=#{seckillSessionId};")
    Integer getBookNumber(Integer seckillId,Integer seckillSessionId);

    @Select("select status from smt_seckill where id=#{seckillId} ")
    Integer isStatus(Integer seckillId);
}
