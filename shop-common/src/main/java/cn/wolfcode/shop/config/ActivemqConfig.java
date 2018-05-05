package cn.wolfcode.shop.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.Destination;

/**
 * Created by Reimu on 2018/4/25.
 */
@Configuration
public class ActivemqConfig {

    @Bean
    public Destination generateOrderQueue(){
        return new ActiveMQQueue("generateOrderQueue");
    }

    @Bean
    public Destination generateOrderTopic(){
        return new ActiveMQTopic("generateOrderTopic");
    }

    /**
     * 用于监听Topic模型消息的容器bean
     * @param jmsConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory jmsConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        //是否使用订阅和发布的模型监听器，默认为false，也就是说默认使用的是点对点的模型监听器
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(jmsConnectionFactory);
        return bean;
    }

    /**
     * 用于监听Queue模型消息的容器bean
     * @param jmsConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory jmsConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(jmsConnectionFactory);
        return bean;
    }
}
