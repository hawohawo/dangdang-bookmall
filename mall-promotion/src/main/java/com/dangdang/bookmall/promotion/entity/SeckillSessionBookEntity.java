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
 * @date 2020-10-19 16:54:13
 */
@Data
@TableName("smt_seckill_session_book")
public class SeckillSessionBookEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer seckillId;
	/**
	 * 
	 */
	private Integer seckillSessionId;
	/**
	 * 
	 */
	private Integer bookId;
	/**
	 * 
	 */
	private Integer number;
	/**
	 * 
	 */
	private BigDecimal price;
	/**
	 * 
	 */
	private Integer sort;

}
