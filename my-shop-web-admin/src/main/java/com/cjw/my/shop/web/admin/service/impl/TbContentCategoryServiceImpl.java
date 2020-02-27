package com.cjw.my.shop.web.admin.service.impl;

import com.cjw.my.shop.domain.TbContentCategory;
import com.cjw.my.shop.web.admin.dao.TbContentCategoryDao;
import com.cjw.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:my-shop
 * @description:
 * @author:xxs_cjw
 * @create:2020-02-25 14:41
 **/
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Autowired
    private TbContentCategoryDao tbContentCategoryDao;

    @Override
    public List<TbContentCategory> selectAll() {

        return tbContentCategoryDao.selectAll();
    }


}
