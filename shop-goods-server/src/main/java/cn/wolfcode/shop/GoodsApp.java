package cn.wolfcode.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Reimu on 2018/4/19.
 */
@SpringBootApplication
@MapperScan({"cn.wolfcode.shop"})
@PropertySource({"zookeeper.properties","redis.properties"})
public class GoodsApp {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApp.class, args);
    }
}
