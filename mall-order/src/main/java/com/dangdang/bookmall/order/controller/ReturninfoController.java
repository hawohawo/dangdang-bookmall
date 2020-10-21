package com.dangdang.bookmall.order.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.order.entity.ReturninfoEntity;
import com.dangdang.bookmall.order.service.ReturninfoService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;



/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:50:45
 */
@RestController
@RequestMapping("order/returninfo")
public class ReturninfoController {
    @Autowired
    private ReturninfoService returninfoService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:returninfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = returninfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:returninfo:info")
    public R info(@PathVariable("id") Long id){
		ReturninfoEntity returninfo = returninfoService.getById(id);

        return R.ok().put("returninfo", returninfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:returninfo:save")
    public R save(@RequestBody ReturninfoEntity returninfo){
		returninfoService.save(returninfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:returninfo:update")
    public R update(@RequestBody ReturninfoEntity returninfo){
		returninfoService.updateById(returninfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:returninfo:delete")
    public R delete(@RequestBody Long[] ids){
		returninfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
