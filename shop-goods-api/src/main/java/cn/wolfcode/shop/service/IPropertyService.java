package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Property;

import java.util.List;

/**
 * Created by Reimu on 2018/4/21.
 */
public interface IPropertyService {

    /**
     * 通过商品分类获取属性
     * @param catalogId
     * @return
     */
    List<Property> getProByCatalogId(Long catalogId);

    /**
     * 新增或编辑属性
     * @param property
     */
    void save(Property property);

    /**
     * 删除属性
     * @param id
     */
    void delete(Long id);
}
