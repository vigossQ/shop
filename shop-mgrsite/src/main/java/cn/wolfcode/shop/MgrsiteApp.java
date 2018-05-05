package cn.wolfcode.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Reimu on 2018/4/19.
 */
@SpringBootApplication
@PropertySource({"zookeeper.properties"})
public class MgrsiteApp {
    public static void main(String[] args) {
        SpringApplication.run(MgrsiteApp.class, args);
    }
}
