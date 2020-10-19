package com.dangdang.bookmall.user.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dangdang.bookmall.user.entity.CarEntity;
import com.dangdang.bookmall.user.service.CarService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;



/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 17:09:29
 */
@RestController
@RequestMapping("user/car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:car:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = carService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("user:car:info")
    public R info(@PathVariable("id") Integer id){
		CarEntity car = carService.getById(id);

        return R.ok().put("car", car);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("user:car:save")
    public R save(@RequestBody CarEntity car){
		carService.save(car);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:car:update")
    public R update(@RequestBody CarEntity car){
		carService.updateById(car);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("user:car:delete")
    public R delete(@RequestBody Integer[] ids){
		carService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
