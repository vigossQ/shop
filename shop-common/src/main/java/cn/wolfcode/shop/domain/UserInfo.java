package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserInfo extends BaseDomain {

    private String nickName;

    private String realName;

    private String headImg;

    private String idNumber;

    private String phone;

    private String email;

    private Boolean sex;

    private Date birthday;

    private String province;

    private String city;

    private String district;

    private Date registTime;
}