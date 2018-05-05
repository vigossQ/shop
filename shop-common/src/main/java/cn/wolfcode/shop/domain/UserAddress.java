package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddress extends BaseDomain {

    private Integer userId;

    private String consignee;

    private String phone;

    private String country;

    private String province;

    private String city;

    private String district;

    private String address;

    private String zipcode;

    private Byte status;
}