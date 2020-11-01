package com.dangdang.bookmall.promotion.entity.vo;

import com.dangdang.bookmall.promotion.entity.dto.SeckillAndSeckillBooksDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

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

    /** 新增
     * 该秒杀活动是否已经开始
     */
    private String isNow;

    /** 新增
     * 该秒杀活动的剩余时间
     */
    private Long surplusTime;

//    -------------------------

    private List<SeckillAndSeckillBooksDto> seckillAndSeckillBooksDto;


}
