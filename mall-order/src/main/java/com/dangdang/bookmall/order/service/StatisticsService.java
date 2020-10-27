package com.dangdang.bookmall.order.service;

import com.dangdang.bookmall.order.entity.dto.SalesrRankingOfTheMonthDto;

import java.util.List;

public interface StatisticsService {
    List<SalesrRankingOfTheMonthDto> getSalesrRankingOfTheMonth();
    //当月销量排名

    String getThisMonthOrderNum();
    String getLastMonthOrderNum();
    //本月，上月订单量
    String getThisWeekOrderNum();
    String getLastWeekOrderNum();
    //本周，上周订单量
    String getSalesThisMonth();
    String getSalesLastMonth();
    //本月，上月销售额统计
    String getOrderToBeShipped();
    //待发货订单量
    String getTotalOnShelves();
    //上架商品总数
    String getTotalOffShelves();
    //下架商品总数
    String getDaySales();
    // 今日销售额
    String getAllSales();
    //总销售额

}
