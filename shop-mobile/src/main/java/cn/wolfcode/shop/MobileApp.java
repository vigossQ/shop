package cn.wolfcode.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Reimu on 2018/4/19.
 */
@SpringBootApplication
@PropertySource({"zookeeper.properties", "activemq.properties"})
public class MobileApp {
    public static void main(String[] args) {
        SpringApplication.run(MobileApp.class, args);
    }
}
