package com.dangdang.bookmall.user.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.user.entity.AddressEntity;
import com.dangdang.bookmall.user.service.AddressService;
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
@RequestMapping("user/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:address:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = addressService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("user:address:info")
    public R info(@PathVariable("id") Integer id){
		AddressEntity address = addressService.getById(id);

        return R.ok().put("address", address);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("user:address:save")
    public R save(@RequestBody AddressEntity address){
		addressService.save(address);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:address:update")
    public R update(@RequestBody AddressEntity address){
		addressService.updateById(address);
        return R.ok();
    }

    /**
     * 地址排序置为999
     */
    @PostMapping("/updatesort")
    //@RequiresPermissions("user:address:update")
    public R updateSort(){
        addressService.updateAddress();
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("user:address:delete")
    public R delete(@RequestBody Integer[] ids){
		addressService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/default")
    //@RequiresPermissions("user:address:delete")
    public R defaultAddress(){
        AddressEntity defaultAddress = addressService.getDefaultAddress();
        return R.ok().put("address",defaultAddress);
    }

    @GetMapping("/feigndefault")
    //@RequiresPermissions("user:address:delete")
    public R feignDefault(){
        AddressEntity defaultAddress = addressService.getDefaultAddress();
        return R.ok().put("address",defaultAddress.getAddress()).put("userPhone",defaultAddress.getPhone()).put("name",defaultAddress.getName()).put("postal",defaultAddress.getPostal());

    }

}
