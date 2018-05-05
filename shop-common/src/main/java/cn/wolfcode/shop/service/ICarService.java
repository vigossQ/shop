package cn.wolfcode.shop.service;

import cn.wolfcode.shop.exception.UserException;

/**
 * Created by Reimu on 2018/4/25.
 */
public interface ICarService {

    void addCar(String token,Long skuId,Integer productNumber) throws UserException;

    void delete(Long id);
}
