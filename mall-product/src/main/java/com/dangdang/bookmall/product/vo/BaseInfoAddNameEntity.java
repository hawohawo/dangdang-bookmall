package com.dangdang.bookmall.product.vo;


import com.dangdang.bookmall.product.entity.BaseinfoEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 *联表查询
 */
@Data
public class BaseInfoAddNameEntity {

    private BaseinfoEntity baseinfoEntity;

    /**
     * 图书分类名称
     */
    private String name1;

}
