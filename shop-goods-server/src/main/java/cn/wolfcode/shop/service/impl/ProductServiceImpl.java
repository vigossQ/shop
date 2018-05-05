package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.Product;
import cn.wolfcode.shop.domain.ProductImage;
import cn.wolfcode.shop.mapper.ProductDetailsMapper;
import cn.wolfcode.shop.mapper.ProductImageMapper;
import cn.wolfcode.shop.mapper.ProductMapper;
import cn.wolfcode.shop.mapper.ProductPropertyValueMapper;
import cn.wolfcode.shop.qo.PageResult;
import cn.wolfcode.shop.qo.QueryObject;
import cn.wolfcode.shop.service.IProductService;
import cn.wolfcode.shop.vo.ProductVo;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by luohaipeng on 2018/1/10.
 */
@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductDetailsMapper productDetailsMapper;
    @Autowired
    ProductImageMapper productImageMapper;
    @Autowired
    private ProductPropertyValueMapper productPropertyValueMapper;
    

    @Override
    public List<Product> getAllProduct() {
        return productMapper.selectAll();
    }

    @Override
    public PageResult productPage(QueryObject qo) {
        int totalCount = productMapper.queryCount(qo);
        List data = productMapper.queryList(qo);
        return new PageResult(data,totalCount,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public void save(ProductVo productVo) {
        //保存商品对象
        productVo.getProduct().setCreatedDate(new Date());
        productVo.getProduct().setLastModifiedDate(new Date());
        int record = productMapper.insert(productVo.getProduct());
        if(record > 0){
            //保存商品详细
            productVo.getProductDetails().setProductId(productVo.getProduct().getId());
            productDetailsMapper.insert(productVo.getProductDetails());
            //保存商品相册
            List<ProductImage> productImagesList = productVo.getProductImagesList();
            productImagesList.forEach(image -> {
                if(image.getImagePath() != null && image.getImagePath().trim().length() > 0){
                    image.setProductId(productVo.getProduct().getId());
                    productImageMapper.insert(image);
                }
            });
            //保存商品属性
            productVo.getProductPropertyValueList().forEach(productPropertyValue -> {
                productPropertyValue.setProductId(productVo.getProduct().getId());
                productPropertyValueMapper.insert(productPropertyValue);
            });
        }
    }

    @Override
    public Product getProductById(Long productId) {
        return productMapper.selectByPrimaryKey(productId);
    }
}
