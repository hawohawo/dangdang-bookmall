package com.dangdang.bookmall.order.entity.vo;

import com.dangdang.bookmall.order.entity.BookinfoEntity;
import com.dangdang.bookmall.order.entity.OrderinfoEntity;
import lombok.Data;

import java.util.List;

/**
 * @author zengyuzhi
 * @date 2020/11/1 下午3:36
 */

@Data
public class OrderInfoAndBookInfoVo {
    private OrderinfoEntity orderinfoEntity;
    private List<BookinfoEntity> bookinfoEntitys;
}
