package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.UserLogin;
import cn.wolfcode.shop.exception.UserException;
import cn.wolfcode.shop.service.IUserService;
import cn.wolfcode.shop.vo.JSONResultVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Reimu on 2018/4/24.
 */
@RestController
@RequestMapping("/apis/users")
public class UserController {

    @Reference
    private IUserService userService;

    /**
     * 注册接口
     * 路径:/apis/users
     * 方法:POST
     * 返回:新注册的用户对象
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public JSONResultVo register(UserLogin userLogin) {
        JSONResultVo jsonResult = new JSONResultVo();
        try {
            userService.register(userLogin);
            jsonResult.setResult(userLogin);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setErrorMsg(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 登陆接口
     * 路径:/apis/users/tokens
     * 方法:POST
     * 返回:token
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/tokens", method = RequestMethod.POST)
    public JSONResultVo login(String username, String password) {
        JSONResultVo jsonResult = new JSONResultVo();
        try {
            String token = userService.login(username, password);
            Map<String, String> tokenMap = new HashMap<String, String>();
            tokenMap.put("token", token);
            jsonResult.setResult(tokenMap);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setResult(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 注销接口
     * 路径:/apis/users/tokens
     * 方法:DELETE
     *
     * @return
     */
    @RequestMapping(value = "/tokens", method = RequestMethod.DELETE)
    public JSONResultVo logout(@RequestHeader("token") String token) {
        JSONResultVo jsonResult = new JSONResultVo();
        try {
            if (token == null || token.trim().length() <= 0) {
                throw new UserException("登陆用户异常");
            }
            userService.logout(token);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setResult(e.getMessage());
        }
        return jsonResult;
    }
}
