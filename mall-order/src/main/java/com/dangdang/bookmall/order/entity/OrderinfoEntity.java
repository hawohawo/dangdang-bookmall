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
@TableName("omt_orderinfo")
public class OrderinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 订单编号
	 */
	private String code;
	/**
	 * 下单时间
	 */
	private Date timeXd;
	/**
	 * 订单总价
	 */
	private BigDecimal moneyTotal;
	/**
	 * 订单状态
	 */
	private Integer status;
	/**
	 * 物流单号
	 */
	private String logisticsNum;
	/**
	 * 收货地址
	 */
	private String address;
	/**
	 * 用户联系方式
	 */
	private String userPhone;
	/**
	 * 收货人
	 */
	private String userName;
	/**
	 * 邮政编码
	 */
	private String postal;
	/**
	 * 订单备注
	 */
	private String remark;
	/**
	 * 支付方式
	 */
	private String paymentType;
	/**
	 * 支付时间
	 */
	private Date paymentTime;
	/**
	 * 发货时间
	 */
	private Date deliverTime;
	/**
	 * 确认收货时间
	 */
	private Date reciveTime;
	/**
	 * 运费
	 */
	private BigDecimal freight;
	/**
	 * 积分抵扣
	 */
	private BigDecimal discountScore;
	/**
	 * 优惠券抵扣
	 */
	private BigDecimal discountCoupon;
	/**
	 * 自动收货时间
	 */
	private Date autorecive;
	/**
	 * 
	 */
	private String userId;

}
