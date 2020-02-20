package com.cjw.my.shop.web.admin.dao;

import com.cjw.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @program:my-shop
 * @description:用户数据访问接口
 * @author:xxs_cjw
 * @create:2020-01-15 16:14
 **/

@Repository
public interface TbUserDao {

    /**
     * @Description: 查询全部用户信息
     * @Author: 蔡嘉伟 on 2020/1/15 16:15
     * @param:
     * @return: list
    */
    public List<TbUser> selectAll();

    /**
     * @Description: 新增用户
     * @Author: 蔡嘉伟 on 2020/2/3 15:49
     * @param:  TbUser
     * @return: int
     */
    public int insert(TbUser tbUser);

    /**
     * @Description: 删除用户
     * @Author: 蔡嘉伟 on 2020/2/3 16:08
     * @param:  TbUser
     * @return: int
    */
    public int delete(TbUser tbUser);

    /**
     * @Description: 获取单个用户
     * @Author: 蔡嘉伟 on 2020/2/3 16:16
     * @param:  id
     * @return: TbUser
    */
    public TbUser getById(Long id);

    /**
     * @Description: 更新用户
     * @Author: 蔡嘉伟 on 2020/2/3 16:18
     * @param:  TbUser
     * @return: int
    */
    public int update(TbUser tbUser);

    /**
     * @Description: 根据名字模糊查询用户
     * @Author: 蔡嘉伟 on 2020/2/3 16:45
     * @param:  String
     * @return: list
    */
    public List<TbUser> selectByName(String username);

    /**
     * @Description: 根据邮箱查询用户
     * @Author: 蔡嘉伟 on 2020/2/4 10:02
     * @param:  String email
     * @return: TbUser
    */
    public TbUser getByEmail(String email);

    /**
     * 搜索
     * @return list
     */
    public List<TbUser> search(TbUser tbUser);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);


    /**
     * 分页查询
     * @param params
     * @return
     */
    public List<TbUser>  page(Map<String,Object> params);

    /**
     * 总数
     * @return
     */
    public Integer count(TbUser tbUser);
}
