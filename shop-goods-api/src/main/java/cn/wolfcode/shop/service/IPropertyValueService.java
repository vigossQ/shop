package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.PropertyValue;

import java.util.List;

/**
 * Created by Reimu on 2018/4/21.
 */
public interface IPropertyValueService {

    /**
     * 通过属性名获取属性值
     * @param proId
     * @return
     */
    List<PropertyValue> getProValueByProId(Long proId);

    /**
     * 新增或修改属性值
     * @param propertyValues
     */
    void save(List<PropertyValue> propertyValues);

    /**
     * 删除属性值
     * @param id
     */
    void delete(Long id);
}
