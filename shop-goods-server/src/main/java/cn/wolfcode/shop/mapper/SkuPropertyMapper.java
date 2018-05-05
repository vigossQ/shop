package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.SkuProperty;

import java.util.List;

public interface SkuPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkuProperty record);

    SkuProperty selectByPrimaryKey(Long id);

    List<SkuProperty> selectAll();

    int updateByPrimaryKey(SkuProperty record);

    List<SkuProperty> getProByCatalogId(Long proId);
}