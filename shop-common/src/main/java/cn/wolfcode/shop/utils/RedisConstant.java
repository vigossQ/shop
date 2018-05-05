package cn.wolfcode.shop.utils;

/**
 * Created by Reimu on 2018/4/21.
 */
public class RedisConstant {
    /**
     * redis中的商品分类
     */
    public static final String CATALOG_ALL="catalog:all";

    /**
     * 分类下的分类属性个数
     */
    public static final String CATALOG_PROPERTY_COUNT="catalog:ID:propertyCount";

    /**
     * 分类下的分类商品个数
     */
    public static final String CATALOG_PRODUCT_COUNT="catalog:ID:productCount";

    /**
     * 登陆用户信息
     */
    public static final String USER_LOGIN = "user:login:";
}
