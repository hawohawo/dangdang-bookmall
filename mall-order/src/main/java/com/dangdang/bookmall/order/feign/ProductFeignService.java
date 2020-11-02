package com.dangdang.bookmall.order.feign;

import com.dangdang.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zengyuzhi
 * @date 2020/10/18 上午10:52
 */

@FeignClient("product-service")
public interface ProductFeignService {
    @GetMapping("/product/baseinfo/testbook")
    public R setAndGetBook();

    @GetMapping("/product/baseinfo/scoreById/{id}")
    public Integer scoreById(@PathVariable("id") int id);

    //锁定库存
    @GetMapping("/product/baseinfo/lockstock")
    public boolean lockStock(@RequestParam("ids") Integer[] ids,@RequestParam("nums") Integer[] nums);



}
