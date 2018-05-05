package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.Brand;
import cn.wolfcode.shop.mapper.BrandMapper;
import cn.wolfcode.shop.service.IBrandService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luohaipeng on 2018/2/14.
 */
@Service
@Transactional
public class BrandServiceImpl implements IBrandService {
    @Autowired
    BrandMapper brandMapper;

    @Override
    public List<Brand> getAllBrand() {
        return brandMapper.selectAll();
    }
}
