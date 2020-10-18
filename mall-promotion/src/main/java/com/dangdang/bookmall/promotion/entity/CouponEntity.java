package com.dangdang.bookmall.promotion.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-17 20:59:11
 */
@Data
@TableName("smt_coupon")
public class CouponEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private BigDecimal threshold;
	/**
	 * 
	 */
	private BigDecimal price;
	/**
	 * 
	 */
	private Date startDate;
	/**
	 * 
	 */
	private Date endDate;
	/**
	 * 0 正常 1 停止使用
	 */
	private Integer status;
	/**
	 * 
	 */
	private Integer number;

}
