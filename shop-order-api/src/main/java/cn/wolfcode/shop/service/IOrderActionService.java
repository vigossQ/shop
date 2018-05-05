package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.OrderAction;
import cn.wolfcode.shop.domain.OrderInfo;

import java.util.List;

/**
 * Created by luohaipeng on 2018/3/24.
 */
public interface IOrderActionService {


    List<OrderAction> getOrderAction(Long orderId);
}
