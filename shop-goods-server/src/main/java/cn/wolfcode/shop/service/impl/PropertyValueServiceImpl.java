package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.PropertyValue;
import cn.wolfcode.shop.mapper.PropertyValueMapper;
import cn.wolfcode.shop.service.IPropertyValueService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Reimu on 2018/4/21.
 */
@Service
@Transactional
public class PropertyValueServiceImpl implements IPropertyValueService {

    @Autowired
    private PropertyValueMapper propertyValueMapper;

    @Override
    public List<PropertyValue> getProValueByProId(Long proId) {
        return propertyValueMapper.getProValueByProId(proId);
    }

    @Override
    public void save(List<PropertyValue> propertyValues) {
        if (propertyValues != null && propertyValues.size() != 0) {
            propertyValues.forEach(PropertyValue -> {
                if (PropertyValue.getId() == null) {
                    propertyValueMapper.insert(PropertyValue);
                } else {
                    propertyValueMapper.updateByPrimaryKey(PropertyValue);
                }
            });
        }
    }

    @Override
    public void delete(Long id) {
        propertyValueMapper.deleteByPrimaryKey(id);
    }
}
