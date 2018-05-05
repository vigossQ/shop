package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.UserAddress;
import java.util.List;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAddress record);

    UserAddress selectByPrimaryKey(Long id);

    List<UserAddress> selectAll();

    int updateByPrimaryKey(UserAddress record);
}