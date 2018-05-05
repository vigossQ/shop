package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ProductImage extends BaseDomain{

    private Long productId;

    private String imagePath;

    private Integer sort;
}