package com.dangdang.bookmall.order.controller;

import java.util.*;

import com.dangdang.bookmall.order.entity.dto.SalesrRankingOfTheMonthDto;
import com.dangdang.bookmall.order.entity.dto.StatisticsDto;
import com.dangdang.bookmall.order.service.StatisticsService;
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


    @Autowired
    private StatisticsService statisticsService;

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






//




    /**
     * 当月销量排名
     * **/
    @GetMapping("/srotm")
    public R getSalesrRankingOfTheMonth(){
        List<SalesrRankingOfTheMonthDto> salesrRankingOfTheMonth = statisticsService.getSalesrRankingOfTheMonth();
        List<String> bookNameDate= new ArrayList<>();
        List<Integer> bookNumDate= new ArrayList<>();

        for(SalesrRankingOfTheMonthDto salesrRankingOfTheMonthDto: salesrRankingOfTheMonth){
            bookNameDate.add(salesrRankingOfTheMonthDto.getBookName());
            bookNumDate.add(salesrRankingOfTheMonthDto.getBookNum());
        }




        return R.ok().put("bookNameDate",bookNameDate).put("bookNumDate",bookNumDate);
    }



    @GetMapping("/month/order")//月订单量统计
    public R getMonthOrder(){
        List<StatisticsDto> statisticsDtos=new ArrayList<>();
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setName("本月订单量");
        statisticsDto.setValue(Double.valueOf(statisticsService.getThisMonthOrderNum()));
        StatisticsDto statisticsDto2 = new StatisticsDto();
        statisticsDto2.setName("上月订单量");
        statisticsDto2.setValue(Double.valueOf(statisticsService.getLastMonthOrderNum()));
        statisticsDtos.add(statisticsDto);
        statisticsDtos.add(statisticsDto2);
        return R.ok().put("monthorder",statisticsDtos);
    }
    @GetMapping("/week/order")//周订单量统计
    public  R getWeekOrder(){
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setName("本周订单量");
        statisticsDto.setValue(Double.valueOf(statisticsService.getThisWeekOrderNum()));
        StatisticsDto statisticsDto2 =new StatisticsDto();
        statisticsDto2.setName("上周订单量");
        statisticsDto2.setValue(Double.valueOf(statisticsService.getLastWeekOrderNum()));
        statisticsDtos.add(statisticsDto);
        statisticsDtos.add(statisticsDto2);
        return  R.ok().put("weekorder",statisticsDtos);
    }
    @GetMapping("/sales/statistics")//月销售额统计
    public  R getSalesStatistics(){
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setName("本月销售额");
        statisticsDto.setValue(Double.valueOf(statisticsService.getSalesThisMonth()));
        StatisticsDto statisticsDto2 =new StatisticsDto();
        statisticsDto2.setName("上月销售额");
        statisticsDto2.setValue(Double.valueOf(statisticsService.getSalesLastMonth()));
        statisticsDtos.add(statisticsDto);
        statisticsDtos.add(statisticsDto2);
        return  R.ok().put("salesstatistics",statisticsDtos);
    }
    @GetMapping("/order/shipped")//待发货订单量统计
    public  R getOrderToBeShipped(){
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setName("待发货订单量");
        statisticsDto.setValue(Double.valueOf(statisticsService.getOrderToBeShipped()));
        return  R.ok().put("ordershipped",statisticsDto);
    }
    @GetMapping("/day/sales")//今日销售额
    public R getDaySales(){
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setName("今日销售额");
        statisticsDto.setValue(Double.valueOf(statisticsService.getDaySales()));
        return  R.ok().put("daysales",statisticsDto);
    }
    @GetMapping("/all/sales")//总销售额
    public R getAllSales(){
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setName("总销售额");
        statisticsDto.setValue(Double.valueOf(statisticsService.getAllSales()));
        return  R.ok().put("allsales",statisticsDto);
    }



}
