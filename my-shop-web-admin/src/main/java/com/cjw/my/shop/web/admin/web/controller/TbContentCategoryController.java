package com.cjw.my.shop.web.admin.web.controller;

import com.cjw.my.shop.domain.TbContentCategory;
import com.cjw.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;

/**
 * @program:my-shop
 * @description:
 * @author:xxs_cjw
 * @create:2020-02-25 14:42
 **/
@Controller
@RequestMapping(value = "/content/category")
public class TbContentCategoryController {

    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @RequestMapping(value = "list")
    public String selectAll(Model model){
        List<TbContentCategory> targetList = new ArrayList<>();
        List<TbContentCategory> sourceList = tbContentCategoryService.selectAll();
        //排序
        sortList(sourceList,targetList,0L);
        model.addAttribute("content_category_list",targetList);
        return "content_category_list";
    }

    /**
     * 排序
     * @param sourcetList 数据源集合
     * @param targetList  排序后集合
     * @param parentId    父节点ID
     */
    private void sortList(List<TbContentCategory> sourcetList,List<TbContentCategory> targetList,Long parentId){
        for (TbContentCategory tbContentCategory : sourcetList) {
            if(tbContentCategory.getParentId().equals(parentId)){
                targetList.add(tbContentCategory);

                //判断有没有子节点，有则继续追加
                if(tbContentCategory.getParent()){
                    for (TbContentCategory contentCategory:sourcetList){
                        if(contentCategory.getParentId().equals(tbContentCategory.getId())){
                            //递归
                            sortList(sourcetList,targetList,tbContentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
