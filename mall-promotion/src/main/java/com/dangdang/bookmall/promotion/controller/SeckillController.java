package com.dangdang.bookmall.promotion.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dangdang.bookmall.promotion.entity.vo.SeckillMiniAppVo;
import com.dangdang.bookmall.promotion.entity.vo.SeckillSessionAndBookInfoVo;
import com.dangdang.bookmall.promotion.entity.vo.SeckillSessionAndBookNumVo;
import com.dangdang.bookmall.promotion.feign.ProductFeignService;
import com.dangdang.bookmall.promotion.service.SeckillSessionBookService;
import com.dangdang.bookmall.promotion.service.SeckillSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.promotion.entity.SeckillEntity;
import com.dangdang.bookmall.promotion.service.SeckillService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;



/**
 * 
 *
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-19 16:54:13
 */
@RestController
@RequestMapping("promotion/seckill")
public class SeckillController {
    @Autowired
    private SeckillService seckillService;
    @Autowired
    private SeckillSessionService seckillSessionService;
    @Autowired
    private SeckillSessionBookService seckillSessionBookService;

    @Autowired
    private ProductFeignService productFeignService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("promotion:seckill:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillService.queryPage(params);

        return R.ok().put("page", page);
    }



    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("promotion:seckill:save")
    public R save(@RequestBody SeckillEntity seckill){
		seckillService.save(seckill);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("promotion:seckill:update")
    public R update(@RequestBody SeckillEntity seckill){
		seckillService.updateById(seckill);

        return R.ok();
    }

    /**
     * 删除
     * 删除 秒杀活动，对应的秒杀商品也随之下架
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("promotion:seckill:delete")
    public R delete(@RequestBody Integer[] ids){
		seckillService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 列表
     * 查询秒杀活动对应的时间段及对应下的商品数量 (时间段必须是上架的时间段)
     */
    @GetMapping("/sessionbooknum/{id}")
    public R getSeckillSessionBooksNum(@PathVariable("id") Integer id){
        List<SeckillSessionAndBookNumVo> seckillSessionBooksNum = seckillService.getSeckillSessionBooksNum(id);
        return R.ok().put("info",seckillSessionBooksNum);
    }

    /**
     * 列表
     * 查询秒杀活动对应的时间段及对应下的商品详细信息
     * tips:查询对应下架的时间段或者下架的秒杀服务不会进行相应的显示
     */
    @GetMapping("/sessionbookinfo/{seckillId}/{seckillSessionId}")
    public R getSeckillSessionBooksinfo(@PathVariable("seckillId") Integer seckillId,@PathVariable("seckillSessionId") Integer seckillSessionId){
        List<SeckillSessionAndBookInfoVo> seckillSessionAndBookInfoVos = seckillService.getSeckillSessionBooksInfo(seckillId,seckillSessionId);
        return R.ok().put("info",seckillSessionAndBookInfoVos);
    }

    /**
     * 列表
     * 查询秒杀活动添加商品的列表
     */
    @GetMapping("/addlist")
    public R addlist(@RequestParam Map<String, Object> params){
        R r = productFeignService.addSeckillBookByInsale(params);
        return r;
    }

    /**
     * 用户 秒杀时间段
     */
    @GetMapping("/seckill")
    public R seckill(){
        List<SeckillMiniAppVo> seckillMiniAppVos = seckillService.seckillDisplay();

        return R.ok().put("info",seckillMiniAppVos);
    }



    /**
     * ==================================================end
     */

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("promotion:seckill:info")
    public R info(@PathVariable("id") Integer id){
        SeckillEntity seckill = seckillService.getById(id);

        return R.ok().put("seckill", seckill);
    }



}
