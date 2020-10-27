package com.dangdang.bookmall.order.service.impl;


import com.dangdang.bookmall.order.dao.StatisticsDao;
import com.dangdang.bookmall.order.entity.dto.SalesrRankingOfTheMonthDto;
import com.dangdang.bookmall.order.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.spi.SyncResolver;
import java.util.List;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;

    @Override
    public List<SalesrRankingOfTheMonthDto> getSalesrRankingOfTheMonth() {
        return statisticsDao.getSalesrRankingOfTheMonth();
    }

    @Override
    public String getThisMonthOrderNum() {
        return statisticsDao.getThisMonthOrderNum();
    }

    @Override
    public String getLastMonthOrderNum() {
        return statisticsDao.getLastMonthOrderNum();

    }
    @Override
    public String getThisWeekOrderNum(){

        return statisticsDao.getThisWeekOrderNum();
    }
    @Override
    public  String getLastWeekOrderNum(){

        return statisticsDao.getLastWeekOrderNum();
    }
    @Override
    public  String getSalesThisMonth(){

        return  statisticsDao.getSalesThisMonth();
    }
    @Override
    public  String getSalesLastMonth(){

        return statisticsDao.getSalesLastMonth();
    }
    @Override
    public  String getOrderToBeShipped(){

        return  statisticsDao.getOrderToBeShipped();
    }
    @Override
    public  String getTotalOnShelves(){

        return statisticsDao.getTotalOnShelves();
    }
    public String getTotalOffShelves(){

        return  statisticsDao.getTotalOffShelves();
    }
    public  String getDaySales(){
        return  statisticsDao.getDaySales();
    }
    public  String getAllSales(){
        return  statisticsDao.getAllSales();
    }
}
