package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.ProductSkuProperty;
import java.util.List;

public interface ProductSkuPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSkuProperty record);

    ProductSkuProperty selectByPrimaryKey(Long id);

    List<ProductSkuProperty> selectAll();

    int updateByPrimaryKey(ProductSkuProperty record);
}