package com.dangdang.bookmall.order.feign;

import com.dangdang.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("user-service")
public interface UserFeignService {
    //查询地址
    @GetMapping("/user/address/feigndefault")
    public R feignDefault();
}
