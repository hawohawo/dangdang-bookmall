package com.dangdang.bookmall.order.entity;

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
 * @date 2020-10-19 16:50:45
 */
@Data
@TableName("omt_returninfo")
public class ReturninfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 退货编号
	 */
	private String code;
	/**
	 * 所属订单编号
	 */
	private Long orderId;
	/**
	 * 退货时间
	 */
	private Date time;
	/**
	 * 图书id
	 */
	private Long bookId;
	/**
	 * 图书名称
	 */
	private String bookName;
	/**
	 * 退款数量
	 */
	private Integer bookNum;
	/**
	 * 退款金额
	 */
	private BigDecimal bookPrice;
	/**
	 * 退款订单状态
	 */
	private Integer status;
	/**
	 * 退款理由
	 */
	private String reason;
	/**
	 * 问题描述
	 */
	private String problem;
	/**
	 * 图书图片
	 */
	private String bookPic;



}
