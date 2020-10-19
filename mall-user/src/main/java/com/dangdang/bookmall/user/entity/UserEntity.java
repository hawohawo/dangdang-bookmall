package com.dangdang.bookmall.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:49:33
 */
@Data
@TableName("umt_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private String phone;
	/**
	 * 0 正常 1 冻结
	 */
	private Integer status;
	/**
	 * 
	 */
	private String notice;

}
