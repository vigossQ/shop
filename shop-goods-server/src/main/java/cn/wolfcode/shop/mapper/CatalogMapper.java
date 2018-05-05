package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.Catalog;

import java.util.List;

public interface CatalogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Catalog record);

    Catalog selectByPrimaryKey(Long id);

    List<Catalog> selectAll();

    int updateByPrimaryKey(Catalog record);

    List<Catalog> getChildCatalog(Long catalogId);

    Integer getPropertyCount(Long catalogId);

    Integer getProductCount(Long id);

    List<Catalog> getParentCatalog(Long id);
}