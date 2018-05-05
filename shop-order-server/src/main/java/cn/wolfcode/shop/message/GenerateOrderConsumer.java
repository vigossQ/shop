package cn.wolfcode.shop.message;

import cn.wolfcode.shop.service.IOrderInfoService;
import cn.wolfcode.shop.vo.OrderVo;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Reimu on 2018/4/25.
 */
@Component
public class GenerateOrderConsumer {

    @Reference
    private IOrderInfoService orderInfoService;

    //监听订单的消息
    @JmsListener(destination = "generateOrderTopic",containerFactory = "jmsListenerContainerTopic")
    public void receiveMsg(String msg){
        System.out.println(msg);
        OrderVo orderVo = JSON.parseObject(msg, OrderVo.class);
        orderInfoService.generateOrder(orderVo);
    }
}
