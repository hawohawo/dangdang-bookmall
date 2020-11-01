package com.dangdang.bookmall.promotion.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dangdang.bookmall.promotion.entity.SeckillSessionEntity;
import com.dangdang.bookmall.promotion.service.SeckillSessionService;
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
@RequestMapping("promotion/seckillsession")
public class SeckillSessionController {
    @Autowired
    private SeckillSessionService seckillSessionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("promotion:seckillsession:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillSessionService.queryPage(params);

        return R.ok().put("page", page);
    }



    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("promotion:seckillsession:save")
    public R save(@RequestBody SeckillSessionEntity seckillSession){
        System.out.println(seckillSession.getStartTime());
//		seckillSessionService.save(seckillSession);
//
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("promotion:seckillsession:update")
    public R update(@RequestBody SeckillSessionEntity seckillSession){
		seckillSessionService.updateById(seckillSession);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("promotion:seckillsession:delete")
    public R delete(@RequestBody Integer[] ids){
		seckillSessionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * ======================================================
     */

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("promotion:seckillsession:info")
    public R info(@PathVariable("id") Integer id){
        SeckillSessionEntity seckillSession = seckillSessionService.getById(id);

        return R.ok().put("seckillSession", seckillSession);
    }




}
