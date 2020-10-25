package com.dangdang.bookmall.promotion.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dangdang.bookmall.promotion.entity.vo.HotBookVo;
import com.dangdang.bookmall.promotion.feign.ProductFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dangdang.bookmall.promotion.entity.HotEntity;
import com.dangdang.bookmall.promotion.service.HotService;
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
@RequestMapping("promotion/hot")
public class HotController {
    @Autowired
    private HotService hotService;

    @Autowired
    private ProductFeignService productFeignService;

    /**
     * 新增推荐图书的列表
     */
    @RequestMapping("/addlist")
    public R addList(@RequestParam Map<String, Object> params){
        R r = productFeignService.infobByInsale(params);
        return r;
    }



    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = hotService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("promotion:hot:info")
    public R info(@PathVariable("id") Integer id){
		HotEntity hot = hotService.getById(id);

        return R.ok().put("hot", hot);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("promotion:hot:save")
    public R save(@RequestBody HotEntity hot){
		hotService.save(hot);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("promotion:hot:update")
    public R update(@RequestBody HotEntity hot){
		hotService.updateById(hot);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("promotion:hot:delete")
    public R delete(@RequestBody Integer[] ids){
		hotService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
