package com.dangdang.bookmall.promotion.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.promotion.entity.SeckillSessionBookEntity;
import com.dangdang.bookmall.promotion.service.SeckillSessionBookService;
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
@RequestMapping("promotion/seckillsessionbook")
public class SeckillSessionBookController {
    @Autowired
    private SeckillSessionBookService seckillSessionBookService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("promotion:seckillsessionbook:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillSessionBookService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("promotion:seckillsessionbook:info")
    public R info(@PathVariable("id") Integer id){
		SeckillSessionBookEntity seckillSessionBook = seckillSessionBookService.getById(id);

        return R.ok().put("seckillSessionBook", seckillSessionBook);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("promotion:seckillsessionbook:save")
    public R save(@RequestBody SeckillSessionBookEntity seckillSessionBook){
		seckillSessionBookService.save(seckillSessionBook);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("promotion:seckillsessionbook:update")
    public R update(@RequestBody SeckillSessionBookEntity seckillSessionBook){
		seckillSessionBookService.updateById(seckillSessionBook);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("promotion:seckillsessionbook:delete")
    public R delete(@RequestBody Integer[] ids){
		seckillSessionBookService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
