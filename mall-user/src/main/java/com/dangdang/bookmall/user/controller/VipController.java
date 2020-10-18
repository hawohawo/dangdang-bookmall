package com.dangdang.bookmall.user.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dangdang.bookmall.user.entity.VipEntity;
import com.dangdang.bookmall.user.service.VipService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;



/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-17 20:59:59
 */
@RestController
@RequestMapping("user/vip")
public class VipController {
    @Autowired
    private VipService vipService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:vip:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = vipService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("user:vip:info")
    public R info(@PathVariable("id") Integer id){
		VipEntity vip = vipService.getById(id);

        return R.ok().put("vip", vip);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("user:vip:save")
    public R save(@RequestBody VipEntity vip){
		vipService.save(vip);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:vip:update")
    public R update(@RequestBody VipEntity vip){
		vipService.updateById(vip);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("user:vip:delete")
    public R delete(@RequestBody Integer[] ids){
		vipService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
