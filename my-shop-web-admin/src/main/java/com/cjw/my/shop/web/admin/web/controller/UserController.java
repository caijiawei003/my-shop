package com.cjw.my.shop.web.admin.web.controller;

import com.cjw.my.shop.domain.TbUser;
import com.cjw.my.shop.web.admin.service.TbUserService;
import com.my.shop.commons.dto.BaseResult;
import com.my.shop.commons.dto.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program:my-shop
 * @description:用户控制器
 * @author:xxs_cjw
 * @create:2020-02-06 15:56
 **/
@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;

    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser = null;
        if(id != null){
            tbUser = tbUserService.selectById(id);
        }
        else {
            tbUser = new TbUser();
        }
        return tbUser;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return "user_list";
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser, RedirectAttributes redirectAttributes,Model model){
        BaseResult baseResult =  tbUserService.save(tbUser);
        //新增成功
        if(baseResult.getStatus() == BaseResult.SUCCESS_200){
           redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }
        //新增失败
        else{
            model.addAttribute("baseResult",baseResult);
            return "/user_form";
        }
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if(ids != null){
            String[] idArray = ids.split(",");
            tbUserService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除成功！");
        }
        else{
            baseResult = BaseResult.fail("删除失败！");
        }
        return baseResult;
    }

    /**
     * 分页
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page")
    public PageInfo<TbUser> page(HttpServletRequest request,TbUser tbUser){
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");
        int draw = strDraw == null? 0:Integer.parseInt(strDraw);
        int start = strStart == null? 0:Integer.parseInt(strStart);
        int length =  strLength == null? 10:Integer.parseInt(strLength);
        PageInfo<TbUser> pageInfo = tbUserService.page(start,length,draw,tbUser);
        return pageInfo;
    }

    /**
     * 显示用户详情
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbUser tbUser){
        System.out.println(tbUser.getUsername());
       return "user_detail";
    }

    /**
     * 删除用户
     * @param tbUser
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteById")
    public BaseResult deleteById(TbUser tbUser){
        BaseResult baseResult = null;
        if(tbUser != null && tbUser.getId() != null){
            tbUserService.delete(tbUser);
            baseResult = BaseResult.success("删除成功！");
        }
        else{
            baseResult = BaseResult.fail("删除失败！");
        }
        return baseResult;
    }
}
