package com.dangdang.bookmall.order.feign;

import com.dangdang.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zengyuzhi
 * @date 2020/10/18 上午10:52
 */

@FeignClient("product-service")
public interface ProductFeignService {
    @GetMapping("/product/baseinfo/testbook")
    public R setAndGetBook();

}
