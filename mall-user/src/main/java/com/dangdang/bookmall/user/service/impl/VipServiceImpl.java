package com.dangdang.bookmall.user.service.impl;

import jdk.internal.org.objectweb.asm.Handle;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.user.dao.VipDao;
import com.dangdang.bookmall.user.entity.VipEntity;
import com.dangdang.bookmall.user.service.VipService;


@Service("vipService")
public class VipServiceImpl extends ServiceImpl<VipDao, VipEntity> implements VipService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VipEntity> page = this.page(
                new Query<VipEntity>().getPage(params),
                new QueryWrapper<VipEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public VipEntity getByUserId(String userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",userId);
        List<VipEntity> vipEntities = baseMapper.selectByMap(map);
        return vipEntities.get(0);
    }

}