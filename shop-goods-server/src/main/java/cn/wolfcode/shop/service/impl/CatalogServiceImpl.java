package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.Catalog;
import cn.wolfcode.shop.mapper.CatalogMapper;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.utils.RedisConstant;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Reimu on 2018/4/19.
 */
@Service
@Transactional
public class CatalogServiceImpl implements ICatalogService {

    @Autowired
    private CatalogMapper catalogMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Catalog> getList() {
        return catalogMapper.selectAll();
    }

    @Override
    public List<Catalog> getChildCatalog(Long catalogId) {
        List<Catalog> childCatalog = catalogMapper.getChildCatalog(catalogId);
        childCatalog.forEach(catalog -> {
            Integer propertyCount = (Integer) redisTemplate.opsForValue().get(RedisConstant.CATALOG_PROPERTY_COUNT.replace("ID", catalog.getId() + ""));
            Integer productCount = (Integer) redisTemplate.opsForValue().get(RedisConstant.CATALOG_PRODUCT_COUNT.replace("ID", catalog.getId() + ""));
            if (propertyCount == null) {
                propertyCount = catalogMapper.getPropertyCount(catalog.getId());
                redisTemplate.opsForValue().set(RedisConstant.CATALOG_PROPERTY_COUNT.replace("ID", catalog.getId() + ""),propertyCount);
                productCount = catalogMapper.getProductCount(catalog.getId());
                redisTemplate.opsForValue().set(RedisConstant.CATALOG_PRODUCT_COUNT.replace("ID", catalog.getId() + ""),productCount);
            }
            catalog.setPropertyCount(propertyCount);
            catalog.setProductCount(productCount);
        });
        return childCatalog;
    }

    @Override
    public void updateSort(Long[] ids) {
        int i = 1;
        for (Long id : ids) {
            Catalog catalog = catalogMapper.selectByPrimaryKey(id);
            catalog.setSort(i++);
            catalogMapper.updateByPrimaryKey(catalog);
        }
        this.reloadCatalogCache();
    }

    @Override
    public void save(Catalog catalog) {
        if (catalog.getId() == null) {
            //新增
            Catalog parent = catalogMapper.selectByPrimaryKey(catalog.getPId());
            if (parent.getIsParent() == 0) {
                parent.setIsParent(new Byte("1"));
                catalogMapper.updateByPrimaryKey(parent);
            }
            catalogMapper.insert(catalog);
        } else {
            //更新
            catalogMapper.updateByPrimaryKey(catalog);
        }
        this.reloadCatalogCache();
    }

    @Override
    public void delete(Long catalogId) {
        Catalog catalog = catalogMapper.selectByPrimaryKey(catalogId);
        Catalog parent = catalogMapper.selectByPrimaryKey(catalog.getPId());
        catalogMapper.deleteByPrimaryKey(catalogId);
        //获取父分类的子分类
        List<Catalog> childCatalog = catalogMapper.getChildCatalog(parent.getId());
        if (childCatalog == null || childCatalog.size() == 0) {
            parent.setIsParent(new Byte("0"));
            catalogMapper.updateByPrimaryKey(parent);
        }
        this.reloadCatalogCache();
    }

    @Override
    public String getRedisCatalog() {
        //从redis中获取缓存的商品分类
        String catalogList = (String) redisTemplate.opsForValue().get(RedisConstant.CATALOG_ALL);
        //判断redis中是否存在商品分类
        if (catalogList == null) {
            catalogList = this.reloadCatalogCache();
        }
        return catalogList;
    }

    @Override
    public Integer propertyCount(Long id) {
        return catalogMapper.getPropertyCount(id);
    }

    /**
     * 更新redis的缓存
     *
     * @return
     */
    private String reloadCatalogCache() {
        String catalogList = JSON.toJSONString(getList());
        //如果不存在就设置进去
        redisTemplate.opsForValue().set(RedisConstant.CATALOG_ALL, catalogList);
        return catalogList;
    }
}
