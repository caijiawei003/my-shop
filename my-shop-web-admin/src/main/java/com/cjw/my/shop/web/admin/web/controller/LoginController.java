package com.cjw.my.shop.web.admin.web.controller;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.cjw.my.shop.domain.TbUser;
import com.cjw.my.shop.web.admin.service.TbUserService;
import com.my.shop.commons.constant.ConstantUtils;
import com.my.shop.commons.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController{

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = {"","/login"},method = RequestMethod.GET)
    public String login(HttpServletRequest req,HttpServletResponse resp){
        String userInfo = CookieUtils.getCookieValue(req,ConstantUtils.COOKIE_NAME_USER_INFO);
        if(userInfo != null && !StringUtils.isBlank(userInfo)){
            String[] userInfoArray = userInfo.split(":");
            String email = userInfoArray[0];
            String password = userInfoArray[1];
            req.setAttribute("email",email);
            req.setAttribute("password",password);
            req.setAttribute("isRemember",true);
        }
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, HttpServletRequest request, HttpServletResponse response,Model model, String isRemember){

        TbUser tbUser = tbUserService.login(email,password);
        Boolean isRememberVal = isRemember == null?false:true;
        //登录失败
        if(tbUser == null){
            model.addAttribute("message","邮箱或密码错误");
            return login(request,response);
        }
        //登录成功
        else{
            if(isRememberVal) {
                //设置Cookie
                CookieUtils.setCookie(request, response, ConstantUtils.COOKIE_NAME_USER_INFO, String.format("%s:%s", email, password), 7 * 24 * 60 * 60);
            }else{
                CookieUtils.deleteCookie(request,response,ConstantUtils.COOKIE_NAME_USER_INFO);
            }
            request.getSession().setAttribute(ConstantUtils.SESSION_USER,tbUser);
            return "redirect:/main";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute(ConstantUtils.SESSION_USER);
        return login(request,response);
    }

}