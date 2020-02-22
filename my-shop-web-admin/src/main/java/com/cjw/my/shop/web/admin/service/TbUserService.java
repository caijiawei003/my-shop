package com.cjw.my.shop.web.admin.service;

import com.cjw.my.shop.domain.TbUser;
import com.my.shop.commons.dto.BaseResult;
import com.my.shop.commons.dto.PageInfo;

import java.util.List;

/**
 * @program:my-shop
 * @description: 用户业务接口
 * @author:xxs_cjw
 * @create:2020-01-15 16:16
 **/

public interface TbUserService {

    /**
     * @Description: 查询全部用户信息
     * @Author: 蔡嘉伟 on 2020/1/15 16:17
     * @param:
     * @return:
     */
     List<TbUser> selectAll();

    /**
     * 邮箱密码登录
     * @param email
     * @param password
     * @return
     */
     TbUser login(String email,String password);

    /**
     * 保存用户
     * @param tbUser
     */
     BaseResult save(TbUser tbUser);

    /**
     * 根据ID查询用户
     * @param id
     * @return TbUser
     */
     TbUser selectById(Long id);

    /**
     * 更新用户信息
     * @param tbUser
     */
    void update(TbUser tbUser);

    /**
     * 删除用户信息
     * @param ids
     */
     void deleteMulti(String[] ids);

    /**
     * 根据条件分页查询用户信息
     */
    PageInfo<TbUser> page(int start, int length,int draw,TbUser tbUser);

    /**
     * 符合条件用户总数
     * @return
     */
     Integer count(TbUser tbUser);
}
