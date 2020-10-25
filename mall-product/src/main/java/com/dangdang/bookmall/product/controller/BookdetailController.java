package com.dangdang.bookmall.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.checkerframework.common.util.report.qual.ReportUnqualified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.product.entity.BookdetailEntity;
import com.dangdang.bookmall.product.service.BookdetailService;
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
@RequestMapping("product/bookdetail")
public class BookdetailController {
    @Autowired
    private BookdetailService bookdetailService;

    /**
     * 添加图文详情
     */
    @RequestMapping("/saveBookDetail")
    public R saveBookDetail(@RequestBody BookdetailEntity bookdetail){
        //TODO 还没写校验
        bookdetailService.save(bookdetail);
        return R.ok();
    }

    /**
     * 删除图文详情
     */
    @RequestMapping("/deleteBookDetail")
    public R deleteBookDetail(@RequestBody Long[] ids){
        //TODO 还没写校验
        bookdetailService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 修改图文详情
     */
    @RequestMapping("/updateBookDetail")
    //@RequiresPermissions("product:bookdetail:update")
    public R updateBookDetail(@RequestBody BookdetailEntity bookdetail){
        bookdetailService.updateById(bookdetail);

        return R.ok();
    }





    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:bookdetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bookdetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info")
    public R info(@RequestParam Long id){
		BookdetailEntity bookdetail = bookdetailService.getById(id);

        return R.ok().put("bookdetail", bookdetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:bookdetail:save")
    public R save(@RequestBody BookdetailEntity bookdetail){
		bookdetailService.save(bookdetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:bookdetail:update")
    public R update(@RequestBody BookdetailEntity bookdetail){
		bookdetailService.updateById(bookdetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:bookdetail:delete")
    public R delete(@RequestBody Long[] ids){
		bookdetailService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
