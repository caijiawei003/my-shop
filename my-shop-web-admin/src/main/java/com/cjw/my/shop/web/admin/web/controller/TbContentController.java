package com.cjw.my.shop.web.admin.web.controller;

import com.cjw.my.shop.domain.TbContent;
import com.cjw.my.shop.domain.TbUser;
import com.cjw.my.shop.web.admin.service.TbContentService;
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
@RequestMapping(value = "content")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    /**
     * 在每个@RequestMapping方法之前运行，实现自动封装实体到modal传值到前端
     * @param id 根据是否有id返回数据
     * @return
     */
    @ModelAttribute
    public TbContent getTbUser(Long id){
        TbContent tbContent = null;
        if(id != null){
            tbContent = tbContentService.selectById(id);
        }
        else {
            tbContent = new TbContent();
        }
        return tbContent;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return "content_list";
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "content_form";
    }

    /**
     * 新增用户
     * @param tbContent
     * @param redirectAttributes 重定向的Attributes
     * @param model
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbContent tbContent, RedirectAttributes redirectAttributes,Model model){
        BaseResult baseResult =  tbContentService.save(tbContent);
        //新增成功
        if(baseResult.getStatus() == BaseResult.SUCCESS_200){
           redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }
        //新增失败
        else{
            model.addAttribute("baseResult",baseResult);
            return "/content_form";
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
            tbContentService.deleteMulti(idArray);
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
    public PageInfo<TbContent> page(HttpServletRequest request,TbContent tbContent){
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");
        int draw = strDraw == null? 0:Integer.parseInt(strDraw);
        int start = strStart == null? 0:Integer.parseInt(strStart);
        int length =  strLength == null? 10:Integer.parseInt(strLength);
        PageInfo<TbContent> pageInfo = tbContentService.page(start,length,draw,tbContent);
        return pageInfo;
    }

    /**
     * 显示用户详情
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbContent tbContent){
       return "content_detail";
    }

    /**
     * 删除用户
     * @param tbContent
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteById")
    public BaseResult deleteById(TbContent tbContent){
        BaseResult baseResult = null;
        if(tbContent != null && tbContent.getId() != null){
            tbContentService.delete(tbContent);
            baseResult = BaseResult.success("删除成功！");
        }
        else{
            baseResult = BaseResult.fail("删除失败！");
        }
        return baseResult;
    }
}
