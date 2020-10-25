package com.dangdang.bookmall.product.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SelectBookByInsale {

    private Long id;
    /**
     * 图书名称
     */
    private String name;

    /**
     * 图书售价
     */
    private BigDecimal priceSj;

}
