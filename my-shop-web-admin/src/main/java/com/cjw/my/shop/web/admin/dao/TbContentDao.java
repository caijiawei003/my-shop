package com.cjw.my.shop.web.admin.dao;

import com.cjw.my.shop.domain.TbContent;
import com.cjw.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @program:my-shop
 * @description:内容访问接口
 * @author:xxs_cjw
 * @create:2020-01-15 16:14
 **/

@Repository
public interface TbContentDao {

    /**
     * @Description: 查询全部内容信息
     * @Author: 蔡嘉伟 on 2020/1/15 16:15
     * @param:
     * @return: list
    */
    public List<TbContent> selectAll();

    /**
     * @Description: 新增内容
     * @Author: 蔡嘉伟 on 2020/2/3 15:49
     * @param:  TbContent
     * @return: int
     */
    public int insert(TbContent tbContent);

    /**
     * @Description: 删除内容
     * @Author: 蔡嘉伟 on 2020/2/3 16:08
     * @param:  TbContent
     * @return: int
    */
    public int delete(TbContent tbContent);

    /**
     * @Description: 获取单个内容
     * @Author: 蔡嘉伟 on 2020/2/3 16:16
     * @param:  id
     * @return: TbContent
    */
    public TbContent getById(Long id);

    /**
     * @Description: 更新内容
     * @Author: 蔡嘉伟 on 2020/2/3 16:18
     * @param:  TbContent
     * @return: int
    */
    public int update(TbContent tbContent);


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
    public List<TbContent>  page(Map<String, Object> params);

    /**
     * 总数
     * @return
     */
    public Integer count(TbContent tbContent);
}
