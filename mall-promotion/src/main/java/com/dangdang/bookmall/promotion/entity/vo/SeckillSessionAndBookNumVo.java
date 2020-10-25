package com.dangdang.bookmall.promotion.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author zengyuzhi
 * @date 2020/10/25 下午10:36
 */
@Data
public class SeckillSessionAndBookNumVo {
    private Integer id;

    private Date startTime;

    private Date endTime;

    private Integer bookNum;

}
