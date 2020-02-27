package com.cjw.my.shop.web.admin.service;

import com.cjw.my.shop.domain.TbContentCategory;

import java.util.List;

/**
 * @program:my-shop
 * @description:内容分类管理Service
 * @author:xxs_cjw
 * @create:2020-02-25 14:40
 **/
public interface TbContentCategoryService {

    List<TbContentCategory> selectAll();
}
