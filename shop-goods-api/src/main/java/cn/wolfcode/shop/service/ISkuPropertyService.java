package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.SkuProperty;

import java.util.List;

/**
 * Created by Reimu on 2018/4/21.
 */
public interface ISkuPropertyService {

    /**
     * 通过商品分类获取属性
     * @param catalogId
     * @return
     */
    List<SkuProperty> getProByCatalogId(Long catalogId);

    /**
     * 新增或编辑属性
     * @param skuProperty
     */
    void save(SkuProperty skuProperty);

    /**
     * 删除属性
     * @param id
     */
    void delete(Long id);

    /**
     * 通过id获取sku属性
     * @param skuPropertyId
     * @return
     */
    SkuProperty get(Long skuPropertyId);
}
