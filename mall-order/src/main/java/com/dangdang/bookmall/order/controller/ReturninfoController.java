package com.dangdang.bookmall.order.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.dangdang.bookmall.order.entity.OrderinfoEntity;
import com.dangdang.bookmall.order.service.OrderinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.order.entity.ReturninfoEntity;
import com.dangdang.bookmall.order.service.ReturninfoService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;



/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:50:45
 */
@RestController
@RequestMapping("order/returninfo")
public class ReturninfoController {
    @Autowired
    private ReturninfoService returninfoService;

    @Autowired
    private OrderinfoService orderinfoService;


    /**
     * 退货申请列表
     */
    @GetMapping("/returns")
    //@RequiresPermissions("order:returninfo:list")
    public R returns(@RequestParam Map<String, Object> params){
        List<ReturninfoEntity> list = returninfoService.list();
        return R.ok().put("returns", list);
    }

    /**
     * 退货申请筛选（多条件模糊）
     */
    @GetMapping("/findReturn")
    //@RequiresPermissions("order:returninfo:list")
    public R findReturn(@RequestParam Map<String, Object> returnMap){

        String[] conditions = {"code","userPhone","status","timeXd"};
        Object value;
        for (String condition : conditions) {
            if ((value=returnMap.get(condition))==null){
                returnMap.put(condition,"");
            }
        }
        List<ReturninfoEntity> returninfoEntityList = returninfoService.findReturn(returnMap);

        return R.ok().put("return", returninfoEntityList);
    }

    /**
     * 退货申请详情
     */
    @GetMapping("/return/{id}")
    //@RequiresPermissions("order:returninfo:list")
    public R returninfo(@PathVariable Long id){
//        List<ReturninfoEntity> list = returninfoService.list();
        ReturninfoEntity returninfoEntity = returninfoService.getById(id);
        OrderinfoEntity orderinfoEntity = orderinfoService.getById(returninfoEntity.getOrderId());
        List<ReturninfoEntity> returninfoEntities = new ArrayList<>();
        returninfoEntities.add(returninfoEntity);
        return R.ok().put("return", returninfoEntities).put("ordercode",orderinfoEntity.getCode());
    }

    /**
     * 同意退货
     * 状态：  1-申请退货，2-同意退货，4-拒绝退货
     *        3：确认收货，
     */
    @PostMapping("/updateAccept")
    //@RequiresPermissions("order:returninfo:update")
    public R updateAccept(@RequestBody ReturninfoEntity returninfo){
        String address = returninfo.getAddress();
        returninfo = returninfoService.getById(returninfo.getId());
        Integer status = returninfo.getStatus();
        if(status==1) {
            returninfo.setAddress(address);
            returninfo.setStatus(2);
            boolean result = returninfoService.updateById(returninfo);
            if (result) {
                return R.ok();
            } else
                return R.error(400, "同意退货失败");
        } else
            return R.error(400, "当前状态下不可同意退货");
    }

    /**
     * 拒绝退货
     * 状态：  1-申请退货，2-同意退货，4-拒绝退货
     *        3：确认收货，
     */
    @PostMapping("/updateRefuse")
    //@RequiresPermissions("order:returninfo:update")
    public R updateRefuse(@RequestBody ReturninfoEntity returninfo){
        returninfo = returninfoService.getById(returninfo.getId());
        Integer status = returninfo.getStatus();
        if(status==1) {
            returninfo.setStatus(4);
            boolean result = returninfoService.updateById(returninfo);
            if (result) {
                return R.ok();
            } else
                return R.error(400, "拒绝退货失败");
        } else
            return R.error(400, "当前状态下不可拒绝退货");
    }

    /**
     * 确认收货
     * 状态：  1-申请退货，2-同意退货，4-拒绝退货
     *        3：确认收货，
     */
    @PostMapping("/updateReceive")
    //@RequiresPermissions("order:returninfo:update")
    public R updateReceive(@RequestBody ReturninfoEntity returninfo){
        returninfo = returninfoService.getById(returninfo.getId());
        Integer status = returninfo.getStatus();
        if(status==2) {
            returninfo.setStatus(3);
            boolean result = returninfoService.updateById(returninfo);
            if (result) {
                return R.ok();
            } else
                return R.error(400, "确认收货失败");
        } else
            return R.error(400, "当前状态下不可确认收货");
    }






    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:returninfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = returninfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:returninfo:info")
    public R info(@PathVariable("id") Long id){
		ReturninfoEntity returninfo = returninfoService.getById(id);

        return R.ok().put("returninfo", returninfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:returninfo:save")
    public R save(@RequestBody ReturninfoEntity returninfo){
		returninfoService.save(returninfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:returninfo:update")
    public R update(@RequestBody ReturninfoEntity returninfo){
		returninfoService.updateById(returninfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:returninfo:delete")
    public R delete(@RequestBody Long[] ids){
		returninfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
