package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.ProductPropertyValue;
import java.util.List;

public interface ProductPropertyValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductPropertyValue record);

    ProductPropertyValue selectByPrimaryKey(Long id);

    List<ProductPropertyValue> selectAll();

    int updateByPrimaryKey(ProductPropertyValue record);
}