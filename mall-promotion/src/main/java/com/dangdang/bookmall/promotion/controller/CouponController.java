package com.dangdang.bookmall.promotion.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dangdang.bookmall.promotion.entity.CouponUserEntity;
import com.dangdang.bookmall.promotion.entity.vo.CouponDetailVo;
import com.dangdang.bookmall.promotion.service.CouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.promotion.entity.CouponEntity;
import com.dangdang.bookmall.promotion.service.CouponService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;



/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:54:13
 */
@RestController
@RequestMapping("promotion/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponUserService couponUserService;


    /**
     * 优惠券列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("promotion:coupon:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 新增优惠券
     */
    @RequestMapping("/save")
    //@RequiresPermissions("promotion:coupon:save")
    public R save(@RequestBody CouponEntity coupon){
        couponService.save(coupon);

        return R.ok();
    }

    /**
     * 修改优惠券信息(包括优惠券的停用)
     */
    @RequestMapping("/update")
    //@RequiresPermissions("promotion:coupon:update")
    public R update(@RequestBody CouponEntity coupon){
        couponService.updateById(coupon);

        return R.ok();
    }

    @RequestMapping("/delete")
    //@RequiresPermissions("promotion:coupon:delete")
    public R delete(@RequestBody Integer[] ids){
        couponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 优惠券详细信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("promotion:coupon:info")
    public R info(@PathVariable("id") Integer id){
        //优惠券详细信息封装信息 名称 使用门槛 面值 状态 有效期 总发行量
        //已领取 待领取 已使用 未使用
        CouponDetailVo couponDetailVo = new CouponDetailVo();
        couponDetailVo.setCouponEntity(couponService.getById(id));

        //获取领取的用户信息
        QueryWrapper<CouponUserEntity> cuQueryWrapper = new QueryWrapper<>();
        cuQueryWrapper.eq("coupon_id",id);
        List<CouponUserEntity> couponUsers = couponUserService.list(cuQueryWrapper);
        couponDetailVo.setCouponUserEntity(couponUsers);

        QueryWrapper<CouponUserEntity> countQueryWrapper = new QueryWrapper<>();
        countQueryWrapper.eq("coupon_id",id).eq("status",0);
        int notUsed = couponUserService.count(countQueryWrapper);
        int receivedNum = couponUsers.size();

        //封装已领取 待领取 已使用 未使用
        couponDetailVo.setReceived(receivedNum);
        couponDetailVo.setUnclaimed(couponDetailVo.getCouponEntity().getNumber() - receivedNum);
        couponDetailVo.setUsed(receivedNum-notUsed);
        couponDetailVo.setNotUsed(notUsed);

        return R.ok().put("coupondetail", couponDetailVo);
    }

    /**
     * =================================================end
     */



}
