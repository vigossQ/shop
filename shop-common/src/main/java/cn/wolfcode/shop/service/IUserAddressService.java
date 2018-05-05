package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.UserAddress;

/**
 * Created by Reimu on 2018/4/25.
 */
public interface IUserAddressService {

    /**
     * 通过主键获取用户地址对象
     * @param id
     * @return
     */
    UserAddress getById(Long id);
}
