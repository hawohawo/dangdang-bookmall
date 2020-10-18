package com.dangdang.bookmall.order.controller;

import java.util.Arrays;
import java.util.Map;

import com.dangdang.bookmall.order.entity.BookinfoEntity;
import com.dangdang.bookmall.order.feign.ProductFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.order.entity.OrderinfoEntity;
import com.dangdang.bookmall.order.service.OrderinfoService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;



/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-17 20:58:04
 */
@RestController
@RequestMapping("order/orderinfo")
public class OrderinfoController {
    @Autowired
    private OrderinfoService orderinfoService;

    //远程调用测试接口
    @Autowired
    private ProductFeignService productFeignService;

    /**
     * 远程调用接口测试
     * for ： 订单服务（this） -> 商品服务
     * detail ： 输出订单信息 和 商品的信息
     */

    @GetMapping("/books")
    public R test(){
        OrderinfoEntity orderinfoEntity = new OrderinfoEntity();
        orderinfoEntity.setId(1L);
        orderinfoEntity.setCode("1erf-we25-de4c-f78d");
        R book = productFeignService.setAndGetBook();
        return R.ok().put("orderinfo",orderinfoEntity).put("book",book.get("book"));

    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:orderinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:orderinfo:info")
    public R info(@PathVariable("id") Long id){
		OrderinfoEntity orderinfo = orderinfoService.getById(id);

        return R.ok().put("orderinfo", orderinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:orderinfo:save")
    public R save(@RequestBody OrderinfoEntity orderinfo){
		orderinfoService.save(orderinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:orderinfo:update")
    public R update(@RequestBody OrderinfoEntity orderinfo){
		orderinfoService.updateById(orderinfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:orderinfo:delete")
    public R delete(@RequestBody Long[] ids){
		orderinfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
