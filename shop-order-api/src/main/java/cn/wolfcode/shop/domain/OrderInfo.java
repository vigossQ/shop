package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter@Getter
public class OrderInfo extends BaseDomain{

    private String orderSn;

    private Long userId;

    private BigDecimal orderAmount;

    private Byte orderStatus;

    private Byte flowStatus;

    private Byte payStatus;

    private Date orderTime;

    private String phone;

    private String consignee;

    private String country;

    private String province;

    private String city;

    private String district;

    private String address;

    private String zipcode;

    private Byte payType;

    private List<OrderProduct> orderProductList;

    //付款方式,0为余额付款，1为在线付款，2为货到付款
    public String getPayTypeStr(){
        switch (this.payType){
            case 0:
                return "余额付款";
            case 1:
                return "在线付款";
            case 2:
                return "货到付款";
            default:
                return "余额付款";
        }
    }

    /**
     * 订单状态：0为未确认，1为已确认，2,为已完成，3为取消
     * 订单物流状态：0为未发货，1为已发货，2为确认收货，3为退货
     * 订单支付状态：0为未付款，1为付款
     * @return
     */
    public String getStatus(){
        //先判断是支付方式是什么
        if(payType == 2){
            return judge();
        }else{
            //判断付款状态是什么
            if(payStatus == 0){
                return "未付款";
            }else{
                return judge();
            }
        }

    }

    /**
     * 判断订单状态方法
     * @return
     */
    private String judge() {
        //判断订单状态
        if(orderStatus == 0){
            return "未确认";
        }else if(orderStatus == 1){
            if(flowStatus == 1){
                return "已发货";
            }else {
                return "已确认";
            }
        }else if(orderStatus == 2){
            if(flowStatus == 3){
                return "已退货";
            }else{
                return "已完成";
            }
        }else {
            return "取消";
        }
    }

    /**
     * 订单状态：0为未确认，1为已确认，2,为已完成，3为取消
     * @return
     */
    public String getOrderStatusStr(){
        switch (this.orderStatus){
            case 0:
                return "未确认";
            case 1:
                return "已确认";
            case 2:
                return "以完成";
            case 3:
                return "取消";
            default:
                return "未确认";
        }
    }

    /**
     * 订单物流状态：0为未发货，1为已发货，2为确认收货，3为退货
     * @return
     */
    public String getFlowStatusStr(){
        switch (this.flowStatus){
            case 0:
                return "未发货";
            case 1:
                return "已发货";
            case 2:
                return "确认收货";
            case 3:
                return "退货";
            default:
                return "未发货";
        }
    }

    /**
     * 订单支付状态：0为未付款，1为付款
     * @return
     */
    public String getPayStatusStr(){
        switch (this.payStatus){
            case 0:
                return "未付款";
            case 1:
                return "付款";
            default:
                return "未付款";
        }
    }
}