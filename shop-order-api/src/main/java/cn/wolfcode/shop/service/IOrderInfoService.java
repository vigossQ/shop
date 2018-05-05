package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.OrderInfo;
import cn.wolfcode.shop.qo.OrderQueryObject;
import cn.wolfcode.shop.qo.PageResult;
import cn.wolfcode.shop.vo.OrderVo;

/**
 * Created by Reimu on 2018/4/25.
 */
public interface IOrderInfoService {

    /**
     * 生成订单
     * @param vo
     */
    void generateOrder(OrderVo vo);

    PageResult query(OrderQueryObject qo);

    OrderInfo getById(Long id);
}
