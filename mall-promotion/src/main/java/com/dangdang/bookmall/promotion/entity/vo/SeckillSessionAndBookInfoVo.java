package com.dangdang.bookmall.promotion.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zengyuzhi
 * @date 2020/10/25 下午10:36
 */
@Data
public class SeckillSessionAndBookInfoVo {
    private Integer id;

    private Integer seckillId;

    private Integer seckillSessionId;

    private Integer bookId;

    private Integer number;

    private BigDecimal price;

    private Integer sort;

    private String bookName;

    private Double priceSj;



}
