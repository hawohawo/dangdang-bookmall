package com.dangdang.bookmall.promotion.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dangdang.bookmall.promotion.entity.AdEntity;
import com.dangdang.bookmall.promotion.service.AdService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;



/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-17 20:59:11
 */
@RestController
@RequestMapping("promotion/ad")
public class AdController {
    @Autowired
    private AdService adService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("promotion:ad:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("promotion:ad:info")
    public R info(@PathVariable("id") Integer id){
		AdEntity ad = adService.getById(id);

        return R.ok().put("ad", ad);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("promotion:ad:save")
    public R save(@RequestBody AdEntity ad){
		adService.save(ad);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("promotion:ad:update")
    public R update(@RequestBody AdEntity ad){
		adService.updateById(ad);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("promotion:ad:delete")
    public R delete(@RequestBody Integer[] ids){
		adService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
