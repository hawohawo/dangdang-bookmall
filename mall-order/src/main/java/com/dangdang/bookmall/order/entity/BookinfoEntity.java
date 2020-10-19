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
@TableName("omt_bookinfo")
public class BookinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 所属订单编号
	 */
	private String orderId;
	/**
	 * 图书id
	 */
	private Long bookId;
	/**
	 * 图书名称
	 */
	private String bookName;
	/**
	 * 图书图片
	 */
	private String bookPic;
	/**
	 * 购买数量
	 */
	private Integer bookNum;
	/**
	 * 付款价格
	 */
	private BigDecimal bookPrice;
	/**
	 * 图书状态
	 */
	private Integer status;

}
