package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.mapper.InvoiceMapper;
import cn.wolfcode.shop.mapper.OrderInfoMapper;
import cn.wolfcode.shop.mapper.OrderProductMapper;
import cn.wolfcode.shop.mapper.OrderProductPropertyMapper;
import cn.wolfcode.shop.qo.OrderQueryObject;
import cn.wolfcode.shop.qo.PageResult;
import cn.wolfcode.shop.service.*;
import cn.wolfcode.shop.utils.IdGenerateUtil;
import cn.wolfcode.shop.utils.RedisConstant;
import cn.wolfcode.shop.vo.OrderVo;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Reimu on 2018/4/25.
 */
@Service
@Transactional
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Reference
    private IUserAddressService userAddressService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Reference
    private IProductSkuService productSkuService;
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private OrderProductPropertyMapper orderProductPropertyMapper;
    @Autowired
    private ICarService carService;
    @Autowired
    private InvoiceMapper invoiceMapper;
    @Reference
    IOrderActionService orderActionService;

    @Override
    public PageResult query(OrderQueryObject qo) {
        int count = orderInfoMapper.queryCount(qo);
        if(count == 0){
            return PageResult.empty();
        }
        List<OrderInfo> orderInfoList = orderInfoMapper.queryList(qo);

        return new PageResult(orderInfoList,count,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public OrderInfo getById(Long id) {
        return orderInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void generateOrder(OrderVo vo) {
        //通过token获取当前登陆用户
        String token = vo.getToken();
        UserLogin userLogin = (UserLogin) redisTemplate.opsForValue().get(RedisConstant.USER_LOGIN + token);
        //创建订单,并且设置userId的值
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userLogin.getId());
        //通过用户地址id获取用户地址信息,并把信息填到订单中
        UserAddress userAddress = userAddressService.getById(vo.getUserAddressId());
        orderInfo.setAddress(userAddress.getAddress());
        orderInfo.setCity(userAddress.getCity());
        orderInfo.setConsignee(userAddress.getConsignee());
        orderInfo.setCountry(userAddress.getCountry());
        orderInfo.setDistrict(userAddress.getDistrict());
        orderInfo.setProvince(userAddress.getProvince());
        orderInfo.setPhone(userAddress.getPhone());
        orderInfo.setZipcode(userAddress.getZipcode());
        //设置订单其他默认信息
        orderInfo.setOrderTime(new Date());//下单时间
        orderInfo.setOrderStatus(new Byte("0"));//订单状态
        orderInfo.setFlowStatus(new Byte("0"));//物流状态
        orderInfo.setPayType(vo.getPayVo().getPayType());//支付方式
        orderInfo.setPayStatus(new Byte("0"));//支付状态
        //生成订单编号
        orderInfo.setOrderSn(IdGenerateUtil.get().nextId() + "");
        //先设置订单总价为0
        orderInfo.setOrderAmount(BigDecimal.ZERO);
        BigDecimal orderAmount = BigDecimal.ZERO;
        //保存订单到数据库(获取自动生成的id)
        orderInfoMapper.insert(orderInfo);
        //遍历购物车集合
        for (Car car : vo.getCarList()) {
            //把购物车中商品sku数据转移到订单明细中
            OrderProduct orderProduct = new OrderProduct();
            //设置订单明细所属的订单id
            orderProduct.setOrderId(orderInfo.getId());
            orderProduct.setSkuId(car.getSkuId());
            //计算每条明细的小计
            orderProduct.setProductName(car.getProductName());
            orderProduct.setProductNumber(car.getProductNumber());
            orderProduct.setProductImg(car.getProductImg());
            //通过skuId获取商品sku
            ProductSku productSku = productSkuService.getSkuById(car.getSkuId());
            orderProduct.setProductPrice(productSku.getPrice());
            //计算小计
            orderProduct.setTotalPrice(orderProduct.getProductPrice().multiply(new BigDecimal(orderProduct.getProductNumber())));
            //累加计算出订单总价
            orderAmount = orderAmount.add(orderProduct.getTotalPrice());
            //保存明细
            orderProductMapper.insert(orderProduct);
            //通过sku获取sku属性组合
            //遍历商品sku属性
            for (ProductSkuProperty productSkuProperty : productSku.getProductSkuPropertyList()) {
                OrderProductProperty orderProductProperty = new OrderProductProperty();
                //生成订单属性明细
                orderProductProperty.setOrderProductId(orderProduct.getId());
                //设置该属性所属订单明细id
                orderProductProperty.setName(productSkuProperty.getSkuProperty().getName());
                orderProductProperty.setValue(productSkuProperty.getValue());
                //保存进数据库
                orderProductPropertyMapper.insert(orderProductProperty);
            }
            //判断该购物车数据的id是否为null
            if (car.getId() != null) {
                //如果不为null,删除购物车数据(清空购物车)
                carService.delete(car.getId());
            }
        }
        //给订单对象设置总价
        orderInfo.setOrderAmount(orderAmount);
        //更新该订单
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        //增加发票对象
        Invoice invoice = vo.getInvoice();
        //设置发票对象所属订单id和所属用户id
        invoice.setOrderId(orderInfo.getId());
        invoice.setUserId(userLogin.getId());
        invoiceMapper.insert(invoice);
    }
}
