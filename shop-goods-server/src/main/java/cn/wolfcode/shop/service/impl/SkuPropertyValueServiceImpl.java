package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.SkuPropertyValue;
import cn.wolfcode.shop.mapper.SkuPropertyValueMapper;
import cn.wolfcode.shop.service.ISkuPropertyValueService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Reimu on 2018/4/21.
 */
@Service
@Transactional
public class SkuPropertyValueServiceImpl implements ISkuPropertyValueService {

    @Autowired
    private SkuPropertyValueMapper skuPropertyValueMapper;

    @Override
    public List<SkuPropertyValue> getProValueByProId(Long proId) {
        return skuPropertyValueMapper.getProValueByProId(proId);
    }

    @Override
    public void save(List<SkuPropertyValue> skuPropertyValues) {
        if (skuPropertyValues != null && skuPropertyValues.size() != 0) {
            skuPropertyValues.forEach(SkuPropertyValue -> {
                if (SkuPropertyValue.getId() == null) {
                    skuPropertyValueMapper.insert(SkuPropertyValue);
                } else {
                    skuPropertyValueMapper.updateByPrimaryKey(SkuPropertyValue);
                }
            });
        }
    }

    @Override
    public void delete(Long id) {
        skuPropertyValueMapper.deleteByPrimaryKey(id);
    }
}
