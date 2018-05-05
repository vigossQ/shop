package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.PropertyValue;
import cn.wolfcode.shop.service.IPropertyValueService;
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
@RequestMapping("propertyValue")
public class PropertyValueController {

    @Reference
    private IPropertyValueService propertyValueService;

    @RequestMapping("/get/{propertyId}")
    public String getProValueByProId(@PathVariable("propertyId") Long propertyId, Model model){
        List<PropertyValue> propertyValueList = propertyValueService.getProValueByProId(propertyId);
        model.addAttribute("propertyValueList",propertyValueList);
        return "property/property_value_list";
    }

    @RequestMapping("/save")
    @ResponseBody
    public JSONResultVo save(@RequestBody List<PropertyValue> propertyValues){
        JSONResultVo jsonResult = new JSONResultVo();
        try {
            propertyValueService.save(propertyValues);
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
            propertyValueService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setErrorMsg(e.getMessage());
        }
        return jsonResult;
    }
}
