package com.dangdang.bookmall.order.service.impl;

import com.dangdang.bookmall.order.entity.ReasonEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.order.dao.ReceiveDao;
import com.dangdang.bookmall.order.entity.ReceiveEntity;
import com.dangdang.bookmall.order.service.ReceiveService;


@Service("receiveService")
public class ReceiveServiceImpl extends ServiceImpl<ReceiveDao, ReceiveEntity> implements ReceiveService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = params.get("key").toString();
        QueryWrapper<ReceiveEntity> receiveEntityQueryWrapper = new QueryWrapper<>();
        receiveEntityQueryWrapper.like("id",key).or().like("receive_name",key).or().like("receive_tel",key).or().like("receive_addr",key).or().like("postal",key);

        IPage<ReceiveEntity> page = this.page(
                new Query<ReceiveEntity>().getPage(params),
                receiveEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}