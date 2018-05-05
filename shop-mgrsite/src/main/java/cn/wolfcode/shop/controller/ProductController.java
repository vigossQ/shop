package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.Brand;
import cn.wolfcode.shop.domain.Catalog;
import cn.wolfcode.shop.domain.Property;
import cn.wolfcode.shop.domain.PropertyValue;
import cn.wolfcode.shop.qo.PageResult;
import cn.wolfcode.shop.qo.ProductQueryObject;
import cn.wolfcode.shop.service.*;
import cn.wolfcode.shop.utils.UploadUtil;
import cn.wolfcode.shop.vo.JSONResultVo;
import cn.wolfcode.shop.vo.ProductVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by luohaipeng on 2018/1/10.
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Value("${file.path}")
    private String filePath;

    @Reference
    IProductService productService;
    @Reference
    ICatalogService catalogService;
    @Reference
    IBrandService brandService;
    @Reference
    IPropertyService propertyService;
    @Reference
    IPropertyValueService propertyValueService;


    /**
     * 商品列表界面
     */
    @RequestMapping(value = "")
    public String product() {
        return "product/product";
    }

    /**
     * 具体的商品数据
     */
    @RequestMapping(value = "/page")
    public String productPage(ProductQueryObject qo, Map map) {
        PageResult pageResult = productService.productPage(qo);
        map.put("pageResult", pageResult);
        return "product/product_list";
    }

    @RequestMapping(value = "/add")
    public String add(Map map) {
        List<Catalog> catalogList = catalogService.getList();
        List<Brand> brandList = brandService.getAllBrand();
        map.put("catalogList", catalogList);
        map.put("brandList", brandList);
        return "product/product_add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSONResultVo save(ProductVo productVo) {
        JSONResultVo jsonResultVo = new JSONResultVo();
        try {
            productService.save(productVo);
        } catch (Exception e) {
            jsonResultVo.setErrorMsg(e.getMessage());
        }
        return jsonResultVo;

    }

    /**
     * @param upload   ckedit默认找upload变量名
     * @param request  ckedit上传图片需要用到
     * @param response ckedit上传图片需要用到
     */
    @RequestMapping(value = "/ckeditUploadImg")
    public void ckeditUploadImg(MultipartFile upload, HttpServletRequest request, HttpServletResponse response) {
        try {
            //上传文件并返回文件名
            String fileName = UploadUtil.upload(upload, filePath);
            // 结合ckeditor功能
            // imageContextPath为图片在服务器地址，如/upload/123.jpg,非绝对路径
            String imageContextPath = "/upload/" + fileName;
            response.setContentType("text/html;charset=UTF-8");
            String callback = request.getParameter("CKEditorFuncNum");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageContextPath + "',''" + ")");
            out.println("</script>");
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public String uploadImage(MultipartFile img) {
        String fileName = UploadUtil.upload(img, filePath);
        return "/upload/" + fileName;
    }

    @RequestMapping("/getPropertyValueTr")
    public String getPropertyValueTr(Long catalogId, Model model) {
        List<Property> propertyList = propertyService.getProByCatalogId(catalogId);
        //遍历集合判断是否为下拉列表,如果是就设置下拉列表属性
        propertyList.forEach(property -> {
            if (property.getType()==1){
                List<PropertyValue> propertyValueList = propertyValueService.getProValueByProId(property.getId());
                property.setPropertyValueList(propertyValueList);
            }
        });
        model.addAttribute("propertyList", propertyList);
        return "product/product_property_value";
    }
}
