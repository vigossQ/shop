package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.UserInfo;
import cn.wolfcode.shop.domain.UserLogin;
import cn.wolfcode.shop.exception.UserException;
import cn.wolfcode.shop.mapper.UserInfoMapper;
import cn.wolfcode.shop.mapper.UserLoginMapper;
import cn.wolfcode.shop.service.IUserService;
import cn.wolfcode.shop.utils.RedisConstant;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Reimu on 2018/4/24.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserLogin register(UserLogin userLogin) throws UserException {
        //判断该注册用户的用户名是否存在,存在则提示用户名已存在
        if (this.isExists(userLogin.getUserName())) {
            throw new UserException("用户名已存在");
        }
        //新增用户
        userLogin.setState(new Byte("0"));
        userLoginMapper.insert(userLogin);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userLogin.getId());
        userInfo.setRegistTime(new Date());
        userInfoMapper.insert(userInfo);
        return userLogin;
    }

    @Override
    public String login(String username, String password) throws UserException {
        //通过用户名密码去数据库查询对应用户,如果用户不存在,则提示用户名或密码错误
        UserLogin userLogin = userLoginMapper.login(username, password);
        if (userLogin == null) {
            throw new UserException("用户名或密码错误");
        }
        //如果用户存在,生成token,并且把用户存放在redis中,redis中key是token,value是用户
        String token = generateToken(userLogin);
        return token;
    }

    @Override
    public void logout(String token) throws UserException {
        UserLogin userLogin = (UserLogin) redisTemplate.opsForValue().get(RedisConstant.USER_LOGIN + token);
        if (userLogin == null) {
            throw new UserException("请重新登陆");
        }
        redisTemplate.delete(RedisConstant.USER_LOGIN + token);
    }

    /**
     * 创建token并把用户保存到redis中
     *
     * @return
     */
    private String generateToken(UserLogin userLogin) {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(RedisConstant.USER_LOGIN + token, userLogin);
        //设置登陆用户的存活时间(30天有效)
        redisTemplate.expire(RedisConstant.USER_LOGIN + token, 30L, TimeUnit.DAYS);
        return token;
    }

    /**
     * 通过用户名判断该注册用户是否存在,如果存在则返回true,不存在返回false
     *
     * @param userName
     * @return
     */
    private boolean isExists(String userName) {
        int result = userLoginMapper.queryUserByUsername(userName);
        if (result == 0) {
            return false;
        }
        return true;
    }

    @Override
    public UserInfo getById(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }
}
