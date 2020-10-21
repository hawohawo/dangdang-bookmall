package com.dangdang.bookmall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.dangdang.bookmall.product.dto.BaseinfosEntity;
import com.dangdang.bookmall.product.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.product.entity.BaseinfoEntity;
import com.dangdang.bookmall.product.service.BaseinfoService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;

/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-17 19:56:33
 */
@RestController
@RequestMapping("product/baseinfo")
public class BaseinfoController {
    @Autowired
    private BaseinfoService baseinfoService;

    @Autowired
    private TypeService typeService;

    /**
     * 查询全部图书信息
     */
    @RequestMapping("/books")
    //@RequiresPermissions("product:baseinfo:list")
    public R books(@RequestParam Map<String, Object> params){
        PageUtils info  =  baseinfoService.getBooksType(params);
        return R.ok().put("info",info);
    }

    /**
     * 列表（按图书分类查询图书信息）
     */
    @RequestMapping("/infoByType/{typeId}")
    //@RequiresPermissions("product:baseinfo:info")
    public R infobByType(@PathVariable("typeId") int typeId){
        List<BaseinfoEntity> info  =  baseinfoService.getBooksByType(typeId);
        return R.ok().put("info", info);
    }





    /**
     * 远程调用接口测试
     * for ： 订单服务 -> 商品服务（this）
     * detail ： 输出订单信息 和 某个商品的信息
     */
    @GetMapping("/testbook")
    public R setAndGetBook(){
        BaseinfoEntity baseinfoEntity = new BaseinfoEntity();
        baseinfoEntity.setId(1L);
        baseinfoEntity.setAuthor("王尔德");
        baseinfoEntity.setName("豌豆实验");
        return R.ok().put("book",baseinfoEntity);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:baseinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = baseinfoService.queryPage(params);
        return R.ok().put("page", page);

    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:baseinfo:info")
    public R info(@PathVariable("id") Long id){
		BaseinfoEntity baseinfo = baseinfoService.getById(id);

        return R.ok().put("baseinfo", baseinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:baseinfo:save")
    public R save(@RequestBody BaseinfoEntity baseinfo){
		baseinfoService.save(baseinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:baseinfo:update")
    public R update(@RequestBody BaseinfoEntity baseinfo){
		baseinfoService.updateById(baseinfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:baseinfo:delete")
    public R delete(@RequestBody Long[] ids){
		baseinfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
