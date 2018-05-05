package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductProperty extends BaseDomain {

    private Long orderProductId;

    private String name;

    private String value;
}