package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.SkuPropertyValue;

import java.util.List;

/**
 * Created by Reimu on 2018/4/21.
 */
public interface ISkuPropertyValueService {

    /**
     * 通过属性名获取属性值
     * @param proId
     * @return
     */
    List<SkuPropertyValue> getProValueByProId(Long proId);

    /**
     * 新增或修改属性值
     * @param skuPropertyValues
     */
    void save(List<SkuPropertyValue> skuPropertyValues);

    /**
     * 删除属性值
     * @param id
     */
    void delete(Long id);
}
