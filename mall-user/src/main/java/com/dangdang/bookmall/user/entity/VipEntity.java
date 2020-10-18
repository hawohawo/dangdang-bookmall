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
 * @date 2020-10-17 20:59:59
 */
@Data
@TableName("umt_vip")
public class VipEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String userId;
	/**
	 * 0.普通会员 1倍
1.银会员 1.2倍
2.金会员 1.5倍
每800成长值升级一个段。成长值获取的积分是普通积分的n倍
	 */
	private Integer level;
	/**
	 * 
	 */
	private Integer growth;
	/**
	 * 
	 */
	private Integer score;

}
