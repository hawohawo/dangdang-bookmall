package com.dangdang.bookmall.order.dao;

import com.dangdang.bookmall.order.entity.dto.SalesrRankingOfTheMonthDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StatisticsDao {

    @Select("SELECT a.book_name,a.book_num FROM omt_bookinfo a JOIN omt_orderinfo b ON a.order_id = b.`code` where DATE_FORMAT(time_xd,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m') ORDER BY a.book_num DESC")

    List<SalesrRankingOfTheMonthDto> getSalesrRankingOfTheMonth();

    @Select("SELECT   COUNT(id) from omt_orderinfo where `status` =7 AND DATE_FORMAT(time_xd,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m')")

    String getThisMonthOrderNum();

    @Select("SELECT   COUNT(id) from omt_orderinfo where `status` =7 AND DATE_FORMAT(time_xd,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m')")

    String getLastMonthOrderNum();

    @Select("SELECT COUNT(id) FROM omt_orderinfo WHERE `status` =7 AND YEARWEEK(DATE_FORMAT(time_xd,'%Y-%m-%d')) = YEARWEEK(now())")

    String getThisWeekOrderNum();

    @Select("SELECT COUNT(id) FROM omt_orderinfo WHERE `status` =7 AND YEARWEEK(DATE_FORMAT(time_xd,'%Y-%m-%d')) = YEARWEEK(now())-1")

    String getLastWeekOrderNum();

    @Select("SELECT SUM(a.book_price) FROM omt_bookinfo a JOIN omt_orderinfo b ON a.order_id = b.`code` where date_format(time_xd,'%Y-%m')=date_format(now(),'%Y-%m')")

    String getSalesThisMonth();

    @Select("SELECT SUM(a.book_price) FROM omt_bookinfo a JOIN omt_orderinfo b ON a.order_id = b.`code` where date_format(time_xd,'%Y-%m')=date_format(DATE_SUB(CURDATE(),INTERVAL 1 MONTH),'%Y-%m')")

    String getSalesLastMonth();

    @Select("SELECT COUNT(id) FROM omt_orderinfo WHERE `status` =1")

    String getOrderToBeShipped();

    @Select("SELECT COUNT(id) FROM bmt_baseinfo WHERE insale = 0")

    String getTotalOnShelves();

    @Select("SELECT COUNT(id) FROM bmt_baseinfo WHERE insale = 1")

    String getTotalOffShelves();

//    @Select("SELECT name,stock FROM bmt_baseinfo WHERE stock <=5 ORDER BY stock")
//
//    List<StockDto> getStockDto();

    @Select("SELECT SUM(a.book_price) FROM omt_bookinfo a JOIN omt_orderinfo b ON a.order_id = b.`code` where TO_DAYS(b.time_xd) = TO_DAYS(NOW())")

    String getDaySales();

    @Select("SELECT SUM(book_price) FROM omt_bookinfo")

    String getAllSales();
}
