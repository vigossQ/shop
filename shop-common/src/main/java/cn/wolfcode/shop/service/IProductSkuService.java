package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.ProductSku;
import cn.wolfcode.shop.vo.GenerateSkuVo;
import cn.wolfcode.shop.vo.ProductVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Reimu on 2018/4/22.
 */
public interface IProductSkuService {

    /**
     * 生成SKU数据
     * @param vo
     * @return
     */
    List<Map<String,String>> getSku(GenerateSkuVo vo);

    void save(ProductVo productVo);

    /**
     * 通过商品id获取sku集合
     * @param id
     * @return
     */
    List<ProductSku> getSkuList(Long id);

    ProductSku getSkuById(Long skuId);
}
