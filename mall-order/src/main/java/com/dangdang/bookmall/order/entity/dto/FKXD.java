package com.dangdang.bookmall.order.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FKXD implements Serializable {
    private String goods_author;
    private Integer goods_id;
    private String goods_introduce;
    private String goods_name;
    private String goods_price;
    private String goods_priceYf;
    private Integer num;
    private String pics;
private boolean isChecked;
}
