package com.cjw.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program:my-shop
 * @description:首页controller
 * @author:xxs_cjw
 * @create:2020-01-03 10:22
 **/
@Controller
public class MainController {

    @RequestMapping(value = "/main")
    public String main(){
        return "main";
    }
}
