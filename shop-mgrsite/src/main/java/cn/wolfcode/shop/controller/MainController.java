package cn.wolfcode.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Reimu on 2018/4/19.
 */
@Controller
public class MainController {

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/")
    public String root(){
        return "forward:/main";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
