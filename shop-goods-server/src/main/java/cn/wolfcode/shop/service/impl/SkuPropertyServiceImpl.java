package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.SkuProperty;
import cn.wolfcode.shop.mapper.SkuPropertyMapper;
import cn.wolfcode.shop.service.ISkuPropertyService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Reimu on 2018/4/21.
 */
@Service
@Transactional
public class SkuPropertyServiceImpl implements ISkuPropertyService {

    @Autowired
    private SkuPropertyMapper skuPropertyMapper;

    @Override
    public List<SkuProperty> getProByCatalogId(Long catalogId) {
        return skuPropertyMapper.getProByCatalogId(catalogId);
    }

    @Override
    public void save(SkuProperty skuProperty) {
        if (skuProperty.getId() == null) {
            skuPropertyMapper.insert(skuProperty);
        } else {
            skuPropertyMapper.updateByPrimaryKey(skuProperty);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            skuPropertyMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public SkuProperty get(Long skuPropertyId) {
        return skuPropertyMapper.selectByPrimaryKey(skuPropertyId);
    }
}
