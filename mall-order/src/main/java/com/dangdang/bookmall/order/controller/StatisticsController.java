package com.dangdang.bookmall.order.controller;


import com.dangdang.bookmall.order.entity.dto.SalesrRankingOfTheMonthDto;
import com.dangdang.bookmall.order.entity.dto.StatisticsDto;
import com.dangdang.bookmall.order.service.StatisticsService;
import com.dangdang.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * 当月销量排名
     * **/
    @GetMapping("/srotm")
    public R getSalesrRankingOfTheMonth(){
        List<SalesrRankingOfTheMonthDto> salesrRankingOfTheMonth = statisticsService.getSalesrRankingOfTheMonth();
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
        return R.ok().put("salesrRankingOfTheMonth",salesrRankingOfTheMonth);
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
