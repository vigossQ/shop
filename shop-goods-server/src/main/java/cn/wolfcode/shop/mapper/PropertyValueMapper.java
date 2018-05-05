package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.PropertyValue;
import java.util.List;

public interface PropertyValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PropertyValue record);

    PropertyValue selectByPrimaryKey(Long id);

    List<PropertyValue> selectAll();

    int updateByPrimaryKey(PropertyValue record);

    List<PropertyValue> getProValueByProId(Long proId);
}