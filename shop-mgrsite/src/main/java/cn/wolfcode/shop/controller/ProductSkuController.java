package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.service.*;
import cn.wolfcode.shop.vo.GenerateSkuVo;
import cn.wolfcode.shop.vo.JSONResultVo;
import cn.wolfcode.shop.vo.ProductVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Reimu on 2018/4/22.
 */
@Controller
@RequestMapping("/productSku")
public class ProductSkuController {

    @Reference
    private IProductService productService;
    @Reference
    private ICatalogService catalogService;
    @Reference
    private ISkuPropertyService skuPropertyService;
    @Reference
    private ISkuPropertyValueService skuPropertyValueService;
    @Reference
    private IProductSkuService productSkuService;

    @RequestMapping("")
    public String generateSku(Long productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        //通过商品id获取该商品sku集合
        List<ProductSku> productSkuList = productSkuService.getSkuList(product.getId());
        //如果该sku集合不为空,就跳转到sku管理界面
        if (productSkuList != null && productSkuList.size() > 0) {
            model.addAttribute("skuList",productSkuList);
            ProductSku productSku = productSkuList.get(0);
            List<String> productSkuProperties = new ArrayList<>();
            productSku.getProductSkuPropertyList().forEach(productSkuProperty -> {
                productSkuProperties.add(productSkuProperty.getSkuProperty().getName());
            });
            model.addAttribute("skuPropertyList",productSkuProperties);
            return "product_sku/sku_list";
        }
        List<SkuProperty> skuPropertyList = skuPropertyService.getProByCatalogId(product.getCatalog().getId());
        model.addAttribute("skuPropertyList", skuPropertyList);
        return "product_sku/generate_sku";
    }

    @RequestMapping("/getSkuPropertyValue")
    public String getSkuPropertyValue(Long skuPropertyId, Model model) {
        List<SkuPropertyValue> skuPropertyValueList = skuPropertyValueService.getProValueByProId(skuPropertyId);
        model.addAttribute("skuPropertyValueList", skuPropertyValueList);
        model.addAttribute("skuProperty", skuPropertyService.get(skuPropertyId));
        return "product_sku/sku_property_value_table";
    }

    @RequestMapping(value = "/generateSku", method = RequestMethod.POST)
    public String generateSku(@RequestBody GenerateSkuVo vo, Model model) {
        List<Map<String, String>> skuList = productSkuService.getSku(vo);
        model.addAttribute("skuProList", vo.getSkuPropertyList());
        model.addAttribute("skuList", skuList);
        return "product_sku/sku_form";
    }

    /**
     * 保存sku
     *
     * @param productVo
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public JSONResultVo save(ProductVo productVo) {
        JSONResultVo jsonResult = new JSONResultVo();
        try {
            productSkuService.save(productVo);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setErrorMsg(e.getMessage());
        }
        return jsonResult;
    }
}
