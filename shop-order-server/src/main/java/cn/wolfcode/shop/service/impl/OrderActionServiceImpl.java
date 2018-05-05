package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.OrderAction;
import cn.wolfcode.shop.domain.OrderInfo;
import cn.wolfcode.shop.mapper.OrderActionMapper;
import cn.wolfcode.shop.service.IOrderActionService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by luohaipeng on 2018/3/24.
 */
@Service
@Transactional
public class OrderActionServiceImpl implements IOrderActionService{

    @Autowired
    OrderActionMapper orderActionMapper;

    @Override
    public List<OrderAction> getOrderAction(Long orderId) {
        return orderActionMapper.getOrderAction(orderId);
    }
}
