package com.dangdang.bookmall.product.entity.vo;

import com.dangdang.bookmall.product.entity.BaseinfoEntity;
import com.dangdang.bookmall.product.entity.BookdetailEntity;
import com.dangdang.bookmall.product.entity.PublishEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zengyuzhi
 * @date 2020/10/28 上午12:28
 */
@Data
public class BookAllInfoVo {
    /**
     * 图书图片
     */
    private String picture;
    /**
     * 图书分类ID
     */
    private Long typeId;
    /**
     * 图书名称
     */
    private String name;
    /**
     * 副标题
     */
    private String fname;
    /**
     * 图书介绍
     */
    private String introduce;
    /**
     * 作者
     */
    private String author;
    /**
     * 图书评分
     */
    private BigDecimal score;
    /**
     * 图书定价
     */
    private BigDecimal priceDj;
    /**
     * 图书售价
     */
    private BigDecimal priceSj;
    /**
     * 运费
     */
    private BigDecimal priceYf;
    /**
     * 是否上架（0：已上架 1：未上架）
     */
    private Integer insale;
    /**
     * 图书关键字
     */
    private String keyword;
    /**
     * 图书库存
     */
    private Long stock;
    /**
     * 积分
     */
    private Integer integral;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 出版信息ID
     */
    private Long publishId;
    /**
     * 图书详情ID
     */
    private Long bookdetailId;

    private String content;
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
