package com.dangdang.bookmall.promotion.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author zengyuzhi
 * @date 2020/11/1 上午10:31
 */
@Data
public class SeckillMiniAppVo {
    private Integer id;
    /**
     *
     */
    private Date startTime;
    /**
     *
     */
    private Date endTime;
    /**
     * 0 未启用 1 启用
     */
    private Integer status;


    private String isNow;

//    -------------------------

    private SeckillSessionAndBookInfoVo seckillSessionAndBookInfoVo;


}
