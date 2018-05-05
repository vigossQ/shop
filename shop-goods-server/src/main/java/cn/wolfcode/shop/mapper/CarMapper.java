package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Car record);

    Car selectByPrimaryKey(Long id);

    List<Car> selectAll();

    int updateByPrimaryKey(Car record);

    Car selectByUserIdAndSkuId(@Param("userId") Long userId, @Param("skuId") Long skuId);
}