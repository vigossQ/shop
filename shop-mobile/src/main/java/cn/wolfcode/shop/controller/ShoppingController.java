package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.exception.UserException;
import cn.wolfcode.shop.message.GenerateOrderProducer;
import cn.wolfcode.shop.service.ICarService;
import cn.wolfcode.shop.vo.JSONResultVo;
import cn.wolfcode.shop.vo.OrderVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Reimu on 2018/4/25.
 */
@RestController
@RequestMapping("/apis")
public class ShoppingController {

    @Reference
    private ICarService carService;
//    @Reference
//    private IOrderInfoService orderInfoService;
    @Autowired
    private GenerateOrderProducer generateOrderProducer;

    /**
     * 生成购物车接口
     * 路径:/apis/cars
     * 方法:POST
     *
     * @return
     */
    @RequestMapping(value = "/cars",method = RequestMethod.POST)
    public JSONResultVo addCar(Long skuId, Integer number, HttpServletRequest request) {
        JSONResultVo jsonResult = new JSONResultVo();
        String token = request.getHeader("token");
        try {
            if (token == null || token.trim().length() == 0) {
                throw new UserException("登陆信息异常");
            }
            carService.addCar(token, skuId, number);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setResult(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 生成订单接口
     * 路径:/apis/orders
     * 方法:POST
     * @return
     */
    @RequestMapping(value = "/orders",method = RequestMethod.POST)
    public JSONResultVo addOrder(@RequestBody OrderVo vo,HttpServletRequest request){
        JSONResultVo jsonResult = new JSONResultVo();
        String token = request.getHeader("token");
        try {
            if (token==null||token.trim().length()==0){
                throw new UserException("用户信息异常");
            }
            vo.setToken(token);
            //发送一个生成订单消息
            //orderInfoService.generateOrder(vo);
            generateOrderProducer.sendMsg(vo);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setResult(e.getMessage());
        }
        return jsonResult;
    }
}
