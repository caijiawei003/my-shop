package com.cjw.my.shop.web.admin.service;

import com.cjw.my.shop.domain.TbContent;
import com.cjw.my.shop.domain.TbUser;
import com.my.shop.commons.dto.BaseResult;
import com.my.shop.commons.dto.PageInfo;

import java.util.List;

/**
 * @program:my-shop
 * @description: 内容业务接口
 * @author:xxs_cjw
 * @create:2020-01-15 16:16
 **/

public interface TbContentService {

    /**
     * @Description: 查询全部用户信息
     * @Author: 蔡嘉伟 on 2020/1/15 16:17
     * @param:
     * @return:
     */
     List<TbContent> selectAll();

    /**
     * 保存用户
     * @param tbContent
     */
     BaseResult save(TbContent tbContent);

    /**
     * 根据ID查询用户
     * @param id
     * @return TbContent
     */
     TbContent selectById(Long id);

    /**
     * 更新用户信息
     * @param tbContent
     */
    void update(TbContent tbContent);

    /**
     * 删除用户信息
     * @param ids
     */
     void deleteMulti(String[] ids);

    /**
     * 根据条件分页查询用户信息
     */
    PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent);

    /**
     * 符合条件用户总数
     * @return
     */
     Integer count(TbContent tbContent);

    /**
     * 根据Id删除用户
     * @param tbContent
     */
    void delete(TbContent tbContent);
}
