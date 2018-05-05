package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductSku extends BaseDomain {

    private Long productId;

    private String code;

    private BigDecimal price;

    private List<ProductSkuProperty> productSkuPropertyList;
}