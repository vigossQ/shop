package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.ProductSku;
import java.util.List;

public interface ProductSkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSku record);

    ProductSku selectByPrimaryKey(Long id);

    List<ProductSku> selectAll();

    int updateByPrimaryKey(ProductSku record);

    List<ProductSku> getSkuList(Long productId);
}