package com.dangdang.bookmall.order.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dangdang.bookmall.order.entity.ReasonEntity;
import com.dangdang.bookmall.order.service.ReasonService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;



/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:50:46
 */
@RestController
@RequestMapping("order/reason")
public class ReasonController {
    @Autowired
    private ReasonService reasonService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:reason:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = reasonService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:reason:info")
    public R info(@PathVariable("id") Long id){
		ReasonEntity reason = reasonService.getById(id);

        return R.ok().put("reason", reason);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:reason:save")
    public R save(@RequestBody ReasonEntity reason){
		reasonService.save(reason);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:reason:update")
    public R update(@RequestBody ReasonEntity reason){
		reasonService.updateById(reason);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:reason:delete")
    public R delete(@RequestBody Long[] ids){
		reasonService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
