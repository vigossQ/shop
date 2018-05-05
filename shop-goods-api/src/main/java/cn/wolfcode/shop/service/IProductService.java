package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Product;
import cn.wolfcode.shop.qo.PageResult;
import cn.wolfcode.shop.qo.QueryObject;
import cn.wolfcode.shop.vo.ProductVo;

import java.util.List;

/**
 * Created by luohaipeng on 2018/1/10.
 */
public interface IProductService {

    List<Product> getAllProduct();

    PageResult productPage(QueryObject qo);

    /**
     * 保存商品
     */
    void save(ProductVo productVo);

    /**
     * 通过商品id获取商品对象
     * @param productId
     * @return
     */
    Product getProductById(Long productId);
}
