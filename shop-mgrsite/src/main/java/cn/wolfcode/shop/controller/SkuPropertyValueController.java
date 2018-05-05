package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.SkuPropertyValue;
import cn.wolfcode.shop.service.ISkuPropertyValueService;
import cn.wolfcode.shop.vo.JSONResultVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Reimu on 2018/4/21.
 */
@Controller
@RequestMapping("skuPropertyValue")
public class SkuPropertyValueController {

    @Reference
    private ISkuPropertyValueService skuPropertyValueService;

    @RequestMapping("/get/{propertyId}")
    public String getProValueByProId(@PathVariable("propertyId") Long propertyId, Model model){
        List<SkuPropertyValue> skuPropertyValueList = skuPropertyValueService.getProValueByProId(propertyId);
        model.addAttribute("skuPropertyValueList",skuPropertyValueList);
        return "sku_property/property_value_list";
    }

    @RequestMapping("/save")
    @ResponseBody
    public JSONResultVo save(@RequestBody List<SkuPropertyValue> skuPropertyValues){
        JSONResultVo jsonResult = new JSONResultVo();
        try {
            skuPropertyValueService.save(skuPropertyValues);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setErrorMsg(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public JSONResultVo delete(@PathVariable("id") Long id){
        JSONResultVo jsonResult = new JSONResultVo();
        try {
            skuPropertyValueService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setErrorMsg(e.getMessage());
        }
        return jsonResult;
    }
}
