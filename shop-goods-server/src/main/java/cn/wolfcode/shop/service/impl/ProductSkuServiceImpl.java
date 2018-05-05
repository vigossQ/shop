package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.mapper.CatalogMapper;
import cn.wolfcode.shop.mapper.ProductSkuMapper;
import cn.wolfcode.shop.mapper.ProductSkuPropertyMapper;
import cn.wolfcode.shop.service.IProductSkuService;
import cn.wolfcode.shop.vo.GenerateSkuVo;
import cn.wolfcode.shop.vo.ProductVo;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Reimu on 2018/4/22.
 */
@Service
@Transactional
public class ProductSkuServiceImpl implements IProductSkuService {

    @Autowired
    private CatalogMapper catalogMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductSkuPropertyMapper productSkuPropertyMapper;

    @Override
    public List<Map<String, String>> getSku(GenerateSkuVo vo) {
        List<Map<String, String>> skuList = new ArrayList<>();
        Product product = vo.getProduct();
        List<SkuProperty> skuPropertyList = vo.getSkuPropertyList();
        List<SkuPropertyValue> skuPropertyValueList = vo.getSkuPropertyValueList();
        //生成sku编号前缀
        //2位商品一级分类缩写+2位商品二级分类顺序+2位商品三级分类顺序+商品ID+该SKU在整个SKU笛卡尔积的位置
        List<Catalog> parentCatalogList = catalogMapper.getParentCatalog(product.getCatalog().getId());
        String codeFix = generateCodeFix(parentCatalogList) + product.getId();
        System.out.println(codeFix);

        //生成sku数据
        //用于接收sku拆分中间值的临时map
        Map<String, List<String>> tempMap = new HashMap<>();

        //遍历sku属性集合
        skuPropertyList.forEach(skuProperty -> {
            //创建list集合把sku属性值分开
            List<String> tempSkuProValueList = new ArrayList<>();
            skuPropertyValueList.forEach(skuPropertyValue -> {
                //判断如果是当前sku属性就把sku属性值放入集合
                if (skuProperty.getId() == skuPropertyValue.getSkuPropertyId()) {
                    tempSkuProValueList.add(skuPropertyValue.getValue());
                }
            });
            //把得到的集合遍历存入tempMap中
            tempMap.put(skuProperty.getName(), tempSkuProValueList);
        });

        //用于接收sku中间值的临时list(用于做递归的基础数据)
        List<List<String>> tempList = new ArrayList<>();
        for (List<String> stringList : tempMap.values()) {
            tempList.add(stringList);
        }
        //用于接收sku数据的list(进行递归算法得到的)
        List<List<String>> tempSkus = new ArrayList<>();
        List<String> tempSku = new ArrayList<>();
        //层级递归
        recusive(tempList, tempSkus, 0, tempSku);

        System.out.println(tempSkus);
        //循环遍历到前台显示
        for (int i = 0; i < tempSkus.size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put("code", codeFix + (i + 1));
            map.put("price", product.getBasePrice().toString());
            for (int j = 0; j < tempSkus.get(i).size(); j++) {
                map.put(skuPropertyList.get(j).getId().toString(), tempSkus.get(i).get(j));
            }
            skuList.add(map);
        }
        return skuList;
    }

    @Override
    public void save(ProductVo productVo) {
        productVo.getProductSkuList().forEach(productSku -> {
            productSku.setProductId(productVo.getProduct().getId());
            productSkuMapper.insert(productSku);
            productSku.getProductSkuPropertyList().forEach(productSkuProperty -> {
                productSkuProperty.setProductSkuId(productSku.getId());
                productSkuPropertyMapper.insert(productSkuProperty);
            });
        });

    }

    @Override
    public List<ProductSku> getSkuList(Long id) {
        return productSkuMapper.getSkuList(id);
    }

    @Override
    public ProductSku getSkuById(Long skuId) {
        return productSkuMapper.selectByPrimaryKey(skuId);
    }

    /**
     * 递归算法
     *
     * @param tempList 基础数据
     * @param tempSkus 最终结果数据
     * @param layer    递归层级
     * @param tempSku  上层递归数据
     */
    private void recusive(List<List<String>> tempList, List<List<String>> tempSkus, int layer, List<String> tempSku) {
        for (String sku : tempList.get(layer)) {
            List<String> skus = new ArrayList<>();
            skus.add(sku);
            if (layer == tempList.size() - 1) {
                skus.addAll(tempSku);
                tempSkus.add(skus);
            } else {
                recusive(tempList, tempSkus, layer + 1, skus);
            }
        }
    }

    /**
     * 生成编码前缀
     *
     * @param parentCatalogList
     * @return
     */
    private String generateCodeFix(List<Catalog> parentCatalogList) {
        StringBuilder sb = new StringBuilder();
        //删除根分类
        parentCatalogList.remove(0);
        for (int i = 0; i < parentCatalogList.size(); i++) {
            Catalog catalog;
            if (i == 0) {
                catalog = parentCatalogList.get(i);
                sb.append(catalog.getCode().length() > 2 ? catalog.getCode().substring(0, 2) : catalog.getCode());
            } else {
                catalog = parentCatalogList.get(i);
                //如果分类编号小于两位就用0补上
                sb.append(catalog.getSort() > 9 ? catalog.getSort() : "0" + catalog.getSort());
            }
        }
        return sb.toString();
    }
}
