package com.dangdang.bookmall.order.entity.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

/**
 * @author zengyuzhi
 * @date 2020/10/26 下午4:16
 */
@Data
public class BookinfoAndPriceVo {
    /**
     * 自增主键
     */

    private Long id;
    /**
     * 所属订单编号
     */
    private String orderId;
    /**
     * 图书id
     */
    private Long bookId;
    /**
     * 图书名称
     */
    private String bookName;
    /**
     * 图书图片
     */
    private String bookPic;
    /**
     * 购买数量
     */
    private Integer bookNum;
    /**
     * 付款价格
     */
    private Double bookPrice;
    /**
     * 图书状态
     */
    private Integer status;

    /**
     * 图书状态
     */
    private Double priceSum;


}
