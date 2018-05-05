package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SkuPropertyValue extends BaseDomain {

    private Long skuPropertyId;

    private String value;

}