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
@TableName("smt_seckill")
public class SeckillEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String title;
	/**
	 * 
	 */
	private Date startDate;
	/**
	 * 
	 */
	private Date endDate;
	/**
	 * 0 未上线 1 上线
	 */
	private Integer status;

}
