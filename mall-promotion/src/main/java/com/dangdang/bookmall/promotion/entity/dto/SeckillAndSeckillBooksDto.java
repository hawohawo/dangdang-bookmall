package com.dangdang.bookmall.promotion.entity.dto;

import com.dangdang.bookmall.promotion.entity.SeckillEntity;
import com.dangdang.bookmall.promotion.entity.SeckillSessionBookEntity;
import lombok.Data;

import java.util.List;

/**
 * @author zengyuzhi
 * @date 2020/11/1 上午11:58
 */
@Data
public class SeckillAndSeckillBooksDto {
    private SeckillEntity seckillEntity;
    private List<SeckillSessionBookAndBookDto> seckillSessionBookAndBookDtos;
}
