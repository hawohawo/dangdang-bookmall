package com.dangdang.bookmall.order.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dangdang.bookmall.order.entity.ReceiveEntity;
import com.dangdang.bookmall.order.service.ReceiveService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;



/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:50:46
 */
@RestController
@RequestMapping("order/receive")
public class ReceiveController {
    @Autowired
    private ReceiveService receiveService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:receive:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = receiveService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:receive:info")
    public R info(@PathVariable("id") Long id){
		ReceiveEntity receive = receiveService.getById(id);

        return R.ok().put("receive", receive);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:receive:save")
    public R save(@RequestBody ReceiveEntity receive){
		receiveService.save(receive);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:receive:update")
    public R update(@RequestBody ReceiveEntity receive){
		receiveService.updateById(receive);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:receive:delete")
    public R delete(@RequestBody Long[] ids){
		receiveService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
