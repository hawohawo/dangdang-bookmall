package com.dangdang.bookmall.promotion.entity.vo;

import com.dangdang.bookmall.promotion.entity.HotEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zengyuzhi
 * @date 2020/10/25 上午9:50
 */
@Data
public class HotBookVo{
    private HotEntity hotEntity;
    private String bookName;
    private String picture;
    private String author;
    private BigDecimal priceSj;


}
