package com.dangdang.bookmall.promotion.feign;

import com.dangdang.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author zengyuzhi
 * @date 2020/10/19 下午5:25
 */

@FeignClient("product-service")
public interface ProductFeignService {
    @GetMapping("product/baseinfo/feignbookname/{id}")
    public String getBookNameById(@PathVariable("id") Long id);

    /**
     * 列表（查询所有图书，参数条图书名称和id还有售价，条件是这些商品是上架的商品）
     */
    @GetMapping("product/baseinfo/infobByInsale")
    public R infobByInsale(@RequestParam Map<String, Object> params);


}
