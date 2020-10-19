package com.dangdang.bookmall.user.dao;

import com.dangdang.bookmall.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:49:33
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
