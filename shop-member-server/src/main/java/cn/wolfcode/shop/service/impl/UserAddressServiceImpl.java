package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.UserAddress;
import cn.wolfcode.shop.mapper.UserAddressMapper;
import cn.wolfcode.shop.service.IUserAddressService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Reimu on 2018/4/25.
 */
@Service
@Transactional
public class UserAddressServiceImpl implements IUserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public UserAddress getById(Long id) {
        return userAddressMapper.selectByPrimaryKey(id);
    }
}
