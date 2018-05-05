package cn.wolfcode.shop.scheduler;

import cn.wolfcode.shop.domain.Catalog;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.utils.RedisConstant;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Reimu on 2018/4/21.
 */
@Component
public class CatalogCountScheduler {

    @Reference
    private ICatalogService catalogService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0 0 3 * * ?")
    public void catalogCount() {
        List<Catalog> allCatalog = catalogService.getList();
        allCatalog.forEach(catalog -> {
            Integer propertyCount = catalogService.propertyCount(catalog.getId());
            redisTemplate.opsForValue().set(RedisConstant.CATALOG_PROPERTY_COUNT.replace("ID", catalog.getId() + ""),propertyCount);
        });

        System.out.println("定时");
    }
}
