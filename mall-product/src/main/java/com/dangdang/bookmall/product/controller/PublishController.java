package com.dangdang.bookmall.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.product.entity.PublishEntity;
import com.dangdang.bookmall.product.service.PublishService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;



/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:51:28
 */
@RestController
@RequestMapping("product/publish")
public class PublishController {
    @Autowired
    private PublishService publishService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:publish:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = publishService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:publish:info")
    public R info(@PathVariable Long id){
		PublishEntity publish = publishService.getById(id);

        return R.ok().put("publish", publish);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:publish:save")
    public R save(@RequestBody PublishEntity publish){
		publishService.save(publish);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:publish:update")
    public R update(@RequestBody PublishEntity publish){
		publishService.updateById(publish);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:publish:delete")
    public R delete(@RequestBody Long[] ids){
		publishService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
