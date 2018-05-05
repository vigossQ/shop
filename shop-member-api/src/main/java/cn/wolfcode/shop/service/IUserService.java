package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.UserInfo;
import cn.wolfcode.shop.domain.UserLogin;
import cn.wolfcode.shop.exception.UserException;

/**
 * Created by Reimu on 2018/4/24.
 */
public interface IUserService {

    /**
     * 注册接口
     *
     * @param userLogin
     * @return
     */
    UserLogin register(UserLogin userLogin) throws UserException;

    /**
     * 登陆接口
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password) throws UserException;

    /**
     * 注销接口
     *
     * @param token
     */
    void logout(String token) throws UserException;

    UserInfo getById(Long id);
}
