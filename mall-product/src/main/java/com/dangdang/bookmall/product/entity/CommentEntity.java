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
@TableName("bmt_comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 评论时间
	 */
	private Date time;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 评论类型
	 */
	private String type;

	/**
	 * 所属图书id
	 */
	private int bookId;

}
