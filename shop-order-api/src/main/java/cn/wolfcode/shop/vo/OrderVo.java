package cn.wolfcode.shop.vo;

import cn.wolfcode.shop.domain.Car;
import cn.wolfcode.shop.domain.Invoice;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Reimu on 2018/4/25.
 */
@Getter@Setter
public class OrderVo implements Serializable {

    private String token;
    //收货地址id
    private Long userAddressId;
    //购物车数据列表
    private List<Car> carList;
    //其他信息的vo
    private PayVo payVo;
    //发票
    private Invoice invoice;
}
