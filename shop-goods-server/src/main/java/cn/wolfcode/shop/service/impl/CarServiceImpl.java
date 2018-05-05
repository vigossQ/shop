package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.Car;
import cn.wolfcode.shop.domain.Product;
import cn.wolfcode.shop.domain.ProductSku;
import cn.wolfcode.shop.domain.UserLogin;
import cn.wolfcode.shop.exception.UserException;
import cn.wolfcode.shop.mapper.CarMapper;
import cn.wolfcode.shop.mapper.ProductMapper;
import cn.wolfcode.shop.mapper.ProductSkuMapper;
import cn.wolfcode.shop.service.ICarService;
import cn.wolfcode.shop.utils.RedisConstant;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Reimu on 2018/4/25.
 */
@Service
@Transactional
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarMapper carMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addCar(String token, Long skuId, Integer productNumber) throws UserException {
        //通过token获取当前登录用户,如果没有登陆则提示登陆
        UserLogin userLogin = (UserLogin) redisTemplate.opsForValue().get(RedisConstant.USER_LOGIN + token);
        if (userLogin == null) {
            throw new UserException("请登录");
        }
        //通过用户id和skuId查询购物车数据,如果存在则表示该用户已经添加过该商品,应该增加数量
        Car car = carMapper.selectByUserIdAndSkuId(userLogin.getId(), skuId);
        if (car != null) {
            car.setProductNumber(car.getProductNumber() + productNumber);
            carMapper.updateByPrimaryKey(car);
        } else {
            //否则创建一条新的购物车数据
            car = new Car();
            car.setProductNumber(productNumber);
            car.setSkuId(skuId);
            car.setUserId(userLogin.getId());
            //通过skuId间接获取商品信息
            ProductSku productSku = productSkuMapper.selectByPrimaryKey(skuId);
            Product product = productMapper.selectByPrimaryKey(productSku.getProductId());
            car.setProductImg(product.getImage());
            car.setProductName(product.getName());
            carMapper.insert(car);
        }
    }

    @Override
    public void delete(Long id) {
        carMapper.deleteByPrimaryKey(id);
    }
}
