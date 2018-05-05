package cn.wolfcode.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Reimu on 2018/4/21.
 */
@SpringBootApplication
@EnableScheduling
@PropertySource({"zookeeper.properties","redis.properties"})
public class SchedulerApp {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApp.class, args);
    }
}
