package com.dangdang.bookmall.product.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.dangdang.bookmall.product.dto.BaseInfoAddNameEntity;
import com.dangdang.bookmall.product.dto.SelectBookByInsale;
import com.dangdang.bookmall.product.dto.SelectBookByParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 获取积分（条件：id）
     */
    @GetMapping("/scoreById/{id}")
    public R scoreById(@RequestParam(value = "current", required = false, defaultValue = "1") int current,
                         @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                         @PathVariable("id") int id){
        //TODO 还没写校验
        Page<BaseinfoEntity> objectPage = new Page<>(current,size);
        BigDecimal info  =  baseinfoService.getScoreById(id);
        return R.ok().put("info", info);
    }

    /**
     * 删除一条图书信息(批量修改)
     */
    @RequestMapping("/deletebook")
    public R deletebook(@RequestBody Long[] ids){
        //TODO 还没写校验
        baseinfoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 修改一条图书信息
     */
    @RequestMapping("/updatebook")
    public R updatebook(@RequestBody BaseinfoEntity baseinfo){
        //TODO 还没写校验
        baseinfoService.updateById(baseinfo);
        return R.ok();
    }

    /**
     * 添加一条图书信息
     */
    @RequestMapping("/savebook")
    public R savebooks(@RequestBody BaseinfoEntity baseinfo){
        //TODO 还没写校验
        baseinfoService.save(baseinfo);
        return R.ok();
    }

    /**
     * 分页示例
     * 查询全部图书信息
     */
    @RequestMapping("/books")
    //@RequiresPermissions("product:baseinfo:list")
    public R books(@RequestParam(value = "current", required = false, defaultValue = "1") int current,
                   @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        Page<BaseInfoAddNameEntity> objectPage = new Page<>(current,size);
        IPage<BaseInfoAddNameEntity> info  =  baseinfoService.getBooksType(objectPage);
        return R.ok().put("info",info);


    }

    /**
     * 列表（按图书分类查询图书信息）
     */
    @RequestMapping("/infoByType")
    public R infobByType(@RequestParam(value = "current", required = false, defaultValue = "1") int current,
                         @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                         @RequestParam int id){
        //TODO 还没写校验
        Page<BaseinfoEntity> objectPage = new Page<>(current,size);
        IPage<BaseinfoEntity> info  =  baseinfoService.getBooksByType(objectPage,id);
        return R.ok().put("info", info);
    }

    /**
     * 列表（查询所有图书，参数条图书名称和id还有售价，条件是这些商品是上架的商品）
     */
    @GetMapping("/infobByInsale")
    public R infobByInsale(@RequestParam(value = "current", required = false, defaultValue = "1") int current,
                         @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        //TODO 还没写校验
        Page<SelectBookByInsale> objectPage = new Page<>(current,size);
        IPage<SelectBookByInsale> info  =  baseinfoService.getBooksByInsale(objectPage);
        return R.ok().put("info", info);
    }

    /**
     * 查询图书信息列表（多条件）
     */
    @RequestMapping("/infoByParams")
    public R infoByParams(@RequestParam(value = "current", required = false, defaultValue = "1") int current,
                          @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                          SelectBookByParam sbbp
                          ){
        //TODO 还没写校验
        Page<BaseinfoEntity> objectPage = new Page<>(current,size);
        IPage<BaseinfoEntity> info  =  baseinfoService.getBooksByParams(objectPage,sbbp);
        return R.ok().put("info", info);
    }


    /**
     * 远程调用接口测试
     * 根据图书id 获取到图书的名称
     */
    @GetMapping("/feignbookname/{id}")
    public String getBookNameById(@PathVariable("id") Long id){
        String bookName = baseinfoService.getBookNameById(id);
        return bookName;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = baseinfoService.queryPage(params);
        return R.ok().put("page", page);

    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(@RequestParam Long id){
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
