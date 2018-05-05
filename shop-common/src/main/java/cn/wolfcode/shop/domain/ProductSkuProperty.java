package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSkuProperty extends BaseDomain {

    private Long productSkuId;

    private SkuProperty skuProperty;

    private String value;

    private String image;
}