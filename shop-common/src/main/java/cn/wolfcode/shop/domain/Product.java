package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class Product extends BaseDomain {

    private String name;

    private String code;

    private Catalog catalog;

    private Brand brand;

    private BigDecimal marketPrice;

    private BigDecimal basePrice;

    private Date createdDate;

    private Date lastModifiedDate;

    private String image;

    private String keyword;

    private String label;


}