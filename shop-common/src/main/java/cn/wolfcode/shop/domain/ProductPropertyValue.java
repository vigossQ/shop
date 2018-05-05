package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPropertyValue extends BaseDomain {

    private Long productId;

    private String name;

    private String value;
}