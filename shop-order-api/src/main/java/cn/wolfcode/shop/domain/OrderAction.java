package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter@Getter
public class OrderAction extends BaseDomain{

    private Long orderId;

    private String actionUser;

    private Byte orderStatus;

    private Byte flowStatus;

    private Byte payStatus;

    private String actionPlace;

    private String actionNote;

    private Date actionTime;

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