package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.SkuProperty;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.service.ISkuPropertyService;
import cn.wolfcode.shop.vo.JSONResultVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Reimu on 2018/4/21.
 */
@Controller
@RequestMapping("/skuProperty")
public class SkuPropertyController {

    @Reference
    private ICatalogService catalogService;
    @Reference
    private ISkuPropertyService skuPropertyService;

    @RequestMapping("")
    public String getList(Model model){
        model.addAttribute("allCatalog",catalogService.getRedisCatalog());
        return "sku_Property/property";
    }

    @RequestMapping("/get/{id}")
    public String getChildSkuProperty(@PathVariable("id") Long catalogId,Model model){
        model.addAttribute("skuPropertyList",skuPropertyService.getProByCatalogId(catalogId));
        return "sku_Property/property_list";
    }

    @RequestMapping("/save")
    @ResponseBody
    public JSONResultVo save(SkuProperty skuProperty){
        JSONResultVo jsonResult = new JSONResultVo();
        try {
            skuPropertyService.save(skuProperty);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setErrorMsg(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public JSONResultVo dalete(@PathVariable("id") Long id){
        JSONResultVo jsonResult = new JSONResultVo();
        try {
            skuPropertyService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setErrorMsg(e.getMessage());
        }
        return jsonResult;
    }
}
