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
 * @date 2020-10-17 20:58:04
 */
@Data
@TableName("omt_receive")
public class ReceiveEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 收货人
	 */
	private String receiveName;
	/**
	 * 联系电话
	 */
	private String receiveTel;
	/**
	 * 收货地址
	 */
	private String receiveAddr;
	/**
	 * 邮政编码
	 */
	private String postal;

}
