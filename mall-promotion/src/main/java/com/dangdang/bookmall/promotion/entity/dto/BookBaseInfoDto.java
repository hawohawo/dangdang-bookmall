package com.dangdang.bookmall.promotion.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zengyuzhi
 * @date 2020/11/1 下午12:05
 */
@Data
public class BookBaseInfoDto {
    private Long id;
    /**
     * 图书图片
     */
    private String picture;
    /**
     * 图书名称
     */
    private String bookName;
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
     * 出版信息ID
     */
    private Integer publishId;
    /**
     * 图书详情ID
     */
    private Integer bookdetailId;
}
