package com.dangdang.bookmall.promotion.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dangdang.bookmall.promotion.entity.CouponUserEntity;
import com.dangdang.bookmall.promotion.service.CouponUserService;
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
@RequestMapping("promotion/couponuser")
public class CouponUserController {
    @Autowired
    private CouponUserService couponUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("promotion:couponuser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponUserService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("promotion:couponuser:info")
    public R info(@PathVariable("id") Integer id){
		CouponUserEntity couponUser = couponUserService.getById(id);

        return R.ok().put("couponUser", couponUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("promotion:couponuser:save")
    public R save(@RequestBody CouponUserEntity couponUser){
		couponUserService.save(couponUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("promotion:couponuser:update")
    public R update(@RequestBody CouponUserEntity couponUser){
		couponUserService.updateById(couponUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("promotion:couponuser:delete")
    public R delete(@RequestBody Integer[] ids){
		couponUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
