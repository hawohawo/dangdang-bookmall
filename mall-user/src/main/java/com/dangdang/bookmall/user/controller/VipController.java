package com.dangdang.bookmall.user.controller;

import java.util.Arrays;
import java.util.Map;

import com.dangdang.bookmall.user.entity.vo.IdAndGrowthVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.user.entity.VipEntity;
import com.dangdang.bookmall.user.service.VipService;
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
@RequestMapping("user/vip")
public class VipController {
    @Autowired
    private VipService vipService;

    private final Integer GROWTH_VALUE = 200;

    /**
     * 计算会员成长值并升级会员登记
     * 0.普通会员 1倍
     * 1.银会员 1.2倍
     * 2.金会员 1.5倍
     * 3.钻石会员 2倍
     * 每200成长值升级一个段。成长值获取的积分是普通积分的n倍
     */
    @PostMapping("/updateVip")
    //@RequiresPermissions("user:vip:update")
    public R updateVip(@RequestBody IdAndGrowthVo idAndGrowthVo){
        VipEntity vip = vipService.getById(idAndGrowthVo.getId());
        Integer vipGrowth = vip.getLevel() * GROWTH_VALUE + vip.getGrowth() +idAndGrowthVo.getTotalJF();
        //取商
        Integer viplevel = vipGrowth / GROWTH_VALUE;
        if (viplevel>=3){
            vip.setLevel(3);
            vip.setGrowth(0);
            vip.setScore(vip.getScore()+idAndGrowthVo.getTotalJF());
        }else {
            vip.setLevel(viplevel);
            vip.setGrowth(vipGrowth-viplevel*GROWTH_VALUE);
            vip.setScore(vip.getScore()+idAndGrowthVo.getTotalJF());
        }
        vipService.updateById(vip);
        return R.ok();
//        if(vip.getLevel()==0 && viplevel>0){
//            if(vipGrowth<=600){
//                vip.setLevel(viplevel);
//                vip.setGrowth(vipGrowthNew);
//                vipService.updateById(vip);
//                return R.ok();
//            }else {
//                vip.setLevel(3);
//                vip.setGrowth(vipGrowth-600);
//                vipService.updateById(vip);
//                return R.ok();
//            }
//        }else if(vip.getLevel()==1 && viplevel>0){
//            if(vipGrowth<=400){
//                vip.setLevel(viplevel+1);
//                vip.setGrowth(vipGrowthNew);
//                vipService.updateById(vip);
//                return R.ok();
//            }else {
//                vip.setLevel(3);
//                vip.setGrowth(vipGrowth-400);
//                vipService.updateById(vip);
//                return R.ok();
//            }
//        }else if(vip.getLevel()==2 && viplevel>0){
//            if(vipGrowth<=200){
//                vip.setLevel(viplevel+2);
//                vip.setGrowth(vipGrowthNew);
//                vipService.updateById(vip);
//                return R.ok();
//            }else {
//                vip.setLevel(3);
//                vip.setGrowth(vipGrowth-200);
//                vipService.updateById(vip);
//                return R.ok();
//            }
//        }else if(vip.getLevel()==3){
//            return R.error(400,"当前会员的等级已达最高");
//        }else {
//            return R.error(400,"当前会员积分不足，升级失败");
//        }
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:vip:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = vipService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("user:vip:info")
    public R info(@PathVariable("id") Integer id){
        VipEntity vip = vipService.getById(id);

        return R.ok().put("vip", vip);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("user:vip:save")
    public R save(@RequestBody VipEntity vip){
        vipService.save(vip);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:vip:update")
    public R update(@RequestBody VipEntity vip){
        vipService.updateById(vip);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("user:vip:delete")
    public R delete(@RequestBody Integer[] ids){
        vipService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
