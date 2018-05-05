package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.OrderAction;
import java.util.List;

public interface OrderActionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderAction record);

    OrderAction selectByPrimaryKey(Long id);

    List<OrderAction> selectAll();

    int updateByPrimaryKey(OrderAction record);

    List<OrderAction> getOrderAction(Long orderId);
}