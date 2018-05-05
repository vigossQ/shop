package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Catalog;

import java.util.List;

/**
 * Created by Reimu on 2018/4/19.
 */
public interface ICatalogService {
    List<Catalog> getList();

    /**
     * 通过分类id获取该分类下的子分类
     * @param catalogId 父分类id
     * @return
     */
    List<Catalog> getChildCatalog(Long catalogId);

    /**
     * 更新分类的排序
     * @param ids 分类的id
     */
    void updateSort(Long[] ids);

    /**
     * 添加或修改商品分类
     * @param catalog
     */
    void save(Catalog catalog);

    /**
     * 删除分类
     * @param catalogId
     */
    void delete(Long catalogId);

    /**
     * 通过redis获取商品分类树
     * @return
     */
    String getRedisCatalog();

    /**
     * 统计分类的属性个数
     * @param id
     * @return
     */
    Integer propertyCount(Long id);
}
