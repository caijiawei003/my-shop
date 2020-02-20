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
    public List<TbUser> selectAll();

    /**
     * 邮箱密码登录
     * @param email
     * @param password
     * @return
     */
    public TbUser login(String email,String password);

    /**
     * 保存用户
     * @param tbUser
     */
    public BaseResult save(TbUser tbUser);

    /**
     * 根据ID查询用户
     * @param id
     * @return TbUser
     */
    public TbUser selectById(Long id);

    /**
     * 多条件搜索
     * @param tbUser
     * @return list
     */
    public List<TbUser> search(TbUser tbUser);

    /**
     * 批量删除
     * @param ids
     */
    public void deleteMulti(String[] ids);

    /**
     * 分页
     */
    PageInfo<TbUser> page(int start, int length,int draw,TbUser tbUser);

    /**
     * 总数
     * @return
     */
    public Integer count(TbUser tbUser);
}
