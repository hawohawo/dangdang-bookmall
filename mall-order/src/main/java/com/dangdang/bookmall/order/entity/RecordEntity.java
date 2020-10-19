package com.dangdang.bookmall.order.entity;

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
 * @date 2020-10-19 16:50:46
 */
@Data
@TableName("omt_record")
public class RecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 订单id
	 */
	private Long orderId;
	/**
	 * 用户操作id
	 */
	private String userId;
	/**
	 * 订单所属状态
	 */
	private Integer status;
	/**
	 * 操作备注
	 */
	private String remark;

}
