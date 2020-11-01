package com.dangdang.bookmall.user.dao;

import com.dangdang.bookmall.user.entity.AddressEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 
 * 
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 17:09:29
 */
@Mapper
public interface AddressDao extends BaseMapper<AddressEntity> {

    @Update("update umt_address set sort='999'")
    void updateAllAddress();
}
