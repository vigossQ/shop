package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.Property;
import cn.wolfcode.shop.mapper.PropertyMapper;
import cn.wolfcode.shop.service.IPropertyService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Reimu on 2018/4/21.
 */
@Service
@Transactional
public class PropertyServiceImpl implements IPropertyService {

    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public List<Property> getProByCatalogId(Long catalogId) {
        return propertyMapper.getProByCatalogId(catalogId);
    }

    @Override
    public void save(Property property) {
        if (property.getId() == null) {
            propertyMapper.insert(property);
        } else {
            propertyMapper.updateByPrimaryKey(property);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            propertyMapper.deleteByPrimaryKey(id);
        }
    }
}
