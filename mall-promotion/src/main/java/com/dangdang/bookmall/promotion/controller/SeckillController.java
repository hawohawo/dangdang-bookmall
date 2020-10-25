package com.dangdang.bookmall.promotion.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dangdang.bookmall.promotion.entity.SeckillEntity;
import com.dangdang.bookmall.promotion.service.SeckillService;
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
@RequestMapping("promotion/seckill")
public class SeckillController {
    @Autowired
    private SeckillService seckillService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("promotion:seckill:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillService.queryPage(params);

        return R.ok().put("page", page);
    }



    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("promotion:seckill:save")
    public R save(@RequestBody SeckillEntity seckill){
		seckillService.save(seckill);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("promotion:seckill:update")
    public R update(@RequestBody SeckillEntity seckill){
		seckillService.updateById(seckill);

        return R.ok();
    }

    /**
     * 删除
     * 删除 秒杀活动，对应的秒杀商品也随之下架
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("promotion:seckill:delete")
    public R delete(@RequestBody Integer[] ids){
		seckillService.removeByIds(Arrays.asList(ids));
        //TODO 秒杀的商品也需要下架
        return R.ok();
    }



    /**
     * ==================================================end
     */

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("promotion:seckill:info")
    public R info(@PathVariable("id") Integer id){
        SeckillEntity seckill = seckillService.getById(id);

        return R.ok().put("seckill", seckill);
    }

}
