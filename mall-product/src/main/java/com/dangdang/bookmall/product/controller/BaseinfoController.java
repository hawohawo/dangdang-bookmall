package com.dangdang.bookmall.product.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.dangdang.bookmall.product.entity.BookdetailEntity;
import com.dangdang.bookmall.product.entity.PublishEntity;
import com.dangdang.bookmall.product.entity.dto.StatisticsDto;
import com.dangdang.bookmall.product.entity.dto.StockDto;
import com.dangdang.bookmall.product.entity.vo.BookAllInfoVo;
import com.dangdang.bookmall.product.service.BookdetailService;
import com.dangdang.bookmall.product.service.PublishService;
import com.dangdang.bookmall.product.vo.SelectBookByInsale;
import com.dangdang.bookmall.product.vo.SelectBookByParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dangdang.bookmall.product.entity.BaseinfoEntity;
import com.dangdang.bookmall.product.service.BaseinfoService;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.R;

/**
 * @author zengyuzhi
 * @email shbyku@gmail.com
 * @date 2020-10-17 19:56:33
 */
@RestController
@RequestMapping("product/baseinfo")
public class BaseinfoController {
    @Autowired
    private BaseinfoService baseinfoService;

    @Autowired
    private BookdetailService bookdetailService;

    @Autowired
    private PublishService publishService;


    /**
     * 添加一本图书
     */
    @PostMapping("/book")
    public R addBook(@RequestBody BookAllInfoVo bookAllInfoVo) {
        System.out.println("获取添加图书参数信息:===" + JSON.toJSONString(bookAllInfoVo));
        BaseinfoEntity baseinfoEntity = new BaseinfoEntity();
        PublishEntity publishEntity = new PublishEntity();
        BookdetailEntity bookdetailEntity = new BookdetailEntity();
        BeanUtils.copyProperties(bookAllInfoVo, baseinfoEntity);
        BeanUtils.copyProperties(bookAllInfoVo, publishEntity);
        BeanUtils.copyProperties(bookAllInfoVo, bookdetailEntity);
        try {        //开启事物 并调用服务，返回id值为给 baseinfo进行封装
            //写入图书详细信息部分
            int bookdetailId = bookdetailService.insert(bookdetailEntity);
            //写入图书出版社
            int publichId = publishService.insert(publishEntity);
            //写入图书基本信息
            baseinfoEntity.setPublishId(Long.parseLong(publichId + ""));
            baseinfoEntity.setBookdetailId(Long.parseLong(bookdetailId + ""));
            int insert = baseinfoService.insert(baseinfoEntity);
//      事物结束
        }catch (Exception e){
            return R.error(400,"数据校验出现错误");
        }
        return R.ok();
    }

    /**
     * 获取积分（条件：id）
     */
    @GetMapping("/scoreById/{id}")
    public R scoreById(@RequestParam(value = "current", required = false, defaultValue = "1") int current,
                       @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                       @PathVariable("id") int id) {
        //TODO 还没写校验
        Page<BaseinfoEntity> objectPage = new Page<>(current, size);
        BigDecimal info = baseinfoService.getScoreById(id);
        return R.ok().put("info", info);
    }

    /**
     * 分页示例
     * 查询全部图书信息
     */
    @RequestMapping("/books")
    //@RequiresPermissions("product:baseinfo:list")
    public R books(@RequestParam Map<String, Object> params) {
        PageUtils page = baseinfoService.getBooksType(params);
        return R.ok().put("page", page);

//        IPage<BaseInfoAddNameEntity> info  =  baseinfoService.getBooksType(objectPage);
//        return R.ok().put("info",info);
    }

    /**
     * 列表（按图书分类查询图书信息）
     */
    @RequestMapping("/infoByType")
    public R infobByType(@RequestParam(value = "current", required = false, defaultValue = "1") int current,
                         @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                         @RequestParam int id) {
        //TODO 还没写校验
        Page<BaseinfoEntity> objectPage = new Page<>(current, size);
        IPage<BaseinfoEntity> info = baseinfoService.getBooksByType(objectPage, id);
        return R.ok().put("info", info);
    }

    /**
     * 列表（查询所有图书，参数条图书名称和id还有售价，条件是这些商品是上架的商品）
     */
    @GetMapping("/infobByInsale")
    public R infobByInsale(@RequestParam Map<String, Object> params) {
        //TODO 还没写校验
        PageUtils page = baseinfoService.getBooksByInsale(params);
        return R.ok().put("page", page);
    }

    /**
     * 查询图书信息列表（多条件）
     */
    @RequestMapping("/infoByParams")
    public R infoByParams(@RequestParam(value = "current", required = false, defaultValue = "1") int current,
                          @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                          SelectBookByParam sbbp
    ) {
        //TODO 还没写校验
        Page<BaseinfoEntity> objectPage = new Page<>(current, size);
        IPage<BaseinfoEntity> info = baseinfoService.getBooksByParams(objectPage, sbbp);
        return R.ok().put("info", info);
    }


    /**
     * 远程调用接口测试
     * 根据图书id 获取到图书的名称
     */
    @GetMapping("/feignbookname/{id}")
    public String getBookNameById(@PathVariable("id") Long id) {
        String bookName = baseinfoService.getBookNameById(id);
        return bookName;
    }

    /**
     * 远程调用接口测试
     * 根据图书id 获取到图书的基本信息
     * 返回类型为 BaseinfoEntity
     */
    @GetMapping("/feignbookinfo/{id}")
    public R feignBookInfoById(@PathVariable("id") Long id) {
        BaseinfoEntity baseinfoEntity = baseinfoService.feignBookInfoById(id);
        return R.ok().put("name", baseinfoEntity.getName()).put("priceSj", baseinfoEntity.getPriceSj());
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = baseinfoService.queryPage(params);
        return R.ok().put("page", page);

    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(@RequestParam Long id) {
        BaseinfoEntity baseinfo = baseinfoService.getById(id);
        return R.ok().put("baseinfo", baseinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:baseinfo:save")
    public R save(@RequestBody BaseinfoEntity baseinfo) {
        baseinfoService.save(baseinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:baseinfo:update")
    public R update(@RequestBody BaseinfoEntity baseinfo) {
        baseinfoService.updateById(baseinfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:baseinfo:delete")
    public R delete(@RequestBody Long[] ids) {
        baseinfoService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }


    //    统计 远程调用

    @GetMapping("/off/shelves")//下架总数统计
    public R getTotalOffShelves() {
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setName("下架总数");
        statisticsDto.setValue(Double.valueOf(baseinfoService.getTotalOffShelves()));
        return R.ok().put("offshelves", statisticsDto);
    }

    @GetMapping("/on/shelves")//上架总数统计
    public R getTotalOnShelves() {
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setName("上架总数");
        statisticsDto.setValue(Double.valueOf(baseinfoService.getTotalOnShelves()));
        return R.ok().put("onshelves", statisticsDto);
    }

    @GetMapping("/all/shelves")//全部商品数
    public R getAllShelves() {
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setName("全部商品数");
        statisticsDto.setValue(Double.valueOf(baseinfoService.getAllShelves()));
        return R.ok().put("allshelves", statisticsDto);
    }

    @GetMapping("/stock")//库存
    public R getSalesrRankingOfTheMonth() {
        List<StockDto> StockDto = baseinfoService.getStockDto();
//        String[] result = new String[salesrRankingOfTheMonth.size()+1];
//        int i = 1;
//        for(SalesrRankingOfTheMonthDto salesrRankingOfTheMonthDto : salesrRankingOfTheMonth){
//            result[i] = salesrRankingOfTheMonthDto.getBookNum()+","+salesrRankingOfTheMonthDto.getBookNum()+","+salesrRankingOfTheMonthDto.getBookName();
//            i++;
//        }
//        result[0] = "'score','bookNum','bookName'";
//
//        for(String a : result)
//            System.out.println(a);
        return R.ok().put("Stock", StockDto);
    }


}
