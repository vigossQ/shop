package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.Catalog;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.vo.JSONResultVo;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Reimu on 2018/4/19.
 */
@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Reference
    private ICatalogService catalogService;

    @RequestMapping("/list")
    @ResponseBody
    public List<Catalog> getList(){
        return catalogService.getList();
    }

    @RequestMapping("")
    public String catalog(Map map){
        //JSON.toJSONString(catalogService.getList());
        map.put("allCatalogJson", catalogService.getRedisCatalog());
        return "catalog/catalog";
    }

    @RequestMapping("/getChildCatalog")
    public String getChildCatalog(Long catalogId,Model model){
        List<Catalog> catalogList = catalogService.getChildCatalog(catalogId);
        model.addAttribute("catalogList",catalogList);
        return "catalog/child_catalog";
    }

    @RequestMapping(value = "/updateSort",method = RequestMethod.POST)
    @ResponseBody
    public JSONResultVo updateSort(@RequestBody Long[] ids){
        JSONResultVo jsonResult = new JSONResultVo();
        try {
            catalogService.updateSort(ids);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setErrorMsg(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping("/save")
    @ResponseBody
    public JSONResultVo save(Catalog catalog){
        JSONResultVo jsonResult = new JSONResultVo();
        try {
            catalogService.save(catalog);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setErrorMsg(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONResultVo delete(Long catalogId){
        JSONResultVo jsonResult = new JSONResultVo();
        try {
            catalogService.delete(catalogId);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setErrorMsg(e.getMessage());
        }
        return jsonResult;
    }
}
