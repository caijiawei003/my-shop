package com.cjw.my.shop.web.admin.dao;

import com.cjw.my.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program:my-shop
 * @description:内容分类管理Dao
 * @author:xxs_cjw
 * @create:2020-02-25 14:39
 **/
@Repository
public interface TbContentCategoryDao{

    List<TbContentCategory> selectAll();

}
