package com.dangdang.bookmall.product.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *联表查询
 */
@Data
public class BaseInfoAddNameEntity {

    private Long id;
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
    /**
     * 评论ID
     */
    private Long commentId;
    /**
     * 图书分类名称
     */
    private String name1;

}
