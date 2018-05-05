package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.Property;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.service.IPropertyService;
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
@RequestMapping("/property")
public class PropertyController {

    @Reference
    private ICatalogService catalogService;
    @Reference
    private IPropertyService propertyService;

    @RequestMapping("")
    public String getList(Model model){
        model.addAttribute("allCatalog",catalogService.getRedisCatalog());
        return "property/property";
    }

    @RequestMapping("/get/{id}")
    public String getChildProperty(@PathVariable("id") Long catalogId,Model model){
        model.addAttribute("propertyList",propertyService.getProByCatalogId(catalogId));
        return "property/property_list";
    }

    @RequestMapping("/save")
    @ResponseBody
    public JSONResultVo save(Property property){
        JSONResultVo jsonResult = new JSONResultVo();
        try {
            propertyService.save(property);
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
            propertyService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setErrorMsg(e.getMessage());
        }
        return jsonResult;
    }
}
