package com.dangdang.bookmall.promotion.entity.dto;

import com.dangdang.bookmall.promotion.entity.SeckillSessionBookEntity;
import lombok.Data;

/**
 * @author zengyuzhi
 * @date 2020/11/1 下午12:01
 */
@Data
public class SeckillSessionBookAndBookDto {
    private SeckillSessionBookEntity seckillSessionBookEntity;
    private BookBaseInfoDto bookBaseInfoDto;


}
