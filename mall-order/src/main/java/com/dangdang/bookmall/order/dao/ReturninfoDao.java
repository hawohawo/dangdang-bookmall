package com.dangdang.bookmall.order.dao;

import com.dangdang.bookmall.order.entity.OrderinfoEntity;
import com.dangdang.bookmall.order.entity.ReturninfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:50:45
 */
@Mapper
public interface ReturninfoDao extends BaseMapper<ReturninfoEntity> {
    @Select("select * from omt_returninfo where code like '%${code}%' and reason like '%${reason}%' and status like '%${status}%' and time like '%${time}%'")
    List<ReturninfoEntity> findReturn(Map<String, Object> params);
}
