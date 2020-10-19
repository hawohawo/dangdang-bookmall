package com.dangdang.bookmall.product.entity;

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
 * @date 2020-10-19 16:51:28
 */
@Data
@TableName("bmt_publish")
public class PublishEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 出版社
	 */
	private String publisher;
	/**
	 * 出版时间
	 */
	private Date publishTime;
	/**
	 * ISBN
	 */
	private String isbn;
	/**
	 * 字数
	 */
	private Long wordNum;
	/**
	 * 开本
	 */
	private String size;
	/**
	 * 纸张
	 */
	private String paperType;
	/**
	 * 包装
	 */
	private String pack;

}
