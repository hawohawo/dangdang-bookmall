package com.dangdang.bookmall.promotion.entity;

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
 * @date 2020-10-19 16:54:13
 */
@Data
@TableName("smt_coupon_user")
public class CouponUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer couponId;
	/**
	 * 
	 */
	private String code;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	private Date getTime;
	/**
	 * 0 未使用 1 已使用
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date useTime;
	/**
	 * 
	 */
	private String orderinfoCode;

}
