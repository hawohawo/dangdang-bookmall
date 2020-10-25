package com.dangdang.bookmall.promotion.entity.vo;

import com.dangdang.bookmall.promotion.entity.CouponEntity;
import com.dangdang.bookmall.promotion.entity.CouponUserEntity;
import lombok.Data;

import java.util.List;

/**
 * @author zengyuzhi
 * @date 2020/10/23 上午12:50
 */
@Data
public class CouponDetailVo  {
    private CouponEntity couponEntity;
    //已领取Received 待领取unclaimed 已使用used 未使用notUsed
    private Integer received;
    private Integer unclaimed;
    private Integer used;
    private Integer notUsed;

    private List<CouponUserEntity> couponUserEntity;

}
