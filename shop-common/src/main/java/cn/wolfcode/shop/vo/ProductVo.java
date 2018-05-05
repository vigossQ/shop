package cn.wolfcode.shop.vo;

import cn.wolfcode.shop.domain.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter@Getter
public class ProductVo implements Serializable{

    private Product product;
    private ProductDetails productDetails;
    private List<ProductImage> productImagesList;
    private List<ProductPropertyValue> productPropertyValueList;
    private List<ProductSku> productSkuList;

}
