package com.dangdang.bookmall.promotion.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.promotion.entity.AdEntity;
import com.dangdang.bookmall.promotion.service.AdService;
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
@RequestMapping("promotion/ad")
public class AdController {
    @Autowired
    private AdService adService;

    /**
     * 广告列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("promotion:ad:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 显示某条广告信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("promotion:ad:info")
    public R info(@PathVariable("id") Integer id){
		AdEntity ad = adService.getById(id);

        return R.ok().put("ad", ad);
    }

    /**
     * 新增广告信息
     */
    @RequestMapping("/save")
    //@RequiresPermissions("promotion:ad:save")
    public R save(@RequestBody AdEntity ad){
		adService.save(ad);
        return R.ok();
    }

    /**
     * 修改广告信息
     */
    @RequestMapping("/update")
    //@RequiresPermissions("promotion:ad:update")
    public R update(@RequestBody AdEntity ad){
		adService.updateById(ad);

        return R.ok();
    }

    /**
     * 删除byId
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("promotion:ad:delete")
    public R delete(@RequestBody Integer[] ids){
		adService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
