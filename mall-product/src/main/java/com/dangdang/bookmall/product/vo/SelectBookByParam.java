package com.dangdang.bookmall.product.vo;

import lombok.Data;

@Data
public class SelectBookByParam {

    /**
     * 图书名称
     */
    private String name;
    /**
     * 作者
     */
    private String author;

    /**
     * 图书分类ID
     */
    private Long typeId;

    /**
     * 是否上架（0：已上架 1：未上架）
     */
    private Integer insale;

    /**
     * 出版信息ID
     */
    private Long publishId;
}
