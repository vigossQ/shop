package cn.wolfcode.shop.message;

import cn.wolfcode.shop.vo.OrderVo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * Created by Reimu on 2018/4/25.
 */
@Component
public class GenerateOrderProducer {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination generateOrderTopic;

    public void sendMsg(OrderVo vo) {
        //发送一个生成订单的消息
        jmsTemplate.convertAndSend(generateOrderTopic, JSON.toJSONString(vo));
    }
}
