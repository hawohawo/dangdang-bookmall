package com.dangdang.bookmall.order.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.order.entity.RecordEntity;
import com.dangdang.bookmall.order.service.RecordService;
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
@RequestMapping("order/record")
public class RecordController {
    @Autowired
    private RecordService recordService;


    /**
     * 订单操作记录
     */
    @GetMapping("/records/{orderId}")
    //@RequiresPermissions("order:record:list")
    public R records(@PathVariable Long orderId){

        Map<String,Object> cmap = new HashMap<>();
        cmap.put("order_id",orderId);
        List<RecordEntity> recordEntities = recordService.listByMap(cmap);

        return R.ok().put("records", recordEntities);
    }

    /**
     * 新增订单操作
     */
    @PostMapping("/save")
    //@RequiresPermissions("order:record:save")
    public R saveRecord(@RequestBody RecordEntity record){
        boolean result = recordService.save(record);
        if(result){
            return R.ok();
        }else
            return R.error(400,"添加失败");
    }



    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:record:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = recordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:record:info")
    public R info(@PathVariable("id") Long id){
		RecordEntity record = recordService.getById(id);

        return R.ok().put("record", record);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:record:save")
    public R save(@RequestBody RecordEntity record){
		recordService.save(record);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:record:update")
    public R update(@RequestBody RecordEntity record){
		recordService.updateById(record);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:record:delete")
    public R delete(@RequestBody Long[] ids){
		recordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
