package com.dangdang.bookmall.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangdang.common.utils.PageUtils;
import com.dangdang.common.utils.Query;

import com.dangdang.bookmall.user.dao.AddressDao;
import com.dangdang.bookmall.user.entity.AddressEntity;
import com.dangdang.bookmall.user.service.AddressService;


@Service("addressService")
public class AddressServiceImpl extends ServiceImpl<AddressDao, AddressEntity> implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<AddressEntity> addressEntityQueryWrapper = new QueryWrapper<>();
        IPage<AddressEntity> page = this.page(
                new Query<AddressEntity>().getPage(params),
                new QueryWrapper<AddressEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void updateAddress(AddressEntity address) {
        //全部更新为999
        addressDao.updateAllAddress();
        //置为默认地址
        address.setSort(1);
        baseMapper.updateById(address);
    }

}